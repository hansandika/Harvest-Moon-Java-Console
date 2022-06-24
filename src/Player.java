
public class Player {
	private String name;
	private int gold;
	private int stamina;
	private int playerPosX;
	private int playerPosY;
	private int playerRestAreaPosX;
	private int playerRestAreaPosY;
	private int playerGreenHousePosX;
	private int playerGreenHousePosY;
	private int playerBarnPosX;
	private int playerBarnPosY;
	
	public Player(String name) {
		this.name = name;
		this.gold = 500;
		this.stamina = 100;
		this.playerPosX = 5;
		this.playerPosY = 61;
	}
	
	
	public int getPlayerBarnPosX() {
		return playerBarnPosX;
	}

	public void setPlayerBarnPosX(int playerBarnPosX) {
		this.playerBarnPosX = playerBarnPosX;
	}

	public int getPlayerBarnPosY() {
		return playerBarnPosY;
	}

	public void setPlayerBarnPosY(int playerBarnPosY) {
		this.playerBarnPosY = playerBarnPosY;
	}

	public int getPlayerGreenHousePosX() {
		return playerGreenHousePosX;
	}

	public void setPlayerGreenHousePosX(int playerGreenHousePosX) {
		this.playerGreenHousePosX = playerGreenHousePosX;
	}

	public int getPlayerGreenHousePosY() {
		return playerGreenHousePosY;
	}

	public void setPlayerGreenHousePosY(int playerGreenHousePosY) {
		this.playerGreenHousePosY = playerGreenHousePosY;
	}

	public int getPlayerRestAreaPosX() {
		return playerRestAreaPosX;
	}

	public void setPlayerRestAreaPosX(int playerRestAreaPosX) {
		this.playerRestAreaPosX = playerRestAreaPosX;
	}

	public int getPlayerRestAreaPosY() {
		return playerRestAreaPosY;
	}

	public void setPlayerRestAreaPosY(int playerRestAreaPosY) {
		this.playerRestAreaPosY = playerRestAreaPosY;
	}

	public int getPlayerPosX() {
		return playerPosX;
	}

	public void setPlayerPosX(int playerPosX) {
		this.playerPosX = playerPosX;
	}

	public int getPlayerPosY() {
		return playerPosY;
	}


	public void setPlayerPosY(int playerPosY) {
		this.playerPosY = playerPosY;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	

	
	
}
