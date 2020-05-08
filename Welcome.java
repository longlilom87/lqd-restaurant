import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome extends JPanel{
	
    private JPanel barPanel = new JPanel();
	private Button bLogin,bSignup,bMenu;
	private JLabel bg  = new JLabel(new ImageIcon("Image\\hinh_LQD.png"));
//	private static JPanel welcomePanel = new JPanel();
	
	public Welcome() {
		setBounds(0,0,Window.getW(),Window.getH());
		setLayout(null);
		setBackground(new Color(255, 250, 205));
		
		bg.setBounds(0, 0, Window.getW(), Window.getH());
		
		barPanel.setBounds(0, 0, Window.getW(), 100);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(107, 142, 35));
//		add(barPanel);
		
		bLogin = new Button("Log In");
		bLogin.setBounds(400, 600, 100, 50);
		bLogin.setFont(new Font("Bauhaus 93", Font.PLAIN, 18));
		bLogin.setBackground(Color.orange);
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
		add(bLogin);
		
		bSignup = new Button("Sign Up");
		bSignup.setBounds(600, 600, 100, 50);
		bSignup.setBackground(Color.orange);
		bSignup.setFont(new Font("Bauhaus 93", Font.PLAIN, 18));
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
		add(bSignup);
		
		bMenu = new Button("Menu");
		bMenu.setBounds(800,600, 100, 50);
		bMenu.setFont(new Font("Bauhaus 93", Font.PLAIN, 18));
		bMenu.setBackground(Color.orange);
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
				Window.switchPane(new chefscreen());
			}
		});
		add(bMenu);
		add(bg);
	}
	
	private void setupPanel(JPanel p,Color c) {
//		setBounds(0,0,Frame.getW(),Frame.getH());
		p.setLayout(null);
		p.setBackground(c);
	}
}
