import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FoodItem extends JPanel{
	String idFood,name;
	int price,qty=0,y;
	String foodString = null;
	
	static int temp = 0;
	
	static ArrayList<FoodItem> foodList = new ArrayList<>();
	
	
	Button plusButton = new Button("+");
	Button minusButton = new Button("-");

	String path = null;
	ImageIcon icon;
	Image scale;
	
	public FoodItem(String n, int p, int qt) {
		name = n;
		price = p;
		qty = qt;
	}
	
	public FoodItem(String id, String n, int p,int y) {
		this.y = y;
		idFood = id;
		name = n;
		price = p;
		
		plusButton.setBounds(900, 50, 30, 30);
		plusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				qty++;
				for(int i=0; i<foodList.size(); i++) {
					FoodItem t = foodList.get(i);
					if(t.getName().equals(name)) foodList.remove(t);
				}
				foodList.add(new FoodItem(name,price,qty));
				repaint();
			}
		});
		add(plusButton);
		
		minusButton.setBounds(800, 50, 30, 30);
		minusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(qty ==0) return;
				else{
					qty--;
					for(int i=0; i<foodList.size(); i++) {
						FoodItem t = foodList.get(i);
						if(t.getName().equals(name)) foodList.remove(t);
					}
					if(qty!=0) foodList.add(new FoodItem(name,price,qty));
					repaint();
				}
				
			}
		});
		add(minusButton);
		
		setLayout(null);
		setBounds(20,y, Frame.getW() * 3 / 4-50,130);
		setBackground(new Color(255, 250, 205));
	}
	
	public FoodItem(String id, String n, int p) {
		y=temp;
		idFood = id;
		name = n;
		price = p;
		
		plusButton.setBounds(900, 50, 30, 30);
		plusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				qty++;
				for(int i=0; i<foodList.size(); i++) {
					FoodItem t = foodList.get(i);
					if(t.getName().equals(name)) foodList.remove(t);
				}
				foodList.add(new FoodItem(name,price,qty));
				repaint();
			}
		});
		add(plusButton);
		
		minusButton.setBounds(800, 50, 30, 30);
		minusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(qty ==0) return;
				else{
					qty--;
					for(int i=0; i<foodList.size(); i++) {
						FoodItem t = foodList.get(i);
						if(t.getName().equals(name)) foodList.remove(t);
					}
					if(qty!=0) foodList.add(new FoodItem(name,price,qty));
					repaint();
				}
				
			}
		});
		add(minusButton);
		
		setLayout(null);
		setBounds(20,y, Frame.getW() * 3 / 4-50,130);
		setBackground(new Color(255, 250, 205));
		temp+=130;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(path!=null)
		g.drawImage(new ImageIcon(scale).getImage(), 50, 10, null);
		
		g.setColor(new Color(107, 142, 35));
		g.drawRoundRect(25,5, Frame.getW() * 3 / 4-100,123, 70,70);
		
//		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Jokerman",Font.TRUETYPE_FONT,60));
		g.drawString(this.getName(), 150, 70);
		
		g.setFont(new Font("Colonna MT",Font.BOLD,50));
		g.drawString(""+this.getPrice(), 500, 110);
		
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(""+qty, 860, 75);
//		g.drawImage(image,0,0,null);
	}
	
	public static void add(FoodItem f) {
		foodList.add(f);
		System.out.println("foodList after adding");
		for(FoodItem t: foodList) System.out.println(t);
	}
	public static void remove(FoodItem f) {
		foodList.remove(f);
		System.out.println("foodList after removing");
		for(FoodItem t: foodList) System.out.println(t);
	}
	public static ArrayList<FoodItem> getFoodList() {
		return foodList;
	}
	
	public String toString() {
		return "ID:"+this.idFood+" Name:"+this.getName()+" Price:"+this.getPrice();
	}
	
	public String getIdFood() {
		return idFood;
	}
	public void setIdFood(String idFood) {
		this.idFood = idFood;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
//	public void setImage (ImageIcon i) {
//		this.image = i;
//	}
	public String getFoodString() { return foodString;}

	public void setIcon(String path) {
		 this.path = path;
		 icon = new ImageIcon(path);
		 scale = icon.getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH);
	}
	
	public int getQty() {
		return qty;
	}

}
