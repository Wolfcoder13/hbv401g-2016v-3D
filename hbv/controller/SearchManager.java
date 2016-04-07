package hbv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import hbv.view.*;
import hbv.model.*;

// Þessi controller klasi inniheldur fallið sem skal prófa, þ.e. createList().
public class SearchManager {

	private Display view;
	
	private ArrayList<Tour> tours;
	
	public SearchManager(Display view){
		this.view = view;
		this.view.addSearchListener(new searchHandler());
		this.tours = new ArrayList<Tour>();
	}
	
	/*
	 * Þetta er prófunarfallið. 
	 * Þar sem hugsunin hér var sú að fylgja MVC-módelinu algjörlega, þá varð niðurstaðan 
	 * sú sem hér sést. view-ið ætti þá að vera alveg óháð controllernum (þessum klasa) 
	 * og view-ið myndi því bara geyma upplýsingar frá notanda án þess að meðhöndla þær
	 * upplýsingar á nokkurn hátt. Þessi klasi ætti svo að sækja leitarskilyrðin frá 
	 * viewinu til að geta svo sótt tilsvarandi gögn úr DBManager. Þetta er verk createList() 
	 * og því þurftum við að útfæra gettera fyrir breyturnar í mockDisplayinu til að geta
	 * prófað þetta fall rétt.
	 * 
	 * Við munum hins vegar breyta þessu þ.a. UI-ið sér um handlers fyrir gögnin sjálf, eins
	 * og við höfðum þetta upphaflega áður en við fórum að kynna okkur MVC almennilega.
	 * Við gátum þó ekki breytt þessu núna þar sem það væri ekki í samræmi við skilin eins
	 * og þau voru á sunnudag.
	 */
	public void createList(){
		
		// Bý til lista, searchParams, sem mun geyma leitarskilyrðin frá notanda.
		ArrayList<String> searchParams = new ArrayList<String>();
		// Sæki leitarskilyrðin frá viewinu og set í searchParams þ.a. þeir passi
		// sem WHERE hlutinn í SQL-fyrirspurn.
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

		// Fæ gögnin frá DBManager miðað við leitarskilyrðin.
		String[][] dbData = DBManager.getTours(searchParams);
				
		// hreinsa gömlu leitarniðurstöðurnar úr tours (ef einhverjar eru).
		tours.clear();
		// Bý til Tour-hluti í samræmi við leitarniðurstöður og set í listann tours.
		for(int i=0; i<dbData.length;i++){
			tours.add(new Tour(dbData[i][0],dbData[i][1],Integer.valueOf(dbData[i][2]),dbData[i][3],
				Integer.valueOf(dbData[i][4]),Float.valueOf(dbData[i][5]),Integer.valueOf(dbData[i][6]),
				Integer.valueOf(dbData[i][7]),dbData[i][8],dbData[i][9],dbData[i][10]));
		}
	}
	
	// Verður notað til að birta tiltekin lykilgögn úr tours-listanum, sem lista á UI, sem 
	// notandi getur svo smellt á til að skoða nánar.
	public void publishList(){
		// TODO implement þegar UI er tilbúið.
	}
    
	// Bara fyrir testing.
    public boolean isToursEmpty(){
        return false;
    }
	
	// bara fyrir testing til að athuga innihald tours.
	public ArrayList<Tour> getTours(){
		return this.tours;
	}
	
	// Handler fyrir search takkann.
	class searchHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			createList();
			publishList();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// Bý til instance af mockUI-inu sem controllerinn fær svo
		// til að sækja upplýsingar frá.
		Display view = new MockDisplay();
		SearchManager controller = new SearchManager(view);
		
		((MockDisplay)view).setVisible(true);
		
	}

}
