package hbv.model;

import java.util.Locale;

/* Þessi class er ekki mockObject þar sem við vorum byrjaðir
   á honum fyrir. Okkur fannst því óþarfi að að vera gera annan
   klasa þar sem þessi var alveg tilbúinn til notkunar fyrir þetta 
   verkefni.

   Eina fallið í klasanum sem skiptir máli fyrir verkefnið
   er í raun constructorinn, enda gerir createList ekkert við
   Tour hlutina nema búa þá til.
*/
// TODO implement comparable
public class Tour{
	private String name;
	private String description;
	private int seatsAvailable;
	private String date;
	private int price;
	private int duration;
	private String destination;
	private float rating;
	private int numberOfRatings;
	private String departureLocation;
	private String type;
	// private List<Guide> guides;
	// private List<TourReview> reviews;
	
	// TODO taka af comments
	public Tour(String name, String description, int seatsAvailable, String date,
			int duration, float rating, int numberOfRatings, int price, String destination, 
			String departureLocation, String type){
		this.name = name;
		this.description = description;
		this.seatsAvailable = seatsAvailable;
		this.date = date;
		this.duration = duration;
		this.rating = rating;
		this.numberOfRatings = numberOfRatings;
		this.price = price;
		this.departureLocation = departureLocation;
		this.type = type;
		this.destination = destination;
		
		// guides = null;
		// reviews = null;
	}
	
	public void setGuides(){
		// TODO implement þegar Guide class er tilbúinn.
	}
	
	// TODO implement þegar Guide class er tilbúinn.
	/*public List<Guide> getGuides(){
		return this.guides;
	}
	*/
	
	public void setReviews(){
		// TODO implement þegar review class er tilbúinn.
	}
	
	// TODO þegar Reviews stuffið er allt komið
	/*public List<TourReview> void getReviews(){
	     return this.reviews;
	}
	*/

	
	public void bookSeats(int bookedSeats){
		this.seatsAvailable -= bookedSeats;
	}
	
	public void updateRating(float rating, int numberOfRatings){
		this.numberOfRatings = numberOfRatings;
		this.rating = rating;
		
	}
	
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	public int getSeatsAvailable(){
		return this.seatsAvailable;
	}
	public int getPrice(){
		return this.price;
	}
	public String getDate(){
		return this.date;
	}
	public float getRating(){
		String tmp = String.format(Locale.ENGLISH,"%.1f", this.rating);
		return Float.parseFloat(tmp);
	}
	public String getLocation(){
		return this.destination;
	}
	public int getDuration(){
		return this.duration;
	}
	
	public int getNumberOfRatings(){
		return this.numberOfRatings;
	}
	
	public String getDestination(){
		return this.destination;
	}
	
	public String getDepartureLocation(){
		return this.departureLocation;
	}

	public String getType() {
		return this.type;
	}
		
	
	/*
	@Override
	public int compareTo(Object arg0) {
		// TODO unimplemented
		return 0;
	}*/
	
}