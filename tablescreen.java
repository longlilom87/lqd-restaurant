import java.awt.EventQueue;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class tablescreen {

	
	private JFrame frame;

	static drawTable drawTable = new drawTable();
	private JTextField textField;
	private JTextField textField_1;
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
//		Menu.Update("DROP TABLE Res_table");
//		Menu.Update("CREATE TABLE IF NOT EXISTS Res_table(tableID int PRIMARY KEY, Table_status int);");
//		Menu.Update("INSERT INTO Res_table values (1,1),(2,1),(3,0),(4,0),(5,1);");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablescreen window = new tablescreen();
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
	public tablescreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
//		frame.setBounds(100, 100, 697, 699);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(drawTable);
		
		drawTable.setLayout(null);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvailable.setBounds(456, 164, 123, 82);
		drawTable.add(lblAvailable);
		
		JLabel lblReserved = new JLabel("Reserved");
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReserved.setBounds(456, 273, 123, 82);
		drawTable.add(lblReserved);
		
		textField = new JTextField();
		textField.setForeground(SystemColor.menu);
		textField.setBackground(Color.GREEN);
		textField.setEditable(false);
		textField.setBounds(376, 197, 54, 22);
		drawTable.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(SystemColor.menu);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(376, 306, 54, 22);
		drawTable.add(textField_1);
		
		
		
		Choice choice = new Choice();
		choice.setBounds(170, 535, 123, 25);
		drawTable.add(choice);
		
		List<Table> tablelist = getTablelist();
		for(int i = 0; i<tablelist.size();i++) {
			choice.add(Integer.toString(tablelist.get(i).getX()));
		}
		
		JButton btnBlank = new JButton("Blank");
		btnBlank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(choice.getSelectedItem(), 0);
				drawTable.repaint();
			}
		});
		btnBlank.setBounds(376, 532, 97, 25);
		drawTable.add(btnBlank);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(choice.getSelectedItem(),1);
				drawTable.repaint();
			}
		});
		btnCustomer.setBounds(376, 570, 97, 25);
		drawTable.add(btnCustomer);
		
		
	}
 
	
	public static List<Table> getTablelist() {
		List<Table> tablelist = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
			
			
			String getTable = "select * from Res_table";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(getTable);
			while (rs.next()) {
				tablelist.add(new Table (rs.getInt("TableID"),rs.getInt("Table_status")));
			}
			
			} catch (Exception err1) {
			System.out.println(err1);
		}
	return tablelist;
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	public void update(String id, int n) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
			String update = "update Res_table set Table_status="+n+" where TableID="+id;
			PreparedStatement st = c.prepareStatement(update);
			st.executeUpdate();
			
		} catch (Exception err1) {
			System.out.println(err1);
	}
	}
}
