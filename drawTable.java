
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class drawTable extends JPanel {

	User user = LoginFrame.user;
	List<Table> tablelist = getTablelist();

	JLabel[] tbls = new JLabel[tablelist.size()];
	JLabel[] nameTbl = new JLabel[tablelist.size()];
	JLabel[] tblChosen = new JLabel[tablelist.size()];
	private int x = 400;
	private int n = 4;
	static JPanel tableMenu = new JPanel();
	JPanel table;

	public drawTable() {
		setBounds(0, 0, 1382, 744);
		setLayout(null);
		setBackground(new Color(255, 250, 205));
		
		// Aligning color explanation box
		int available = 80;
		int reserved = available + 100;
		int text = 1182;
		int colortext = text - 80;

		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvailable.setBounds(text, available, 123, 82);
		lblAvailable.setVisible(true);
		add(lblAvailable);

		JLabel lblReserved = new JLabel("Serving");
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReserved.setBounds(text, reserved, 123, 82);
		lblReserved.setVisible(true);
		add(lblReserved);

		JTextField textField = new JTextField();
		textField.setForeground(SystemColor.menu);
		textField.setBackground(new Color(82, 224, 139));
		textField.setEditable(false);
		textField.setBounds(colortext, available, 54, 82);
		add(textField);

		JTextField textField_1 = new JTextField();
		textField_1.setForeground(SystemColor.menu);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(colortext, reserved, 54, 82);
		add(textField_1);

		int button = 90;
		int ybutton = 200;

		Choice choice = new Choice();
		choice.setBounds(button, ybutton - 40, 150, 25);
		choice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(choice);

		for (int i = 0; i < tablelist.size(); i++) {
			Table t = tablelist.get(i);
			choice.add(Integer.toString(t.getX()));
		}
		
		JLabel bBack = new JLabel();
		bBack.setIcon(new ImageIcon(new ImageIcon("Image/back_shadow.png").getImage().getScaledInstance(50,50,
				java.awt.Image.SCALE_SMOOTH)));
		bBack.setBounds(button, ybutton + 60, 150,100);
		bBack.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				bBack.setIcon(new ImageIcon(new ImageIcon("Image/back_shadow.png").getImage().getScaledInstance(50,50,
						java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				bBack.setIcon(new ImageIcon(new ImageIcon("Image/back_shadow.png").getImage().getScaledInstance(70,70,
						java.awt.Image.SCALE_SMOOTH)));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
					Window.switchPane(new customerscreen());
				
			}
		});
		bBack.setHorizontalAlignment(SwingConstants.CENTER);
		add(bBack);


		JButton btnCustomer = new JButton("Choose Table");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Table t : tablelist)
					if (String.valueOf(t.getX()).equals(choice.getSelectedItem()))
						if (t.getY() == 1) {
							JOptionPane.showMessageDialog(null, "Please choose another table");
							return;
						}

				try {
					Menu.bl = true;
					Window.switchPane(new Menu());
					user.setTableID(choice.getSelectedItem());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				update(choice.getSelectedItem(), 1);
				repaint();
			}
		});
		btnCustomer.setBounds(button, ybutton, 150, 25);
		add(btnCustomer);

		JButton bDelivery = new JButton("Delivery");
		bDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.homeLayer.removeAll();
				try {
					Menu.bl = true;
					Window.homeLayer.add(new Menu());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Window.homeLayer.repaint();
				Window.homeLayer.revalidate();
			}
		});
		bDelivery.setBounds(button, ybutton + 30, 150, 25);
		add(bDelivery);

		tableMenu.setLayout(null);
		tableMenu.setBounds(0, 0, 1382 / 4, Window.getH());
		tableMenu.setBackground(new Color(107, 142, 35));

		Image scale = new ImageIcon("Image/logo.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		JLabel label = new JLabel(new ImageIcon(scale));
		label.setBounds(193, 62, 50, 50);
		add(label);
		
		JLabel name = new JLabel("LQD");
		name.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 50));
		name.setBounds(88, 11, 130, 100);
		name.setForeground(Color.white);
		name.setVisible(true);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		add(name);
		add(tableMenu);
		

		table = new JPanel();
		table.setBounds(30, 0, 1000, Window.getH());
		table.setBackground(new Color(255, 250, 205));
		table.setLayout(null);
		table.setVisible(true);
		add(table);

		for (int i = 0; i < tablelist.size(); i++) {
			ImageIcon ic;
			tbls[i] = new JLabel();
			tbls[i].setBounds(x - (lineDevision(i, n) - 1) * n * 150, 100 * lineDevision(i, n), 108, 62);

			nameTbl[i] = new JLabel();
			nameTbl[i].setBounds(tbls[i].getX(), tbls[i].getY(), 
					tbls[i].getWidth(), tbls[i].getHeight());
			nameTbl[i].setBackground(Color.BLUE);
			if (tablelist.get(i).getY() == 1) {
				ic = new ImageIcon("Image\\tbl_black.png");
				nameTbl[i].setForeground(Color.white);
			} else {
				ic = new ImageIcon("Image\\tbl_green.png");
				nameTbl[i].setForeground(Color.blue);
			}
			nameTbl[i].setText(String.valueOf(tablelist.get(i).getX()));
			nameTbl[i].setFont(new Font("Tahoma", Font.BOLD, 20));

			final Integer innerI = new Integer(i);

			nameTbl[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					for (Table t : tablelist)
						if (String.valueOf(t.getX()).equals(nameTbl[innerI].getText()))
							if (t.getY() == 1) {
								JOptionPane.showMessageDialog(null, "Please choose another table");
								return;
							}

					try {
						Menu.bl = true;
						Window.switchPane(new Menu());
						user.setTableID(String.valueOf(nameTbl[innerI].getText()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					update(choice.getSelectedItem(), 1);
					repaint();

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
			nameTbl[i].setHorizontalAlignment(SwingConstants.CENTER);
			nameTbl[i].setVerticalAlignment(SwingConstants.CENTER);
			table.add(nameTbl[i]);

			Image img = ic.getImage().getScaledInstance(tbls[i].getWidth(), tbls[i].getHeight(), Image.SCALE_SMOOTH);
			ImageIcon scaledImage = new ImageIcon(img);
			tbls[i].setIcon(scaledImage);
			table.add(tbls[i]);

			x += 150;

		}

	}

	public void update(String id, int n) {
		try {
			Connection c = Menu.Connect();
			String update = "update Res_table set Table_status=" + n + " where TableID=" + id;
			PreparedStatement st = c.prepareStatement(update);
			st.executeUpdate();
			c.close();

		} catch (Exception err1) {
			System.out.println(err1);
		}
	}

	public static List<Table> getTablelist() {
		List<Table> tablelist = new ArrayList<>();
		try {
			
			Connection c = Menu.Connect();
			String getTable = "select * from Res_table order by TableID";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(getTable);
			while (rs.next()) {
				tablelist.add(new Table(rs.getInt("TableID"), rs.getInt("Table_status")));
			}

		} catch (Exception err1) {
			System.out.println(err1);
		}
		return tablelist;
	}

	public int lineDevision(int a, int b) {
		return (int) a / b + 1;

	}

}
