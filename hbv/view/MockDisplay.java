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

// Við bjuggum til nokkrar útgáfur af þessum klasa, sem allir implementa Display,
// fyrir prófanir í staðinn fyrir að vera með settera fyrir breyturnar þar sem 
// okkur fannst klasinn vera orðinn ansi stór fyrir mockObject.
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
	
	// Bý til display sem hefur bara einn takka.
	// Takkinn varð svo óþarfur, en við gleymdum að taka hann út
	// þegar við vorum búnir að átta okkur á því hvernig test-cases virka
	// (Sjá comment hjá neðsta fallinu).
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
	
	// stilli prufubreytur sem verða notaðar í prófun.
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
	
	// Ætluðum að nota þetta fall í testinu fyrir createList() til að 
	// starta handlernum sem kallar á createList() í SearchManager klasanum, í 
	// test case-unum áður en við vorum búnir að implementa test-cases.
	// Sáum svo að þetta var óþarfi, og gleymdum að taka þetta fall burt (Enda
	// hægt að sjá að þetta fall er ekkert notað í test-cases).
	// Þetta á því ekkert að vera hérna, bara gleymdist, og við gleymdum einnig að
	// taka út sjálfan takkann, enda varð hann óþarfur eftir þetta.
	// Hef þetta þó með hérna svo þetta sé í samræmi við skilin á sunnudaginn.
	public void pushSearchBtn(){
		searchBtn.doClick();
	}

	
	
}
