import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class FileHandler {
    public static void saveReservations(List<Reservation> reservations) {
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter("reservation.txt"))){
    		for(Reservation r : reservations) {
    			writer.write(r.toString());
    			writer.newLine();		
    			 }
    	} catch (IOException e) {
			
            System.out.println("Error saving reservations: " + e.getMessage());

		}
    }
    		
    	public static ArrayList<Reservation> loadReservations() {
    	        ArrayList<Reservation> reserve = new ArrayList<>();
    	        try (BufferedReader reader = new BufferedReader(new FileReader("reservation.txt"))) {
    	            String line;
    	            while ((line = reader.readLine()) != null) {
    	            	
    	            	System.out.println("Read Line: "+ line);
    	            }
    	        } catch (IOException e) {
    	            System.err.println("Error loading reservations: " + e.getMessage());
    	        }
    	        return reserve;
    	    }
    	}
    	