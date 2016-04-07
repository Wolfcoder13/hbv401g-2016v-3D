package hbv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/* �essi class er ekki mockObject �ar sem vi� vorum byrja�ir
   � honum fyrir. Okkur fannst �v� ��arfi a� a� vera gera annan
   klasa �ar sem �essi var alveg tilb�inn til notkunar fyrir �etta 
   verkefni.

   Eina falli� � klasanum sem skiptir m�li fyrir verkefni�
   er � raun getTours().
*/
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
					+ "\\hbv401g-2016v-3D\\src\\HBV.db");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("no class or conn");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	// Loka �llum tengingum
	private static void closeAll(){
		try {
			conn.close();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// �etta er eina falli� sem skiptir m�li fyrir �essi skil.
	public static String[][] getTours(ArrayList<String> where){
		setUp();
		
		Statement stmtTours = null;
		Statement stmtRows = null;
		// Fylli tours � eftir me� g�gnunum � resultsettinu sem f�st �r SQL-fyrirspurninni.
		String[][] tours = null;
		
		// Set WHERE skilyr�in upp � streng.
		String whereString = "";
		for(String x: where){
			whereString += x+" AND ";
		}

		// Losna vi� s��asta "AND" � strengnum.
		whereString = whereString.substring(0, whereString.length()-5);

		try {
			// F� fj�lda ra�a sem munu koma �t �r SQL-fyrirspurninni.
			stmtRows = conn.createStatement();
			res =stmtRows.executeQuery("SELECT COUNT(*) FROM Tours WHERE "+whereString+";");		
			int rows = Integer.valueOf(res.getString(1));

			// F� g�gnin sj�lf.
			stmtTours = conn.createStatement();
			res = stmtTours.executeQuery("SELECT * FROM Tours WHERE "+whereString+";");
	
			// F� fj�lda d�lka � leitarni�urst��unum.
 			ResultSetMetaData rsmd = res.getMetaData();
			int cols = rsmd.getColumnCount();	
			
			// �urfti a� vita v�ddirnar � resultSet til a� geta upphafsstillt tours h�r.
			tours = new String[rows][cols];
			// F�ri svo g�gnin � �h��a 2D fylki� tours, sem ver�ur skilagildi fallsins.
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
	
	// TODO kl�ra
	public static String[] getTourReviews(){
		String[] tourReviews = null;
		return tourReviews;
	}
	
	// TODO kl�ra
	public static String[][] getGuides(){
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
			pst = conn.prepareStatement("UPDATE Tours SET SeatsAvailable=? "
					+ "WHERE name=?");
			pst.setString(1, String.valueOf(newSeats));
			pst.setString(2, tourName);
			pst.execute();
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
			pst.execute();
			
			pst = conn.prepareStatement("UPDATE Tours SET NumberOfRatings=? "
					+ "WHERE name=?");
			pst.setString(1, String.valueOf(numberOfRatings));
			pst.setString(2, tourName);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll();	
		}
	}
}
