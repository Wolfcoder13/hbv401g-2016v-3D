package hbv.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.NoSuchElementException;

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
	private static void setUp() throws SQLException,ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		File database = new File("src\\HBV.db");
		String dbPath = database.getAbsolutePath();
		conn = DriverManager.getConnection("JDBC:sqlite:"+dbPath);
	}
	
	// Loka �llum tengingum
	private static void closeAll(){
		try {
			pst.close();
			res.close();
			conn.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String[][] getData(String columns, String table, HashMap<String,Object> whereParams) throws NoSuchElementException{
		
		String prepWhere = buildWhereString(whereParams);
		String rowCounter = "SELECT COUNT(*) FROM "+table;
		String search = "SELECT "+columns+" FROM "+table;
		// data mun innihalda g�gnin sem f�st �r SQL-fyrirspurninni og ver�ur �� skilagildi fallsins.
		String[][] data = null;
		
		try {
			setUp();
			// S�kjum fj�lda ra�a sem munu koma �t �r SQL-fyrirspurninni.
			pst = conn.prepareStatement(rowCounter+prepWhere.toString());
			bindParams(whereParams);
			res = pst.executeQuery();
			// F�um fj�lda ra�a � leitarni�urst��unum til a� stilla fj�lda ra�a � data fylkinu � eftir.
			int rows = Integer.valueOf(res.getString(1));
			// Ef engar ra�ir eru � res, �� hendum vi� exception � sta�inn fyrir a� skila t�mu fylki.
			if(rows==0) throw new NoSuchElementException("No matching data found.");

			// S�kjum g�gnin sj�lf.			
			pst = conn.prepareStatement(search+prepWhere.toString());
			bindParams(whereParams);
			res = pst.executeQuery();

			// F�um fj�lda d�lka � leitarni�urst��unum til a� geta upphafsstillt data fylki�.
 			ResultSetMetaData rsmd = res.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("cols: "+cols);
			
			// F�rum g�gnin � res yfir � data fylki�.
			data = new String[rows][cols];
			int i = 0;
			while(res.next()){
				for(int j = 0; j<cols;j++){
					data[i][j] = res.getString(j+1);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			closeAll();
		}
		return data;
	}
	
	private static String buildWhereString(HashMap<String,Object> whereParams){
		StringBuilder prepWhere = new StringBuilder(" WHERE ");
		for(String key: whereParams.keySet()){
			prepWhere.append(key+"? AND ");
		}
		prepWhere.delete(prepWhere.length()-5, prepWhere.length());
		prepWhere.append(';');
		
		return prepWhere.toString();
	}
	
	private static void bindParams(HashMap<String,Object> searchParams) throws SQLException{
		int index = 1;
		for(String key: searchParams.keySet()){
			if(key.contains("LIKE")){
				pst.setString(index, "%"+searchParams.get(key)+"%");
			}else{
				pst.setString(index, String.valueOf(searchParams.get(key)));
			}
			index++;
		}
	}

	public static void updateTable(String table, String ColName, String newValue, HashMap<String,Object> whereParams){
		try {
			setUp();
			
			String prepared = "UPDATE "+table+" SET "+ColName+"="+newValue+buildWhereString(whereParams);
			pst = conn.prepareStatement(prepared);
			bindParams(whereParams);
			pst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
