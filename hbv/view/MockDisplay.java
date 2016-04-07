package hbv.view;

import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Við bjuggum til nokkrar útgáfur af þessum klasa, sem allir implementa Display,
// fyrir prófanir í staðinn fyrir að vera með settera fyrir breyturnar þar sem 
// okkur fannst klasinn vera orðinn ansi stór fyrir mockObject.
public class MockDisplay extends JFrame implements Display{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton searchBtn;
	
	private int priceLower;
	private int priceHigher;
	private int durationLow;
	private int durationHigh;
	private Date dateLower;
	private Date dateHigher;
	private String tourType;
	private int minAvailableSeats;
	private String tourName;
	private String destination;
	private String departureLocation;
	
	// Bý til display sem hefur bara einn takka.
	// Takkinn varð svo óþarfur, en við gleymdum að taka hann út
	// þegar við vorum búnir að átta okkur á því hvernig test-cases virka
	// (Sjá comment hjá neðsta fallinu).
	public MockDisplay(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,200);
		
		searchBtn = new JButton("Search");
		panel = new JPanel();
		panel.add(searchBtn);
		this.getContentPane().add(panel);
		
		initMockVars();
	}
	
	// stilli prufubreytur sem verða notaðar í prófun.
	@SuppressWarnings("deprecation")
	private void initMockVars(){
		priceLower = 4;
		priceHigher = 100000;
		durationLow = 3;
		durationHigh = 20;
		dateLower = new Date(1); 
		dateHigher = new Date(2017,5,5);
		tourType = "Adventure";
		minAvailableSeats = 6;
		tourName = "Snow";
		destination = "Vatnajokull";
		departureLocation = "Vik";
	}
	
	public void addSearchListener(ActionListener action){
		searchBtn.addActionListener(action);
	}

	public int getPriceLower() {
		return priceLower;
	}

	public int getPriceHigher() {
		return priceHigher;
	}

	public int getDurationLow() {
		return durationLow;
	}

	public int getDurationHigh() {
		return durationHigh;
	}

	public Date getDateLower() {
		return dateLower;
	}

	public Date getDateHigher() {
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
	public void pushSearchBtn(){
		searchBtn.doClick();
	}

	
	
}
