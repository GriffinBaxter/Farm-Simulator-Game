package main;

public class Crop extends Store{

	private String cropName;
	private Double purchasePrice;
	private Double sellPrice;
	private int harvestDays;
	
	public Crop(String name, Double initPurchasePrice,Double initSellPrice, int initHarvestDays)
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
	
	public Double getPurchasePrice()
	{
		return purchasePrice;
	}	
	
	public Double getSellPrice()
	{
		return sellPrice;
	}
	
	public void grow()
	{
		harvestDays--;
	}
	
	
}
