//import java.awt.Button;
//import java.awt.CardLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Rectangle;
//import java.awt.Scrollbar;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.AdjustmentEvent;
//import java.awt.event.AdjustmentListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javax.swing.JFrame;
//import javax.swing.JInternalFrame;
//import javax.swing.JLabel;
//import javax.swing.JLayeredPane;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollBar;
//import javax.swing.JScrollPane;
//
//
//public class Menu extends JPanel {
//	
//	JPanel namePanel = new JPanel();
//	JLabel name = new JLabel("LQD");
//	
//	static int total = 0;
//	
//	static ArrayList<FoodItem> cashList = new ArrayList<>();
//	static ArrayList<FoodItem> foodList = FoodItem.foodList;
//	
//	static JLayeredPane menuLayer = new JLayeredPane();
//	JPanel chickenPanel = new JPanel();
//	static JPanel beveragePanel = new JPanel();
//	JPanel burgerPanel = new JPanel();
//	static JPanel pizzaPanel = new JPanel();
//	static JPanel homePanel = new JPanel();
//	
//	Button btnQuin = new Button("Burger");
//	Button bChicken,bBeverage,bPizza,bBurger,bMenu,Cash,bPay;
//	
//	public Menu() throws SQLException {
//		setLayout(null);
//		setBounds(0,0,Frame.getW(),Frame.getH());
//		
//		Cash = new Button("Cash");
//		add(Cash);
//		add(namePanel);
//		add(menuLayer);
//		
//		Cash.setBounds(1250, 0, 100, 50);
//		Cash.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				bill bill = new bill(0,"","",foodList);
//				
////				bill bill = new bill(0, "", "", foodList);
//				bill.setVisible(true);
//			}
//		});
//		
//		bPay = new Button("Pay");
//		add(bPay);
//		bPay.setBounds(1100, 0, 100, 50);
//		bPay.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(foodList.size() ==0)
//					JOptionPane.showMessageDialog(null,"You did not order anything");
//			}
//		});
//		
//		namePanel();
//		Layer();
//		
//	}
//
//	public void Layer() throws SQLException {
//		// LAYER PANE
//		Connection c = Connect();
//		
//		menuLayer.setBounds(Frame.getW() / 4, 100, Frame.getW() * 3 / 4, Frame.getH());
////		contentPane.add(layerPane);
//		menuLayer.setLayout(new CardLayout(0, 0));
//
//		setLayeredMenuPanel(chickenPanel);
//		PreparedStatement stmt  =c.prepareStatement("SELECT * FROM Menu WHERE idFood LIKE 'C%'");
//		ResultSet result = stmt.executeQuery();
//		int y=0;
//		while(result.next()) {
//			FoodItem tFood = new FoodItem(result.getString("idFood"),result.getString("name"),result.getInt("price"),y);
//			tFood.setIcon("Image\\"+result.getString("name")+".png");
//			chickenPanel.add(tFood);
//			y+=130;
//		}
//
//		setLayeredMenuPanel(beveragePanel);
//		stmt  =c.prepareStatement("SELECT * FROM Menu WHERE idFood LIKE 'D%'");
//		result = stmt.executeQuery();
//		y=0;
//		while(result.next()) {
//			FoodItem tFood = new FoodItem(result.getString("idFood"),result.getString("name"),result.getInt("price"),y);
//			tFood.setIcon("Image\\"+result.getString("name")+".png");
//			beveragePanel.add(tFood);
//			y+=130;
//		}
//
//		setLayeredMenuPanel(burgerPanel);
//		stmt  =c.prepareStatement("SELECT * FROM Menu WHERE idFood LIKE 'B%'");
//		result = stmt.executeQuery();
//		y=0;
//		while(result.next()) {
//			FoodItem tFood = new FoodItem(result.getString("idFood"),result.getString("name"),result.getInt("price"),y);
//			tFood.setIcon("Image\\"+result.getString("name")+".png");
//			burgerPanel.add(tFood);
//			y+=130;
//		}
//		
//		setLayeredMenuPanel(pizzaPanel);
//		stmt  =c.prepareStatement("SELECT * FROM Menu WHERE idFood LIKE 'P%'");
//		result = stmt.executeQuery();
//		y=0;
//		while(result.next()) {
//			FoodItem tFood = new FoodItem(result.getString("idFood"),result.getString("name"),result.getInt("price"),y);
//			tFood.setIcon("Image\\"+result.getString("name")+".png");
//			pizzaPanel.add(tFood);
//			y+=130;
//		}
//		
//		
//	}
//	
//	public void setLayeredMenuPanel(JPanel p) {
//		p.setLayout(null);
//		p.setBounds(Frame.getW() / 4, 100, Frame.getW() * 3 / 4, Frame.getH());
//		p.setBackground(new Color(255, 250, 205));
//	}
//	
//	public void namePanel() {
//		// NAME PANEL
//		namePanel.setLayout(null);
//		namePanel.setBounds(0, 0, Frame.getW() / 4, Frame.getH());
//		namePanel.setBackground(new Color(107, 142, 35));
//		
//		btnQuin.setBounds(0, 100, Frame.getW() / 4, 100);
//		btnQuin.setBackground(new Color(107, 142, 35));
//		btnQuin.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				switchPane(burgerPanel);
//			}
//		});
//		bChicken = new Button("Chicken");
//		bChicken.setBounds(69, 150, 75, 25);
//		bChicken.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				switchPane(chickenPanel);
//			}
//		});
//		bBeverage = new Button("Beverage");
//		bBeverage.setBounds(69, 200, 75, 25);
//		bBeverage.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				switchPane(beveragePanel);
//			}
//		});
//		bPizza = new Button ("Pizza");
//		bPizza.setBounds(69, 250, 75, 25);
//		bPizza.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				switchPane(pizzaPanel);
//			}
//		});
//		
//		name.setBounds(60, 0, 300, 100);
//		name.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 50));
//
//		namePanel.add(name);
//		namePanel.add(bPizza);
//		namePanel.add(bBeverage);
//		namePanel.add(bChicken);
//		namePanel.add(btnQuin);
//	}
//	
//	public static Connection Connect() throws SQLException {
//		Connection connection = null;
//		try {
//			Class.forName("org.sqlite.JDBC");
//			String url = "jdbc:sqlite:Restaurant.db";
//			connection = DriverManager.getConnection(url);
//			System.out.println("ConnectJDBC");
//			
//		} catch (ClassNotFoundException e) {
//			System.out.println("ERROR :"+e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
//			e.printStackTrace();
//		}
//		return connection;
//	}
//	
//	public static void Update(String str) throws SQLException {
//		Connection c = Connect();
//		PreparedStatement create = c.prepareStatement(str);
//		create.executeUpdate();
//	}
//	
//	public static ArrayList<String> Select(String select,String tableName) throws SQLException{
//		Connection c = Connect();
//		PreparedStatement stmt  =c.prepareStatement("SELECT "+select+" FROM "+tableName+";");
//		ResultSet result = stmt.executeQuery();
//		
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		while(result.next()) {
//			System.out.println(result.getString(select));
//			arr.add(result.getString(select));
//		}
//		return arr;
//	}
//	public static ArrayList<String> Select (String select, String tableName, String condition) throws SQLException{
//		Connection c = Connect();
//		PreparedStatement stmt  =c.prepareStatement("SELECT "+select+" FROM "+tableName+" WHERE "+ condition+";");
//		ResultSet result = stmt.executeQuery();
//		
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		while(result.next()) {
//			System.out.println(result.getString(select));
//			arr.add(result.getString(select));
//		}
//		return arr;
//	}
//	
//	private static void switchPane(JPanel p) {
//		menuLayer.removeAll();
//		menuLayer.add(p);
//		menuLayer.repaint();
//		menuLayer.revalidate();
//	}
//	
//}
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

//import draw.FoodItem;  


public class Menu extends JPanel {
	
	JPanel namePanel = new JPanel();
	JLabel name = new JLabel("LQD");
	
	static int total = 0;
	static ArrayList<FoodItem> foodList = FoodItem.foodList;
	
	static JLayeredPane menuLayer = new JLayeredPane();
	JPanel chickenPanel = new JPanel();
	static JPanel beveragePanel = new JPanel();
	JPanel burgerPanel = new JPanel();
	static JPanel pizzaPanel = new JPanel();
	static JPanel homePanel = new JPanel();
	
	Button btnQuin = new Button("Burger");
	Button bChicken,bBeverage,bPizza,bBurger,bMenu,Cash;
	
	public Menu() throws SQLException {
		setLayout(null);
		setBounds(0,0,Window.getW(),Window.getH());
		
		Cash = new Button("Cash");
		
		add(Cash);
		add(namePanel);
		add(menuLayer);
		
		Cash.setBounds(1250, 0, 100, 50);
		Cash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bill bill = new bill(0,"","",foodList);
				bill.setVisible(true);
				bill.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		namePanel();
		Layer();
		
	}

	public void Layer() throws SQLException {
		// LAYER PANE
		Connection c = Connect();
		
		menuLayer.setBounds(Window.getW() / 4, 100,Window.getW() * 3 / 4, Window.getH());
//		contentPane.add(layerPane);
		menuLayer.setLayout(new CardLayout(0, 0));

		addMenuPanel(c,chickenPanel,"'O%'");
		addMenuPanel(c,beveragePanel,"'D%'");
		addMenuPanel(c,burgerPanel, "'B%'");
		addMenuPanel(c,pizzaPanel,"'P%'");	
	}
	
	public void setLayeredMenuPanel(JPanel p) {
		p.setLayout(null);
		p.setBounds(Window.getW() / 4, 100, Window.getW() * 3 / 4, Window.getH());
		p.setBackground(new Color(255, 250, 205));
	}
	
	public void namePanel() {
		// NAME PANEL
		namePanel.setLayout(null);
		namePanel.setBounds(0, 0, Window.getW() / 4, Window.getH());
		namePanel.setBackground(new Color(107, 142, 35));
		
		btnQuin.setBounds(69, 100, 75, 25);
		btnQuin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPane(burgerPanel);
			}
		});
		bChicken = new Button("Other");
		bChicken.setBounds(69, 150, 75, 25);
		bChicken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPane(chickenPanel);
			}
		});
		bBeverage = new Button("Beverage");
		bBeverage.setBounds(69, 200, 75, 25);
		bBeverage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPane(beveragePanel);
			}
		});
		bPizza = new Button ("Pizza");
		bPizza.setBounds(69, 250, 75, 25);
		bPizza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPane(pizzaPanel);
			}
		});
		
		name.setBounds(60, 0, 300, 100);
		name.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 50));

		namePanel.add(name);
		namePanel.add(bPizza);
		namePanel.add(bBeverage);
		namePanel.add(bChicken);
		namePanel.add(btnQuin);
	}
	
	public static Connection Connect() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:Restaurant.db";
			connection = DriverManager.getConnection(url);
			System.out.println("ConnectJDBC");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR :"+e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void Update(String str) throws SQLException {
		Connection c = Connect();
		PreparedStatement create = c.prepareStatement(str);
		create.executeUpdate();
	}
	
	public static ArrayList<String> Select(String select,String tableName) throws SQLException{
		Connection c = Connect();
		PreparedStatement stmt  =c.prepareStatement("SELECT "+select+" FROM "+tableName+";");
		ResultSet result = stmt.executeQuery();
		
		ArrayList<String> arr = new ArrayList<String>();
		
		while(result.next()) {
			System.out.println(result.getString(select));
			arr.add(result.getString(select));
		}
		return arr;
	}
	
	private void addMenuPanel(Connection c, JPanel panel, String rule) {
		JScrollBar scroll = new JScrollBar();
		setLayeredMenuPanel(panel);
		PreparedStatement stmt;
		try {
			stmt = c.prepareStatement("SELECT * FROM Menu WHERE idFood LIKE" + rule);
			ResultSet result = stmt.executeQuery();
			int y=0,sl=0;
			while(result.next()) {
				FoodItem tFood = new FoodItem(result.getString("idFood"),result.getString("name"),result.getInt("price"),y);
				tFood.setIcon("Image\\"+result.getString("name")+".png");
				panel.add(tFood);
				y+=130;
				sl++;
				if(sl>=5) {
					scroll.setBounds(1000, 0, 20, 605);
					scroll.setMaximum(100+(sl-5)*130);
				}
					scroll.addAdjustmentListener(new AdjustmentListener() {
						int y = tFood.getY();
						@Override
						public void adjustmentValueChanged(AdjustmentEvent e) {
							tFood.setY(y-scroll.getValue());
						}
					});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(scroll);
		
	}
	public static ArrayList<String> Select (String select, String tableName, String condition) throws SQLException{
		Connection c = Connect();
		PreparedStatement stmt  =c.prepareStatement("SELECT "+select+" FROM "+tableName+" WHERE "+ condition+";");
		ResultSet result = stmt.executeQuery();
		
		ArrayList<String> arr = new ArrayList<String>();
		
		while(result.next()) {
			System.out.println(result.getString(select));
			arr.add(result.getString(select));
		}
		return arr;
	}
	
	private static void switchPane(JPanel p) {
		menuLayer.removeAll();
		menuLayer.add(p);
		menuLayer.repaint();
		menuLayer.revalidate();
	}
	
}
