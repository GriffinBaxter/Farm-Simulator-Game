package main;

public class Crop {

	private String cropName;
	private Double purchasePrice;
	private Double sellPrice;
	private int harvestDays;
	
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
	
	
}
