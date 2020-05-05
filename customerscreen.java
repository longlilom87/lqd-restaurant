import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class customerscreen extends JPanel{
	
	private JPanel barPanel = new JPanel();
	private Button bLogout,bInfo,bOrder;
	
	public customerscreen() {
		setBounds(0,0,Window.getW(),Window.getH());
		setLayout(null);
		setBackground(new Color(255, 250, 205));
		
		barPanel.setBounds(0, 0, Window.getW(), 100);
		barPanel.setLayout(null);
		barPanel.setBackground(new Color(107, 142, 35));
		add(barPanel);
		
		bLogout= new Button("Log out");
		bLogout.setBounds(1000, 0, 100, 100);
		bLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.switchPane(new Welcome());
			}
		});
		barPanel.add(bLogout);
	}
}
