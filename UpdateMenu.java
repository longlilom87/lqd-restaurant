
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

		JLabel instruction = new JLabel ("Please click on Go to see the Table");
		instruction.setBounds(1200, 150, 300, 55);
		instruction.setFont(new Font("Arial",Font.ITALIC,15));
		instruction.setForeground(Color.red);
		add(instruction);
		
		JButton bGo = new JButton("Go!");
		bGo.setBounds(1050, 150, 60, 55);
		bGo.setVisible(true);
		bGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				instruction.setVisible(false);
				menuTable = new JTable(createTableModel()) {

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}

				};
				menuTable.setFont(new Font("Tahoma", Font.PLAIN, 30));
				menuTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 30));
				menuTable.setRowHeight(60);

				for (int i = 0; i < column.length; i++) {
					JTableHeader header = menuTable.getTableHeader();
					TableColumn col = header.getColumnModel().getColumn(i);
					col.setHeaderValue(column[i]);
					header.repaint();
				}

				JScrollPane js = new JScrollPane(menuTable);
				js.setBounds(0, 230, Window.getW() - 20, 400);
				js.setVisible(true);
				add(js);
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
		textField.setBounds(lblDeleteItem.getX()+115, lblDeleteItem.getY()+85, 146, 34);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Item ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(lblDeleteItem.getX()-31, lblDeleteItem.getY()+94, 105, 16);
		add(lblNewLabel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(lblDeleteItem.getX()+44, lblDeleteItem.getY()+154, 134, 42);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection c= Menu.Connect(); 
					String sql_1 = "Select idFood from Menu where idFood= '"+textField.getText()+"' ";
					PreparedStatement s1= c.prepareStatement(sql_1);
					ResultSet rs= s1.executeQuery();
					if (rs.next()==true) {
						String sql = "Delete from Menu where id = '"+textField.getText()+"' ";
						PreparedStatement s2= c.prepareStatement(sql);
						s2.executeUpdate();
						for (Food i: food ) {
							if (i.getId().equals(textField.getText())&& menuTable.isVisible()==true){
								menuTable.repaint();
							}
						}
							
					}else JOptionPane.showMessageDialog(null,"No food "+textField.getText(),"Alert",JOptionPane.WARNING_MESSAGE);
					
					
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		add(btnDelete);
		
		
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

}
