
public class RestArea {
	public char restAreaMap[][];
	
	public RestArea() {
		this.restAreaMap = new char [15][40];
		generateRestArea();
	}
	
	public void generateRestArea() {
		String [] tempMap = new String[15];
		tempMap[0] = ".---------------------------------.";
        tempMap[1] = "|  ___   [||||||||]  [||||||||]    |";
        tempMap[2] = "| [___]                            |";
        tempMap[3] = "| |  0|                            |";
        tempMap[4] = "| |__0|                            |";
        tempMap[5] = "|                                  |";
        tempMap[6] = "|                                  |";
        tempMap[7] = "|                                  |";
        tempMap[8] = "|                                  |";
        tempMap[9]=  "|                                  |";
        tempMap[10]= "|                           __..__ |";
        tempMap[11]= "|                          [      ]|";
        tempMap[12]= "'-------------|======|-------------'";
        for(int i = 0; i < 13; i++)
        {
            for(int j = 0; j < tempMap[i].length(); j++)
            {
                this.restAreaMap[i][j] = tempMap[i].charAt(j);
            }
        }
	}
}
