package main_cli;

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
	
	/**
	 * for copying an item class (this is used when you buy an item)
	 * @param item
	 */
	public Item(Item item)
	{
		itemName = item.getName();
		type = item.getType();
		purchasePrice = item.getPurchasePrice();
		bonus = item.getBonus();
		
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