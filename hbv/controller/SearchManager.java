package hbv.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.sql.Date;


import hbv.view.*;
import hbv.model.*;

// �essi controller klasi inniheldur falli� sem skal pr�fa, �.e. createList().
public class SearchManager {

	
	private static ArrayList<Tour> tours = new ArrayList<Tour>();
	
	/*
	 * �etta er pr�funarfalli�. 
	 * �ar sem hugsunin h�r var s� a� fylgja MVC-m�delinu algj�rlega, �� var� ni�ursta�an 
	 * s� sem h�r s�st. view-i� �tti �� a� vera alveg �h�� controllernum (�essum klasa) 
	 * og view-i� myndi �v� bara geyma uppl�singar fr� notanda �n �ess a� me�h�ndla ��r
	 * uppl�singar � nokkurn h�tt. �essi klasi �tti svo a� s�kja leitarskilyr�in fr� 
	 * viewinu til a� geta svo s�tt tilsvarandi g�gn �r DBManager. �etta er verk createList() 
	 * og �v� �urftum vi� a� �tf�ra gettera fyrir breyturnar � mockDisplayinu til a� geta
	 * pr�fa� �etta fall r�tt.
	 * 
	 * Vi� munum hins vegar breyta �essu �.a. UI-i� s�r um handlers fyrir g�gnin sj�lf, eins
	 * og vi� h�f�um �etta upphaflega ��ur en vi� f�rum a� kynna okkur MVC almennilega.
	 * Vi� g�tum �� ekki breytt �essu n�na �ar sem �a� v�ri ekki � samr�mi vi� skilin eins
	 * og �au voru � sunnudag.
	 */
	// �etta ver�ur kalla� � me� search takkanum.
	public static ArrayList<Tour> createList(int priceLower,int priceHigher,int durationLower,int durationHigher,
			Date dateLower, Date dateHigher,int minAvailableSeats,String destination,String departure,
			String type, String name) throws NoSuchElementException{
		
		// B� til lista, searchParams, sem mun geyma leitarskilyr�in fr� notanda.
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		searchParams.put("Price>=", priceLower);
		searchParams.put("Price<=", priceHigher);
		searchParams.put("Duration>=", durationLower);
		searchParams.put("Duration<=", durationHigher);
		searchParams.put("Date>=", dateLower);
		searchParams.put("Date<=", dateHigher);
		searchParams.put("SeatsAvailable>=", minAvailableSeats);
		searchParams.put("Destination=", destination);
		searchParams.put("Departure=", departure);
		searchParams.put("Type=", type);
		searchParams.put("Name LIKE ", name);
		
		// B� til lista af Tour hlutum mi�a� vi� leitarskilyr�in.
		String[][] dbData = DBManager.getData("*","Tours",searchParams);
		
		// hreinsa g�mlu leitarni�urst��urnar �r tours (ef einhverjar eru).
		tours.clear();
		// B� til Tour-hluti � samr�mi vi� leitarni�urst��ur og set � listann tours.
		for(int i=0; i<dbData.length;i++){
			tours.add(new Tour(dbData[i][0],dbData[i][1],Integer.valueOf(dbData[i][2]),dbData[i][3],
				Integer.valueOf(dbData[i][4]),Float.valueOf(dbData[i][5]),Integer.valueOf(dbData[i][6]),
				Integer.valueOf(dbData[i][7]),dbData[i][8],dbData[i][9],dbData[i][10]));
		}
		
		return tours;
		
	}
	

	public static void bookTourSeats(String tourName, int bookedSeats) throws NoSuchElementException, IllegalArgumentException{

		// Byrjum a� athuga n�verandi s�tafj�lda.
		HashMap<String,Object> whereParams = new HashMap<String,Object>();
		whereParams.put("Name=", tourName);
		String[][] seatData = DBManager.getData("SeatsAvailable", "Tours", whereParams);
		int seats = Integer.parseInt(seatData[0][0]);
		if(bookedSeats<0) throw new IllegalArgumentException("Second input parameter must be positive.");
		
		// L�kkum s�tafj�ldann til a� t�kna a� b�kun hafi �tt s�r sta� a� �v� gefnu a� n�gilegt s�tamagn s� � bo�i.
		if(seats>bookedSeats){
			DBManager.updateTable("Tours", "SeatsAvailable", String.valueOf(seats-bookedSeats), whereParams);
		} else throw new IllegalArgumentException("Too few seats available.");
		
		// Ef a� t�rinn er � n�verandi tours lista, update-um vi� hann l�ka.
		for(Tour tour: tours){
			if(tour.getName()==tourName){
				tour.bookSeats(bookedSeats);
			}
		}
	}
	
	public static void updateRating(String tourName, int newRating) throws NoSuchElementException, IllegalArgumentException{

		// S�kjum fj�lda einkunnagjafa og n�verandi einkunn.
		HashMap<String,Object> whereParams = new HashMap<String,Object>();
		whereParams.put("Name=", tourName);
		String[][] RatingData = DBManager.getData("NumberOfRatings, Rating", "Tours", whereParams);
		int amountOfRatings = Integer.parseInt(RatingData[0][0]);
		float oldRating = Float.parseFloat(RatingData[0][1]);
		
		// Reiknum �t n�ju einkunnina
		int expanded = (int)(oldRating*amountOfRatings)+newRating;
		amountOfRatings++;
		float rating = (float)expanded/amountOfRatings;
		
		// Skrifum fj�lda einkunna �samt n�ju einkunninni � gagnagrunninn.
		DBManager.updateTable("Tours", "NumberOfRatings", String.valueOf(amountOfRatings), whereParams);
		DBManager.updateTable("Tours", "Rating", String.valueOf(rating), whereParams);
		
		// Uppf�rum t�rinn ef hann er � n�verandi lista.
		for(Tour tour: tours){
			if(tour.getName()==tourName){
				tour.updateRating(rating, amountOfRatings);
			}
		}
	}

	
    
	// Bara fyrir testing.
    public static boolean isToursEmpty(){
        return false;
    }
	
	// bara fyrir testing til a� athuga innihald tours.
	public static ArrayList<Tour> getTours(){
		return tours;
	}
	
	

	public static void main(String[] args) {

		// B� til instance af mockUI-inu sem controllerinn f�r svo
		// til a� s�kja uppl�singar fr�.
		Display view = new MockDisplay();
		((MockDisplay)view).setLocationRelativeTo(null);
		((MockDisplay)view).setVisible(true);

	}

}
