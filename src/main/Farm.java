package main;
import java.util.ArrayList;

public class Farm {
	
	private String farmName;
	private String farmType;
	private Farmer farmer;
	private ArrayList<Crop> crops;
	private ArrayList<Animal> animal;
	private double money;
	private int freeSpace;
	
	
	public Farm(String name, String type, Farmer farmer) 
	{
        farmName = name;
        farmType = type;
        farmer = farmer
    }
	
	/**
	 *	Tends to the Farm to make animals happier and increase space available
	 */
	public void tendFarm()
	{
		//Some cool stuff
		freeSpace++;
	}
	
	public double harvestCrops()
	{
		double moneyMade = 0;
		
		for(Crop crop: crops) 
		{
			if (crop.harvestDays == 0)
			{
				//Harvest here
				moneyMade += crop.sellPrice;
			}
		}
		
		return moneyMade;
	}
		
	
	
}
