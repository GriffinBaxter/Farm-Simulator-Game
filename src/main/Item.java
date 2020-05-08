package main;

public class Item implements StoreItem{

	private String itemName;
	private String type;//Type item can be used on ("Crop" or "Animal")
	private double purchasePrice;
	private double bonus;
	
	public Item(String name, String initType, double price, double initBonus)
	{
		itemName = name;
		type = initType;
		purchasePrice = price;
		bonus = initBonus;
		
	}
	
	public String getName()
	{
		return itemName;
	}
	
	public double getPurchasePrice() 
	{
		return purchasePrice;
	}
	public Double getBonus()
	{
		return bonus;
	}
	public String getType()
	{
		return type;
	}

	
	
	
}
