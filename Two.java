import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Two {
	private static Map<String, String> tieCombos = Map.of(
			"A", "X",
			"B", "Y",
			"C", "Z");
	private static Map<String, String> winningCombos = Map.of(
			"A", "Y",
			"B", "Z",
			"C", "X");
	private static Map<String, String> losingCombos = Map.of(
			"A", "Z",
			"B", "X",
			"C", "Y");
	private static Map<String, Integer> shapeValues = Map.of(
			"X", 1,
			"Y", 2,
			"Z", 3);
	
	public static void main(String[] args) {
		try {
			File myObj = new File("src/input/TwoInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        int totalScore1 = 0;
	        int totalScore2 = 0;
	        while (myReader.hasNextLine()) {
	        	String data = myReader.nextLine();
	        	String[] splitData = data.split(" ");
	        	totalScore1 += calculateRoundScore(splitData[0], splitData[1]);
	        	totalScore2 += calculateRoundScore2(splitData[0], splitData[1]);
	        }
	        myReader.close();
	        System.out.println("First part score: " + totalScore1);
	        System.out.println("Second part score: " + totalScore2);
	      } catch (FileNotFoundException e) {
	    	  System.out.println("An error occurred.");
	    	  e.printStackTrace();
	      }
	}
	
	private static int calculateRoundScore(String opponentShape, String myShape) {
		int roundScore = 0;
		roundScore += shapeValues.get(myShape);
		if (winningCombos.get(opponentShape).equals(myShape)) {
			roundScore += 6;
		} else if (tieCombos.get(opponentShape).equals(myShape)) {
			roundScore += 3;
		}
		return roundScore;
	}
	
	private static int calculateRoundScore2(String opponentShape, String myStrategy) {
		int roundScore = 0;
		if (myStrategy.equals("X")) {
			roundScore += shapeValues.get(losingCombos.get(opponentShape));
		} else if (myStrategy.equals("Y")) {
			roundScore += 3;
			roundScore += shapeValues.get(tieCombos.get(opponentShape));
		} else {
			roundScore += 6;
			roundScore += shapeValues.get(winningCombos.get(opponentShape));
		}
		return roundScore;
	}
	
	
}
