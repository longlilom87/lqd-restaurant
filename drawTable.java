package draw;


import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cate_list.Table;
import main_app.Frame;
import main_app.Menu;
import main_app.tablescreen;

public class drawTable extends JPanel{

	public drawTable() {
		setLayout(null);
		
		// Aligning color explanation box
//		int available = 200;
//		int reserved = available+100;
//		int text = 1150;
//		int colortext = text-80;
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvailable.setBounds(1150, 200, 123, 82);
		add(lblAvailable);
		
		JLabel lblReserved = new JLabel("Reserved");
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReserved.setBounds(1150, 300, 123, 82);
		add(lblReserved);
		
		JTextField textField = new JTextField();
		textField.setForeground(SystemColor.menu);
		textField.setBackground(Color.GREEN);
		textField.setEditable(false);
		textField.setBounds(1070, 200, 54, 82);
		add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setForeground(SystemColor.menu);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(1070, 300, 54, 82);
		add(textField_1);
		
	
		Choice choice = new Choice();
		choice.setBounds(790, 530, 123, 25);
		add(choice);
		
		List<Table> tablelist = getTablelist();
		for(int i = 0; i<tablelist.size();i++) {
			choice.add(Integer.toString(tablelist.get(i).getX()));
		}
		
		JButton btnBlank = new JButton("Blank");
		btnBlank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(choice.getSelectedItem(), 0);
				repaint();
			}
		});
		btnBlank.setBounds(990, 530, 97, 25);
		add(btnBlank);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(choice.getSelectedItem(),1);
				repaint();
			}
		});
		btnCustomer.setBounds(990, 570, 97, 25);
		add(btnCustomer);
		
		JButton bDelivery = new JButton("Delivery");
		bDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.homeLayer.removeAll();
				try {
					Frame.homeLayer.add(new Menu());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Frame.homeLayer.repaint();
				Frame.homeLayer.revalidate();
			}
		});
		bDelivery.setBounds(990,610,97,25);
		add(bDelivery);
		
		
	}
	
	public void update(String id, int n) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
			String update = "update Res_table set Table_status="+n+" where TableID="+id;
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
			Connection c = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
			
			
			String getTable = "select * from Res_table";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(getTable);
			while (rs.next()) {
				tablelist.add(new Table (rs.getInt("TableID"),rs.getInt("Table_status")));
			}
			
			} catch (Exception err1) {
			System.out.println(err1);
		}
	return tablelist;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		List<Table> tablelist = tablescreen.getTablelist();
		
		int n = 4;
		int size = 80;
		int radius = 50;
		int borderx = 650;   //align the table colors
		int bordery = 120;
		super.paintComponent(g);
		for (int i=0;i<tablelist.size();i++) {
			int x = circleCenter(i, size, tablelist);
			int y = lineDevision(i,n) *size;
				if (tablelist.get(i).getY()==0) {
					g.setColor(Color.green);
				} else g.setColor(Color.BLACK);
				g.fillOval(i*size-checkLine(i,n,size)+borderx, y+bordery, radius, radius);
				g.setColor(Color.BLUE);
				Font font = new Font("Verdana", Font.BOLD, 20);
				g.setFont(font);
				g.drawString(String.valueOf(tablelist.get(i).getX()), x-checkLine(i,n,size)+borderx, y+76+bordery);
			
		}
		
		
	}
	
	public int circleCenter(int a,int size, List<Table>tablelist) {
		if (a>=10) {
			return (int) (a*size+18-6*Math.log10(tablelist.get(a).getX()));
		}
		else return a*size+18;
	}
	
	public int lineDevision(int a, int b) {
		 return (int)a/b +1;
	}
	public int checkLine(int a, int b,int size) {
		if (a<b) {
			return 0;
		}else {
			
		     return (lineDevision(a,b)-1)*b*size;
		}
	}
}
