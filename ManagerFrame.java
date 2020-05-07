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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtAboutUs;
	private JTextField txtEmployee;
	private JTextField txtMenu;
	private JTextField txtTable;
	private JTextField txtBill;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		txtMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		txtMenu.setEditable(false);
		txtMenu.setText("Menu");
		txtMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMenu.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtMenu.setBackground(Color.LIGHT_GRAY);
		txtMenu.setBounds(901, 114, 140, 33);
		contentPane.add(txtMenu);
		txtMenu.setColumns(10);
		
		txtTable = new JTextField();
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
		

		
		
	}
}
