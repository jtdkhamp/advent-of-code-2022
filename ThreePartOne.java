import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ThreePartOne {
	private static Map<Character, Integer> itemPriorityMap = new HashMap<>();
	
	public static void main(String[] args) {
		populateItemPriorityMap();
		try {
			File myObj = new File("src/input/ThreeInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        int prioritySum = 0;
	        while (myReader.hasNextLine()) {
	        	String data = myReader.nextLine();
	        	String firstHalf = data.substring(0, data.length()/2);
	        	String secondHalf = data.substring(data.length()/2, data.length());
	        	
	        	Set<Character> itemTracker = new HashSet<>();
	        	for (char c : firstHalf.toCharArray()) {
	        		itemTracker.add(c);
	        	}
	        	
	        	for (char c : secondHalf.toCharArray()) {
	        		if (itemTracker.contains(c)) {
	        			prioritySum += itemPriorityMap.get(c);
	        		}
	        	}
	        }
	        myReader.close();
	        System.out.println("Priority sum: " + prioritySum);	        
	      } catch (FileNotFoundException e) {
	    	  System.out.println("An error occurred.");
	    	  e.printStackTrace();
	      }
	}
	
	private static void populateItemPriorityMap() {
		int start = 1;
		for (char alphabet = 'a'; alphabet <='z'; alphabet++) {
			itemPriorityMap.put(alphabet, start++);
		}
		for (char alphabet = 'A'; alphabet <='Z'; alphabet++) {
			itemPriorityMap.put(alphabet, start++);
		}
	}

}
