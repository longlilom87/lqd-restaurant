package main_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame{

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JTextField txtLogin;
	private JTextField txtLogin_1;
	private JLabel Password;
	private JLabel Validation;
	
	String user,pass;
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public LoginFrame(String u, String p) {
		user = u;
		pass=p;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public static void main(String[] args) throws SQLException {
		Connection c = Connect();
		PreparedStatement create = c.prepareStatement("CREATE TABLE IF NOT EXISTS Authentication_Login (" + 
				"     username varchar(100)  Unique,\r\n" + 
				"	 userpassword varchar(14),\r\n" + 
				"	 Login_Role varchar(20 ) );");
		create.executeUpdate();
		
//		PreparedStatement insert = c.prepareStatement("INSERT INTO Authentication_Login (username, userpassword, Login_Role) values ('QuinLeaderr156', '1234231', 'Manager'),('DuckDuck', 'anhiuem123', 'Chef'),('Q_Dragon', 'nhantobanh', 'Customer');\r\n" + 
//				"");
//		insert.executeUpdate();
		
		PreparedStatement stmt = c.prepareStatement("SELECT * FROM Authentication_Login;");
		ResultSet r = stmt.executeQuery();
		while(r.next()) {
			System.out.println(r.getString("username")+" "+r.getString("userpassword")+" "+r.getString("Login_Role"));
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		check();
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
	
	public static ArrayList<LoginFrame> check() throws SQLException{
		Connection c = Connect();
		PreparedStatement stmt = c.prepareStatement("SELECT username,userpassword FROM Authentication_Login;" );
		ResultSet r = stmt.executeQuery();
		
		ArrayList<LoginFrame> arr = new ArrayList<>();
		
		while(r.next() ) {
//			System.out.println("User: "+r.getString("username")+" pass: "+r.getString("userpassword"));
			arr.add(new LoginFrame(r.getString("username"),r.getString("userpassword")));
		}
		return arr;
	}
	
	public LoginFrame() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 0, 255));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(450, 40, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		username = new JTextField();
		username.setBounds(158, 75, 219, 29);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(158, 115, 219, 29);
		frame.getContentPane().add(password);
		
		txtLogin = new JTextField();
		txtLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterFrame register = new RegisterFrame();
				register.frame.setVisible(true);  
				frame.dispose();
		

				
			}
		});
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogin.setEditable(false);
		txtLogin.setText("Register");
		txtLogin.setBounds(158, 173, 103, 20);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
	
		txtLogin_1 = new JTextField();
		txtLogin_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ArrayList<LoginFrame> arr;
				try {
					arr = check();
					int test=0;
					for(LoginFrame t : arr) {
						if ((t.getUser().equals(username.getText())) && t.getPass().equals(password.getText())) {
							test=1;
							break;
						}
					else test=0;
					}
					if (test==1) {
						JOptionPane.showMessageDialog(null, "ok");
					}else JOptionPane.showMessageDialog(null, "Incorrect");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtLogin_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogin_1.setEditable(false);
		txtLogin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogin_1.setText("Login");
		txtLogin_1.setBounds(282, 173, 97, 20);
		frame.getContentPane().add(txtLogin_1);
		txtLogin_1.setColumns(10);
		
		JLabel Users = new JLabel("User");
		Users.setFont(new Font("Tahoma", Font.BOLD, 12));
		Users.setHorizontalAlignment(SwingConstants.LEFT);
		Users.setBackground(new Color(175, 238, 238));
		Users.setBounds(44, 75, 71, 29);
		frame.getContentPane().add(Users);
		
		JLabel lblNewLabel = new JLabel("RESTAURANT");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(169, 26, 187, 38);
		frame.getContentPane().add(lblNewLabel);
		
		Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.BOLD, 12));
		Password.setHorizontalAlignment(SwingConstants.LEFT);
		Password.setBounds(44, 115, 71, 29);
		frame.getContentPane().add(Password);
		
		JLabel Validation = new JLabel("");
		Validation.setForeground(new Color(255, 255, 255));
		Validation.setBounds(158, 155, 219, 14);
		frame.getContentPane().add(Validation);
	}
}
