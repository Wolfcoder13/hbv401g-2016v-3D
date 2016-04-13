package hbv.view;


import java.sql.Date;

public interface Display {

	public int getPriceLower();
	public int getPriceHigher();
	public int getDurationLow();
	public int getDurationHigh();
	public Date getDateLower();
	public Date getDateHigher();
	public String getTourType();
	public int getMinAvailableSeats();
	public String getTourName();
	public String getDestination();
	public String getDepartureLocation();
	
	
}
