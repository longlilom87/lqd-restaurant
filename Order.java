package cate_list;

import java.sql.*;

public class Order {
 private int id;
 private String name;
 private String food;
 private int unit;
 private String address;
 private int status;
public Order(int id, String name, String food, int unit, String address, int status) {
	
	this.id = id;
	this.name = name;
	this.food = food;
	this.unit = unit;
	this.address = address;
	this.status = status;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFood() {
	return food;
}
public void setFood(String food) {
	this.food = food;
}
public int getUnit() {
	return unit;
}
public void setUnit(int unit) {
	this.unit = unit;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public static int getRowNumber() {
	int i=0;
	Connection c;
	try {
		
		Class.forName("org.sqlite.JDBC");
		c=DriverManager.getConnection("jdbc:sqlite:order.db");
		String query2= "select max (id) from Delivery";
        Statement st2 = c.createStatement();
		ResultSet rs = st2.executeQuery(query2);
		i= rs.getInt(1);
	}
	catch (Exception e) {
		System.out.println(e);
	}	
	return i;
}
 
}
