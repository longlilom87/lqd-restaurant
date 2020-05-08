import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class customerscreen extends JPanel{
	
	private JPanel barPanel = new JPanel();
	private JPanel barPanel2 = new JPanel();
	private JPanel infoPanel, historyPanel, infoChangePanel;
	
	
	private Button bLogout,bInfo,bOrder;
	private JLayeredPane layer = new JLayeredPane();
	
	public customerscreen() {
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
				Window.switchPane(new Welcome());
			}
		});
		barPanel2.add(bLogout);
		
		bInfo = new Button("Your informatio");
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
		setLayerPanel(infoPanel);
		
		
	}
	
	private void setLayerPanel(JPanel p) {
		p.setBounds(0,0,Window.getW(), Window.getH()-100);
		p.setLayout(null);
	}
	
	private void switchPane(JPanel p) {
		layer.removeAll();
		layer.add(p);
		layer.repaint();
		layer.revalidate();
	}
}
