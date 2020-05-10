import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class customerscreen extends JPanel{
	
	private JPanel barPanel = new JPanel();
	private JPanel barPanel2 = new JPanel();
	private JPanel infoPanel, historyPanel, infoChangePanel;
	private JTextField nameField,addressField, usernameField;
	private static Connection c;
	
	private Button bLogout,bInfo,bOrder;
	private JLayeredPane layer = new JLayeredPane();
	
	public customerscreen() {
//		try {
//			c = Menu.Connect();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		setBounds(0,0,Window.getW(),Window.getH());
		setLayout(null);
		setBackground(new Color(255, 250, 205));
		
//		barPanel.set
		
		barPanel2.setBounds(10, 10, Window.getW()-35, 80);
		barPanel2.setLayout(null);
		barPanel2.setBackground(Color.white);
		add(barPanel2);
		
		barPanel.setBounds(0, 0, Window.getW(), 100);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(107, 142, 35));
		add(barPanel);
		
		bLogout= new Button("Log out");
		bLogout.setBounds(1000, 0, 50,50);
		bLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame.user.Logout();
				Window.switchPane(new Welcome());
			}
		});
		barPanel2.add(bLogout);
		
		bInfo = new Button("Profile");
		bInfo.setBounds(900, 0, 100, 50);
		bInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPane(infoPanel);
			}
		});
		barPanel2.add(bInfo);
		
		bOrder = new Button("Order");
		bOrder.setBounds(800, 0, 50, 50);
		bOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.switchPane(new drawTable());
			}
		});
		barPanel2.add(bOrder);
		
		layer.setBounds(0, 100,Window.getW(), Window.getH()-100);
		layer.setLayout(new CardLayout(0, 0));
		add(layer);
		
		infoPanel = new JPanel();
		infoChangePanel = new JPanel();
		setLayerPanel(infoChangePanel);
		setLayerPanel(infoPanel);
		
		//INFO PANEL
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		nameLabel.setForeground(Color.ORANGE);
		nameLabel.setBounds(66, 98, 126, 59);
		infoPanel.add(nameLabel);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setForeground(Color.ORANGE);
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		addressLabel.setBounds(66, 198, 172, 59);
		infoPanel.add(addressLabel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.ORANGE);
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		usernameLabel.setBounds(66, 298, 215, 59);
		infoPanel.add(usernameLabel);
		
		JLabel usernameInfo = new JLabel(LoginFrame.user.getUsername());
		usernameInfo.setForeground(new Color(107, 142, 35));
		usernameInfo.setFont(new Font("Tahoma", Font.ITALIC, 40));
		usernameInfo.setBounds(291, 298, 215, 59);
		infoPanel.add(usernameInfo);
		
		JLabel addressInfo = new JLabel(LoginFrame.user.getAddress());
		addressInfo.setForeground(new Color(107, 142, 35));
		addressInfo.setFont(new Font("Tahoma", Font.ITALIC, 40));
		addressInfo.setBounds(291, 198, 215, 59);
		infoPanel.add(addressInfo);
		
		JLabel nameInfo = new JLabel(LoginFrame.user.getName());
		nameInfo.setForeground(new Color(107, 142, 35));
		nameInfo.setFont(new Font("Tahoma", Font.ITALIC, 40));
		nameInfo.setBounds(291, 98, 215, 59);
		infoPanel.add(nameInfo);
		
		Button bChange = new Button("Change...");
		infoPanel.add(bChange);
		bChange.setBounds(1000, 500, 100, 60);
		bChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPane(infoChangePanel);
			}
		});
		
		//INFO CHANGE PANEL
		JLabel nameChangeLabel = new JLabel("Name");
		nameChangeLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		nameChangeLabel.setForeground(Color.ORANGE);
		nameChangeLabel.setBounds(66, 98, 126, 59);
		infoChangePanel.add(nameChangeLabel);
		
		JLabel addressChangeLabel = new JLabel("Address");
		addressChangeLabel.setForeground(Color.ORANGE);
		addressChangeLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		addressChangeLabel.setBounds(66, 198, 172, 59);
		infoChangePanel.add(addressChangeLabel);
		
		JLabel usernameChangeLabel = new JLabel("Username");
		usernameChangeLabel.setForeground(Color.ORANGE);
		usernameChangeLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		usernameChangeLabel.setBounds(66, 298, 215, 59);
		infoChangePanel.add(usernameChangeLabel);
		
		JLabel usernameChangeInfo = new JLabel(LoginFrame.user.getUsername());
		usernameChangeInfo.setBounds(291, 298, 215, 59);
		
		JLabel addressChangeInfo = new JLabel(LoginFrame.user.getAddress());
		addressChangeInfo.setBounds(291, 198, 215, 59);
		
		JLabel nameChangeInfo = new JLabel(LoginFrame.user.getName());
		nameChangeInfo.setBounds(291, 98, 215, 59);
		
		nameField = new JTextField();
		nameField.setBounds(290, 104, 172, 36);
		
		addressField = new JTextField();
		addressField.setBounds(291, 217, 172, 36);
		
		usernameField = new JTextField();
		
		setUpChange(nameField, nameChangeInfo);
		setUpChange(addressField,addressChangeInfo);
		setUpChange(usernameField, usernameChangeInfo);
		
		JButton bChangeName = new JButton("Change");
		bChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bChange(nameField, nameChangeInfo);
			}
		});
		bChangeName.setBounds(687, 111, 89, 23);
		infoChangePanel.add(bChangeName);
		
		click(nameField,nameChangeInfo,"name");
		
		JButton bChangeAddress = new JButton("Change");
		bChangeAddress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bChange(addressField,addressChangeInfo);
			}
		});
		bChangeAddress.setBounds(687, 216, 89, 23);
		infoChangePanel.add(bChangeAddress);
		
		click(addressField,addressChangeInfo,"address");
		
		JButton bChangeUsername = new JButton("Change password");
		bChangeUsername.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePassword();
			}
		});
		bChangeUsername.setBounds(687, 315, 150, 23);
		infoChangePanel.add(bChangeUsername);
		
		
		
	}
	
	private void setLayerPanel(JPanel p) {
		p.setBounds(0,0,Window.getW(), Window.getH()-100);
		p.setLayout(null);
		p.setBackground(new Color(255, 250, 205));
	}
	
	private void setUpChange(JTextField field,JLabel label) {
		field.setColumns(10);
		field.setVisible(false);
		infoChangePanel.add(field);
		
		label.setForeground(new Color(107, 142, 35));
		label.setFont(new Font("Tahoma", Font.ITALIC, 40));
		infoChangePanel.add(label);
	}
	
	private void bChange(JTextField field,JLabel label) {
		label.setVisible(false);
		field.setVisible(true);
	}
	
	private void click(JTextField textField,JLabel label,String column) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode()==KeyEvent.VK_ENTER) {
					int i;
					if(textField.getText().equals("")) {i = 0;}
					else {
						try {
							Menu.Update("UPDATE Authentication_Login"
									+ " SET "+column+"= '"+textField.getText()+"' WHERE username = '"+LoginFrame.user.getUsername()+"';");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						label.setText(textField.getText());
						LoginFrame.user.setName(textField.getText());
					}
					textField.setVisible(false);
					label.setVisible(true);
				}
			}
		});
	}
	
	private void switchPane(JPanel p) {
		layer.removeAll();
		layer.add(p);
		layer.repaint();
		layer.revalidate();
	}
}
