package main;

public class Crop{

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
	
	public Boolean canHarvest() 
	{
		if (daysGrown == daysToGrow)
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
	
	public String getCropName() {
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
	
	// Deprecated, now using getter methods from the GameEnvironment class, keeping here for now just in case testing is required
	/*
	public void printCrop()
	{
		System.out.println(cropName + " Has been growing for " + daysGrown 
				+ " days, it needs " + (daysToGrow - daysGrown) 
				+ " more days to be harvested");
				
	}*/
	
	
}
