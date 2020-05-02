package main;
import java.util.ArrayList;

public class Farm  extends GameEnvironment{
	
	private String farmName;
	private String farmType;
	private Farmer farmer;
	private ArrayList<Crop> crops;
	private ArrayList<Animal> animals;
	private double money;
	private int freeSpace;
	private Double startCropGrowthSpeed;
	
	
	
	public Farm(String name, String type, Farmer newfarmer) 
	{
        farmName = name;
        setFarmType(type);
        farmer = newfarmer;
    }
	
	public void setFarmType(String type) 
	{
		if (type == "1") 
		{
			money = 10000;
			startCropGrowthSpeed = 0.5;
		}
		else if (type == "2") 
		{
			money = 6000;
			startCropGrowthSpeed = 1.5;
		}
		else if (type == "3") 
		{
			money = 8000;
			startCropGrowthSpeed = 1.0;
		}
		
	}
	
	
	
	/**
	 *	Tends to the Farm to make animals happier and increase space available
	 */
	public void tendFarm()
	{
		//Some cool stuff
		startCropGrowthSpeed += 0.1;
		freeSpace++;
	}
	
	public void increaseMoney(Double alpha)
	{
		money += alpha;
	}

	public void decreaseMoney(Double alpha)
	{
		money -= alpha;
	}
	
	
	public Double harvestCrops()
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
	
	public void growCrops()
	{
		for(Crop crop: crops) 
		{
			crop.grow();
		}
	}
	
	public Double collectAnimalMoney()
	{
		Double moneyMade = 0.0;
		for(Animal animal: animals) 
		{
			moneyMade += animal.makeMoney();
		}
		return moneyMade;
	}
	
	
}
