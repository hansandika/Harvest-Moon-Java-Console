	
public class Inventory {
	public String name;
	public int qty;
	public String description;
	private int price;
	private String placeUsed;
	
	public Inventory(String name, int qty, String description, int price,String placeUsed) {
		this.name = name;
		this.qty = qty;
		this.description = description;
		this.price = price;
		this.placeUsed = placeUsed;
	}
	
	
	public String getPlaceUsed() {
		return placeUsed;
	}
	public void setPlaceUsed(String placeUsed) {
		this.placeUsed = placeUsed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
