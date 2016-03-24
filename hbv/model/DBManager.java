package hbv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet res = null;
	
	// Bý til tengingu við sqlite gagnagrunninn
	private static void setUp(){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("JDBC:sqlite:C:\\Users"
					+ "\\Notandi\\Documents\\Eclipse Workspace"
					+ "\\hbv401g-2016v-3D\\src\\HBV.db");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("no class or conn");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	// Loka öllum tengingum
	private static void closeAll(){
		try {
			conn.close();
			//pst.close();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String[][] getTours(String[] where){
		setUp();
		
		Statement stmtTours = null;
		Statement stmtRows = null;
		
		String[][] tours = null;
		
		// Set WHERE skilyrðin upp í streng
		String whereString = "";
		for(int i = 0; i<where.length;i++){
			if(where[i]!=null) whereString += where[i]+" AND ";
		}
		// get rid of last "and"
		whereString = whereString.substring(0, whereString.length()-5);
		// Debug
		System.out.println(whereString);
		String whereTest = "price>0 AND duration>0";
		try {
			// get row count
			stmtRows = conn.createStatement();
			res =stmtRows.executeQuery("SELECT COUNT(*) FROM Tours;");
			
			int rows = Integer.valueOf(res.getString(1));
			//debug
			System.out.println("rows: "+rows);
			
			// get tour data
			stmtTours = conn.createStatement();
			res = stmtTours.executeQuery("SELECT * FROM Tours WHERE "+whereString+";");
	
			// get column count 
			ResultSetMetaData rsmd = res.getMetaData();
			int cols = rsmd.getColumnCount();
			// debug
			System.out.println("columns: "+cols);
			
			// transfer data from res to 2d array tours
			tours = new String[rows][cols];
			int i = 0;
			while(res.next()){
				for(int j = 0; j<cols;j++){
					tours[i][j] = res.getString(j+1);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll();
		}
		return tours;
	}
	
	// TODO klára
	public static String[] getTourReviews(){
		String[] tourReviews = null;
		return tourReviews;
	}
	
	// TODO klára
	public static String[][] getGuides(/* ... */){
		String[][] guides = null;
		return guides;
	}
	
	// TODO klára
	public static String[] getGuideReviews(){
		String[] guideReviews = null;
		return guideReviews;
	}
	
	public static void updateSeats(String tourName,int newSeats){
		setUp();
		
		try {
			pst = conn.prepareStatement("UPDATE Tours SET availableSeats=? "
					+ "WHERE name=?");
			pst.setString(1, String.valueOf(newSeats));
			pst.setString(2, tourName);
			pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{ 
			closeAll();	
		}
	}
	
	public static void updateRating(String tourName, float newRating, int numberOfRatings){
		setUp();
		try {
			pst = conn.prepareStatement("UPDATE Tours SET Rating=? "
					+ "WHERE name=?");
			pst.setString(1, String.valueOf(newRating));
			pst.setString(2, tourName);
			pst.executeQuery();
			
			pst = conn.prepareStatement("UPDATE Tours SET NumberOfRatings=? "
					+ "WHERE name=?");
			pst.setString(1, String.valueOf(numberOfRatings));
			pst.setString(2, tourName);
			pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll();	
		}
	}
}
