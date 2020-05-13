package main_app;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTable;

import draw.UpMenu;
import draw.UpStaff;
import draw.Upcustomer;
import draw.UpdateMenu;
import draw.Uptable;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBill;
	private JTextField textField;
	public static JTable table_2;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JLabel up1;
	private JLabel up2;
	private JLabel up3;
    private JScrollPane scrollPane;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_9;
    private JLabel lblNewLabel_10;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException{ 
		Connection conne = Connect();
//		PreparedStatement create = conne.prepareStatement("CREATE TABLE IF NOT EXISTS Res_table (" + 
//				"     tableID int PRIMARY KEY,\r\n" + 
//				"	  Table_status int,\r\n );");
//		create.executeUpdate();
		PreparedStatement stmt = conne.prepareStatement("SELECT * FROM Res_table;");
//		ResultSet rs = stmt.executeQuery();



		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerFrame frame = new ManagerFrame();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
	 * @throws SQLException 
	 */
	public ManagerFrame() throws SQLException {
	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(107, 142, 35));
		panel.setBounds(0, 0, 1350, 103);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome "+LoginFrame.user.getName());
		lblNewLabel.setBounds(1151, 11, 189, 82);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 251, 986, 412);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		table_2.setVisible(false);
		
		
		 lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(1051, 420, 261, 211);
		contentPane.add(lblNewLabel_1);
		
		up1 = new JLabel("yfyyyg");
		up1.setIcon(new ImageIcon (new ImageIcon("Image/computer.png").getImage().getScaledInstance(106,88, java.awt.Image.SCALE_SMOOTH)));
		up1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UpStaff upstaff;
					upstaff = new UpStaff();
					upstaff.setVisible(true);
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		up1.setBounds(799, 114, 106, 88);
		contentPane.add(up1);
		up1.setVisible(false);

		
		up2 = new JLabel("New label");
		up2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uptable uptable;
				try {
					uptable = new Uptable();
					uptable.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		up2.setIcon(new ImageIcon (new ImageIcon("Image/computer.png").getImage().getScaledInstance(106,88, java.awt.Image.SCALE_SMOOTH)));
		up2.setBounds(799, 114, 104, 89);
		contentPane.add(up2);
		up2.setVisible(false);

		up3 = new JLabel("New label");
		up3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Upcustomer upcus;
				upcus = new Upcustomer();
				upcus.setVisible(true);
			
				
			}
		});
		up3.setIcon(new ImageIcon (new ImageIcon("Image/computer.png").getImage().getScaledInstance(106,88, java.awt.Image.SCALE_SMOOTH)));
		up3.setBounds(799, 114, 106, 89);
		contentPane.add(up3);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				up1.setVisible(false);
				up2.setVisible(false);
				up3.setVisible(true);
				lblNewLabel_1.setIcon(new ImageIcon (new ImageIcon("Image/food.png").getImage().getScaledInstance(246,211, java.awt.Image.SCALE_SMOOTH)));
				table_2.setVisible(true);
				lblNewLabel_10.setVisible(true);
				scrollPane.setVisible(true);

				try {
				Connection connection= Connect();
				String query = "SELECT * FROM Authentication_Login WHERE Login_Role = 'Customer'; ";
				PreparedStatement	stmt = connection.prepareStatement(query);
				ResultSet rs1 = stmt.executeQuery();
				table_2.setModel(DbUtils.resultSetToTableModel(rs1));
			} catch (SQLException e1) {
					
					e1.printStackTrace();
				
			
			}
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon("Image/private-account.png").getImage().getScaledInstance(88,88, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(1238, 114, 88, 88);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				up2.setVisible(true);
				lblNewLabel_10.setVisible(true);

				up1.setVisible(false);
				lblNewLabel_1.setIcon(new ImageIcon (new ImageIcon("Image/Table.png").getImage().getScaledInstance(246,211, java.awt.Image.SCALE_SMOOTH)));
				table_2.setVisible(true);
                up3.setVisible(false);
				scrollPane.setVisible(true);

				try {
					Connection connection= Connect();
					String query = "SELECT * FROM Res_table ORDER by TableID;";
				 PreparedStatement	stmt = connection.prepareStatement(query);
					ResultSet rs = stmt.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
				
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon(new ImageIcon("Image/restaurant_1.png").getImage().getScaledInstance(88,88, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel_3.setBounds(915, 114, 98, 94);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					UpMenu u;
				
				try {
					u= new UpMenu();
					u.setVisible(true);
					u.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
			
		lblNewLabel_4.setIcon(new ImageIcon(new ImageIcon("Image/menu.png").getImage().getScaledInstance(88,88, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel_4.setBounds(1034, 114, 88, 88);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				up1.setVisible(true);
				up2.setVisible(false);
				lblNewLabel_10.setVisible(true);

				up3.setVisible(false);
				lblNewLabel_1.setIcon(new ImageIcon (new ImageIcon("Image/restaurant.png").getImage().getScaledInstance(246,211, java.awt.Image.SCALE_SMOOTH)));
				scrollPane.setVisible(true);
				table_2.setVisible(true);

				try {
					Connection connection= Connect();
					String query = "SELECT * FROM Authentication_Login WHERE Login_Role = 'Chef' or Login_Role = 'Manager' ORDER BY Login_Role DESC ; ";
					PreparedStatement	stmt = connection.prepareStatement(query);
					ResultSet rs2 = stmt.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rs2));
				} catch (SQLException e2) {
						
						e2.printStackTrace();
			}}
		});
		lblNewLabel_5.setIcon(new ImageIcon(new ImageIcon("Image/owner.png").getImage().getScaledInstance(106,88, java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel_5.setBounds(1122, 114, 106, 94);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("CUSTOMER");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(1238, 213, 98, 22);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("STAFF");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(1122, 213, 98, 22);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("MENU");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(1023, 213, 98, 22);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("TABLE");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(915, 213, 98, 22);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("UPDATE");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(799, 213, 98, 22);
		contentPane.add(lblNewLabel_10);
		lblNewLabel_10.setVisible(false);
		up3.setVisible(false);
		
	}
	
	private void addUpdateMenu() {
		UpdateMenu u;
		
		try {
			u= new UpdateMenu();
			u.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
