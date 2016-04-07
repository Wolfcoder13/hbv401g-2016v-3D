package hbv.view;

import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Sjá comments í MockDisplay klasanum.
public class MockDisplay2 extends JFrame implements Display{

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
	
	public MockDisplay2(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,200);
		
		searchBtn = new JButton("Search");
		panel = new JPanel();
		panel.add(searchBtn);
		this.getContentPane().add(panel);
		
		initMockVars();
	}
    
	// sjá comment í MockDisplay klasanum fyrir fallið pushSearchBtn.
    public void clickButton(){
        searchBtn.doClick();
    }
	
	@SuppressWarnings("deprecation")
	private void initMockVars(){
		priceLower = 4;
		priceHigher = 100000;
		durationLow = 3;
		durationHigh = 2;
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

	
	
}
