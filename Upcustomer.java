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

import cate_list.User;
import main_app.RegisterFrame;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;

public class Upcustomer extends JFrame {

	private JPanel contentPane;
	private JTextField newpass;
	private JTextField newname;
	private JTextField newaddress;
	private JTextField account;

	ArrayList<User> userList = new ArrayList<>();
	private JTextField txtDone;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException {
		Connection Conn = Connect();
		
		PreparedStatement create = Conn.prepareStatement("CREATE TABLE IF NOT EXISTS Authentication_Login (" + 
				"     username varchar(100)  Unique,\r\n" + 
				"	 userpassword varchar(14),\r\n" + 
				"	 Login_Role varchar(20 ),"
				+ "name varchar(100),"
				+ "address varchar(100) );");
		create.executeUpdate();
		
		PreparedStatement stmt = Conn.prepareStatement("SELECT * FROM Authentication_Login;");
		ResultSet r = stmt.executeQuery();
		while(r.next()) {
			System.out.println(r.getString("username")+" "+r.getString("userpassword")+" "+r.getString("Login_Role")+" "+r.getString("name")+" "+r.getString("address"));
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upcustomer upcus = new Upcustomer();
					upcus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
			System.out.println("ERROR :"+e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * Create the frame.
	 */
	public Upcustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Updatepass");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insert_1(account.getText(),newpass.getText());
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(120, 169, 123, 22);
		contentPane.add(btnNewButton);
		
		newpass = new JTextField();
		newpass.setBounds(264, 169, 254, 22);
		contentPane.add(newpass);
		newpass.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("UpdateName");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insert_2(account.getText(),newname.getText());
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			
			}
		});
		btnNewButton_1.setBounds(120, 252, 123, 23);
		contentPane.add(btnNewButton_1);
		
		newname = new JTextField();
		newname.setBounds(264, 252, 254, 22);
		contentPane.add(newname);
		newname.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("UpdateAddress");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insert_3(account.getText(),newaddress.getText());
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(120, 339, 123, 29);
		contentPane.add(btnNewButton_2);
		
		newaddress = new JTextField();
		newaddress.setBounds(264, 339, 254, 29);
		contentPane.add(newaddress);
		newaddress.setColumns(10);
		
		JLabel SelectAccount = new JLabel("Account ");
		SelectAccount.setBackground(Color.WHITE);
		SelectAccount.setHorizontalAlignment(SwingConstants.CENTER);
		SelectAccount.setBounds(120, 82, 123, 35);
		contentPane.add(SelectAccount);
		
		account = new JTextField();
		account.setBounds(264, 89, 254, 28);
		contentPane.add(account);
		account.setColumns(10);
		
		btnNewButton_3 = new JButton("DONE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBackground(Color.ORANGE);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setBounds(295, 398, 143, 29);
		contentPane.add(btnNewButton_3);
		
		
	}
	//update passs
	  public void insert_1(String username,String userpassword) throws SQLException {
			String sql = "UPDATE Authentication_Login\r\n" + 
					"SET userpassword = '"+userpassword+
					"' WHERE username = '"+username+"';";
        Connection  c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		
	   
        stmt.executeUpdate();}
      //update name
        public void insert_2(String username,String name) throws SQLException {
			String sql = "UPDATE Authentication_Login\r\n" + 
					"SET name = '"+name+
					"' WHERE username = '"+username+"';";
        Connection  c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		
	   
        stmt.executeUpdate();}
        //update address
        public void insert_3(String username,String address) throws SQLException {
			String sql = "UPDATE Authentication_Login\r\n" + 
					"SET address = '"+address+
					"' WHERE username = '"+username+"';";
        Connection  c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
	
	   
        stmt.executeUpdate();
	   }
}
