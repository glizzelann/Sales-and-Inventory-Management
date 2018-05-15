import java.sql.*;

import javax.swing.JOptionPane;


public class PointOfSales {
	
	private static PointOfSales instance = new PointOfSales();
	public static final String URL = "jdbc:mysql://localhost:3306/admin";
	public static final String SSL = "AutoReconnect = true & SSL = false";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	private PointOfSales(){
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Connection connectDB(){
		Connection con=null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin?AutoReconnect=true&useSSL=false", USER, PASSWORD);
			//System.out.println("Connected");
		}catch (Exception io){
			JOptionPane.showMessageDialog(null, io);
			io.printStackTrace();
		}
		return con;
	}
	public static Connection getConnect(){
		return instance.connectDB();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getConnect();

	}

}
