

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Welcome extends JPanel{
	
	private Button bLogin,bSignup,bMenu;
	private Image image = new ImageIcon("Image/welcome.jpg").getImage().getScaledInstance(Window.getW(), Window.getH(), java.awt.Image.SCALE_SMOOTH);
	private JLabel bg  = new JLabel(new ImageIcon(image));
//	private static JPanel welcomePanel = new JPanel();
	
	public Welcome() {
		setBounds(0,0,Window.getW(),Window.getH());
		setLayout(null);
		setBackground(new Color(255, 250, 205));
		
		bg.setBounds(0, 0, Window.getW(), Window.getH());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(107, 142, 35));
		panel.setBounds(0, 0, 1370, 94);
		add(panel);
		panel.setLayout(null);
		
		bMenu = new Button("Menu");
		bMenu.setForeground(new Color(255, 255, 255));
		bMenu.setBounds(1019, 0, 103, 94);
		panel.add(bMenu);
		bMenu.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 30));
		bMenu.setBackground(new Color(107, 142, 35));
		
		bLogin = new Button("Log In");
		bLogin.setForeground(new Color(255, 255, 255));
		bLogin.setBounds(1121, 0, 117, 94);
		panel.add(bLogin);
		bLogin.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 30));
		bLogin.setBackground(new Color(107, 142, 35));
		
		bSignup = new Button("Sign Up");
		bSignup.setForeground(new Color(255, 255, 255));
		bSignup.setBounds(1238, 0, 132, 94);
		panel.add(bSignup);
		bSignup.setBackground(new Color(107, 142, 35));
		bSignup.setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 30));
		
		JLabel lblLqd = new JLabel("LQD");
		lblLqd.setForeground(new Color(255, 255, 255));
		lblLqd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 80));
		lblLqd.setHorizontalAlignment(SwingConstants.CENTER);
		lblLqd.setBounds(10, 0, 246, 94);
		panel.add(lblLqd);
		
		JLabel lblRestaurant = new JLabel("restaurant");
		lblRestaurant.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestaurant.setForeground(new Color(255, 255, 255));
		lblRestaurant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblRestaurant.setBounds(191, 52, 140, 42);
		panel.add(lblRestaurant);
		bSignup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame register;
				try {
					register = new RegisterFrame();
					register.frame.setVisible(true);  
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
//				dispose();
			}
		});
		bLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame login;
					try {
						login = new LoginFrame();
						login.setVisible(true);
				    login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
		bMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				Menu.bl=false;
//				try {
//					Window.switchPane(new Menu());
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				Goodbye g;
				try {
					g = new Goodbye();
					g.setVisible(true);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
//				try {
//				
//					//Window.switchPane(new UpdateMenu());
//					
//
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
		}
			});
		
		add(bg);
	}
	
	private void setupPanel(JPanel p,Color c) {
//		setBounds(0,0,Frame.getW(),Frame.getH());
		p.setLayout(null);
		p.setBackground(c);
	}
}
