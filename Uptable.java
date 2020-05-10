import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Uptable extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private JTextField Status;
	private static Connection c;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Uptable frame = new Uptable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		Connection ct = Connect();
		PreparedStatement stmt = c.prepareStatement("SELECT * FROM Res_table;");
		ResultSet r = stmt.executeQuery();
		while (r.next()) {
			System.out.println(r.getInt("tableID") + " " + r.getInt("Table_status"));
		}
	}

	/**
	 * Create the application.
	 */
	public static Connection Connect() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:Restaurant.db";
			connection = DriverManager.getConnection(url);
			System.out.println("ConnectJDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR :" + e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Uptable() throws SQLException {
		c = Connect();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ID = new JTextField();
		ID.setBounds(132, 64, 149, 25);
		contentPane.add(ID);
		ID.setColumns(10);

		Status = new JTextField();
		Status.setBounds(132, 114, 149, 25);
		contentPane.add(Status);
		Status.setColumns(10);

		JLabel lblNewLabel = new JLabel("Table ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 69, 57, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Table Status");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(35, 114, 82, 25);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
           

				try {
                
					checkexist();
					String query = "SELECT * FROM Res_table ORDER by tableID;";
					PreparedStatement stmt = c.prepareStatement(query);
					ResultSet rs = stmt.executeQuery();
					ManagerFrame.table_2.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
                    
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(423, 65, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkavailable();
					String query = "SELECT * FROM Res_table Order by tableID ;";
					PreparedStatement stmt = c.prepareStatement(query);
					ResultSet rs = stmt.executeQuery();
					ManagerFrame.table_2.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(423, 115, 89, 23);
		contentPane.add(btnNewButton_1);

	}
// ADD
	public void insert(int tableID, int Table_status) throws SQLException {
		String sql = "Insert into Res_table(tableID, Table_status) VALUES(?,?)";

//		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setInt(1, tableID);
		stmt.setInt(2, Table_status);
		stmt.executeUpdate();

	}
// DELETE
	public void insert_2(int tableID) throws SQLException {
		String sql = "Delete from Res_table where tableID = ? ;";

//		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setInt(1, tableID);
		stmt.executeUpdate();

	}
   //check 1
	public void checkexist() throws SQLException {
		String sql = "SELECT tableID From Res_table where tableID = ? ;";

//		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setInt(1, Integer.parseInt(ID.getText()));
		ResultSet rs = stmt.executeQuery();
		if (rs.next() == true) {
			JOptionPane.showMessageDialog(null, "Table existed");
		} else {
			insert(Integer.parseInt(ID.getText()), Integer.parseInt(Status.getText()));
		}
	}
//check 2
	public void checkavailable() throws SQLException {
		String sql = "SELECT tableID From Res_table where tableID = ? ;";

//		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setInt(1, Integer.parseInt(ID.getText()));
		ResultSet rs = stmt.executeQuery();
		if (rs.next() == false) {
			JOptionPane.showMessageDialog(null, "Table not available");
		} else {
			insert_2(Integer.parseInt(ID.getText()));
		}

	}
}
