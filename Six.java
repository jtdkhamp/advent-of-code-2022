import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Six {
	public static void main(String[] args) {
		try {
			File myObj = new File("src/input/SixInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
        	String line = myReader.nextLine();
	        myReader.close(); 
	        
	        char[] characters = line.toCharArray();
	        Map<Character, Integer> window = new HashMap<>();
	        for (int i = 0; i < characters.length; i++) {
        		window.put(characters[i], window.getOrDefault(characters[i], 0) + 1);
	        	if (i < 13) {
	        		continue;
	        	}
	        	boolean containsDuplicates = false;
	        	for (int v : window.values()) {
	        		if (v > 1) {
	        			containsDuplicates = true;
	        			break;
	        		}
	        	}
	        	if (!containsDuplicates) {
	        		System.out.println(i+1);
	        		break;
	        	}
	        	char lastChar = characters[i-13];
	        	if (window.containsKey(lastChar)) {
	        		if (window.get(lastChar) <= 1) {
		        		window.remove(lastChar);
	        		}
	        		else {
		        		window.put(lastChar, window.get(lastChar)-1);
	        		}
	        	}
	        }
	      } catch (FileNotFoundException e) {
	    	  System.out.println("An error occurred.");
	    	  e.printStackTrace();
	      }
	}
}
