import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Ten {	
	public static void main(String[] args) {
		try {
			Set<Integer> cyclesToTrack = Set.of(20, 60, 100, 140, 180, 220);
			int sumSignalStrength = 0;
			int x = 1;
			int cycle = 1;
			boolean addToX = false;
			int addXValue = 0;
			
			char[] crt = new char[240];
			
			File myObj = new File("src/input/TenInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	        	if (cyclesToTrack.contains(cycle)) {
	        		sumSignalStrength += x*cycle;
	        	}
	        	
	        	// draw pixel on crt
	        	int pixelPosition = cycle - 1;
	        	if (pixelPosition%40 <= x+1 && pixelPosition%40 >= x-1) {
	        		crt[pixelPosition] = '#';
	        	} else {
	        		crt[pixelPosition] = '.';
	        	}
	        	
	        	if (addToX) {
	        		x += addXValue;
	        		addToX = false;
	        	} else {
		        	String line = myReader.nextLine();
		        	if (line.contains("addx")) {
		        		addXValue = Integer.parseInt(line.split(" ")[1]);
		        		addToX = true;
		        	}
	        	}
	        	cycle ++;
	        }
	        myReader.close();
	        
	        System.out.println(sumSignalStrength);
	        
	        int j = 0;
	        for (int i = 0; i < crt.length; i++) {
	        	System.out.print(crt[i]);
	        	if (j++ == 39) {
	        		System.out.println();
	        		j = 0;
	        	}
	        }
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
