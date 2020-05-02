package main;

public class Item extends Store{

	private String itemName;
	private String type;//Type item can be used on (crop or sheep)
	private Double purchasePrice;
	private Double bonus;//idk what the  bonus should be, increase sell price and increase growth speed?
	
	public Item(String name, String initType, Double price, Double initBonus)
	{
		itemName = name;
		type = initType;
		purchasePrice = price;
		bonus = initBonus;
	}
	
	
}
