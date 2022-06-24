
public class Farm{
	private int dayCount;
	private String weather;
	private int time;
	private boolean flag;
	private String currentMap;
	
	public Farm(int dayCount,String weather,int time) {
		this.dayCount = dayCount;
		this.weather = weather;
		this.time = time;
		this.flag = true;
		this.currentMap = "MainMap";
	}
	
	
	public String getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(String currentMap) {
		this.currentMap = currentMap;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}

}
