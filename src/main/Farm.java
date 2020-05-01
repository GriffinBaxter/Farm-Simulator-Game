package main;
import java.util.ArrayList;

public class Farm  extends GameEnvironment{
	
	private String farmName;
	private String farmType;
	private Farmer farmer;
	private ArrayList<Crop> crops;
	private ArrayList<Animal> animal;
	private double money;
	private int freeSpace;
	private Double cropGrowthSpeed;
	
	
	
	public Farm(String name, String type, Farmer newfarmer) 
	{
        farmName = name;
        setFarmType(type);
        farmer = newfarmer;
        System.out.println("money = $" + money + "cropGrowthSpeed =" + cropGrowthSpeed);
    }
	
	public void setFarmType(String type) 
	{
		if (type == "1") 
		{
			money = 10000;
			cropGrowthSpeed = 0.5;
		}
		else if (type == "2") 
		{
			money = 6000;
			cropGrowthSpeed = 1.5;
		}
		else if (type == "3") 
		{
			money = 8000;
			cropGrowthSpeed = 1.0;
		}
		
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
			if (crop.canHarvest())
			{
				//Harvest here
				moneyMade += crop.getSellPrice();
			}
		}
		
		return moneyMade;
	}
		
	
	
}
