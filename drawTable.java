import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class drawTable extends JPanel {

	User user = LoginFrame.user;
	List<Table> tablelist = getTablelist();
	JLabel[] tbls = new JLabel[tablelist.size()];
	JLabel[] name = new JLabel[tablelist.size()];
	private int x = 400;
	private int n = 4;

	public drawTable() {
		setBounds(0,Window.getH()/6,1000,1000);
		setLayout(null);

		for (int i = 0; i < tablelist.size(); i++) {
			ImageIcon ic;
			tbls[i] = new JLabel();
			tbls[i].setBounds(x - (lineDevision(i, n) - 1) * n * 150, 100 * lineDevision(i, n), 108, 62);
			name[i] = new JLabel();
			name[i].setBounds(tbls[i].getX(), tbls[i].getY(), tbls[i].getWidth(), tbls[i].getHeight());
			if (tablelist.get(i).getY() == 1) {
				ic = new ImageIcon("Image\\tbl_black.png");
				name[i].setForeground(Color.white);
			} else {
				ic = new ImageIcon("Image\\tbl_green.png");
				name[i].setForeground(Color.blue);
			}
			name[i].setText(String.valueOf(tablelist.get(i).getX()));
			name[i].setFont(new Font("Tahoma", Font.BOLD, 20));

			name[i].setHorizontalAlignment(SwingConstants.CENTER);
			name[i].setVerticalAlignment(SwingConstants.CENTER);
			add(name[i]);
			Image img = ic.getImage().getScaledInstance(tbls[i].getWidth(), tbls[i].getHeight(), Image.SCALE_SMOOTH);
			ImageIcon scaledImage = new ImageIcon(img);
			tbls[i].setIcon(scaledImage);
			add(tbls[i]);

			x += 150;
		}

		// Aligning color explanation box
		int available = 80;
		int reserved = available + 100;
		int text = 1100;
		int colortext = text - 80;

		int button = 800;
		int tablechoice = button - 200;
		int ybutton = lineDevision(tablelist.size(), n) * 150;

		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvailable.setBounds(text, available, 123, 82);
		add(lblAvailable);

		JLabel lblReserved = new JLabel("Serving");
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReserved.setBounds(text, reserved, 123, 82);
		add(lblReserved);

		JTextField textField = new JTextField();
		textField.setForeground(SystemColor.menu);
		textField.setBackground(new Color(82, 224, 139));
		textField.setEditable(false);
		textField.setBounds(colortext, available, 54, 82);
		add(textField);
		textField.setColumns(10);

		JTextField textField_1 = new JTextField();
		textField_1.setForeground(SystemColor.menu);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(colortext, reserved, 54, 82);
		add(textField_1);

		Choice choice = new Choice();
		choice.setBounds(tablechoice, ybutton, 123, 25);
		choice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(choice);

		for (int i = 0; i < tablelist.size(); i++) {
			Table t = tablelist.get(i);
			choice.add(Integer.toString(t.getX()));
		}

//		JButton btnBlank = new JButton("Blank");
//		btnBlank.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				update(choice.getSelectedItem(), 0);
//				repaint();
//			}
//		});
//		btnBlank.setBounds(button, ybutton, 97, 25);
//		add(btnBlank);

		JButton btnCustomer = new JButton("Choose Table");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Table t : tablelist) {
					if (Integer.toString(t.getX()).equals(choice.getSelectedItem())) {
						if (t.getY() == 1) {
							JOptionPane.showMessageDialog(null, "Please choose another table");
							return;
						}
					}
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

	}

	public void update(String id, int n) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
			String update = "update Res_table set Table_status=" + n + " where TableID=" + id;
			PreparedStatement st = c.prepareStatement(update);
			st.executeUpdate();

		} catch (Exception err1) {
			System.out.println(err1);
		}
	}

	public static List<Table> getTablelist() {
		List<Table> tablelist = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:Restaurant.db");

			String getTable = "select * from Res_table";
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
