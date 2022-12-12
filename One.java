import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class One {
	private static int first = 0;
	private static int second = 0;
	private static int third = 0;
	
	public static void main(String[] args) {
		try {
			File myObj = new File("src/input/OneInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        int tracker = 0;
	        while (myReader.hasNextLine()) {
	        	String data = myReader.nextLine();
	        	if (data.isEmpty()) {
	        		updateTopThree(tracker);
	        		tracker = 0;
	        	}
	        	else {
	        		tracker += Integer.parseInt(data);	
	        	}
	        }
	        myReader.close();
	        updateTopThree(tracker);
	        System.out.println("Highest: " + first);
	        System.out.println("Top three: " + (first + second + third));
	      } catch (FileNotFoundException e) {
	    	  System.out.println("An error occurred.");
	    	  e.printStackTrace();
	      }
	}
	
	private static void updateTopThree(int value) {
		if (value > first) {
			first = value;
		}
		else if (value > second) {
			second = value;
		}
		else if (value > third) {
			third = value;
		}
	}
}
