package hbv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/* Þessi class er ekki mockObject þar sem við vorum byrjaðir
   á honum fyrir. Okkur fannst því óþarfi að að vera gera annan
   klasa þar sem þessi var alveg tilbúinn til notkunar fyrir þetta 
   verkefni.

   Eina fallið í klasanum sem skiptir máli fyrir verkefnið
   er í raun getTours().
*/
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
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Þetta er eina fallið sem skiptir máli fyrir þessi skil.
	public static String[][] getTours(ArrayList<String> where){
		setUp();
		
		Statement stmtTours = null;
		Statement stmtRows = null;
		// Fylli tours á eftir með gögnunum í resultsettinu sem fæst úr SQL-fyrirspurninni.
		String[][] tours = null;
		
		// Set WHERE skilyrðin upp í streng.
		String whereString = "";
		for(String x: where){
			whereString += x+" AND ";
		}

		// Losna við síðasta "AND" í strengnum.
		whereString = whereString.substring(0, whereString.length()-5);

		try {
			// Fæ fjölda raða sem munu koma út úr SQL-fyrirspurninni.
			stmtRows = conn.createStatement();
			res =stmtRows.executeQuery("SELECT COUNT(*) FROM Tours WHERE "+whereString+";");		
			int rows = Integer.valueOf(res.getString(1));

			// Fæ gögnin sjálf.
			stmtTours = conn.createStatement();
			res = stmtTours.executeQuery("SELECT * FROM Tours WHERE "+whereString+";");
	
			// Fæ fjölda dálka í leitarniðurstöðunum.
 			ResultSetMetaData rsmd = res.getMetaData();
			int cols = rsmd.getColumnCount();	
			
			// Þurfti að vita víddirnar á resultSet til að geta upphafsstillt tours hér.
			tours = new String[rows][cols];
			// Færi svo gögnin í óháða 2D fylkið tours, sem verður skilagildi fallsins.
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
	public static String[][] getGuides(){
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
