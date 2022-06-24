import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
	public char [][] map;	
	
	public Map() {
		this.map = new char [35][120];
		readFile();
	}
	
	public void readFile() {
		File currentFile = new File("./main_map.txt");
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(currentFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int row = 0;
		while(fileReader.hasNextLine()) {
			String word = fileReader.nextLine();
			map[row] = word.toCharArray();
			row++;
		}	
	}

}
