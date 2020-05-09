
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class chefscreen extends JPanel {

//	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable orderTable;
	private JScrollBar scrollBar;
	private JScrollPane scrollPane;
	private JButton btnDone;
	static ArrayList<Order> delivery;
	String Welcome;
	String[] column= { "id", "name", "food", "unit", "TableID", "status" };
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chefscreen frame = new chefscreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public chefscreen() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 861, 621);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setBounds(100, 100, 861, 621);
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
		
		setBounds(0,0 ,1366, 768);
		setLayout(null);
		
		
		String todaysOrder= "TODAY'S ORDERS";
		JLabel lblTodaysOrders = new JLabel(todaysOrder,SwingConstants.CENTER);
		lblTodaysOrders.setFont(new Font(".VnArial", Font.BOLD, 60));
		lblTodaysOrders.setBounds(100, 44, 600, 62);
//		contentPane.
		add(lblTodaysOrders);
		
		if (LoginFrame.user.getName()==null) {
			
			Welcome= "Guest";
		} else Welcome= LoginFrame.user.getName();
		
		JLabel lblWelcome = new JLabel("Welcome, "+ Welcome,SwingConstants.CENTER);
		lblWelcome.setFont(new Font(".VnArial", Font.BOLD, 20));
		lblWelcome.setBounds(Window.getW()-500, 44, 600, 62);
//		contentPane.
		add(lblWelcome,SwingConstants.CENTER);
		
		int btnDonex= (lblTodaysOrders.getX()+lblWelcome.getX())/2;
		btnDone = new JButton("Done");
		btnDone.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDone.setBounds(btnDonex, Window.getH()-200, 207, 79);
		btnDone.addActionListener(new ActionListener() {
			private Connection c = null;

			public void actionPerformed(ActionEvent e) {
				try {
					int i = orderTable.getSelectedRow();

					if (i < 0) {
						JOptionPane.showMessageDialog(null, "Please choose a line to perform action");
					} else {
						c = Menu.Connect();
						String change = "update Delivery set status =1 where id='" + item(i, 0) + "' and name ='"
								+ item(i, 1) + "' and food ='" + item(i, 2) + "'";
						PreparedStatement st = c.prepareStatement(change);
						st.executeUpdate();
						orderTable.setModel(createTableModel()); // updateTable()
					}

				} catch (Exception err) {
					System.out.println(err);
				}

			}

		});
//		contentPane.
		add(btnDone);
	    

		orderTable = new JTable(createTableModel());
		orderTable.setFont(new Font("Tahoma", Font.PLAIN, 30));
		orderTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 30));
		orderTable.setRowHeight(60);
		 
		//add column header
		for (int i=0; i<column.length;i++) {
			JTableHeader header = orderTable.getTableHeader();
			TableColumn col = header.getColumnModel().getColumn(i);
			col.setHeaderValue(column[i]);
			header.repaint();
		}

		JScrollPane js=new JScrollPane(orderTable);
		js.setBounds(0, 130, Window.getW()-20, btnDone.getY()-200);
		js.setVisible(true);
		add(js);
		
	
		
	}

	public ArrayList<Order> delivery() {
		Connection c = null;
		ArrayList<Order> delivery = new ArrayList<Order>();
		try {

			c = Menu.Connect();
			String Table = "select * from Delivery where status =0";
			PreparedStatement st = c.prepareStatement(Table);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Order order = new Order(rs.getInt("id"), rs.getString("name"), rs.getString("food"), rs.getInt("unit"),
						rs.getString("address"), rs.getInt("status"));
				delivery.add(order);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return delivery;

	}

	private String item(int i, int a) {
		return orderTable.getValueAt(i, a).toString();
	}

	public DefaultTableModel createTableModel() {
		delivery = delivery();
		Object[] row = new Object[column.length];
		DefaultTableModel model = new DefaultTableModel(0, column.length);

		// add row
		for (int i = 0; i < delivery.size(); i++) {
			row[0] = delivery.get(i).getId();
			row[1] = delivery.get(i).getName();
			row[2] = delivery.get(i).getFood();
			row[3] = delivery.get(i).getUnit();
			row[4] = delivery.get(i).getAddress();
			row[5] = delivery.get(i).getStatus();
			model.addRow(row);
			
		}
		
		return model;

	}

}
