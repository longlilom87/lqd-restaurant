import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;

public class CreditCard extends JFrame {

	private JPanel contentPane;
	private int w = 700,h=700;
	private JPanel loginPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCard frame = new CreditCard();
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
	public CreditCard() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(Window.getW()/4, 0,w,h);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,684,661);
		contentPane.add(layeredPane);
		
		loginPanel.setBounds(0, 0, 684, 661);
		layeredPane.add(loginPanel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(0,0, 50, 50);
		loginPanel.add(usernameLabel);
	}
}
