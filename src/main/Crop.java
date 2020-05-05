package main;

public class Crop extends Store{

	private String cropName;
	private double purchasePrice;
	private double sellPrice;
	private int harvestDays;
	
	public Crop(String name, double initPurchasePrice,double initSellPrice, int initHarvestDays)
	{
		cropName = name;
		purchasePrice = initPurchasePrice;
		sellPrice = initSellPrice;
		harvestDays = initHarvestDays;
	}
	
	public Boolean canHarvest() 
	{
		if (harvestDays <= 0)
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
	
	public void grow()
	{
		harvestDays--;
	}
	
	
}
