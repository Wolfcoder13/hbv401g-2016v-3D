package hbv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import hbv.view.*;
import hbv.model.*;

public class SearchManager {

	private Display view;
	
	private ArrayList<Tour> tours;
	
	public SearchManager(Display view){
		this.view = view;
		this.view.addSearchListener(new searchHandler());
		this.tours = new ArrayList<Tour>();
	}
	
	public void createList(){
		
		ArrayList<String> searchParams = new ArrayList<String>();
		
		searchParams.add("price>="+String.valueOf(this.view.getPriceLower()));
		searchParams.add("price<="+String.valueOf(this.view.getPriceHigher()));
		searchParams.add("Duration>="+String.valueOf(this.view.getDurationLow()));
		searchParams.add("Duration<="+String.valueOf(this.view.getDurationHigh()));
		searchParams.add("Date>='"+String.valueOf(this.view.getDateLower())+"'");
		searchParams.add("Date<='"+String.valueOf(this.view.getDateHigher())+"'");
		searchParams.add("SeatsAvailable>="+String.valueOf(this.view.getMinAvailableSeats()));
		searchParams.add("Destination='"+(this.view.getDestination()+"'"));
		searchParams.add("Departure='"+(this.view.getDepartureLocation()+"'"));
		searchParams.add("Type='"+(this.view.getTourType()+"'"));
		searchParams.add("Name LIKE '%"+(this.view.getTourName()+("%'")));

		
		String[][] dbData = DBManager.getTours(searchParams);
		
		// debug: gögnin frá DBManager
		for(int i=0;i<dbData.length;i++){
			for(int j=0;j<dbData[0].length;j++){
				System.out.println(dbData[i][j]);
			}
		}
		
		tours.clear(); 
		// Búa til lista af tours
		for(int i=0; i<dbData.length;i++){
			tours.add(new Tour(dbData[i][0],dbData[i][1],Integer.valueOf(dbData[i][2]),dbData[i][3],
				Integer.valueOf(dbData[i][4]),Float.valueOf(dbData[i][5]),Integer.valueOf(dbData[i][6]),
				Integer.valueOf(dbData[i][7]),dbData[i][8],dbData[i][9],dbData[i][10]));
		}
	}
	
	public void publishList(){
		// TODO implement
	}
	
	public ArrayList<Tour> getTours(){
		return this.tours;
	}
	
	class searchHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			createList();
			publishList();
		}
	}
		
	// Bara til að testa
	public static void main(String[] args) {

		Display view = new MockDisplay();
		SearchManager controller = new SearchManager(view);
		
		((MockDisplay)view).setVisible(true);
		
	}

}
