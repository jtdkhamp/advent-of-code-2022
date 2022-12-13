import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Nine {
    
	public static void main(String[] args) {
		try {
	        Knot ninth = new Knot(null);
	        
	        // right answer only when there are 9 knots, not 10. Why??
	        Knot currKnot = ninth;
	        for (int i = 0; i < 8; i ++) {
	        	Knot knot = new Knot(currKnot);
	        	currKnot = knot;
	        }
	        Knot head = new Knot(currKnot);
	        
			File myObj = new File("src/input/NineInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        
	        while (myReader.hasNextLine()) {
	        	String[] move = myReader.nextLine().split(" ");
	        	move(head, move[0], Integer.parseInt(move[1]));
	        }
	        myReader.close();
	        
	        System.out.println("Part one: " + head.getChild().getVisited().size());
	        System.out.println("Part two: " + ninth.getVisited().size());
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	
	//...H...
	//.......
	//.H.T.H.
	//.......
	//...H...

	//..H.H..
	//.H...H.
	//...T...
	//.H...H.
	//..H.H..
	
	private static void move(Knot head, String direction, int steps) {
		int step = 1;
		if (direction.equals("L") || direction.equals("D")) {
			step = step * -1;
		}
		
		for (int i = 0; i < steps; i++) {
			if (direction.equals("R") || direction.equals("L")) {
				head.offsetX(step);				
			}
			else {
				head.offsetY(step);
			}
			head.getChild().update(head.getX(), head.getY());
		}
	}
	
	static class Knot {
		private int x = 0;
		private int y = 0;
	    private Set<String> visited = new HashSet<>();
	    private Knot child;
		
		public Knot(Knot child) {
			visited.add(toString());
			this.child = child;
		}
		
		public void offsetX(int val) {
			x += val;
		}
		
		public void offsetY(int val) {
			y += val;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public Knot getChild() {
			return child;
		}
		
		public Set<String> getVisited() {
			return visited;
		}
		
		public String toString() {
			return x + "," + y;
		}
		
		private void update(int parentX, int parentY) {
			int xdiff = parentX - x;
			int ydiff = parentY - y;
			
			if (Math.abs(xdiff) == 2 && Math.abs(ydiff) == 2) {
				offsetX(xdiff/2);
				offsetY(ydiff/2);
				visited.add(toString());
			}
			else if (Math.abs(xdiff) == 2) {
				offsetX(xdiff/2);
				offsetY(ydiff);
				visited.add(toString());
			}
			else if (Math.abs(ydiff) == 2) {
				offsetX(xdiff);
				offsetY(ydiff/2);
				visited.add(toString());
			}
			if (child != null) {
				child.update(x, y);
			}
		}
	}
}