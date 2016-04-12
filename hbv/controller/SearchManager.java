package hbv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
	public static ArrayList<Tour> getSearchList(int priceLower,int priceHigher,int durationLower,int durationHigher,
			Date dateLower, Date dateHigher,int minAvailableSeats,String destination,String departure,
			String type, String name) throws noDataException{
		
		// B� til lista, searchParams, sem mun geyma leitarskilyr�in fr� notanda.
		ArrayList<String> searchParams = new ArrayList<String>();
		// S�ki leitarskilyr�in fr� viewinu og set � searchParams �.a. �eir passi
		// sem WHERE hlutinn � SQL-fyrirspurn.
		searchParams.add("price>="+String.valueOf(priceLower));
		//searchParams.add("price<="+String.valueOf(priceHigher));
		//searchParams.add("Duration>="+String.valueOf(durationLower));
		//searchParams.add("Duration<="+String.valueOf(durationHigher));
		//searchParams.add("Date>='"+String.valueOf(dateLower)+"'");
		//searchParams.add("Date<='"+String.valueOf(dateHigher)+"'");
		//searchParams.add("SeatsAvailable>="+String.valueOf(minAvailableSeats));
		//searchParams.add("Destination='"+destination+"'");
		//searchParams.add("Departure='"+departure+"'");
		//searchParams.add("Type='"+type+"'");
		//searchParams.add("Name LIKE '%"+name+("%'"));

		
		String whereString = "WHERE Price>=? AND Price>=? AND Duration>=? AND Duration<=?"
				+ " AND Date>=? AND Date<=? AND SeatsAvailable>=? AND Destination=? AND "
				+ "Departure=? AND Type=? AND NAME LIKE ?;";
		for(String x: searchParams){
			whereString += x+" AND ";
		}
		//Losna vi� s��asta "AND" � strengnum.
		whereString = whereString.substring(0, whereString.length()-5);
			
		// B� til lista af Tour hlutum mi�a� vi� leitarskilyr�in.
		String[][] dbData = DBManager.getTours("Tours",searchParams);
				
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
		((MockDisplay)view).setVisible(true);

	}

}
