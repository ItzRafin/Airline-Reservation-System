public class Flight {
   private String flightId;
   private String source;
   private String destination;
   private int date;
   private int totalSeats;
   private int availableSeats;
   private double baseFare;
   private double childFare=0.75;
   private double infantFare=0.1;
   
   public Flight(String id, String source, String dest, int seats , double base) {
	   this.flightId=id;
	   this.source=source;
	   this.destination=dest;
	   this.totalSeats=seats;
	   this.availableSeats=seats;
	   this.baseFare=base;
	
   }
   
   public double calculate(Passenger.PassengerType type) {
	   switch(type) {
       case INFANT: return baseFare * infantFare;
       case CHILD: return baseFare* childFare;
       default: return baseFare;
	   }
   }
   public String getFlightId() {
	   return flightId;
   }
   public String getSource() {
	   return source;
   }
   public String getDestination() {
	   return destination;
   }
   public int getAvailableSeats() {
	   return availableSeats;
   }
   public int getTotalSeats() {
	   return totalSeats;
   }
   
   public void bookSeat() throws SeatUnavailableException {
	   if(availableSeats<=0) {
		   throw new SeatUnavailableException("No seats available on this flight");
	   }
	   availableSeats--;
   }


}
  
   
   
   

