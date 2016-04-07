package hbv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import hbv.view.*;
import hbv.model.*;

// �essi controller klasi inniheldur falli� sem skal pr�fa, �.e. createList().
public class SearchManager {

	private Display view;
	
	private ArrayList<Tour> tours;
	
	public SearchManager(Display view){
		this.view = view;
		this.view.addSearchListener(new searchHandler());
		this.tours = new ArrayList<Tour>();
	}
	
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
	public void createList(){
		
		// B� til lista, searchParams, sem mun geyma leitarskilyr�in fr� notanda.
		ArrayList<String> searchParams = new ArrayList<String>();
		// S�ki leitarskilyr�in fr� viewinu og set � searchParams �.a. �eir passi
		// sem WHERE hlutinn � SQL-fyrirspurn.
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

		// F� g�gnin fr� DBManager mi�a� vi� leitarskilyr�in.
		String[][] dbData = DBManager.getTours(searchParams);
				
		// hreinsa g�mlu leitarni�urst��urnar �r tours (ef einhverjar eru).
		tours.clear();
		// B� til Tour-hluti � samr�mi vi� leitarni�urst��ur og set � listann tours.
		for(int i=0; i<dbData.length;i++){
			tours.add(new Tour(dbData[i][0],dbData[i][1],Integer.valueOf(dbData[i][2]),dbData[i][3],
				Integer.valueOf(dbData[i][4]),Float.valueOf(dbData[i][5]),Integer.valueOf(dbData[i][6]),
				Integer.valueOf(dbData[i][7]),dbData[i][8],dbData[i][9],dbData[i][10]));
		}
	}
	
	// Ver�ur nota� til a� birta tiltekin lykilg�gn �r tours-listanum, sem lista � UI, sem 
	// notandi getur svo smellt � til a� sko�a n�nar.
	public void publishList(){
		// TODO implement �egar UI er tilb�i�.
	}
    
	// Bara fyrir testing.
    public boolean isToursEmpty(){
        return false;
    }
	
	// bara fyrir testing til a� athuga innihald tours.
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

		// B� til instance af mockUI-inu sem controllerinn f�r svo
		// til a� s�kja uppl�singar fr�.
		Display view = new MockDisplay();
		SearchManager controller = new SearchManager(view);
		
		((MockDisplay)view).setVisible(true);
		
	}

}
