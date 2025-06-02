import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reservation {
	ArrayList<Passenger>passenger;
private static int counter = 1000;
private String reservationId;

 Flight flight;
 private double totalfare;
 private boolean isRoundTrip;
 private LocalDate departureDate;
 private LocalDate returnDate;
 private LocalDateTime reservationTime;
 
public Reservation(	ArrayList<Passenger> p, Flight f,boolean round, LocalDate dept, LocalDate returntime) {
	this.reservationId= "R" + (++counter);
	this.flight=f;
	this.passenger=p;
	this.reservationTime=LocalDateTime.now();
	this.isRoundTrip=round;
	this.departureDate=dept;
	if(isRoundTrip) {
		this.returnDate=returntime;
	}
	if(!isRoundTrip) {
		this.returnDate = null;
	}
	this.totalfare= totalFare();
}
public double totalFare() {
	double total =0;
	for(Passenger p : passenger) {
		total += flight.calculate(p.getType());
	}
	if(isRoundTrip) {
		total *=2;
	}
	return total;
}
public String getReservationId() {
	return reservationId;
}

public Flight getFlightId() {
	return flight;
}

public int getPasssengerCount() {
	return passenger.size();
}
public void showReservation() {
	
	System.out.println("Reservation Id: " +reservationId);
	System.out.println("Passengers: ");
	for(Passenger p: passenger) {
		System.out.println("- " + p.getName()+ " is a type of " + p.getType());
	}
	System.out.println("Flight no.: "+ flight.getFlightId());
    System.out.println("Broading from "+ flight.getSource() +" to " + flight.getDestination());
    System.out.println("Departure Date: " + departureDate);
    System.out.println("Trip Type: " );
    if(isRoundTrip) {
    	System.out.println("Round Trip");
    }else {
    	System.out.println("One Way");
    }
   if(isRoundTrip) {
	   System.out.println("Return Date: "+ returnDate);
   }
   System.out.println("Reservation Time: "+ reservationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
   System.out.println("Total Fare: BDT " + totalfare);
}
}

			
		
			


