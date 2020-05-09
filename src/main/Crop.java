package main;

public class Crop implements StoreItem{

	private String cropName;
	private double purchasePrice;
	private double sellPrice;
	private int daysToGrow;
	private int daysGrown = 0;
	
	public Crop(String name, double initPurchasePrice,double initSellPrice, int initDaysToGrow)
	{
		cropName = name;
		purchasePrice = initPurchasePrice;
		sellPrice = initSellPrice;
		daysToGrow = initDaysToGrow;
	}
	
	/**
	 * for copying an crop class (this is used when you buy an crop)
	 * @param crop
	 */
	public Crop(Crop crop)
	{
		cropName = crop.getName();
		purchasePrice = crop.getPurchasePrice();
		sellPrice = crop.getSellPrice();
		daysToGrow = crop.getDaysToGrow();
		daysGrown = 0;
	}
	
	public Boolean canHarvest() 
	{
		if (daysGrown >= daysToGrow)
		{
			return true;
		}
		return false;
	}
	
	public double getPurchasePrice()
	{
		return purchasePrice;
	}	
	
	public double getSellPrice()
	{
		return sellPrice;
	}
	
	public String getName() {
		return cropName;
	}
	
	public int getDaysGrown() {
		return daysGrown;
	}
	
	public int getDaysToGrow() {
		return daysToGrow;
	}
	
	public void grow()
	{
		daysGrown++;
	}
	
	public void tend(double daysToIncrease)
	{
		daysGrown += daysToIncrease;
	}
	
	
}
