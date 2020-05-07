import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Upcustomer extends JFrame {

	private JPanel contentPane;
	private JTextField newpass;
	private JTextField newname;
	private JTextField newaddress;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upcustomer frame = new Upcustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		btnNewButton.setBounds(120, 169, 123, 22);
		contentPane.add(btnNewButton);
		
		newpass = new JTextField();
		newpass.setBounds(264, 169, 254, 22);
		contentPane.add(newpass);
		newpass.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("UpdateName");
		btnNewButton_1.setBounds(120, 252, 123, 23);
		contentPane.add(btnNewButton_1);
		
		newname = new JTextField();
		newname.setBounds(264, 252, 254, 22);
		contentPane.add(newname);
		newname.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("UpdateAddress");
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
		
		textField = new JTextField();
		textField.setBounds(264, 89, 254, 28);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
