import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BangladeshAirlines {
	static ArrayList<Flight>f = new ArrayList<>();
	static ArrayList<Reservation> reserve= new ArrayList<>();
	static ArrayList<Passenger>p=new ArrayList<>();
	static Scanner s = new Scanner(System.in);
	

	public static void main(String[] args) {
		initializeFlights();
		showWelcomeScreen();

		
	}

	private static void showWelcomeScreen() {
		System.out.println("\n***WELCOME TO BANGLADESH AIRLINES***");
		System.out.println("\nReservation Chart: ");
		System.out.println("1. Book Flight");
		System.out.println("2. Manage Booking");
		System.out.println("3. Check Travel Requirments");
		System.out.println("4. Admin Login");
		System.out.println("5. Exit");
		
		try {
			int a = s.nextInt();
			s.nextLine();
			switch (a) {
			case 1:
				bookflight();
				break;
			case 2:
				manageBooking();
				break;
			case 3:
				showTravelRequirements();
				break;
			case 4: 
				adminLogin();
				break;
			case 5:
				System.out.println("Thank You for choosing Bangladesh Airlines");
				FileHandler.saveReservations(reserve);
				return;
			default: 
				System.out.println("Invalid Option. Please try again.");
			}
			showWelcomeScreen();
		}catch(Exception e) {
			e.toString();
			showWelcomeScreen();
		}
		
	}

	private static void bookflight() {
		System.out.println("\n____FLIGHT BOOKING____");
		System.out.println("Available Destinations: ");
		for(int i=0; i<f.size();i++) {
			System.out.println((i+1)+ ". "+ f.get(i).getDestination());
		}
		System.out.println("Select Destination(number): ");
		int dest=s.nextInt()-1;
		s.nextLine();
		if(dest<0 || dest>=f.size()) {
			System.out.println("Invalid");
			return;
		}
		Flight selectedflight= f.get(dest);
		
		System.out.println("Do you want a round trip?(yes/no)");
		String roundInput = s.nextLine().toLowerCase();
		boolean round = roundInput.equals("yes");
		LocalDate returnDate =null;
		if(round) {
			System.out.println("Enter return date (yyyy-mm-dd): ");
			String date= s.nextLine();
			try {
				returnDate= LocalDate.parse(date);

			}catch(Exception e) {
				System.out.println("Invalid date format. Please use yyyy-mm-dd format.");
			return;
			}
		}
		System.out.println("Number of Passengers: ");
		int count= s.nextInt();
		s.nextLine();
		for(int i=1; i<=count;i++) {
			System.out.println("Passenger no. " +i+ " details: ");
			System.out.println("Name: ");
			String name = s.nextLine();
			System.out.println("Email: ");
			String email = s.nextLine();
			System.out.println("age: ");
			int age = s.nextInt();
			s.nextLine();
			
			p.add(new Passenger(name, email, age));
		}
			try {
				for(int i=0;i<count;i++) {
				selectedflight.bookSeat();
				}
				Reservation r = new Reservation(p,selectedflight,round,LocalDate.now().plusDays(7),returnDate);
				reserve.add(r);
				System.out.println("_____Booked Successfully______");
				System.out.println("Your Reservation ID: "+ r.getReservationId());
				System.out.println("Total fare: "+ r.totalFare());
				
			
			}catch(SeatUnavailableException e) {
				System.out.println("Error: "+ e.getMessage());
			}
			
		}
		
	

	private static void showTravelRequirements() {
		System.out.println("\n____TRAVEL REQUIREMENTS____");
		System.out.println("1. Valid ID required for all passengers. ");
		System.out.println("2. Check-in opens 3 hours before departure. ");
		System.out.println("3. Face masks are recommended. ");
		System.out.println("Press Enter to continue....");
		
	}

	private static void manageBooking() {
		System.out.println("\n_____MANAGE BOOKING_____");
		System.out.println("Enter your Reservation ID: ");
		String id = s.nextLine();
		Reservation found = null;
		for(Reservation r : reserve) {
			if(r.getReservationId().equals(id)) {
				found = r;
				break;
			}
		}
		if(found==null) {
			System.out.println("Reservation not found");
			return;
		}
		found.showReservation();
		System.out.println("1. Cancel booking");
		System.out.println("2. Back to Chart");
		System.out.println("Select option: ");
		int a=s.nextInt();
		s.nextLine();
		if(a==1) {
	        Admin.record(found.getPasssengerCount());

			reserve.remove(found);
			System.out.println("\n____Booking cancelled successfully____");
		}
	}

	private static void adminLogin() {
		System.out.println("\n_____ADMIN LOGIN______");
		System.out.println("Enter admin id: ");
		String id = s.nextLine();
		System.out.println("Enter admin Password: ");
		String pass = s.nextLine();
		Admin ad = new Admin ("System Admin", "admin@bangladeshAir.com",id,pass);
		System.out.println("1. View all flights");
		System.out.println("2. View all reservations");
		System.out.println("3. View cancellation statistics");
		System.out.println("4. back to chart");
		System.out.println("Select option: ");
		int a= s.nextInt();
		switch(a) {
		case 1: ad.viewAllFlights(f);
		       break;
		case 2: ad.viewAllReservations(reserve);
		       break;
		case 3: ad.viewCancellationSeats();
		       break;
		}
	}

	private static void initializeFlights() {
		
		f.add(new Flight("F101","Dhaka","Chattogram",50,5000.00));
		f.add(new Flight("F102","Dhaka","Sylhet",30,5200.00));
		f.add(new Flight("F103","Dhaka","Cox's Bazar",40,6199.00));
		f.add(new Flight("F104","Dhaka","Rajshahi",10,6000.00));
		f.add(new Flight("F105","Dhaka","Jashore",23,4899.00));
		
		f.add(new Flight("F206","Dhaka","Doha",80,49785.00));
		f.add(new Flight("F207","Dhaka","Bangkok",55,20134.00));
		f.add(new Flight("F218","Dhaka","Kolkata",98,9389.00));
		f.add(new Flight("F205","Dhaka","Male",58,32266.00));
		f.add(new Flight("F202","Dhaka","Singapore",73,28183.00));
		f.add(new Flight("F201","Dhaka","Switzerland",50,98000.00));
		f.add(new Flight("F203","Dhaka","Kuala Lumpur",10,45264.00));
		f.add(new Flight("F209","Dhaka","Riyadh",34,43694.00));

	}

}
