import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Four {
	public static void main(String[] args) {
		try {
			File myObj = new File("src/input/FourInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        int fullCount = 0;
	        int partialCount = 0;
	        while (myReader.hasNextLine()) {
	        	String data = myReader.nextLine();
	        	String[] pair = data.split(",");
	        	String[] first = pair[0].split("-");
	        	String[] second = pair[1].split("-");
	        	
	        	int a1_1 = Integer.parseInt(first[0]);
	        	int a1_2 = Integer.parseInt(first[1]);
	        	int a2_1 = Integer.parseInt(second[0]);
	        	int a2_2 = Integer.parseInt(second[1]);
	        	
	        	if (sectionsFullyOverlap(a1_1, a1_2, a2_1, a2_2)) {
	        		fullCount ++;
	        	}
	        	
	        	if (sectionsPartiallyOverlap(a1_1, a1_2, a2_1, a2_2)) {
	        		partialCount ++;
	        	}
	        }
	        myReader.close();
	        System.out.println("Full count: " + fullCount);	 
	        System.out.println("Partial count: " + partialCount);	        
	      } catch (FileNotFoundException e) {
	    	  System.out.println("An error occurred.");
	    	  e.printStackTrace();
	      }
	}
	
	private static boolean sectionsFullyOverlap(int a1_1, int a1_2, int a2_1, int a2_2) {
    	if (a1_1 <= a2_1) {
    		if (a1_2 >= a2_2) {
    			return true;
    		}
    	}
    	if (a1_1 >= a2_1) {
    		if (a1_2 <= a2_2) {
    			return true;
    		}
    	}
    	return false;
	}
	
	private static boolean sectionsPartiallyOverlap(int a1_1, int a1_2, int a2_1, int a2_2) {
    	if (a1_1 <= a2_2 && a1_1 >= a2_1) {
    		return true;
    	}
    	if (a2_1 <= a1_2 && a2_1 >= a1_1) {
    		return true;
    	}
    	return false;
	}
}
