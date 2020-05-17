
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class Menu extends JPanel {

	JPanel namePanel = new JPanel();
	JLabel name = new JLabel("LQD");
	public static boolean bl;

	public static int total = 0;
	public static int orderMore = 0;
	static ArrayList<FoodItem> foodList = FoodItem.foodList;
	static ArrayList<FoodItem> payFoodList = new ArrayList<>();

	static JLayeredPane menuLayer = new JLayeredPane();
	static JPanel chickenPanel = new JPanel();
	static JPanel beveragePanel = new JPanel();
	static JPanel burgerPanel = new JPanel();
	static JPanel pizzaPanel = new JPanel();
	static JPanel homePanel = new JPanel();

	Button btnQuin = new Button("Burger");
	Button bChicken, bBeverage, bPizza, Cash, bPay, bBack;

	public Menu() throws SQLException {
		setLayout(null);
		setBounds(0, 0, Window.getW(), Window.getH());

		Cash = new Button("Order");
		add(namePanel);
		add(menuLayer);
		Cash.setBounds(1250, 0, 100, 50);
		Cash.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				total = 0;
				if (foodList.size() == 0) {
					JOptionPane.showMessageDialog(null, "Please order");
					return;
				}

				bill.cash = false;

				for (FoodItem t : foodList) {
					total = total + t.getPrice() * t.getQty();
				}
				bill bill = new bill(LoginFrame.user, foodList);
				bill.setVisible(true);
				bill.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			}
		});

		bPay = new Button("Cash");
		if (bl == true) {
			add(Cash);
			add(bPay);
		}
		bPay.setBounds(1000, 0, 100, 50);
		bPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				total = 0;
//				System.out.println("CASH");
				if (payFoodList.size() == 0) {
					JOptionPane.showMessageDialog(null, "You haven't order anything to pay");
					return;
				}

				bill.cash = true;

				for (FoodItem t : payFoodList) {
					total = total + t.getPrice() * t.getQty();
				}

				bill bill = new bill(LoginFrame.user, payFoodList);
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

		menuLayer.setBounds(Window.getW() / 4, 100, Window.getW() * 3 / 4, Window.getH());
