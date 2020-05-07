import java.awt.BorderLayout;
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
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtAboutUs;
	private JTextField txtEmployee;
	private JTextField txtMenu;
	private JTextField txtTable;
	private JTextField txtBill;
	private JTextField textField;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException { 
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
	 */
	public ManagerFrame() {
	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 1370, 103);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Welcome .....  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 112, 158, 25);
		contentPane.add(lblNewLabel);
		
		txtAboutUs = new JTextField();
		txtAboutUs.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				try {
				Connection connection= Connect();
				String query = "SELECT * FROM Authentication_Login;";
				PreparedStatement	stmt = connection.prepareStatement(query);
				ResultSet rs1 = stmt.executeQuery();
				table_2.setModel(DbUtils.resultSetToTableModel(rs1));
			} catch (SQLException e1) {
					
					e1.printStackTrace();
				
			
			}}
				
		});
		txtAboutUs.setEditable(false);
		txtAboutUs.setBackground(Color.LIGHT_GRAY);
		txtAboutUs.setHorizontalAlignment(SwingConstants.CENTER);
		txtAboutUs.setText("Customer");
		txtAboutUs.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtAboutUs.setBounds(1220, 114, 140, 33);
		contentPane.add(txtAboutUs);
		txtAboutUs.setColumns(10);
		
		txtEmployee = new JTextField();
		txtEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		txtEmployee.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtEmployee.setBackground(Color.LIGHT_GRAY);
		txtEmployee.setEditable(false);
		txtEmployee.setText("Chef");
		txtEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmployee.setBounds(1060, 114, 140, 33);
		contentPane.add(txtEmployee);
		txtEmployee.setColumns(10);
		
		txtMenu = new JTextField();
		txtMenu.setEditable(false);
		txtMenu.setText("Menu");
		txtMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMenu.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtMenu.setBackground(Color.LIGHT_GRAY);
		txtMenu.setBounds(901, 114, 140, 33);
		contentPane.add(txtMenu);
		txtMenu.setColumns(10);
		
		txtTable = new JTextField();
		txtTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
			
				try {
					Connection connection= Connect();
					String query = "SELECT * FROM Res_table;";
				 PreparedStatement	stmt = connection.prepareStatement(query);
					ResultSet rs = stmt.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			
				
				
				
				
			}
		});
		txtTable.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtTable.setEditable(false);
		txtTable.setHorizontalAlignment(SwingConstants.CENTER);
		txtTable.setBackground(Color.LIGHT_GRAY);
		txtTable.setForeground(Color.BLACK);
		txtTable.setText("Table ");
		txtTable.setBounds(739, 114, 140, 33);
		contentPane.add(txtTable);
		txtTable.setColumns(10);
		
		txtBill = new JTextField();
		txtBill.setEditable(false);
		txtBill.setHorizontalAlignment(SwingConstants.CENTER);
		txtBill.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtBill.setText("Bill");
		txtBill.setBackground(Color.LIGHT_GRAY);
		txtBill.setBounds(574, 114, 140, 33);
		contentPane.add(txtBill);
		txtBill.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 220, 986, 412);
		contentPane.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update update ;
				update = new Update();
				update.setVisible(true);
			}
		});
		btnNewButton.setBounds(1044, 547, 120, 85);
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
