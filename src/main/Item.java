package main;

/**
 * Item class where items are extended from.
 * Here the user can get the bonus of the Item.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Item implements StoreItem
{

	/**
	 * The name of the item.
	 */
	private String itemName;
	
	/**
	 * The type of the item, type can be "Crop" or "Animal".
	 */
	private String type;
	
	/**
	 * The purchase price of the item.
	 */
	private double purchasePrice;
	
	/**
	 * The bonus of the item.
	 */
	private double bonus;
	
	/**
	 * The Constructor function for Item Class, this constructor initialises variables <code>itemName</code>, <code>type</code>, <code>purchasePrice</code> and <code>bonus</code>.
	 * @param name Name of the Item
	 * @param initType Type of the Item.
	 * @param price Price of the Item
	 * @param initBonus Bonus of the Item.
	 */
	public Item(String name, String initType, double price, double initBonus)
	{
		itemName = name;
		type = initType;
		purchasePrice = price;
		bonus = initBonus;
		
	}
	
	/**
	 * for copying an item class (this is used when you buy an item)
	 * @param item Item Class.
	 */
	public Item(Item item)
	{
		itemName = item.getName();
		type = item.getType();
		purchasePrice = item.getPurchasePrice();
		bonus = item.getBonus();
		
	}
	
	/**
	 * Returns the name of the item.
	 * @return the name of item.
	 */
	public String getName()
	{
		return itemName;
	}
	
	/**
	 * Returns the purchase price of the item.
	 * @return The purchase price of item.
	 */
	public double getPurchasePrice() 
	{
		return purchasePrice;
	}
	
	/**
	 * Returns the bonus given by the item.
	 * @return The bonus of item.
	 */
	public Double getBonus()
	{
		return bonus;
	}
	
	/**
	 * Returns the type of the item.
	 * @return The type of item.
	 */
	public String getType()
	{
		return type;
	}

	
	
	
}
