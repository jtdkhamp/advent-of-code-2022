import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Five {
	public static void main(String[] args) {
		try {
			File myObj = new File("src/input/FiveInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        List<Stack<String>> containers = getContainersFromInput(myReader);
	        while (myReader.hasNextLine()) {
	        	String line = myReader.nextLine();
	        	Matcher matcher = Pattern.compile("\\d+").matcher(line);
	        	List<Integer> moves = new ArrayList<>();
	        	while (matcher.find()) {
	        	    moves.add(Integer.valueOf(matcher.group()));
	        	}
	        	int num =  moves.get(0);
	        	int from = moves.get(1);
	        	int to = moves.get(2);
	        	
	        	// part 1
//	        	for (int i = 0; i < num; i++) {
//	        		containers.get(to-1).add(containers.get(from-1).pop());
//	        	}
	        	
	        	//part 2
	        	//ZFSJBPRFP
	        	Stack<String> temp = new Stack<String>();
	        	for (int i = 0; i < num; i++) {
	        		temp.add(containers.get(from-1).pop());
	        	}
	        	while (!temp.isEmpty()) {
	        		containers.get(to-1).add(temp.pop());
	        	}
	        	
	        }
	        myReader.close();    
        	for (Stack<String> container : containers) {
        		System.out.print(container.peek());
        	}   
	      } catch (FileNotFoundException e) {
	    	  System.out.println("An error occurred.");
	    	  e.printStackTrace();
	      }
	}
	
	private static List<Stack<String>> getContainersFromInput(Scanner myReader) {
		// read all container lines and put them in a list
		List<String[]> rows = new ArrayList<String[]>();
		while (true) {
			String line = myReader.nextLine();
			if (!line.contains("[")) {
				myReader.nextLine();
				break;
			}
			rows.add(line.split("(?<=\\G.{4})"));
		}
		
		int numCol = rows.get(0).length;
		int numRows = rows.size();
		
		// initialize the number of stacks we need
		List<Stack<String>> containers = new ArrayList<Stack<String>>();
		for (int i = 0; i < numCol; i++) {
			containers.add(new Stack<>());
		}
		
		// read last row to first row, adding elements to the stack
		for (int i = numRows-1; i>=0; i--) {
			String[] row = rows.get(i);
			for (int j = 0; j < numCol; j ++) {
				if (row[j].isBlank()) {
					continue;
				}
				String parsed = row[j].replaceAll("[^\\p{IsAlphabetic}]", "");
				containers.get(j).add(parsed);
			}
		}
		return containers;
	}
}
