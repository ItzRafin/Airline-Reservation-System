public class Passenger extends User {
	 public enum PassengerType { ADULT, CHILD, INFANT }
	private static int id = 100;
    private String passengerId;
    PassengerType type;
    int age;

	public Passenger(String name, String email, int age) {
		super(name, email);
		this.passengerId="P" +(++id);
		this.age=age;
		this.type = determine(age);
		
	}

	public PassengerType determine(int age) {
		if(age<2) {
			return PassengerType.INFANT;
		}else if(age<12) {
			return PassengerType.CHILD;
		}else {
		return PassengerType.ADULT;
	}
	}
	public PassengerType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public String getPassengerId() {
		return passengerId;
	}
	@Override
	public void displayDetails() {
		System.out.println("Passenger ID: " + passengerId + 
				"\nName: " + super.name + 
				"\nEmail: " + super.email+
				"\nType: "+ type +
				"\nAge: "+ age);
		
	}

	}


