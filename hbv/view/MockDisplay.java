package hbv.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hbv.controller.SearchManager;
import hbv.model.Tour;

// Vi� bjuggum til nokkrar �tg�fur af �essum klasa, sem allir implementa Display,
// fyrir pr�fanir � sta�inn fyrir a� vera me� settera fyrir breyturnar �ar sem 
// okkur fannst klasinn vera or�inn ansi st�r fyrir mockObject.
public class MockDisplay extends JFrame implements Display{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton searchBtn;
	
	private int priceLower;
	private int priceHigher;
	private int durationLower;
	private int durationHigher;
	private Date dateLower;
	private Date dateHigher;
	private String tourType;
	private int minAvailableSeats;
	private String tourName;
	private String destination;
	private String departureLocation;
	private String type;
	
	// B� til display sem hefur bara einn takka.
	// Takkinn var� svo ��arfur, en vi� gleymdum a� taka hann �t
	// �egar vi� vorum b�nir a� �tta okkur � �v� hvernig test-cases virka
	// (Sj� comment hj� ne�sta fallinu).
	public MockDisplay(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		searchBtn = new JButton("Search");
		panel = new JPanel();
		panel.add(searchBtn);
		this.getContentPane().add(panel);
		
		initMockVars();
	}
	
	// stilli prufubreytur sem ver�a nota�ar � pr�fun.
	private void initMockVars(){
		priceLower = 4;
		priceHigher = 100000;
		durationLower = 3;
		durationHigher = 20;
		dateLower = java.sql.Date.valueOf("2016-04-12");
		dateHigher = java.sql.Date.valueOf("2016-10-22");
		tourType = "Adventure";
		minAvailableSeats = 6;
		tourName = "Snow";
		destination = "Vatnajokull";
		departureLocation = "Vik";
		type = "Adventure";
		
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				searchBtnActionPerformed(evt);
			}
		});
	}
	

	private void searchBtnActionPerformed(java.awt.event.ActionEvent evt){
		ArrayList<Tour> names = null;
		try {
			//DEBUGGING STUFF
			names = SearchManager.createList(priceLower, priceHigher, durationLower, durationHigher, 
					dateLower, dateHigher, minAvailableSeats, destination, departureLocation, type, tourName);
			System.out.println("Fyrir: "+names.get(0).getName()+" :: "+names.get(0).getSeatsAvailable());
			SearchManager.bookTourSeats(names.get(0).getName(), 5);
			System.out.println("Eftir: "+names.get(0).getName()+" :: "+names.get(0).getSeatsAvailable());
			
			System.out.println("Fyrir: "+names.get(0).getName()+" :: "+names.get(0).getRating());
			SearchManager.updateRating(names.get(0).getName(), 1);
			System.out.println("Eftir: "+names.get(0).getName()+" :: "+names.get(0).getRating());
			
			for(Tour x: names){
				System.out.println("TourName: "+x.getName());
			}
		} catch (NoSuchElementException e) {
			JOptionPane.showMessageDialog(null, "No search results");
		}
		

	}
	public int getPriceLower() {
		return priceLower;
	}

	public int getPriceHigher() {
		return priceHigher;
	}

	public int getDurationLow() {
		return durationLower;
	}

	public int getDurationHigh() {
		return durationHigher;
	}

	public Date getDateLower(){
		return dateLower;
	}
	public Date getDateHigher(){
		return dateHigher;
	}

	public String getTourType() {
		return tourType;
	}

	public int getMinAvailableSeats() {
		return minAvailableSeats;
	}

	public String getTourName() {
		return tourName;
	}

	public String getDestination() {
		return destination;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}
	
	// �tlu�um a� nota �etta fall � testinu fyrir createList() til a� 
	// starta handlernum sem kallar � createList() � SearchManager klasanum, � 
	// test case-unum ��ur en vi� vorum b�nir a� implementa test-cases.
	// S�um svo a� �etta var ��arfi, og gleymdum a� taka �etta fall burt (Enda
	// h�gt a� sj� a� �etta fall er ekkert nota� � test-cases).
	// �etta � �v� ekkert a� vera h�rna, bara gleymdist, og vi� gleymdum einnig a�
	// taka �t sj�lfan takkann, enda var� hann ��arfur eftir �etta.
	// Hef �etta �� me� h�rna svo �etta s� � samr�mi vi� skilin � sunnudaginn.
	public void pushSearchBtn(){
		searchBtn.doClick();
	}

	
	
}
