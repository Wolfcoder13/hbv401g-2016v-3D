package hbv.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.sql.Date;


import hbv.view.*;
import hbv.model.*;

public class SearchManager {

	
	private static ArrayList<Tour> tours = new ArrayList<Tour>();
	
	public static ArrayList<Tour> createList(int priceLower,int priceHigher,int durationLower,int durationHigher,
			Date dateLower, Date dateHigher,int minAvailableSeats,String destination,String departure,
			String type, String name) throws NoSuchElementException{
		
		// Bý til lista, searchParams, sem mun geyma leitarskilyrðin frá notanda.
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
		
		// Bý til lista af Tour hlutum miðað við leitarskilyrðin.
		String[][] dbData = DBManager.getData("*","Tours",searchParams);
		
		// hreinsa gömlu leitarniðurstöðurnar úr tours (ef einhverjar eru).
		tours.clear();
		// Bý til Tour-hluti í samræmi við leitarniðurstöður og set í listann tours.
		for(int i=0; i<dbData.length;i++){
			tours.add(new Tour(dbData[i][0],dbData[i][1],Integer.valueOf(dbData[i][2]),dbData[i][3],
				Integer.valueOf(dbData[i][4]),Float.valueOf(dbData[i][5]),Integer.valueOf(dbData[i][6]),
				Integer.valueOf(dbData[i][7]),dbData[i][8],dbData[i][9],dbData[i][10]));
		}
		return tours;
	}
	

	public static void bookTourSeats(String tourName, int bookedSeats) throws NoSuchElementException, IllegalArgumentException{

		// Byrjum að athuga núverandi sætafjölda.
		HashMap<String,Object> whereParams = new HashMap<String,Object>();
		whereParams.put("Name=", tourName);
		String[][] seatData = DBManager.getData("SeatsAvailable", "Tours", whereParams);
		int seats = Integer.parseInt(seatData[0][0]);
		if(bookedSeats<0) throw new IllegalArgumentException("Second input parameter must be positive.");
		
		// Lækkum sætafjöldann til að tákna að bókun hafi átt sér stað að því gefnu að nægilegt sætamagn sé í boði.
		if(seats>bookedSeats){
			DBManager.updateTable("Tours", "SeatsAvailable", String.valueOf(seats-bookedSeats), whereParams);
		} else throw new IllegalArgumentException("Too few seats available.");
		
		// Ef að túrinn er í núverandi tours lista, update-um við hann líka.
		for(Tour tour: tours){
			if(tour.getName()==tourName){
				tour.bookSeats(bookedSeats);
			}
		}
	}
	
	public static void updateRating(String tourName, int newRating) throws NoSuchElementException, IllegalArgumentException{

		// Sækjum fjölda einkunnagjafa og núverandi einkunn.
		HashMap<String,Object> whereParams = new HashMap<String,Object>();
		whereParams.put("Name=", tourName);
		String[][] RatingData = DBManager.getData("NumberOfRatings, Rating", "Tours", whereParams);
		int amountOfRatings = Integer.parseInt(RatingData[0][0]);
		float oldRating = Float.parseFloat(RatingData[0][1]);
		
		// Reiknum út nýju einkunnina
		int expanded = (int)(oldRating*amountOfRatings)+newRating;
		amountOfRatings++;
		float rating = (float)expanded/amountOfRatings;
		
		// Skrifum fjölda einkunna ásamt nýju einkunninni í gagnagrunninn.
		DBManager.updateTable("Tours", "NumberOfRatings", String.valueOf(amountOfRatings), whereParams);
		DBManager.updateTable("Tours", "Rating", String.valueOf(rating), whereParams);
		
		// Uppfærum túrinn ef hann er í núverandi lista.
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
	
	// bara fyrir testing til að athuga innihald tours.
	public static ArrayList<Tour> getTours(){
		return tours;
	}
	
	

	public static void main(String[] args) {

		// TODO main fara eitthvert annað, líklega display. controllerinn á ekki að eiga eintak af view.
		Display view = new MockDisplay();
		((MockDisplay)view).setVisible(true);

	}

}
