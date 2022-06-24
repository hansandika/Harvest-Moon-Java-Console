import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {
	
	class TimeThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(farm.isFlag()) {					
					farm.setTime(farm.getTime() + 1);
					clear();
					if(farm.getCurrentMap().equals("MainMap")) {
						printMap();
					}else if(farm.getCurrentMap().equals("RestMap")) {
						printRestAreaMap();
					}else if(farm.getCurrentMap().equals("GreenHouseMap")) {
						printGreenHouseMap();
					}else if(farm.getCurrentMap().equals("BarnMap")) {
						printBarnMap();
					}
					if(farm.getTime() == 23){
						farm.setTime(7);
						Random rand = new Random();
						int chance = rand.nextInt(100) + 1;
						if(chance >= 1 && chance <= 20) {
							farm.setWeather("RAINY");
						}else {
							farm.setWeather("SUNNY");
						}
						farm.setDayCount(farm.getDayCount() + 1);
						
						System.out.println("[You faded out because of exhaustion]");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						String [] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
						System.out.printf("DAY %02d : [%s]",farm.getDayCount(),day[(farm.getDayCount()-1) % 7]);
						System.out.println("It's " + farm.getWeather() + " outside");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	Scanner scan = new Scanner(System.in);
	Player player;
	Farm farm;
	Map classMap;
	RestArea classRestArea;
	GreenHouse classGreenHouse;
	Thread timeThread;
	ArrayList<Inventory> inventoryList;
	ArrayList<Plant> cropList;
	ArrayList<Animal> animalList;
	Barn classBarn;
	
	public boolean validateName(String name) {
		if(name.length() < 5 || name.length() > 20) return false;
		for(int i = 0;i < name.length();i++) {
			if(!(name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') && !(name.charAt(i) >= 'a' && name.charAt(i) <= 'z') && !(name.charAt(i) >= '0' && name.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}
	
	public void init(String name) {
		classMap = new Map();
		player = new Player(name);
		classMap.map[player.getPlayerPosX()][player.getPlayerPosY()] = '9';
		classRestArea = new RestArea();
		classGreenHouse = new GreenHouse();
		classBarn = new Barn();
		farm = new Farm(1,"SUNNY",7);
		inventoryList = new ArrayList<>();
		inventoryList.add(new Inventory("Potato Seed",2,"A great food for winter",25,"greenhouse"));
		inventoryList.add(new Inventory("Sheep Hatchling",1,"A very healthy sheep",250,"barn"));
		cropList = new ArrayList<>();
		animalList = new ArrayList<>();
		timeThread = new Thread(new TimeThread());
		timeThread.start();
	}
	
	public void startNewGame() {
		String name;
		do {
			System.out.print("Input player name [5-20 characs | no special characters]: ");
			name = scan.nextLine();
			boolean flag = validateName(name);
			if(flag) break;
		}while(true);
		System.out.println("Generating the map....");
		System.out.println("Press any key to continue!"); scan.nextLine();
			
		init(name);
		clear();
		game();
	}
	
	public void sleep() {
		farm.setFlag(false);
		System.out.println("[You are fast asleep]");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		farm.setDayCount(farm.getDayCount() + 1);
		farm.setTime(7);
		Random rand = new Random();
		int chance = rand.nextInt(100) + 1;
		if(chance >= 1 && chance <= 20) {
			farm.setWeather("RAINY");
		}else {
			farm.setWeather("SUNNY");
		}
		
		String [] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		System.out.printf("DAY %02d : [%s]",farm.getDayCount(),day[(farm.getDayCount()-1) % 7]);
		System.out.println("It's " + farm.getWeather() + " outside");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		farm.setFlag(true);
	}
	
	public void shop() {
		farm.setFlag(false);
		int choose = -1;
		do {
			System.out.println("Shop");
			System.out.println("====");
			System.out.println("1. Sheep Hatchling           500 G");
			System.out.println("2. Chicken Hatchling         500 G");
			System.out.println("3. Turnip's seed             15  G");
			System.out.println("4. Cabbage's seed            300 G");
			System.out.println("5. Potato's seed             450 G\n");
			System.out.println("Current Gold : " + player.getGold());
			System.out.println();
			System.out.println("Input the item's index that you want to buy. 0 to exit.");
			
			do{
				System.out.print(">> ");
				try {
					choose = scan.nextInt();
				}catch (Exception e) {
					// TODO: handle exception
				}scan.nextLine();
				if(choose == 0) return;
				else if(choose >= 1 && choose <= 5) break;
			}while(true);
			
			String [] item = new String[5];
			item[0] = "Sheep Hatchling";
			item[1] = "Chicken Hatchling";
			item[2] = "Turnip's seed";
			item[3] = "Cabbage's seed";
			item[4] = "Potato's seed";
			int [] price = new int[5];
			price[0] = 500;
			price[1] = 500;
			price[2] = 15;
			price[3] = 300;
			price[4] = 450;
			String [] description = new String[5];
			description[0] = "A very healthy sheep";
			description[1] = "A very healthy chicken";
			description[2] = "Use this to plant turnip";
			description[3] = "Use this to plant cabbage";
			description[4] = "Use this to plant potato";
	
			char ch;
			do {
				System.out.printf("Are you sure you want to buy %s [Y|N]? ",item[choose-1]);
				ch = scan.next().charAt(0);
				if(ch == 'Y' || ch == 'y') {
					if(player.getGold() - price[choose-1] >= 0) {
						String placeUsed;
						if(choose <= 2) {
							placeUsed = "barn";
						}else {
							placeUsed = "greenhouse";
						}
						player.setGold(player.getGold() - price[choose-1]);
						inventoryList.add(new Inventory(item[choose-1],1,description[choose-1],price[choose-1]/2,placeUsed));
						System.out.println("[Item has been added to your inventory]"); scan.nextLine();
					}else {
						System.out.println("[You don't have enough money]"); scan.nextLine();
					}
					break;
				}else if(ch == 'N' || ch == 'n') {
					break;
				}
			}while(true);
			
			clear();
		}while(choose != 0);
		farm.setFlag(true);
	}
	
	public void inventory() {
		farm.setFlag(false);
		char ch = 'z';
		String [] choose = new String[15];
		int chooseItem = 0;
		int currentPage = 1;
		int totalPage;
		
		if(inventoryList.size() == 0){
			totalPage = 1;
		}else {
			if(inventoryList.size() % 10 == 0) {
				totalPage=  inventoryList.size() / 10;
			}else {
				totalPage = inventoryList.size() / 10 + 1;
			}
		}
		int firstItem = 0;
		int lastItem = 10;
		choose[0] = "<0";
		for(int i = 1;i < 10;i++) {
			choose[i] = "  ";
		}
		
		do {
			printInventory(firstItem,lastItem,currentPage,totalPage,choose,chooseItem);
			System.out.print(">> ");
			ch = scan.next().charAt(0); scan.nextLine();
			
			clear();
			if(ch == 'w' || ch == 'W') {
				if(chooseItem > 0) {
					choose[chooseItem] = "  ";
					chooseItem--;
					choose[chooseItem] = "<0";
				}
			}else if(ch == 's' || ch == 'S') {
				if(currentPage == 1) {
					if(chooseItem < inventoryList.size()-1) {
						choose[chooseItem] = "  ";
						chooseItem++;
						choose[chooseItem] = "<0";	
					}
				}
				else if(chooseItem < 9) {
					choose[chooseItem] = "  ";
					chooseItem++;
					choose[chooseItem] = "<0";						
				}
			}else if(ch == 'l' || ch == 'L') {
				int qtyChoose = -1;
				
				printInventory(firstItem,lastItem,currentPage,totalPage,choose,chooseItem);
				
				do {
					System.out.printf("How many of %s that you want to sell [1-%d]? ",inventoryList.get(chooseItem).getName(),inventoryList.get(chooseItem).getQty());
					try {
						qtyChoose = scan.nextInt();
					}catch (Exception e) {
						// TODO: handle exception
					}scan.nextLine();
					if(qtyChoose >= 1 && qtyChoose <= inventoryList.get(chooseItem).getQty()) break;
				}while(true);
				
				
				int price = inventoryList.get(chooseItem).getPrice() * qtyChoose;
				String chooseYes;
				do {
					System.out.printf("Are you sure you want to sell x%d %s for %dG [Y|N]? ",qtyChoose,inventoryList.get(chooseItem).getName(),price);
					chooseYes = scan.nextLine();
					if(chooseYes.equalsIgnoreCase("Y")) {
						if(inventoryList.get(chooseItem).getQty()-qtyChoose >= 1) {	
							inventoryList.get(chooseItem).setQty(inventoryList.get(chooseItem).getQty() - qtyChoose);
						}else {
							chooseItem = 0;
							inventoryList.remove(chooseItem);
							inventoryList.trimToSize();
						}
						player.setGold(player.getGold() + price);
						System.out.println("[Item has been sold]"); scan.nextLine();
						clear();
						break;
					}
					else {
						break;
					}
				}while(true);

			}else if(ch == 'e' || ch == 'E') {
				printInventory(firstItem,lastItem,currentPage,totalPage,choose,chooseItem);
				if(inventoryList.get(chooseItem).getPlaceUsed().equals("greenhouse") && farm.getCurrentMap().equals("GreenHouseMap")) {
					String plantName = inventoryList.get(chooseItem).getName();
					int growTime = -1;
					if(plantName.equals("Turnip's seed")) {
						plantName = "Turnip";
						growTime = 2;
					}else if(plantName.equals("Potato Seed") || plantName.equals("Potato's seed")) {
						plantName = "Potato";
						growTime = 4;
					}else if(plantName.equals("Cabbage's seed")) {
						plantName = "Cabbage";
						growTime = 3;
					}
					cropList.add(new Plant(plantName,farm.getDayCount(),growTime));
					
					if(inventoryList.get(chooseItem).getQty() >= 2) {
						inventoryList.get(chooseItem).setQty(inventoryList.get(chooseItem).getQty()-1);
					}else {
						inventoryList.remove(chooseItem);
						inventoryList.trimToSize();
					}
					chooseItem = 0;
					System.out.println("[A new crop is planted in the greenhouse! take a good care of it]"); scan.nextLine();
					break;
				}else if(inventoryList.get(chooseItem).getPlaceUsed().equals("barn") && farm.getCurrentMap().equals("BarnMap")) {
					String name = "";
					do {
						System.out.print("What name you'd give to this little buddy [1-20 Character]? ");
						name = scan.nextLine();
						if(name.length() >= 1 && name.length() <= 20) break;
					}while(true);
					
					if(inventoryList.get(chooseItem).getName().equals("Sheep Hatchling")) {
						animalList.add(new Animal(name,"sheep"));						
					}else {
						animalList.add(new Animal(name,"chicken"));
					}
					
					if(inventoryList.get(chooseItem).getQty() >= 2) {
						inventoryList.get(chooseItem).setQty(inventoryList.get(chooseItem).getQty()-1);
					}else {
						inventoryList.remove(chooseItem);
						inventoryList.trimToSize();
					}
					
					chooseItem = 0;
					System.out.println("\nGreat name!\n");
					System.out.println("[" + name + " will surely enjoy its new home]"); scan.nextLine();
				}else {
					System.out.println("[This item could only be used inside the " + inventoryList.get(chooseItem).getPlaceUsed() + "!]"); scan.nextLine();
				}
				clear();
			}else if(ch == 'd' || ch == 'D') {
				if(currentPage+1 <= totalPage) {
					choose[chooseItem] = "  ";
					chooseItem = 0;
					choose[chooseItem] = "<0";
					currentPage++;
					firstItem = (currentPage - 1) * 10;
					lastItem = firstItem + 10;
				}
			}else if(ch == 'a' || ch == 'A') {
				if(currentPage-1 >= 1) {
					choose[chooseItem] = "  ";
					chooseItem = 0;
					choose[chooseItem] = "<0";
					currentPage--;
					firstItem = (currentPage - 1) * 10;
					lastItem = firstItem + 10;
				}
			}
		}while(ch != 'X' && ch != 'x');
		farm.setFlag(true);
	}
	
	public void printCrops(String choose[],int chooseItem) {
		System.out.println("Plants");
		System.out.println(".---------------------------------.   .----------------------------------------.");
		System.out.println("| No. Name                        |   | Description                            |");
		System.out.println("|---------------------------------|   |----------------------------------------|");
		for(int i = 0,j = 0;i <= 10;i++,j++) {
			if(cropList.size() >= i+1) {					
				System.out.printf("| %2d. %-20s     %s |   ",i+1,cropList.get(i).getPlantName(),choose[i]);
			}
			else {
				System.out.print("|                                 |   ");
			}
			
			if(j >= 0 && j <= 3) {					
				if(cropList.size() >= 1) {
					if(j == 0) {							
						System.out.printf("| %-38s |",cropList.get(chooseItem).getPlantName());
					}
					else if(j == 2) {
						int dayLeft = cropList.get(chooseItem).getGrowTime() - (farm.getDayCount() - cropList.get(chooseItem).getPlantTime());
						System.out.printf("| Day until Harvest : %-18d |",dayLeft);
					}else {
						System.out.print("|                                        |");
					}
				}else {
					System.out.print("|                                        |");
				}
			}
			else if(j == 4) System.out.print("'----------------------------------------'");
			else if(j == 5) {
				if(cropList.size() >= 1) {					
					if(!cropList.get(chooseItem).isWatered()) {
						System.out.println("[M to water the plant]");
					}
				}
			}
			System.out.println();
		}
		System.out.println("|                                 |");
		System.out.println("'---------------------------------'");
		System.out.println("X to EXIT, W S to move UP and DOWN");;
		System.out.print(">> ");
	}
	
	public void printAnimal(String choose[],int chooseItem) {
		System.out.println("Animals");
		System.out.println(".---------------------------------.   .----------------------------------------.");
		System.out.println("| No. Name                        |   | Description                            |");
		System.out.println("|---------------------------------|   |----------------------------------------|");
		for(int i = 0,j = 0;i <= 10;i++,j++) {
			if(animalList.size() >= i+1) {					
				System.out.printf("| %2d. %-20s     %s |   ",i+1,animalList.get(i).getName(),choose[i]);
			}
			else {
				System.out.print("|                                 |   ");
			}
			
			if(j >= 0 && j <= 3) {					
				if(animalList.size() >= 1) {
					if(j == 0) {							
						System.out.printf("| Name : %-31s |",animalList.get(chooseItem).getName());
					}
					else if(j == 1) {
						if(animalList.get(chooseItem).getType().equals("chicken")) {
							System.out.printf("| Breed : %-30s |",animalList.get(chooseItem).getBreed());
						}
						else {
							System.out.print("|                                        |");
						}
					}else if(j == 2){
						System.out.printf("| Mood : %-31d |",animalList.get(chooseItem).getMood());
					}else {
						System.out.print("|                                        |");
					}
				}else {
					System.out.print("|                                        |");
				}
			}
			else if(j == 4) System.out.print("'----------------------------------------'");
			else if(j == 6) {
				if(animalList.size() >= 1) {					
					System.out.print("[K to kill animal]   ");	
					if(animalList.get(chooseItem).getType().equals("sheep")) {
						System.out.print("[E to collect Wool]");						
					}else {
						System.out.print("[E to collect Egg]");
					}
				}
			}else if(j == 7) {
				System.out.print("[G to pet animal]    ");
				if(animalList.size() >= 1) {					
					if(animalList.get(chooseItem).getType().equals("sheep")) {
						System.out.print("[M to milk sheep]");						
					}
				}
			}
			System.out.println();
		}
		System.out.println("|                                 |");
		System.out.println("'---------------------------------'");
		System.out.println("X to EXIT, W S to move UP and DOWN");;
		System.out.print(">> ");
	}
	
	public void manageLiveStock() {
		farm.setFlag(false);
		String [] choose = new String[15];
		choose[0] = "<0";
		for(int i = 1;i < 10;i++) {
			choose[i] = "  ";
		}
		int chooseItem = 0;
		char ch;
		do {
			printAnimal(choose,chooseItem);
			ch = scan.next().charAt(0); scan.nextLine();
			
			clear();
			if(ch == 'w' || ch == 'W') {
				if(chooseItem > 0) {
					choose[chooseItem] = "  ";
					chooseItem--;
					choose[chooseItem] = "<0";
				}
			}else if(ch == 's' || ch == 'S') {
				if(chooseItem < animalList.size()-1) {
					choose[chooseItem] = "  ";
					chooseItem++;
					choose[chooseItem] = "<0";	
				}
			}else if(ch == 'k' || ch == 'K') {
				player.setStamina(player.getStamina() - 7);
				if(animalList.get(chooseItem).getType().equals("sheep")) {
					printAnimal(choose,chooseItem);
					System.out.printf("[You killed %s for 4 Lamb Meat]",animalList.get(chooseItem).getName()); scan.nextLine();
					inventoryList.add(new Inventory("Lamb Meat",4,"Perfect for a steak party",100,"food"));
					clear();
				}else {
					printAnimal(choose,chooseItem);
					System.out.printf("[You killed %s for 3 Chicken Meat]",animalList.get(chooseItem).getName()); scan.nextLine();
					inventoryList.add(new Inventory("Chicken Meat",3,"Nuggets! Nuggets! Chicken nuggets!",125,"food"));
					clear();
				}
				animalList.remove(chooseItem);
				animalList.trimToSize();
				chooseItem = 0;
			}else if(ch == 'g' || ch == 'G') {
				player.setStamina(player.getStamina() - 5);
				printAnimal(choose,chooseItem);
				System.out.printf("[You petted %s. It loves you more!]",animalList.get(chooseItem).getName()); scan.nextLine();
				animalList.get(chooseItem).setMood(100);
				clear();
			}else if(ch == 'e' || ch == 'E') {
				if(animalList.get(chooseItem).getType().equals("sheep") && animalList.get(chooseItem).getMood() >= 50) {
					player.setStamina(player.getStamina() - 10);
					printAnimal(choose,chooseItem);
					System.out.printf("[Collected wool from %s]",animalList.get(chooseItem).getName()); scan.nextLine();
					inventoryList.add(new Inventory("Wool",2,"Winter is coming",220,"clothe"));
					clear();
				}else if(animalList.get(chooseItem).getType().equals("chicken") && animalList.get(chooseItem).getMood() >= 50){
					player.setStamina(player.getStamina() - 10);
					printAnimal(choose,chooseItem);
					System.out.printf("[Collected eeg from %s]",animalList.get(chooseItem).getName()); scan.nextLine();
					inventoryList.add(new Inventory("Egg",8,"An egg a day keeps the doctor away",25,"food"));
					clear();
				}
			}else if(ch == 'M' || ch == 'm') {
				if(animalList.get(chooseItem).getType().equals("sheep") && animalList.get(chooseItem).getMood() >= 50) {
					player.setStamina(player.getStamina() - 10);
					printAnimal(choose,chooseItem);
					System.out.printf("[Collected milk from %s]",animalList.get(chooseItem).getName()); scan.nextLine();
					inventoryList.add(new Inventory("Milk",1,"4 Healthy 5 Perfect",50,"food"));
					clear();
				}
			}else if(ch == 'x' || ch == 'X') {
				break;
			}
		}while(true);
		farm.setFlag(true);
	}
	
	public void manageCrops() {
		farm.setFlag(false);
		String [] choose = new String[15];
		choose[0] = "<0";
		for(int i = 1;i < 10;i++) {
			choose[i] = "  ";
		}
		int chooseItem = 0;
		char ch;
		do {
			printCrops(choose,chooseItem);
			ch = scan.next().charAt(0); scan.nextLine();
			
			clear();
			if(ch == 'w' || ch == 'W') {
				if(chooseItem > 0) {
					choose[chooseItem] = "  ";
					chooseItem--;
					choose[chooseItem] = "<0";
				}
			}else if(ch == 's' || ch == 'S') {
				if(chooseItem < cropList.size()-1) {
					choose[chooseItem] = "  ";
					chooseItem++;
					choose[chooseItem] = "<0";	
				}
			}else if(ch == 'm' || ch == 'M') {
				if(!cropList.get(chooseItem).isWatered()) {
					cropList.get(chooseItem).setWatered(true);
					printCrops(choose,chooseItem);
					player.setStamina(player.getStamina() - 7);
					System.out.println("[You watered this plant]"); scan.nextLine();
					if(player.getStamina() <= 0) {
						farm.setTime(7);
						Random rand = new Random();
						int chance = rand.nextInt(100) + 1;
						if(chance >= 1 && chance <= 20) {
							farm.setWeather("RAINY");
						}else {
							farm.setWeather("SUNNY");
						}
						farm.setDayCount(farm.getDayCount() + 1);
						
						System.out.println("[You faded out because of exhaustion]");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						String [] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
						System.out.printf("DAY %02d : [%s]",farm.getDayCount(),day[(farm.getDayCount()-1) % 7]);
						System.out.println("It's " + farm.getWeather() + " outside");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					clear();
				}
			}else if(ch == 'x' || ch == 'X') {
				break;
			}
		}while(true);
		
		farm.setFlag(true);
	}
	
	public void gameBarn() {
		do{
			printBarnMap();
			char ch = scan.next().charAt(0);
			
			clear();
			int posX = player.getPlayerBarnPosX();
			int posY = player.getPlayerBarnPosY();
			if(ch == 'W' || ch == 'w') {
				if(posX > 0 && classBarn.barnMap[posX-1][posY] == ' ' ) {
					classBarn.barnMap[posX][posY] = ' ';
					classBarn.barnMap[posX-1][posY] = '9';
					player.setPlayerBarnPosX(posX-1);
					player.setPlayerBarnPosY(posY);
				}
			}else if(ch == 'A' || ch == 'a') {
				if(posY > 0 && classBarn.barnMap[posX][posY-1] == ' ' ) {
					classBarn.barnMap[posX][posY] = ' ';
					classBarn.barnMap[posX][posY-1] = '9';
					player.setPlayerBarnPosX(posX);
					player.setPlayerBarnPosY(posY-1);
				}
			}else if(ch == 'S' || ch == 's') { 
				if(classBarn.barnMap[posX+1][posY] == ' ') {
					classBarn.barnMap[posX][posY] = ' ';
					classBarn.barnMap[posX+1][posY] = '9';
					player.setPlayerBarnPosX(posX+1);
					player.setPlayerBarnPosY(posY);
				}else if(posX == 10 && (posY == 19 || posY == 20 || posY == 21 || posY == 22) ){
					classBarn.barnMap[posX][posY] = ' ';
					player.setPlayerPosX(30);
					player.setPlayerPosY(posY + 83);
					classMap.map[30][posY+83] = '9';
					farm.setCurrentMap("MainMap");
					break;
				}
			}else if(ch == 'D' || ch == 'd') {
				if(classBarn.barnMap[posX][posY+1] == ' ' ) {
					classBarn.barnMap[posX][posY] = ' ';
					classBarn.barnMap[posX][posY+1] = '9';
					player.setPlayerBarnPosX(posX);
					player.setPlayerBarnPosY(posY+1);
				}
			}else if(ch == 'f' || ch == 'F') {
				manageLiveStock();
			}else if(ch == 'I' || ch == 'i') {
				inventory();
			}else if(ch == 'P' || ch == 'p') {
				shop();
			}else if(ch == 'Y' || ch == 'y') {
				
			}else if(ch == 'X' || ch == 'x') {
				
			}
			clear();
		}while(true);
	}
	
	public void gameGreenHouse() {
		do{
			printGreenHouseMap();
			char ch = scan.next().charAt(0);
			
			clear();
			int posX = player.getPlayerGreenHousePosX();
			int posY = player.getPlayerGreenHousePosY();
			if(ch == 'W' || ch == 'w') {
				if(posX > 0 && classGreenHouse.greenHouseMap[posX-1][posY] == ' ' ) {
					classGreenHouse.greenHouseMap[posX][posY] = ' ';
					classGreenHouse.greenHouseMap[posX-1][posY] = '9';
					player.setPlayerGreenHousePosX(posX-1);
					player.setPlayerGreenHousePosY(posY);
				}
			}else if(ch == 'A' || ch == 'a') {
				if(posY > 0 && classGreenHouse.greenHouseMap[posX][posY-1] == ' ' ) {
					classGreenHouse.greenHouseMap[posX][posY] = ' ';
					classGreenHouse.greenHouseMap[posX][posY-1] = '9';
					player.setPlayerGreenHousePosX(posX);
					player.setPlayerGreenHousePosY(posY-1);
				}
			}else if(ch == 'S' || ch == 's') { 
				if(classGreenHouse.greenHouseMap[posX+1][posY] == ' ') {
					classGreenHouse.greenHouseMap[posX][posY] = ' ';
					classGreenHouse.greenHouseMap[posX+1][posY] = '9';
					player.setPlayerGreenHousePosX(posX+1);
					player.setPlayerGreenHousePosY(posY);
				}else if(posX == 10 && (posY == 19 || posY == 20 || posY == 21 || posY == 22) ){
					classGreenHouse.greenHouseMap[posX][posY] = ' ';
					player.setPlayerPosX(9);
					player.setPlayerPosY(posY + 32);
					classMap.map[9][posY+32] = '9';
					farm.setCurrentMap("MainMap");
					break;
				}
			}else if(ch == 'D' || ch == 'd') {
				if(classGreenHouse.greenHouseMap[posX][posY+1] == ' ' ) {
					classGreenHouse.greenHouseMap[posX][posY] = ' ';
					classGreenHouse.greenHouseMap[posX][posY+1] = '9';
					player.setPlayerGreenHousePosX(posX);
					player.setPlayerGreenHousePosY(posY+1);
				}
			}else if(ch == 'f' || ch == 'F') {
				manageCrops();
			}else if(ch == 'I' || ch == 'i') {
				inventory();
			}else if(ch == 'P' || ch == 'p') {
				shop();
			}else if(ch == 'Y' || ch == 'y') {
				
			}else if(ch == 'X' || ch == 'x') {
				
			}
			clear();
		}while(true);
	}
	
	public void gameRestArea() {
		do{
			printRestAreaMap();
			char ch = scan.next().charAt(0); scan.nextLine();
			
			clear();
			int posX = player.getPlayerRestAreaPosX();
			int posY = player.getPlayerRestAreaPosY();
			if(ch == 'W' || ch == 'w') {
				if(posX > 0 && classRestArea.restAreaMap[posX-1][posY] == ' ' ) {
					classRestArea.restAreaMap[posX][posY] = ' ';
					classRestArea.restAreaMap[posX-1][posY] = '9';
					player.setPlayerRestAreaPosX(posX-1);
					player.setPlayerRestAreaPosY(posY);
				}
			}else if(ch == 'A' || ch == 'a') {
				if(posY > 0 && classRestArea.restAreaMap[posX][posY-1] == ' ' ) {
					classRestArea.restAreaMap[posX][posY] = ' ';
					classRestArea.restAreaMap[posX][posY-1] = '9';
					player.setPlayerRestAreaPosX(posX);
					player.setPlayerRestAreaPosY(posY-1);
				}
			}else if(ch == 'S' || ch == 's') { 
				if(classRestArea.restAreaMap[posX+1][posY] == ' ') {
					classRestArea.restAreaMap[posX][posY] = ' ';
					classRestArea.restAreaMap[posX+1][posY] = '9';
					player.setPlayerRestAreaPosX(posX+1);
					player.setPlayerRestAreaPosY(posY);
				}else if(posX == 11 && (posY == 15 || posY == 16 || posY == 17 || posY == 18 || posY == 19 || posY == 20) ){
					classRestArea.restAreaMap[posX][posY] = ' ';
					player.setPlayerPosX(4);
					player.setPlayerPosY(posY + 43);
					classMap.map[4][posY+43] = '9';
					farm.setCurrentMap("MainMap");
					break;
				}
			}else if(ch == 'D' || ch == 'd') {
				if(classRestArea.restAreaMap[posX][posY+1] == ' ' ) {
					classRestArea.restAreaMap[posX][posY] = ' ';
					classRestArea.restAreaMap[posX][posY+1] = '9';
					player.setPlayerRestAreaPosX(posX);
					player.setPlayerRestAreaPosY(posY+1);
				}
			}else if(ch == 'Q' || ch == 'q') {
				sleep();
			}else if(ch == 'I' || ch == 'i') {
				inventory();
			}else if(ch == 'P' || ch == 'p') {
				shop();
			}else if(ch == 'Y' || ch == 'y') {
				
			}else if(ch == 'X' || ch == 'x') {
				
			}
			clear();
		}while(true);
	}
	
	public void game() {
		int grassX = 0;
		int grassY = 0;
		do {
			boolean flagGrass = false;
			printMap();
			char ch = scan.next().charAt(0); scan.nextLine();
			clear();
			int posX = player.getPlayerPosX();
			int posY = player.getPlayerPosY();
			if(ch == 'W' || ch == 'w') {
				if(posX > 0 && classMap.map[posX-1][posY] == ' '  || classMap.map[posX-1][posY] == 'v' ) {
					if(classMap.map[posX-1][posY] == 'v') {
						flagGrass = true;
					}
					classMap.map[posX][posY] = ' ';
					classMap.map[posX-1][posY] = '9';
					player.setPlayerPosX(posX-1);
					player.setPlayerPosY(posY);
				}else if(posX == 4 && (posY == 58 || posY == 59 || posY == 60 || posY == 61 || posY == 62 || posY == 63)) {
					int posAreaX = 11;
					int posAreaY = posY - 43;
					classMap.map[posX][posY] = ' ';
					classRestArea.restAreaMap[posAreaX][posAreaY] = '9';
					player.setPlayerRestAreaPosX(posAreaX);
					player.setPlayerRestAreaPosY(posAreaY);
					farm.setCurrentMap("RestMap");
					gameRestArea();
				}else if(posX == 30 && (posY == 100 || posY == 101 || posY == 102 || posY == 103 || posY == 104 || posY == 105 || posY == 106)) {
					int posAreaX = 10;
					int posAreaY = -1;
					if(posY >= 100 && posY <= 101){
						posAreaY = 19;	
					}
					else if(posY >= 102 && posY <= 103) {
						posAreaY = 20;
					}else if(posY == 104) {
						posAreaY = 21;
					}else if(posY >= 105 && posY <= 106) {
						posAreaY = 22;
					}
					classMap.map[posX][posY] = ' ';
					classBarn.barnMap[posAreaX][posAreaY] = '9';
					player.setPlayerBarnPosX(posAreaX);
					player.setPlayerBarnPosY(posAreaY);
					farm.setCurrentMap("BarnMap");
					gameBarn();
				}
				if(grassX != 0 && grassY != 0 && classMap.map[grassX][grassY] == ' ') {
					classMap.map[grassX][grassY] = 'v';
				}
				if(flagGrass) {
					grassX = posX-1;
					grassY = posY;
				}
			}else if(ch == 'A' || ch == 'a') {
				if(posY > 0 && classMap.map[posX][posY-1] == ' '  || classMap.map[posX][posY-1] == 'v') {
					if(classMap.map[posX][posY-1] == 'v') {
						flagGrass = true;
					}
					classMap.map[posX][posY] = ' ';
					classMap.map[posX][posY-1] = '9';
					player.setPlayerPosX(posX);
					player.setPlayerPosY(posY-1);
				}
				if(grassX != 0 && grassY != 0 && classMap.map[grassX][grassY] == ' ') {
					classMap.map[grassX][grassY] = 'v';
				}
				if(flagGrass) {
					grassX = posX;
					grassY = posY-1;
				}
			}else if(ch == 'S' || ch == 's') { 
				if(posX < 32 && classMap.map[posX+1][posY] == ' ' ||  classMap.map[posX+1][posY] == 'v') {
					if(classMap.map[posX+1][posY] == 'v') {
						flagGrass = true;
					}
					classMap.map[posX][posY] = ' ';
					classMap.map[posX+1][posY] = '9';
					player.setPlayerPosX(posX+1);
					player.setPlayerPosY(posY);
				}else if(posX == 9 && (posY == 51 || posY == 52 || posY == 53 || posY == 54)) {
					int posAreaX = 10;
					int posAreaY = posY -32;
					classMap.map[posX][posY] = ' ';
					classGreenHouse.greenHouseMap[posAreaX][posAreaY] = '9';
					player.setPlayerGreenHousePosX(posAreaX);
					player.setPlayerGreenHousePosY(posAreaY);
					farm.setCurrentMap("GreenHouseMap");
					gameGreenHouse();
				}
				if(grassX != 0 && grassY != 0 && classMap.map[grassX][grassY] == ' ') {
					classMap.map[grassX][grassY] = 'v';
				}
				if(flagGrass) {
					grassX = posX+1;
					grassY = posY;
				}
			}else if(ch == 'D' || ch == 'd') {
				if(posY < 119 && classMap.map[posX][posY+1] == ' ' || classMap.map[posX][posY+1] == 'v') {
					if(classMap.map[posX][posY+1] == 'v') {
						flagGrass = true;
					}
					classMap.map[posX][posY] = ' ';
					classMap.map[posX][posY+1] = '9';
					player.setPlayerPosX(posX);
					player.setPlayerPosY(posY+1);
					if(grassX != 0 && grassY != 0 && classMap.map[grassX][grassY] == ' ') {
						classMap.map[grassX][grassY] = 'v';
					}
					if(flagGrass) {
						grassX = posX;
						grassY = posY+1;
					}
				}
			}else if(ch == 'I' || ch == 'i') {
				inventory();
			}else if(ch == 'P' || ch == 'p') {
				shop();
			}else if(ch == 'Y' || ch == 'y') {
				
			}else if(ch == 'X' || ch == 'x') {
				
			}
			clear();
		}while(true);
		
	}
	
	public Main() {
		int choose = -1;
		do {
			System.out.println("                                 _");
			System.out.println("  /\\  /\\__ _ _ ____   _____  ___| |_");
			System.out.println(" / /_/ / _` | '__\\ \\ / / _ \\/ __| __|");
			System.out.println("/ __  / (_| | |   \\ V /  __/\\__ \\ |_");
			System.out.println("\\/ /_/ \\__,_|_|    \\_/ \\___||___/\\__|\n");
			System.out.println("                            _");
			System.out.println("  /\\/\\   ___   ___  _ __   /_\\");
			System.out.println(" /    \\ / _ \\ / _ \\| '_ \\ //_\\\\");
			System.out.println("/ /\\/\\ \\ (_) | (_) | | | /  _  \\");
			System.out.println("\\/    \\/\\___/ \\___/|_| |_\\_/ \\_/\n");
			System.out.println("     .--------------------.");
			System.out.println("     | 1. Start New Game  |");
			System.out.println("     | 2. Load Game       |");
			System.out.println("     | 3. How To Play     |");
			System.out.println("     | 4. Exit            |");
			System.out.println("     '--------------------'");
			System.out.print("     >> ");
			try {
				choose = scan.nextInt();
			}catch (Exception e) {
				// TODO: handle exception
			}scan.nextLine();
			
			clear();
			switch(choose) {
			case 1:
				startNewGame();
				break;
			case 2:
				break;
			case 3:
				howToPlay();
				break;
			}
			clear();
		}while(choose != 4);
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void printBarnMap() {
		int playerRow = player.getPlayerBarnPosX();
		int playerColumn = player.getPlayerBarnPosY();
		if(playerColumn - 15 >= 0){
			if(playerColumn <= 25) {
				playerColumn-= 15;				
			}else {
				playerColumn = 11;
			}
		}else {
			playerColumn = 0;
		}
		
		if(playerRow - 5 >= 0){
			if(playerRow <= 6) {
				playerRow-= 5;				
			}else {
				playerRow = 1;
			}
		}else {
			playerRow = 0;
		}
		
		char [] plantedAnimal = new char[10];
		for(int i = 0;i < 10;i++) {
			if(animalList.size() >= i+1) {
				plantedAnimal[i] = 'Y';
			}else {
				plantedAnimal[i] = ' ';
			}
		}
		
		classBarn.barnMap[3][3] = plantedAnimal[0];
		classBarn.barnMap[3][31] = plantedAnimal[1];
		classBarn.barnMap[5][3] = plantedAnimal[2];
		classBarn.barnMap[5][31] = plantedAnimal[3];
		classBarn.barnMap[7][3] = plantedAnimal[4];
		classBarn.barnMap[7][31] = plantedAnimal[5];
		classBarn.barnMap[9][3] = plantedAnimal[6];
		classBarn.barnMap[9][31] = plantedAnimal[7];

//		System.out.println(player.getPlayerBarnPosX() + " " + player.getPlayerBarnPosY());
		String [] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		System.out.printf("[D%02d]:%s [%02d:00] WEATHER: [%s]\n",farm.getDayCount(),day[(farm.getDayCount()-1) % 7],farm.getTime(),farm.getWeather());
		for(int i = 0;i < 11;i++,playerRow++) {
			if(i == 0) System.out.println(".---------------------------------.");
			for(int j = 0,k = playerColumn;j < 31;j++,k++) {
				if(j == 0) System.out.print("| ");
				System.out.print(classBarn.barnMap[playerRow][k]);
				if(j == 30) System.out.print(" |");
			}
			if(i == 0) System.out.print("  > Name: ");
			else if(i == 1) System.out.print("     " + player.getName());
			else if(i == 3) System.out.print("  > Gold: " + player.getGold());
			else if(i == 4) System.out.print("  > Stam: " + player.getStamina() + "/100");
			System.out.println();
			if(i == 10) System.out.println(".---------------------------------.\n");
		}
		System.out.println("Input W A S D to move | I inventory | P shop | Y save game | X exit game");
		System.out.println("F to manage Livestocks");
		System.out.print(">> ");
	}
	
	public void printGreenHouseMap() {
		int playerRow = player.getPlayerGreenHousePosX();
		int playerColumn = player.getPlayerGreenHousePosY();
		
		if(playerColumn - 15 >= 0){
			if(playerColumn <= 25) {
				playerColumn-= 15;				
			}else {
				playerColumn = 11;
			}
		}else {
			playerColumn = 0;
		}
		
		if(playerRow - 5 >= 0){
			if(playerRow <= 6) {
				playerRow-= 5;				
			}else {
				playerRow = 1;
			}
		}else {
			playerRow = 0;
		}
		
		char [] plantedPlant = new char[10];
		for(int i = 0;i < 10;i++) {
			if(cropList.size() >= i+1) {
				plantedPlant[i] = 'v';
			}else {
				plantedPlant[i] = ' ';
			}
		}
		
		classGreenHouse.greenHouseMap[2][4] = plantedPlant[0];
		classGreenHouse.greenHouseMap[2][12] = plantedPlant[1];
		classGreenHouse.greenHouseMap[2][20] = plantedPlant[2];
		classGreenHouse.greenHouseMap[2][28] = plantedPlant[3];
		classGreenHouse.greenHouseMap[2][36] = plantedPlant[4];
		classGreenHouse.greenHouseMap[4][4] = plantedPlant[5];
		classGreenHouse.greenHouseMap[4][12] = plantedPlant[6];
		classGreenHouse.greenHouseMap[4][20] = plantedPlant[7];
		classGreenHouse.greenHouseMap[4][28] = plantedPlant[8];
		classGreenHouse.greenHouseMap[4][36] = plantedPlant[9];

//		System.out.println(player.getPlayerGreenHousePosX() + " " + player.getPlayerGreenHousePosY());
		String [] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		System.out.printf("[D%02d]:%s [%02d:00] WEATHER: [%s]\n",farm.getDayCount(),day[(farm.getDayCount()-1) % 7],farm.getTime(),farm.getWeather());
		for(int i = 0;i < 11;i++,playerRow++) {
			if(i == 0) System.out.println(".---------------------------------.");
			for(int j = 0,k = playerColumn;j < 31;j++,k++) {
				if(j == 0) System.out.print("| ");
				System.out.print(classGreenHouse.greenHouseMap[playerRow][k]);
				if(j == 30) System.out.print(" |");
			}
			if(i == 0) System.out.print("  > Name: ");
			else if(i == 1) System.out.print("     " + player.getName());
			else if(i == 3) System.out.print("  > Gold: " + player.getGold());
			else if(i == 4) System.out.print("  > Stam: " + player.getStamina() + "/100");
			System.out.println();
			if(i == 10) System.out.println(".---------------------------------.\n");
		}
		System.out.println("Input W A S D to move | I inventory | P shop | Y save game | X exit game");
		System.out.println("F to manage Crops");
		System.out.print(">> ");
	}
	
	public void printRestAreaMap() {
		int playerRow = player.getPlayerRestAreaPosX();
		int playerColumn = player.getPlayerRestAreaPosY();
		
		if(playerColumn - 15 >= 0){
			if(playerColumn <= 20) {
				playerColumn-= 15;				
			}else {
				playerColumn = 5;
			}
		}else {
			playerColumn = 0;
		}
		
		if(playerRow - 5 >= 0){
			if(playerRow <= 6) {
				playerRow-= 5;				
			}else {
				playerRow = 2;
			}
		}else {
			playerRow = 0;
		}
		
//		System.out.println(player.getPlayerRestAreaPosX() + " " + player.getPlayerRestAreaPosY());
//		System.out.println(playerRow + " " + playerColumn);
		String [] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		System.out.printf("[D%02d]:%s [%02d:00] WEATHER: [%s]\n",farm.getDayCount(),day[(farm.getDayCount()-1) % 7],farm.getTime(),farm.getWeather());
		for(int i = 0;i < 11;i++,playerRow++) {
			if(i == 0) System.out.println(".---------------------------------.");
			for(int j = 0,k = playerColumn;j < 31;j++,k++) {
				if(j == 0) System.out.print("| ");
				System.out.print(classRestArea.restAreaMap[playerRow][k]);
				if(j == 30) System.out.print(" |");
			}
			if(i == 0) System.out.print("  > Name: ");
			else if(i == 1) System.out.print("     " + player.getName());
			else if(i == 3) System.out.print("  > Gold: " + player.getGold());
			else if(i == 4) System.out.print("  > Stam: " + player.getStamina() + "/100");
			System.out.println();
			if(i == 10) System.out.println(".---------------------------------.\n");
		}
		System.out.println("Input W A S D to move | I inventory | P shop | Y save game | X exit game");
		System.out.println("Q to sleep");
		System.out.print(">> ");
	}
	
	public void printMap() {
		int playerRow = player.getPlayerPosX();
		int playerColumn = player.getPlayerPosY();
		
		if(playerColumn - 15 >= 0){
			if(playerColumn <= 104) {
				playerColumn-= 15;				
			}else {
				playerColumn = 89;
			}
		}else {
			playerColumn = 0;
		}
		
		if(playerRow - 5 >= 0) {
			if(playerRow <= 27) {				
				playerRow -= 5;
			}
			else {
				playerRow = 22;
			}
		}else {
			playerRow = 0;
		}
		
//		System.out.println(player.getPlayerPosX() + " " + player.getPlayerPosY());
		String [] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		System.out.printf("[D%02d]:%s [%02d:00] WEATHER: [%s]\n",farm.getDayCount(),day[(farm.getDayCount()-1) % 7],farm.getTime(),farm.getWeather());
		for(int i = 0;i < 11;i++,playerRow++) {
			if(i == 0) System.out.println(".---------------------------------.");
			for(int j = 0,k = playerColumn;j < 31;j++,k++) {
				if(j == 0) System.out.print("| ");
				System.out.print(classMap.map[playerRow][k]);
				if(j == 30) System.out.print(" |");
			}
			if(i == 0) System.out.print("  > Name: ");
			else if(i == 1) System.out.print("     " + player.getName());
			else if(i == 3) System.out.print("  > Gold: " + player.getGold());
			else if(i == 4) System.out.print("  > Stam: " + player.getStamina() + "/100");
			System.out.println();
			if(i == 10) System.out.println(".---------------------------------.");
		}
		System.out.println("Input W A S D to move | I inventory | P shop | Y save game | X exit game\n");
		System.out.print(">> ");
	}
	
	public void printInventory(int firstItem,int lastItem,int currentPage,int totalPage,String [] choose,int chooseItem) {
		System.out.println("Inventory");
		System.out.println(".---------------------------------.   .----------------------------------------.");
		System.out.println("| No. Name                 Qty    |   | Description                            |");
		System.out.println("|---------------------------------|   |----------------------------------------|");
		for(int i = firstItem,j = 0;i < lastItem;i++,j++) {
			if(inventoryList.size() >= i+1) {					
				System.out.printf("| %2d. %-20s %3d %s |   ",i+1,inventoryList.get(i).getName(),inventoryList.get(i).getQty(),choose[i-firstItem]);
			}
			else {
				System.out.print("|                                 |   ");
			}
			
			if(j >= 0 && j <= 5) {					
				if(inventoryList.size() >= 1) {
					if(j == 0) {							
						System.out.printf("| %-38s |",inventoryList.get(chooseItem).getDescription());
					}
					else if(j == 2) {
						System.out.printf("| Qty : %-32d |",inventoryList.get(chooseItem).getQty());
					}else if(j == 3) {
						System.out.printf("| Sell Price : %-25d |",inventoryList.get(chooseItem).getPrice());
					}else {
						System.out.print("|                                        |");
					}
				}else {
					System.out.print("|                                        |");
				}
			}
			else if(j == 6) System.out.print("'----------------------------------------'");
			if(j == 7) System.out.print("[E to USE this item]");
			System.out.println();
		}
		System.out.printf("|                              %d/%d|\n",currentPage,totalPage);
		System.out.println("'---------------------------------'");
		System.out.println("X to EXIT, W S to move UP and DOWN");
		System.out.println("A S to move the page RIGHT and LEFT");
		System.out.println("L to sell the item.");
	}
	
	public void howToPlay() {
		System.out.println("Welcome to Harvest MoonA Lite!\n");
		System.out.println("[Short Description]");
		System.out.println("Harvest MoonA is a farm simulation role-playing game developed by some");
		System.out.println("anonymous programmer in the early 2021.\n");
		System.out.println("There are several things that you can do:\n");
		System.out.println("1. Raise animals and you can collect some goods from them.");
		System.out.println("2. Plant a plants and then water it for about everyday so you can harvest it");
		System.out.println("   later, the moment it grows.");
		System.out.println("3. Cook. The goods collected from the plants and animals can be used for making");
		System.out.println("   a food! You can consume the food to gain staminas.\n");
		System.out.println("Press enter to continue..."); scan.nextLine();
		clear();
		System.out.println("[How To Play]\n");
		System.out.println("1. Start a new game if you haven't play the game yet. If you have a saved game,");
		System.out.println("   just go to the load game and then choose the account that you want to play for.");
		System.out.println("2. If you're a new player, you will receive some basic stats.");
		System.out.println("   (Gold 500, Stamina 100, Day 1, Weather Sunny)");
		System.out.println("3. Input W A S D to move. I to access inventory. P to shop. Y to save game.");
		System.out.println("   X to exit the game.");
		System.out.println("4. Plant a seed in the greenhouse (in the middle of the map) and then don't");
		System.out.println("   forget to water it everyday so it can grow!");
		System.out.println("5. Visit the barn ( in the southeast ) and then try to raise an animal so it can");
		System.out.println("   gives you some goods!");
		System.out.println("6. You can visit the cook place ( in the southwest ) to cook some food.");
		System.out.println("7. If you don't have other things to do in that day,");
		System.out.println("   just visit the rest area ( in the north ) and then input Q to go sleep, skip");
		System.out.println("   into the next day. If you're still awake until 23:00, you will automatically skipped into the next day.");
		System.out.println("8. Discover more feature in the game! Have fun.\n");
		System.out.println("Press enter to continue..."); scan.nextLine();
	}
	
	public void clear() {
		for(int i = 0;i < 50;i++) System.out.println();
	}
	
}
