import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;

public class UpStaff extends JFrame {

	
	private JPanel contentPane;
	private JTextField newpass;
	private JTextField newname;
	private JTextField newaddress;
	private JTextField account;

	private JTextField txtDone;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private static Connection c;
	private JComboBox comboBox;
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpStaff frame = new UpStaff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	/**
	 * Create the frame.
	 */
	public UpStaff() throws SQLException {
		 c = Connect();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
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
		btnNewButton.setBounds(120, 184, 123, 22);
		contentPane.add(btnNewButton);
		
		newpass = new JTextField();
		newpass.setBounds(264, 184, 254, 22);
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
		btnNewButton_1.setBounds(120, 232, 123, 23);
		contentPane.add(btnNewButton_1);
		
		newname = new JTextField();
		newname.setBounds(264, 232, 254, 22);
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
		btnNewButton_2.setBounds(120, 280, 123, 29);
		contentPane.add(btnNewButton_2);
		
		newaddress = new JTextField();
		newaddress.setBounds(264, 280, 254, 29);
		contentPane.add(newaddress);
		newaddress.setColumns(10);
		
		JLabel SelectAccount = new JLabel("Account ");
		SelectAccount.setBackground(Color.WHITE);
		SelectAccount.setHorizontalAlignment(SwingConstants.CENTER);
		SelectAccount.setBounds(120, 125, 123, 35);
		contentPane.add(SelectAccount);
		
		account = new JTextField();
		account.setBounds(264, 128, 254, 28);
		contentPane.add(account);
		account.setColumns(10);
		
		btnNewButton_3 = new JButton("CREATE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	    			Connection connection= Connect();

					checkexist();
                    String query = "SELECT * FROM Authentication_Login WHERE Login_Role = 'Chef' or Login_Role = 'Manager' ORDER BY Login_Role DESC ; ";
					
					PreparedStatement	stmt = connection.prepareStatement(query);
					ResultSet rs2 = stmt.executeQuery();
					ManagerFrame.table_2.setModel(DbUtils.resultSetToTableModel(rs2));
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

			
			}	});
		btnNewButton_3.setBackground(Color.ORANGE);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setBounds(270, 398, 106, 28);
		contentPane.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(107, 142, 35));
		panel.setBounds(0, 0, 684, 71);
		contentPane.add(panel);
		
	    btnNewButton_4 = new JButton("DELETE");
	    btnNewButton_4.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
	    			Connection connection= Connect();
	    			checkdontdeletemainaccount();
	    			String query = "SELECT * FROM Authentication_Login WHERE Login_Role = 'Chef' or Login_Role = 'Manager' ORDER BY Login_Role DESC ; ";
					
					PreparedStatement	stmt = connection.prepareStatement(query);
					ResultSet rs2 = stmt.executeQuery();
					ManagerFrame.table_2.setModel(DbUtils.resultSetToTableModel(rs2));
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
	    		
	    	}
	    });
	    btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnNewButton_4.setBorder(null);
		btnNewButton_4.setForeground(new Color(0, 0, 0));
		btnNewButton_4.setBackground(Color.ORANGE);
		btnNewButton_4.setBounds(411, 398, 106, 29);
		contentPane.add(btnNewButton_4);
		
		comboBox = new JComboBox();
		comboBox.setBounds(264, 343, 254, 29);
		
		contentPane.add(comboBox);
		comboBox.addItem("Chef");
		comboBox.addItem("Manager");
	    

		
		JLabel lblNewLabel = new JLabel("Role");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 343, 123, 29);
		contentPane.add(lblNewLabel);
	}
	public void insert(String username, String userpassword,String Login_Role, String name, String address) throws SQLException {
		String sql = "INSERT INTO Authentication_Login (username, userpassword, Login_Role, name, address) VALUES(?,?,?,?,?)";

//		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, userpassword);
	    stmt.setString(3, Login_Role);
	    stmt.setString(4, name);
	    stmt.setString(5, address);
        stmt.executeUpdate();

	}
	public void insert_4(String username) throws SQLException {
		String sql = "Delete from  Authentication_Login WHERE username = '"+username+"';";
//		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
        stmt.executeUpdate();

		
		

	}

	//update passs
	  public void insert_1(String username,String userpassword) throws SQLException {
			String sql = "UPDATE Authentication_Login\r\n" + 
					"SET userpassword = '"+userpassword+
					"' WHERE   Login_Role ='Chef' and username = '"+username+"';";
      Connection  c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		
	   
      stmt.executeUpdate();}
    //update name
      public void insert_2(String username,String name) throws SQLException {
			String sql = "UPDATE Authentication_Login\r\n" + 
					"SET name = '"+name+
					"' WHERE  (Login_Role ='Chef' or Login_Role = 'Manager' ) and username = '"+username+"';";
      Connection  c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		
	   
      stmt.executeUpdate();}
      //update address
      public void insert_3(String username,String address) throws SQLException {
			String sql = "UPDATE Authentication_Login\r\n" + 
					"SET address = '"+address+
					"' WHERE  Login_Role ='Chef' and username = '"+username+"';";
      Connection  c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
	
	   
      stmt.executeUpdate();
	   }
      public void checkexist() throws SQLException {
    	  String username = account.getText();
  		String sql = "SELECT username From Authentication_Login where username  ='"+username+"';";

//  		Connection c = Connect();
  		PreparedStatement stmt = c.prepareStatement(sql);
		

  		
  		ResultSet rs = stmt.executeQuery();
  		if (rs.next() == true) {
  			JOptionPane.showMessageDialog(null, "Account existed");
  		} else {
			insert(account.getText(),newpass.getText(),comboBox.getSelectedItem().toString(),newname.getText(),newaddress.getText());

  			
  		}}
 
      public void checkavailable() throws SQLException {
    	  
    		String username = account.getText();
			String sql = "SELECT username From Authentication_Login where username  ='"+username +"';";

//    		Connection c = Connect();
    		PreparedStatement stmt = c.prepareStatement(sql);
    		

    		
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next() == false) {
    			JOptionPane.showMessageDialog(null, "Account not available");
    		} else {
    			insert_4(account.getText());
      
}}
      public void checkdontdeletemainaccount() throws SQLException {
    	  
  		
  		
  		
  		if (account.getText().equals("Admin")) {
  			JOptionPane.showMessageDialog(null, "Account is main account, can not delete");
  		} else {
  			checkavailable();;
    
}}
      }