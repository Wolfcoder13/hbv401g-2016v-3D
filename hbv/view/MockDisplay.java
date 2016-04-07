package hbv.view;

import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Vi� bjuggum til nokkrar �tg�fur af �essum klasa, sem allir implementa Display,
// fyrir pr�fanir � sta�inn fyrir a� vera me� settera fyrir breyturnar �ar sem 
// okkur fannst klasinn vera or�inn ansi st�r fyrir mockObject.
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
	
	// B� til display sem hefur bara einn takka.
	// Takkinn var� svo ��arfur, en vi� gleymdum a� taka hann �t
	// �egar vi� vorum b�nir a� �tta okkur � �v� hvernig test-cases virka
	// (Sj� comment hj� ne�sta fallinu).
	public MockDisplay(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,200);
		
		searchBtn = new JButton("Search");
		panel = new JPanel();
		panel.add(searchBtn);
		this.getContentPane().add(panel);
		
		initMockVars();
	}
	
	// stilli prufubreytur sem ver�a nota�ar � pr�fun.
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
	
	// �tlu�um a� nota �etta fall � testinu fyrir createList() til a� 
	// starta handlernum sem kallar � createList() � SearchManager klasanum, � 
	// test case-unum ��ur en vi� vorum b�nir a� implementa test-cases.
	// S�um svo a� �etta var ��arfi, og gleymdum a� taka �etta fall burt (Enda
	// h�gt a� sj� a� �etta fall er ekkert nota� � test-cases).
	// �etta � �v� ekkert a� vera h�rna, bara gleymdist, og vi� gleymdum einnig a�
	// taka �t sj�lfan takkann, enda var� hann ��arfur eftir �etta.
	public void pushSearchBtn(){
		searchBtn.doClick();
	}

	
	
}
