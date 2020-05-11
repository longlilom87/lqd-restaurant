
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


public class UpdateMenu extends JPanel {

	Choice choice = new Choice();
	ArrayList<Food> food;
	String[] column = { "ID", "Name", "Unit Price" };
//	String[] foodlist = { "Burger", "Chicken", "Beverage", "Pizza" };
//	String[] condition = { "'B%'", "'O%'", "'D%'", "'P%'" };
	JTable menuTable;

	/**
	 * Create the panel.
	 */
	public UpdateMenu() {

		setBounds(0, 0, Window.getW(), Window.getH());
		setLayout(null);
		setBackground(new Color(255, 250, 205));

		choice.setBounds(700, 150, 300, 40);
		choice.setFont(new Font("Tahoma", Font.PLAIN, 40));
		choice.addItem("Burger");
		choice.addItem("Chicken");
		choice.addItem("Beverage");
		choice.addItem("Pizza");
		add(choice);

		JLabel instruction = new JLabel("Please click on Go to see the Table");
		instruction.setBounds(1200, 150, 300, 55);
		instruction.setFont(new Font("Arial", Font.ITALIC, 15));
		instruction.setForeground(Color.red);
		add(instruction);
		releaseTable();

		JButton bGo = new JButton("Go!");
		bGo.setBounds(1050, 150, 60, 55);
		bGo.setVisible(true);
		bGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				instruction.setVisible(false);
				releaseTable();

			}

		});
		add(bGo);

		JLabel lblDeleteItem = new JLabel("Delete Item");
		lblDeleteItem.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDeleteItem.setBounds(1200, 650, 218, 59);
		lblDeleteItem.setHorizontalAlignment(JLabel.CENTER);
		add(lblDeleteItem);

		JTextField textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(lblDeleteItem.getX() + 115, lblDeleteItem.getY() + 85, 146, 34);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Enter Item ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(lblDeleteItem.getX() - 31, lblDeleteItem.getY() + 94, 105, 16);
		add(lblNewLabel);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(lblDeleteItem.getX() + 44, lblDeleteItem.getY() + 154, 134, 42);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection c = Menu.Connect();
					int check = selectID(textField.getText());
					if (check == 1) {
						JOptionPane.showMessageDialog(null, "No food " + textField.getText(), "Alert",
								JOptionPane.WARNING_MESSAGE);
					} else {

						if (textField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please input ID", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							String sql = "Delete from Menu where idFood = '" + textField.getText() + "' ";
							PreparedStatement s2 = c.prepareStatement(sql);
							s2.executeUpdate();
							menuTable.setModel(createTableModel());
							refreshHeader();
						}

					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		add(btnDelete);

		JLabel lblUpdateItem = new JLabel("Update Item");
		lblUpdateItem.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblUpdateItem.setHorizontalAlignment(JLabel.CENTER);
		lblUpdateItem.setBounds(400, 650, 209, 69);
		add(lblUpdateItem);

		JLabel lblEnterName = new JLabel("Enter Item Name");
		lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterName.setBounds(lblUpdateItem.getX() - 90, lblUpdateItem.getY() + 82, 190, 35);
		add(lblEnterName);

		JLabel lblEnterPrice = new JLabel("Enter Item Price");
		lblEnterPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterPrice.setBounds(lblEnterName.getX(), lblEnterName.getY() + 60, 190, 35);
		add(lblEnterPrice);

		JTextField txtEnterName = new JTextField();
		txtEnterName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEnterName.setBounds(lblEnterName.getX() + 203, lblEnterName.getY(), 183, 31);
		add(txtEnterName);
		txtEnterName.setColumns(10);

		JTextField txtEnterPrice = new JTextField();
		txtEnterPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEnterPrice.setColumns(10);
		txtEnterPrice.setBounds(txtEnterName.getX(), lblEnterPrice.getY(), 183, 31);
		add(txtEnterPrice);

		JLabel lblEnterID = new JLabel("Enter Item ID");
		lblEnterID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterID.setBounds(lblEnterName.getX(), lblEnterName.getY() + 120, 190, 35);
		add(lblEnterID);

		JTextField txtEnterID = new JTextField();
		txtEnterID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEnterID.setColumns(10);
		txtEnterID.setBounds(txtEnterName.getX(), lblEnterID.getY(), 183, 31);
		add(txtEnterID);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(lblUpdateItem.getX() - 43, lblUpdateItem.getY() + 260, 97, 25);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (txtEnterID.getText().isEmpty() || txtEnterName.getText().isEmpty()
						|| txtEnterPrice.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "You have to input all the fields", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println("txtenter = " + txtEnterID.getText());
					int dialogbutton = JOptionPane.showConfirmDialog(null,
							"Are you sure? \n You can't edit the ID anymore!", "WARNING", JOptionPane.YES_NO_OPTION);

					if (dialogbutton == JOptionPane.YES_OPTION) {
						try {
							Connection c = Menu.Connect();
							int check = selectID(txtEnterID.getText());
							if (check == 2) {
								JOptionPane.showMessageDialog(null, "Food existed. \n You can only update", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								String iFood = "insert into Menu (idFood, name, price) values('" + txtEnterID.getText()
										+ "','" + txtEnterName.getText() + "','" + txtEnterPrice.getText() + "')";
								PreparedStatement s = c.prepareStatement(iFood);
								s.executeUpdate();
								menuTable.setModel(createTableModel());
								refreshHeader();
							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

			}

		});
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(lblUpdateItem.getX() + 147, btnAdd.getY(), 97, 25);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Connection c = null;
				try {
					c = Menu.Connect();
					String update = "", name = "", price = "";
					if (txtEnterName.getText().isEmpty() == false) {
						name = "name ='" + txtEnterName.getText() + "' ";
					}

					if (txtEnterPrice.getText().isEmpty() == false) {
						price = "price ='" + txtEnterPrice.getText() + "' ";
					}
					if (txtEnterName.getText().isEmpty() == false && txtEnterPrice.getText().isEmpty() == false) {
						update = ",";
					}
					int check = selectID(txtEnterID.getText());
					if (check == 1) {
						JOptionPane.showMessageDialog(null, "No food " + textField.getText(), "Alert",
								JOptionPane.WARNING_MESSAGE);
					} else {
						String sql = "update Menu set " + name + update + price + " where idFood = '"
								+ txtEnterID.getText() + "'";
						PreparedStatement s = c.prepareStatement(sql);
						s.executeUpdate();
						menuTable.setModel(createTableModel());
						refreshHeader();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		});
		add(btnUpdate);

	}

	private ArrayList<Food> getMenu(Choice choice) {
		ArrayList<Food> menu = new ArrayList<>();
		String condition = null;

		switch (choice.getSelectedItem()) {
		case "Burger":
			condition = "'B%'";
			break;
		case "Chicken":
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
		js.setBounds(0, 230, Window.getW() - 20, 400);
		js.setVisible(true);
		add(js);

	}

	private int selectID(String ID) {
		Connection c = null;
		int i = 0;
		try {
			c = Menu.Connect();
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
