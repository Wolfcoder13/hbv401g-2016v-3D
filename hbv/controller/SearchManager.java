package hbv.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.ArrayList;
import hbv.model.*;

public class SearchManager {

	private static ArrayList<Tour> tours = new ArrayList<Tour>();
	
	public static void createList(int priceLow,int priceHigh,int durationLow,int durationHigh,
			String dateLower,String dateUpper,String type,int minAvailableSeats, String tourName,
			String location){
				
		String[] where = new String[10]; // skilyrðin fyrir leit
		where[0] = "price>"+priceLow; 
		if(priceHigh!=0) where[1] = "price<"+priceHigh; // check if value was chosen
		
		where[2] = "duration>"+durationLow;
		if(durationHigh!=0)	where[3] = "duration<"+durationHigh; 

		//where[4] = "Date>"+dateLower; // TODO must read up on dates, using strings..
		//where[5] = "Date<"+dateUpper; // TODO -||-
		if(!type.equals("")) where[6] = "Type="+type; // Fyrirfram ákveðnir strengir TODO en ef margir?

		if(minAvailableSeats!=0) where[7] = "AvailableSeats>="+minAvailableSeats;

		if(!location.equals("")) where[8] = "Location="+location;
		
		if(!tourName.equals("")) where[9] = "Name LIKE %"+tourName+"%";
		
		String[][] data = DBManager.getTours(where);
		
		/* Debug
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[0].length;j++){
				System.out.println(data[i][j]);
			}		
		}*/

		for(int i=0; i<data.length;i++){
			tours.add(new Tour(data[i][0],data[i][1],Integer.valueOf(data[i][2]),data[i][3],
					Integer.valueOf(data[i][4]),Float.valueOf(data[i][5]),Integer.valueOf(data[i][6]),
					Integer.valueOf(data[i][7])));
		}
	}
	
	// Bara til að testa
	public static void main(String[] args) {
		createList(0,0,0,0,"","","",0,"","");
		System.out.println(tours.get(0).getName());
	}

}
