import java.util.Random;

public class Animal {
	private String name;
	private int mood;
	private String breed;
	private String type;
	
	public Animal(String name,String type) {
		this.name = name;	
		this.type = type;
		if(type.equals("sheep")) {
			this.breed = "";
		}else {
			Random rand = new Random();
			int chance = rand.nextInt(2);
			if(chance == 1) {				
				this.breed = "Leghorn";
			}else {
				this.breed = "Cochin";
			}
		}
		this.mood = 100;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
