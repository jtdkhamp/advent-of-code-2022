import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ThreePartTwo {
	private static Map<Character, Integer> itemPriorityMap = new HashMap<>();

	public static void main(String[] args) {
		populateItemPriorityMap();

		try {
			File myObj = new File("src/input/ThreeInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        int prioritySum = 0;
	        while (myReader.hasNextLine()) {
	        	String firstLine = myReader.nextLine();
	        	String secondLine = myReader.nextLine();
	        	String thirdLine = myReader.nextLine();
	        	
	        	Set<Character> firstSet = readLine(firstLine);
	        	Set<Character> secondSet = readLine(secondLine);
	        	Set<Character> thirdSet = readLine(thirdLine);
	        	
	        	for (char c : firstSet) {
	        		if (secondSet.contains(c)) {
	        			if (thirdSet.contains(c)) {
	        				prioritySum += itemPriorityMap.get(c);
	        				System.out.println(c);
	        				break;
	        			}
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
	
	private static Set<Character> readLine(String line) {
		Set<Character> set = new HashSet<>();
		for (char c : line.toCharArray()) {
			set.add(c);
		}
		return set;
	}
}
