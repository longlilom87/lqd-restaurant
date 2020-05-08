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
	static ArrayList<FoodItem> payFoodList = new ArrayList<>();
	
	static JLayeredPane menuLayer = new JLayeredPane();
	static JPanel chickenPanel = new JPanel();
	static JPanel beveragePanel = new JPanel();
	static JPanel burgerPanel = new JPanel();
	static JPanel pizzaPanel = new JPanel();
	static JPanel homePanel = new JPanel();
	
	Button btnQuin = new Button("Burger");
	Button bChicken,bBeverage,bPizza,bBurger,bMenu,Cash,bPay;
	
	public Menu() throws SQLException {
		setLayout(null);
		setBounds(0,0,Window.getW(),Window.getH());
		
		Cash = new Button("Order");
		add(Cash);
		add(namePanel);
		add(menuLayer);
		Cash.setBounds(1250, 0, 100, 50);
		Cash.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bill bill = new bill(0,LoginFrame.user, foodList);
				bill.setVisible(true);
				bill.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		
		bPay = new Button("Cash");
		add(bPay);
		bPay.setBounds(1000, 0, 100, 50);
		bPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("CASH");
				bill.cash = true;
				bill bill = new bill(0,LoginFrame.user,payFoodList);
				bill.setVisible(true);
				bill.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
				for(FoodItem t : foodList) System.out.println("FoodList: "+t);
				for(FoodItem t : payFoodList) System.out.println("PayList: "+t+" qty = "+t.getQty());
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
	
	public static void setLayeredMenuPanel(JPanel p) {
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
				try {
					restart();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	public static void addMenuPanel(Connection c, JPanel panel, String rule) {
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
//		panel.repaint();
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
	
	static void switchPane(JPanel p) {
		menuLayer.removeAll();
		menuLayer.add(p);
		menuLayer.repaint();
		menuLayer.revalidate();
	}
	
	public static void restart() throws SQLException {
		burgerPanel = new JPanel();
		try {
			addMenuPanel(Connect(),burgerPanel,"'B%'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
