import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Eight {
	public static void main(String[] args) {
		List<char[]> grid = new ArrayList<>();

		try {
			File myObj = new File("src/input/EightInput.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	        	grid.add(myReader.nextLine().toCharArray());
	        }
	        myReader.close();	        
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
        List<List<Tree>> trees = new ArrayList<>();
        initializeTrees(grid, trees);
        
        long numVisibleTrees = trees.stream()
        		.flatMap(List::stream)
        		.filter(tree -> tree.isVisible())
        		.count();
        
        System.out.println("Number of visible trees: " + numVisibleTrees);
        
        int maxViewDistance = trees.stream()
        		.flatMap(List::stream)
        		.filter(tree -> tree.isVisible())
        		.mapToInt(tree -> tree.getViewDistance())
        		.max().orElseThrow(NoSuchElementException::new);;
        
        System.out.println("Max view distance: " + maxViewDistance);
	}
	
	private static void initializeTrees(List<char[]> grid, List<List<Tree>> trees) {
		for (int i = 0; i < grid.size(); i ++) {
			trees.add(new ArrayList<>());
			for (int j = 0; j < grid.get(i).length; j ++) {
				int height = Integer.parseInt(String.valueOf(grid.get(i)[j]));
				trees.get(i).add(new Tree(height));
			}
		}
		
        for (int i = 0; i < trees.size(); i ++) {
        	for (int j = 0; j < trees.get(i).size(); j ++) {
        		Tree currTree = trees.get(i).get(j);
        		if (j != 0) {
        			currTree.setLeft(trees.get(i).get(j-1));
        		}
        		if (j != trees.get(i).size() - 1) {
        			currTree.setRight(trees.get(i).get(j+1));
        		}
        		if (i != 0) {
        			currTree.setUp(trees.get(i-1).get(j));        		
        			}
        		if (i != trees.size() -1) {
        			currTree.setDown(trees.get(i+1).get(j));
        		}
        	}
        }
	}
	
	static class Tree {
		private Tree left;
		private Tree right;
		private Tree up;
		private Tree down;
		
		private int leftMax = Integer.MIN_VALUE;
		private int rightMax = Integer.MIN_VALUE;
		private int upMax = Integer.MIN_VALUE;
		private int downMax  = Integer.MIN_VALUE;
		
		private int height;
		
		public Tree(int height) {
			this.height = height;
		}
		
		public void setLeft(Tree tree) {
			left = tree;
		}
		
		public void setRight(Tree tree) {
			right = tree;
		}
		
		public void setUp(Tree tree) {
			up = tree;
		}
		
		public void setDown(Tree tree) {
			down = tree;
		}
		
		public int getHeight() {
			return height;
		}
		
		public boolean isVisible() {
			return (getLeftMax() < height || 
					getRightMax() < height || 
					getUpMax() < height || 
					getDownMax() < height);
		}
		
		public int getViewDistance() {
			return getLeftViewDistance(height) * 
					getRightViewDistance(height) * 
					getUpViewDistance(height) * 
					getDownViewDistance(height);
		}
		
		private int getLeftMax() {
			if (leftMax == Integer.MIN_VALUE && left != null) {
				leftMax = Math.max(left.getHeight(), left.getLeftMax());
			}
			return leftMax;
		}
		
		private int getRightMax() {
			if (rightMax == Integer.MIN_VALUE && right != null) {
				rightMax = Math.max(right.getHeight(), right.getRightMax());
			}
			return rightMax;
		}
		
		private int getUpMax() {
			if (upMax == Integer.MIN_VALUE && up != null) {
				upMax = Math.max(up.getHeight(), up.getUpMax());
			}
			return upMax;
		}
		
		private int getDownMax() {
			if (downMax == Integer.MIN_VALUE && down != null) {
				downMax = Math.max(down.getHeight(), down.getDownMax());
			}
			return downMax;
		}
		
		private int getLeftViewDistance(int height) {
			if (left == null) {
				return 0;
			}
			else if (height <= left.getHeight()) {
				return 1;
			}
			else {
				return 1 + left.getLeftViewDistance(height);
			}
		}
		
		private int getRightViewDistance(int height) {
			if (right == null) {
				return 0;
			}
			else if (height <= right.getHeight()) {
				return 1;
			}
			else {
				return 1 + right.getRightViewDistance(height);
			}
		}
		
		private int getUpViewDistance(int height) {
			if (up == null) {
				return 0;
			}
			else if (height <= up.getHeight()) {
				return 1;
			}
			else {
				return 1 + up.getUpViewDistance(height);
			}
		}
		
		private int getDownViewDistance(int height) {
			if (down == null) {
				return 0;
			}
			else if (height <= down.getHeight()) {
				return 1;
			}
			else {
				return 1 + down.getDownViewDistance(height);
			}
		}
	}
}
