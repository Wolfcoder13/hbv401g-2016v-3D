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

	
	public boolean bookSeats(int bookedSeats){
		if(this.seatsAvailable>=bookedSeats){
			this.seatsAvailable -= bookedSeats;
			DBManager.updateSeats(this.name, this.seatsAvailable);
			return true;
		}else{
			return false;
		}
	}
	
	public void updateRating(int rating){
		System.out.println("old inner rating: "+this.rating);
		int expand = (int)(this.rating*numberOfRatings);
		System.out.println("expand: "+expand);
		int newExpand = expand+rating;
		System.out.println("newExpand: "+newExpand);
		numberOfRatings++;
		this.rating = (float)newExpand/numberOfRatings;
		System.out.println("new inner rating: "+this.rating);
		DBManager.updateRating(this.name, this.rating, this.numberOfRatings);
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
	public String getRating(){
		return String.format(Locale.ENGLISH,"%.1f", this.rating);
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
		return this.getDepartureLocation();
	}
		
	/*
	@Override
	public int compareTo(Object arg0) {
		// TODO unimplemented
		return 0;
	}*/
	
}