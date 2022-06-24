
public class GreenHouse {
	public char greenHouseMap[][];
	
	public GreenHouse() {
		this.greenHouseMap = new char[15][50];
		generateGreenHouse();
	}
	
	public void generateGreenHouse() {
		String [] tempMap = new String[12];
		tempMap[0] = ".----------------------------------------.";
		tempMap[1] = "|                                        |";
//					  012345678901234567890123456789012345678901
		tempMap[2] = "|  [ ]     [ ]     [ ]     [ ]     [ ]   |";
		tempMap[3] = "|                                        |";
		tempMap[4] = "|  [ ]     [ ]     [ ]     [ ]     [ ]   |";
		tempMap[5] = "|                                        |";
		tempMap[6] = "|                                        |";
		tempMap[7] = "|                                        |";
		tempMap[8] = "|             .            .             |";
		tempMap[9] = "|             |            |             |";
		tempMap[10] = "| ########### |            | ########### |";
		tempMap[11] = "'-----------------|====|-----------------'";
		for(int i = 0;i < 12;i++) {
			for(int j = 0;j < tempMap[i].length();j++) {
				this.greenHouseMap[i][j] = tempMap[i].charAt(j);
			}
		}
		
	}
}
