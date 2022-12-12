import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Seven {
	private static Set<Directory> allDirs = new HashSet<>();
	private static Directory root = null;
	
	public static void main(String[] args) {
		try {
			File myObj = new File("src/input/SevenInput.txt");
	        Scanner myReader = new Scanner(myObj).useDelimiter("\\$");
	        
	        Directory currDir = null;
	        while (myReader.hasNext()) {
	        	String next = myReader.next();
	        	if (next.startsWith(" cd")) {
	        		currDir = changeDir(next, currDir);
	        		allDirs.add(currDir);
	        	}
	        	else {
	        		list(next, currDir);
	        	}
	        }
	        myReader.close();
	        System.out.println(calculateResult());
	        System.out.println(findDirToDelete().getSize());
	      } catch (FileNotFoundException e) {
	    	  System.out.println("An error occurred.");
	    	  e.printStackTrace();
	      }
	}
	
	private static Directory changeDir(String command, Directory currentDir) {
		String dirName = command.substring(4).strip();
		if (dirName.equals("/")) {
			if (root == null) {
				Directory dir = new Directory("/");
				root = dir;
			}
			return root;
		}
		else if (dirName.equals("..")) {
			return currentDir.getParent();
		}
		else {
			for (Directory childDir : currentDir.getChildDirs()) {
				if (childDir.getName().equals(dirName)) {
					return childDir;
				}
			}
			Directory dir = new Directory(dirName, currentDir);
			currentDir.addChildDir(dir);
			return dir;
		}
	}
	
	private static void list(String line, Directory currentDir) {
		String[] result = line.split("\n");
		for (int i = 1; i < result.length; i ++) {
			if (result[i].startsWith("dir")) {
				continue;
			}
			String[] fileInfo = result[i].split(" ");
			currentDir.addFile(fileInfo[1], Integer.parseInt(fileInfo[0]));
		}
	}
	
	private static int calculateResult() {
		int sum = 0;
		for (Directory dir : allDirs) {
			if (dir.getSize() <= 100000) {
				sum += dir.getSize();
			}
		}
		return sum;
	}
	
	private static Directory findDirToDelete() {
		int totalSpace = 70000000;
		int spaceNeeded = 30000000;

		int currentSpace = totalSpace - root.getSize();
		int spaceToFree = spaceNeeded - currentSpace;
		
		Directory minDir = null;
		int min = Integer.MAX_VALUE;
		for (Directory dir : allDirs) {
			int size = dir.getSize();
			if (size >= spaceToFree && size < min) {
				min = size;
				minDir = dir;
			}
		}
		return minDir;
	}
	
	static class Directory {
		private String name;
		private int size;
		private Set<String> files = new HashSet<>();
		private Set<Directory> childDirs = new HashSet<>();
		private Directory parent;
		
		public Directory(String name) {
			this.name = name;
			this.size = 0;
		}
		
		public Directory(String name, Directory parent) {
			this(name);
			this.parent = parent;
		}
		
		public String getName() {
			return name;
		}
		
		public Directory getParent() {
			return parent;
		}
		
		public Set<Directory> getChildDirs() {
			return childDirs;
		}
		
		public int getSize() {
			int sum = size;
			for (Directory dir : childDirs) {
				sum += dir.getSize();
			}
			return sum;
		}
		
		public void addFile(String fileName, int fileSize) {
			if (!files.contains(fileName)) {
				size += fileSize;
				files.add(fileName);
			}
		}
		
		public void addChildDir(Directory dir) {
			childDirs.add(dir);
		}
	}
}