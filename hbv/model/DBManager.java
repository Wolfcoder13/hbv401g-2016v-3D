package hbv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBManager {

	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet res = null;
	
	// B� til tengingu vi� sqlite gagnagrunninn
	private static void setUp(){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("JDBC:sqlite:C:\\Users"
					+ "\\Notandi\\Documents\\Eclipse Workspace"
					+ "\\HBV_DayTours\\src\\HBV.db");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	// Loka �llum tengingum
	private static void closeAll(){
		try {
			conn.close();
			pst.close();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String[][] getTours(String[] where){
		setUp();
		String[][] tours = null;
		
		// Set WHERE skilyr�in upp � streng
		String whereString = "";
		for(int i = 0; i<where.length;i++){
			if(i==where.length-1) whereString += where[i];
			else whereString += where[i]+" and ";
		}
		try {
			// F� fj�lda ra�a sem ver�a � res
			pst = conn.prepareStatement("SELECT COUNT(*) FROM Tours;");
			res = pst.executeQuery();
			int rows = Integer.valueOf(res.getString(1));	
			
			// S�kja tours g�gnin sj�lf
			pst = conn.prepareStatement("SELECT * FROM Tours WHERE ?;");
			pst.setString(1, whereString);
			res = pst.executeQuery();
			
			// F� fj�lda d�lka � res
			ResultSetMetaData rsmd = res.getMetaData();
			int cols = rsmd.getColumnCount();
			// F�ri g�gnin �r res � tv�v�tt fylki til a� skila (v�ri 
			// m�gulega skynsamlegast a� b�a bara strax til tours 
			// listann h�r �r res g�gnunum og skila honum bara).
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
		}
		closeAll();
		return tours;
	}
	
	// TODO kl�ra
	public static String[] getTourReviews(){
		String[] tourReviews = null;
		return tourReviews;
	}
	
	// TODO kl�ra
	public static String[][] getGuides(/* ... */){
		String[][] guides = null;
		return guides;
	}
	
	// TODO kl�ra
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
		}
		closeAll();	
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
		}
		closeAll();	
	}
}
