package hbv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import hbv.view.*;
import hbv.model.*;

// Þessi controller klasi inniheldur fallið sem skal prófa, þ.e. createList().
public class SearchManager {

	
	private static ArrayList<Tour> tours = new ArrayList<Tour>();
	
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
	// Þetta verður kallað á með search takkanum.
	public static ArrayList<Tour> getSearchList(int priceLower,int priceHigher,int durationLower,int durationHigher,
			Date dateLower, Date dateHigher,int minAvailableSeats,String destination,String departure,
			String type, String name) throws noDataException{
		
		// Bý til lista, searchParams, sem mun geyma leitarskilyrðin frá notanda.
		ArrayList<String> searchParams = new ArrayList<String>();
		// Sæki leitarskilyrðin frá viewinu og set í searchParams þ.a. þeir passi
		// sem WHERE hlutinn í SQL-fyrirspurn.
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
		//Losna við síðasta "AND" í strengnum.
		whereString = whereString.substring(0, whereString.length()-5);
			
		// Bý til lista af Tour hlutum miðað við leitarskilyrðin.
		String[][] dbData = DBManager.getTours("Tours",searchParams);
				
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
	


	
    
	// Bara fyrir testing.
    public static boolean isToursEmpty(){
        return false;
    }
	
	// bara fyrir testing til að athuga innihald tours.
	public static ArrayList<Tour> getTours(){
		return tours;
	}
	
	

	public static void main(String[] args) {

		// Bý til instance af mockUI-inu sem controllerinn fær svo
		// til að sækja upplýsingar frá.
		Display view = new MockDisplay();
		((MockDisplay)view).setVisible(true);

	}

}
