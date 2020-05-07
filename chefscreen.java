
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//chung 1 package thi bo dong nay

import java.awt.Font;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class chefscreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable orderTable;
	private JScrollBar scrollBar;
	private JScrollPane scrollPane;
	private JButton btnDone;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		orderTable = new JTable(createTableModel());
		orderTable.setFont(new Font("Tahoma", Font.PLAIN, 17));
		orderTable.setBounds(46, 133, 750, 299);
		contentPane.add(orderTable);

		btnDone = new JButton("Done");
		btnDone.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDone.setBounds(324, 459, 207, 79);
		btnDone.addActionListener(new ActionListener() {
			private Connection c = null;

			public void actionPerformed(ActionEvent e) {
				try {
					int i = orderTable.getSelectedRow();
				    if (i<0) {
				    	JOptionPane.showMessageDialog(null,"Please choose a line to perform action");
				    }
				    else {
				    	c= Menu.Connect();
						String change = "update Delivery set status =1 where id="
								+ orderTable.getModel().getValueAt(i, 0).toString();
						PreparedStatement st = c.prepareStatement(change);
						st.executeUpdate();
						orderTable.setModel(createTableModel()); // updateTable()
				    }
				} catch (Exception err) {
					System.out.println(err);
				}

			}

		});
		contentPane.add(btnDone);
		
		JLabel lblTodaysOrders = new JLabel("TODAY'S ORDERS");
		lblTodaysOrders.setFont(new Font(".VnArial", Font.BOLD, 60));
		lblTodaysOrders.setBounds(152, 44, 547, 62);
		contentPane.add(lblTodaysOrders);

	}

	public ArrayList<Order> delivery() {
		Connection c = null;
		ArrayList<Order> delivery = new ArrayList<Order>();
		try {

			c= Menu.Connect();
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

	public DefaultTableModel createTableModel() {
		ArrayList<Order> delivery = delivery();
		String[] column = { "id", "name", "food", "unit", "address", "status" };
		Object[] row = new Object[6];
		DefaultTableModel model = new DefaultTableModel(0, column.length);

		// add column name
		for (int i = 0; i < column.length; i++) {
			row[i] = column[i];

		}
		model.addRow(row);
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