//		contentPane.add(layerPane);
		menuLayer.setLayout(new CardLayout(0, 0));

		addMenuPanel(c, chickenPanel, "'O%'");
		addMenuPanel(c, beveragePanel, "'D%'");
		addMenuPanel(c, burgerPanel, "'B%'");
		addMenuPanel(c, pizzaPanel, "'P%'");
	}

	public static void setLayeredMenuPanel(JPanel p) {
		p.setLayout(null);
		p.setBounds(Window.getW() / 4, 100, Window.getW() * 3 / 4, Window.getH());
		p.setBackground(new Color(255, 250, 205));
	}

	public void namePanel() {
		// NAME PANEL
		JPanel namePanel = new JPanel();
		namePanel.setBounds(0, 0, 1382 / 4, 744);
		namePanel.setLayout(null);
		namePanel.setBackground(new Color(107, 142, 35));
		add(namePanel);

		btnQuin.setFont(new Font("Bernard MT Condensed", Font.BOLD | Font.ITALIC, 45));
		btnQuin.setBackground(new Color(0, 128, 0));

		btnQuin.setBounds(10, 137, 325, 71);
		btnQuin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					restart(burgerPanel, "B%");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchPane(burgerPanel);
			}
		});

		bChicken = new Button("Other");
		bChicken.setBounds(10, 289, 325, 71);
		bChicken.setFont(new Font("Bernard MT Condensed", Font.BOLD | Font.ITALIC, 45));
		bChicken.setBackground(new Color(0, 128, 0));
		bChicken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					restart(chickenPanel, "C%");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchPane(chickenPanel);
			}
		});
		bBeverage = new Button("Beverage");
		bBeverage.setBounds(10, 366, 325, 71);
		bBeverage.setFont(new Font("Bernard MT Condensed", Font.BOLD | Font.ITALIC, 45));
		bBeverage.setBackground(new Color(0, 128, 0));
		bBeverage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					restart(beveragePanel, "D%");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchPane(beveragePanel);
			}
		});
		bPizza = new Button("Pizza");
		bPizza.setBounds(10, 212, 325, 71);
		bPizza.setFont(new Font("Bernard MT Condensed", Font.BOLD | Font.ITALIC, 45));
		bPizza.setBackground(new Color(0, 128, 0));
		bPizza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					restart(pizzaPanel, "P%");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchPane(pizzaPanel);
			}
		});

		bBack = new Button("Back");
		bBack.setBounds(10, 443, 325, 71);
		bBack.setFont(new Font("Bernard MT Condensed", Font.BOLD | Font.ITALIC, 45));
		bBack.setBackground(new Color(0, 128, 0));
		bBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.switchPane(new Welcome());
			}
		});

		if (Menu.bl == false)
			namePanel.add(bBack);

		name.setForeground(new Color(255, 255, 255));
		name.setBackground(new Color(0, 204, 204));

		name.setBounds(92, 11, 128, 100);
		name.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 50));

		namePanel.add(name);
		namePanel.add(bPizza);
		namePanel.add(bBeverage);
		namePanel.add(bChicken);
		namePanel.add(btnQuin);

		Image scale = new ImageIcon("Image/logo.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		JLabel label = new JLabel(new ImageIcon(scale));
		label.setBounds(193, 62, 50, 50);
		namePanel.add(label);

		JLabel lblNewLabel = new JLabel(new ImageIcon(
				new ImageIcon("Image/menu.jpg").getImage().getScaledInstance(1026, 749, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(344, 0, 1026, 749);
		add(lblNewLabel);
	}

	public static Connection Connect() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:Restaurant.db";
//			String url = "jdbc:sqlserver://LAPTOP-O9PIICC4\\SQLEXPRESS;"
//					+ "databaseName=Restaurant;user=sa;password=sa";
			connection = DriverManager.getConnection(url);
			System.out.println("ConnectJDBC");

		} catch (Exception e) {
//			System.out.println("ERROR :"+e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
			e.printStackTrace();
		}
		return connection;
	}

	public static void Update(String str) throws SQLException {
		Connection c = Connect();
		PreparedStatement create = c.prepareStatement(str);
		create.executeUpdate();
	}

	public static ArrayList<String> Select(String select, String tableName) throws SQLException {
		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement("SELECT " + select + " FROM " + tableName + ";");
		ResultSet result = stmt.executeQuery();

		ArrayList<String> arr = new ArrayList<String>();

		while (result.next()) {
//			System.out.println(result.getString(select));
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
			int y = 0, sl = 0;
			while (result.next()) {
				FoodItem tFood = new FoodItem(result.getString("idFood"), result.getString("name"),
						result.getInt("price"), y, result.getString("imagePath"));
//				tFood.setIcon("Image\\"+result.getString("name")+".png");
				panel.add(tFood);
				y += 130;
				sl++;
				if (sl >= 5) {
					scroll.setBounds(1000, 0, 20, 605);
					scroll.setMaximum(100 + (sl - 5) * 130);
				}
				scroll.addAdjustmentListener(new AdjustmentListener() {
					int y = tFood.getY();

					@Override
					public void adjustmentValueChanged(AdjustmentEvent e) {
						tFood.setY(y - scroll.getValue());
					}
				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.add(scroll);
	}

	public static ArrayList<String> Select(String select, String tableName, String condition) throws SQLException {
		Connection c = Connect();
		PreparedStatement stmt = c
				.prepareStatement("SELECT " + select + " FROM " + tableName + " WHERE " + condition + ";");
		ResultSet result = stmt.executeQuery();

		ArrayList<String> arr = new ArrayList<String>();

		while (result.next()) {
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

	public static void restart(JPanel p, String rule) throws SQLException {
		p = new JPanel();
		try {
			addMenuPanel(Connect(), p, "'" + rule + "'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
