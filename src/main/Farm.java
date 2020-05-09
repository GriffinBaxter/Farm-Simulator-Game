package main;
import java.util.ArrayList;

public class Farm  extends GameEnvironment{
	
	private String farmName;
	private String farmType;
	private Farmer farmer;
	private Store store = new Store();
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private double money;
	private double initMoney;
	private int freeSpace;
	private double startCropGrowthSpeed;
	
	
	
	public Farm(String name, String type, Farmer newfarmer) 
	{
        farmName = name;
        setFarmType(type);
        farmer = newfarmer;
        //For testing
        //crops.add(store.cropsForSale.get(1));
        //animals.add(store.animalsForSale.get(1));
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
		initMoney = money;
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
	
	public String getFarmName() {
		return farmName;
	}
	
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	
	public boolean canHarvestCrops() {
		for(Crop crop: crops)
		{
			if (crop.canHarvest())
			{
				return true;
			}
		}
		return false;
	}
	
	
	public double harvestAvailableCrops()
	{
		double moneyMade = 0;
		
		ArrayList<Crop> cropsToRemove = new ArrayList<Crop>();
		
		for(Crop crop: crops)
		{
			if (crop.canHarvest())
			{
				//Harvest here
				cropsToRemove.add(crop);
				moneyMade += crop.getSellPrice();
			}
		}
		
		for (Crop crop: cropsToRemove) {
			crops.remove(crops.indexOf(crop));
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
	
	/**
	 * Tends to specific crops with the name cropName and increases their growth time by daysToIncrease
	 * @param cropName
	 * @param daysToIncrease
	 */
	public void tendSpecificCrops(String cropName, double daysToIncrease)
	{
		for(Crop crop: crops) 
		{
			if (crop.getName() == cropName)
			{
				crop.tend(daysToIncrease);
			}
		} 
	}
	
	/**
	 * Feeds all animals and increases their health by healthToIncrease, returns true if there are animals to feed and returns false if there are no animals
	 * @param healthToIncrease
	 */
	public boolean feedAllAnimals(double healthToIncrease)
	{
		int index = 0;
		for(Animal animal: animals)
		{
			index++;
			animal.increaseHealth(healthToIncrease);
		}
		if (index > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean playWithAllAnimals()
	{
		int index = 0;
		for(Animal animal: animals)
		{
			index++;
			animal.increaseHappiness();
		}
		if (index > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double collectAnimalMoney()
	{
		double moneyMade = 0.0;
		for(Animal animal: animals) 
		{
			moneyMade += animal.dailyProfit();
		}
		return moneyMade;
	}
	
	public void increaseCrops(Crop crop)
	{
		crops.add(new Crop(crop));//Creates a new instance of the crop as i was  having problems with crops linking to the same class
		money -= crop.getPurchasePrice();
	}
	
	public void increaseAnimals(Animal animal)
	{
		animals.add(new Animal(animal));
		money -= animal.getPurchasePrice();
	}
	
	public void increaseItems(Item item)
	{
		items.add(new Item(item));
		money -= item.getPurchasePrice();
	}
	
	public void decreaseItems(Item item)
	{
		items.remove(items.indexOf(item));
	}
	
	public String returnCropsString(String cropsString, ArrayList<Crop> crops) 
	{
		int index = 0;
		for(Crop crop: crops) 
		{
			index++;
			cropsString += index + ". " + crop.getName() + "\n";
		}
		return cropsString;
	}
	
	public String returnItemsString(String cropsString, String itemType, int index) 
	{
		for(Item item: items) 
		{
			if (item.getType() == itemType)
			{
				index++;
				cropsString += index + ". " + item.getName() + "\n";
			}
		}
		return cropsString;
	}
	
	
	/**
	 * Returns the different types of crops owned by their Name
	 */
	public ArrayList<Crop> returnDifferentCropsOwned()
	{
		ArrayList<Crop> differentCrops = new ArrayList<Crop>();
		for(Crop crop: store.getCropsForSale())
		{
			for(Crop cropCheck: crops) 
			{
				if (crop.getName() == cropCheck.getName() && !differentCrops.contains(crop))
				{
					differentCrops.add(crop);
				}
			}
		}
		return differentCrops;
	}
	
	
	// Deprecated, now using getter methods from the GameEnvironment class, keeping here for now just in case testing is required
	/*
	public void printCropStatus()
	{
		System.out.println("You have " + crops.size() + " Crops");
		for(Crop crop: crops) 
		{
			crop.printCrop();
		}
		System.out.println("");
	}
	
	public void printAnimalStatus()
	{
		System.out.println("You have " + crops.size() + " Animals");
		for(Animal animal: animals) 
		{
			animal.printAnimal();
		}
		System.out.println("");
	}*/
	
}
