

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.util.ArrayList;

public class bill extends JFrame {

	private JPanel contentPane;
	private JTextArea txtYourName;
	private JTextArea textAddress;
	private JTextArea textPhone;
	private JTextArea textField_2;
	private JTextArea txtPrice;
	private static String name, address, food;
	private static int unit, price;
	private static int id;
	private JTextField txtOrders;
	private JTextField txtUnits;
	private JTextArea txtFoodGoesHere;
	private JTextArea textQty;
	private JTextField txtPriceLabel;
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					bill frame = new bill(id, name, address, food, unit, price);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	
	public bill(int id, String name, String address, ArrayList<FoodItem> foodList) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 734);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setForeground(new Color(240, 255, 255));
		btnPrint.setBackground(new Color(30, 144, 255));
		btnPrint.setFont(new Font("VnFujiyama2", Font.BOLD, 16));
		btnPrint.setBounds(155, 548, 152, 57);
		contentPane.add(btnPrint);
		
		txtYourName = new JTextArea();
		txtYourName.setBackground(Color.WHITE);
		txtYourName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtYourName.setEditable(false);
		txtYourName.setText("Your Name: "+name);
		txtYourName.setBounds(44, 125, 306, 57);
		contentPane.add(txtYourName);
		txtYourName.setColumns(10);
		
		textAddress = new JTextArea();
		textAddress.setText("Address: "+ address);
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textAddress.setEditable(false);
		textAddress.setLineWrap(true);
		textAddress.setColumns(10);
		textAddress.setBackground(Color.WHITE);
		textAddress.setBounds(44, 190, 351, 57);
		contentPane.add(textAddress);
		
		textPhone = new JTextArea();
		textPhone.setText("Phone: ");
		textPhone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPhone.setEditable(false);
		textPhone.setColumns(10);
		textPhone.setBackground(Color.WHITE);
		textPhone.setBounds(44, 260, 306, 37);
		contentPane.add(textPhone);
		
		textField_2 = new JTextArea();
		textField_2.setForeground(Color.BLACK);
		textField_2.setText("#"+id);
		textField_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 45));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(46, 24, 152, 57);
		contentPane.add(textField_2);
		
		
		
		txtOrders = new JTextField();
		txtOrders.setEditable(false);
		txtOrders.setHorizontalAlignment(SwingConstants.CENTER);
		txtOrders.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOrders.setText("Order(s)");
		txtOrders.setBounds(44, 310, 65, 22);
		contentPane.add(txtOrders);
		txtOrders.setColumns(10);
		
		txtUnits = new JTextField();
		txtUnits.setEditable(false);
		txtUnits.setHorizontalAlignment(SwingConstants.CENTER);
		txtUnits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUnits.setText("Unit(s)");
		txtUnits.setBounds(185, 310, 65, 22);
		contentPane.add(txtUnits);
		txtUnits.setColumns(10);
		
		int line = 346;
		for (FoodItem i: foodList) {
			System.out.println(i);
			txtFoodGoesHere = new JTextArea();
			txtFoodGoesHere.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtFoodGoesHere.setEditable(false);
			txtFoodGoesHere.setText(i.getName());
			txtFoodGoesHere.setBounds(44, line, 101, 22);
			contentPane.add(txtFoodGoesHere);
			txtFoodGoesHere.setColumns(10);
			
			
			textQty = new JTextArea();
			textQty.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textQty.setEditable(false);
			textQty.setText(""+i.getQty());
			textQty.setBounds(185, line, 65, 22);
			contentPane.add(textQty);
			textQty.setColumns(10);
			
			txtPrice = new JTextArea();
			txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtPrice.setText(""+i.getPrice()*i.getQty());
			txtPrice.setBounds(301, line, 65, 22);
			contentPane.add(txtPrice);
			txtPrice.setColumns(10);
			
			line = line + txtFoodGoesHere.getFont().getSize()+10;
		}
		
		
		txtPriceLabel = new JTextField();
		txtPriceLabel.setEditable(false);
		txtPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		txtPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPriceLabel.setText("Price");
		txtPriceLabel.setBounds(301, 310, 49, 22);
		contentPane.add(txtPriceLabel);
		txtPriceLabel.setColumns(10);
	}
}
