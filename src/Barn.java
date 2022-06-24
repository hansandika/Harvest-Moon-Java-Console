
public class Barn {
	public char barnMap[][];
	
	public Barn() {
		this.barnMap = new char[12][50];
		generateBarnMap();
	}
	
	public void generateBarnMap() {
		String [] tempMap = new String[15];
		tempMap[0] = ".----------------------------------------.";
		tempMap[1] = "|            ||            ||            |";
		tempMap[2] = "|            ||            ||            |";
		tempMap[3] = "|            ||            ||            |";
		// 3 [2]
		tempMap[4] = "|            ||            ||            |";
		tempMap[5] = "|            ||            ||            |";
		tempMap[6] = "|            ||            ||            |";
		tempMap[7] = "|            ||            ||            |";
		tempMap[8] = "|            ||            ||            |";
		tempMap[9] = "|            ||            ||            |";
//		               012345678901234567890123456789012345678901
		tempMap[10] = "|            ||            ||            |";
		tempMap[11] = "'-----------------|====|-----------------'";
		for(int i = 0;i < 12;i++) {
			for(int j = 0;j < tempMap[i].length();j++) {
				this.barnMap[i][j] = tempMap[i].charAt(j);
			}
		}
	}
}
