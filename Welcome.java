import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Welcome extends JPanel{
	
    private JPanel barPanel = new JPanel();
	private Button bLogin,bSignup,bMenu;
//	private static JPanel welcomePanel = new JPanel();
	
	public Welcome() {
		setBounds(0,0,Window.getW(),Window.getH());
		setLayout(null);
		setBackground(new Color(255, 250, 205));
		
		barPanel.setBounds(0, 0, Window.getW(), 100);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(107, 142, 35));
		add(barPanel);
		
		bLogin = new Button("Log In");
		bLogin.setBounds(500, 0, 100, 100);
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
		barPanel.add(bLogin);
		
		bSignup = new Button("Sign Up");
		bSignup.setBounds(700, 0, 100, 100);
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
		barPanel.add(bSignup);
		
		bMenu = new Button("Menu");
		bMenu.setBounds(900, 0, 100, 100);
		bMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Menu.switchPane(new drawTable());
			}
		});
		
	}
	
	private void setupPanel(JPanel p,Color c) {
//		setBounds(0,0,Frame.getW(),Frame.getH());
		p.setLayout(null);
		p.setBackground(c);
	}
}
