
public class Plant {
	
	private String plantName;
	private int plantTime;
	private int growTime;
	public boolean watered;
	
	public Plant(String plantName, int plantTime, int growTime) {
		this.plantName = plantName;
		this.plantTime = plantTime;
		this.growTime = growTime;
		this.watered = false;
	}
	
	public boolean isWatered() {
		return watered;
	}
	public void setWatered(boolean watered) {
		this.watered = watered;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public int getPlantTime() {
		return plantTime;
	}
	public void setPlantTime(int plantTime) {
		this.plantTime = plantTime;
	}
	public int getGrowTime() {
		return growTime;
	}
	public void setGrowTime(int growTime) {
		this.growTime = growTime;
	}
	
	
}
