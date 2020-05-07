package main;

public class Item{

	private String itemName;
	private String type;//Type item can be used on (crop or sheep)
	private double purchasePrice;
	private double bonus;//idk what the  bonus should be, increase sell price and increase growth speed?
	
	public Item(String name, String initType, double price, double initBonus)
	{
		itemName = name;
		type = initType;
		purchasePrice = price;
		bonus = initBonus;
	}
	
	
}
