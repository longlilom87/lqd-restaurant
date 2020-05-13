package draw;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import cate_list.Food;
import main_app.Menu;
import main_app.Window;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;

public class UpMenu extends JFrame {

	private JPanel contentPane;
	Choice choice = new Choice();
	ArrayList<Food> food;
	String[] column = { "ID", "Name", "Unit Price" };
//	String[] foodlist = { "Burger", "Chicken", "Beverage", "Pizza" };
//	String[] condition = { "'B%'", "'O%'", "'D%'", "'P%'" };
	JTable menuTable;
	private Connection c;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpMenu frame = new UpMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UpMenu() throws SQLException {
		c= Menu.Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 757);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(255, 250, 205));
		setContentPane(contentPane);
		
		
		
		
		JPanel find = new JPanel();
		find.setVisible(true);
		contentPane.add(find,BorderLayout.PAGE_START);
		
		choice = new Choice();
		choice.setFont(new Font("Arial", Font.PLAIN, 23));
		choice.addItem("Burger");
		choice.addItem("Other");
		choice.addItem("Beverage");
		choice.addItem("Pizza");
		
		find.add(choice);
		
		
		Label instruction = new Label("Please click on Go to see the Table!");
		instruction.setForeground(Color.RED);
		
		
		releaseTable();
		Button button = new Button("Go!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				instruction.setVisible(false);
				find.remove(instruction);
				menuTable.setModel(createTableModel());
				refreshHeader();
				menuTable.repaint();
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		find.add(button);
		find.add(instruction);
		
		
	}
	
	private ArrayList<Food> getMenu(Choice choice) {
		ArrayList<Food> menu = new ArrayList<>();
		String condition = null;

		switch (choice.getSelectedItem()) {
		case "Burger":
			condition = "'B%'";
			break;
		case "Other":
			condition = "'O%'";
			break;
		case "Beverage":
			condition = "'D%'";
			break;
		case "Pizza":
			condition = "'P%'";
			break;
		}

		Connection c = null;
		try {
			c = Menu.Connect();
			String getMenu = "SELECT * FROM Menu WHERE idFood LIKE " + condition;
			PreparedStatement s = c.prepareStatement(getMenu);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				Food tFood = new Food(result.getString("idFood"), result.getString("name"), result.getInt("price"));
				menu.add(tFood);
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;

	}

	public DefaultTableModel createTableModel() {
		food = getMenu(choice);
		System.out.println(food);
//		System.out.println(food);
		Object[] row = new Object[column.length];
		DefaultTableModel model = new DefaultTableModel(0, column.length);

		// add row
		for (int i = 0; i < food.size(); i++) {
			row[0] = food.get(i).getId();
			row[1] = food.get(i).getFoodname();
			row[2] = food.get(i).getPrice();
			model.addRow(row);

		}

		return model;

	}

	private void refreshHeader() {
		for (int i = 0; i < column.length; i++) {
			JTableHeader header = menuTable.getTableHeader();
			TableColumn col = header.getColumnModel().getColumn(i);
			col.setHeaderValue(column[i]);
			header.repaint();
		}
	}

	private void releaseTable() {
		menuTable = new JTable(createTableModel()) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		menuTable.setFont(new Font("Tahoma", Font.PLAIN, 30));
		menuTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 30));
		menuTable.setRowHeight(60);
		refreshHeader();
		JScrollPane js = new JScrollPane(menuTable);
		js.setBounds(10, 150, contentPane.getWidth(), 250);
		js.setBackground(new Color(255, 250, 205));
		js.setVisible(true);
		contentPane.add(js,BorderLayout.CENTER);

	}

	private int selectID(String ID) {
		int i = 0;
		try {
			String getID = "SELECT idFood FROM Menu WHERE idFood ='" + ID + "'";
			PreparedStatement s = c.prepareStatement(getID);
			ResultSet result = s.executeQuery();
			if (result.next() == true) {
				i = 2;

			} else
				i = 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
