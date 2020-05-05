package main;
import java.util.ArrayList;

public class Farm  extends GameEnvironment{
	
	private String farmName;
	private String farmType;
	private Farmer farmer;
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private double money;
	private double initMoney;
	private int freeSpace;
	private double startCropGrowthSpeed;
	
	
	
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
			money = 2000;
			startCropGrowthSpeed = 0.5;
		}
		else if (type == "2") 
		{
			money = 1000;
			startCropGrowthSpeed = 1.5;
		}
		else if (type == "3") 
		{
			money = 1500;
			startCropGrowthSpeed = 1.0;
		}
		//System.out.println(money + "  " + startCropGrowthSpeed);
		
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
	
	public void increaseMoney(double alpha)
	{
		money += alpha;
	}

	public void decreaseMoney(double alpha)
	{
		money -= alpha;
	}
	
	public double getMoney()
	{
		return money;
	}
	
	public double getProfit()
	{
		return money - initMoney;
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
	
	public void growCrops()
	{
		for(Crop crop: crops) 
		{
			crop.grow();
		}
	}
	
	public double collectAnimalMoney()
	{
		double moneyMade = 0.0;
		for(Animal animal: animals) 
		{
			moneyMade += animal.makeMoney();
		}
		return moneyMade;
	}
	
	
}
