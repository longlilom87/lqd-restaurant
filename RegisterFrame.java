
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame{

	public JFrame frame;
	private JTextField username,nameField,addressField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JPasswordField Pass1;
	private JPasswordField Pass2;
	
	ArrayList<User> userList = new ArrayList<>();
	/**
	 * Launch the application.
	 * @throws SQLException 
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
					RegisterFrame window = new RegisterFrame();
					window.frame.setVisible(true);
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
	public RegisterFrame() throws SQLException {
		initialize();
		
		Connection Conn = Connect();
		PreparedStatement stmt = Conn.prepareStatement("SELECT * FROM Authentication_Login;");
		ResultSet r = stmt.executeQuery();
		while(r.next()) {
			userList.add(new User(r.getString("username"),r.getString("userpassword"),r.getString("Login_Role"),r.getString("name"),r.getString("address")));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBackground(new Color(173, 216, 230));
		frame.setBounds(450, 40, 537, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(157, 37, 180, 41);
		frame.getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(132, 250, 272, 29);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(132, 110, 272, 29);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setBounds(132, 185, 272, 29);
		frame.getContentPane().add(addressField);
		addressField.setColumns(10);
		
//		JComboBox Role = new JComboBox();
//		Role.setBounds(132, 357, 272, 29);
//		frame.getContentPane().add(Role);
//		Role.addItem("Manager");
//		Role.addItem("Customer");
//		Role.addItem("Chef");
		
		lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(132, 225, 111, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(132, 290, 79, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Confirm Password");
		lblNewLabel_3.setBounds(132, 355, 111, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel name = new JLabel("Your name");
		name.setBounds(132, 85, 65, 14);
		frame.getContentPane().add(name);
		
		JLabel address = new JLabel("Address");
		address.setBounds(132,150,50,14);
		frame.getContentPane().add(address);
		
		JButton btnNewButton = new JButton("Create ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean bl = true;
				String pass = Pass1.getText();
				if(username.getText().equals("") || Pass1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Your username or password is empty");
					bl = false;
					return;
				}
				else {
					for(User t: userList) {
						if(t.getUsername().equals(username.getText())) {
							JOptionPane.showMessageDialog(null, "Please choose another username");
							bl=false;
							return;
						}
					}
					if(Pass1.getText().equals(Pass2.getText())) bl = true;
					else{
						bl = false;
						JOptionPane.showMessageDialog(null, "Your password and confirm password are different");
					}
				}
				
				if (bl) {
					try {
						insert(username.getText(), Pass1.getText(), "Customer",nameField.getText(),addressField.getText());
						JOptionPane.showMessageDialog(null, "Your account is created. Now please log in");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
			
			
		btnNewButton.setBounds(291, 437, 111, 51);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   {
				       frame.dispose();
				    }
				}});
			
		btnNewButton_1.setBounds(140, 437, 103, 51);
		frame.getContentPane().add(btnNewButton_1);
		
		Pass1 = new JPasswordField();
		Pass1.setBounds(132, 315, 269, 29);
		frame.getContentPane().add(Pass1);
		
		Pass2 = new JPasswordField();
		Pass2.setBounds(132, 380, 272, 33);
		frame.getContentPane().add(Pass2);

	}

	   public void insert(String username,String userpassword,String Login_Role,String name,String address) throws SQLException {
			String sql = "INSERT INTO Authentication_Login(username,userpassword, Login_Role,name,address) VALUES(?,?,?,?,?)";
          Connection  c = Connect();
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, userpassword);
	    stmt.setString(3, Login_Role);
	    stmt.setString(4, name);
	    stmt.setString(5, address);
        stmt.executeUpdate();
        

	   }
	   

	

		

}
