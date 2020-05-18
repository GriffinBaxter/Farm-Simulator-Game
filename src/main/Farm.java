package main;
import java.util.ArrayList;

/**
 * Farm class where the users farm gets managed.
 * Here the user can get set the farm type, tend to their farm and tend to their crops.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Farm extends GameEnvironment
{
	
	/**
	 * Farm name String.
	 */
	private String farmName;
	
	/**
	 * Store class.
	 */
	private Store store;
	
	/**
	 * Crops owned ArrayList, gets filled with Crops.
	 */
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	
	/**
	 * Animals owned ArrayList, gets filled with Animals.
	 */
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	
	/**
	 * Items owned ArrayList, gets filled with Items.
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
	
	/**
	 * Money owning.
	 */
	private double money;
	
	/**
	 * Initial money owned when the game started.
	 */
	private double initMoney;
	
	/**
	 * Total crop space available.
	 */
	private int cropSpace;
	
	
	/**
	 * Constructor function for Farm Class, this constructor initialises variables <code>farmName</code> and Sets the farm type.
	 * @param name The name of the farmer.
	 * @param type The type of the farmer in String format.
	 */
	public Farm(String name, String type) 
	{
        farmName = name;
        setFarmType(type);
    }
	
	/**
	 * Returns the store class, Called from the GameEnvironment Class.
	 * @return The Store Class.
	 */
	public Store getStore()
	{
		return store;
	}
	
	
	/**
	 * Sets the farm type from the <code>type</code> String, and uses if statements to set the farm type.
	 * @param type Type of the farm.
	 */
	public void setFarmType(String type)
	{
		if (type == "Normal") 
		{
			initMoney = 150;
			cropSpace = 10;
			store = new Store(1.1); // Average animal happiness
		}
		else if (type == "Rich") 
		{
			initMoney = 200;
			cropSpace = 10;
			store = new Store(1.0); // Low animal happiness
		}
		else if (type == "Happy") 
		{
			initMoney = 100;
			cropSpace = 10;
			store = new Store(1.2); // High animal happiness
		}
		else if (type == "Large") 
		{
			initMoney = 100;
			cropSpace = 20;
			store = new Store(1.0); // Low animal happiness
		}
		money = initMoney;	
	}
	
	
	
	/**
	 *	Tends to the Farm to make animals happier and increase space available, returns true if there are animals to increase their health, otherwise returns false
	 */
	public boolean tendFarm()
	{
		cropSpace++;
		return increaseHappinessAllAnimals(0.1);
	}
	
	/**
	 *	Calculates the available free space
	 */
	public int calculateFreeSpace() 
	{
		return cropSpace - crops.size();
	}
	
	/**
	 * Increase the money the farm has by the indicated difference (<code>alpha</code>).
	 * @param alpha The increase of money.
	 */
	public void increaseMoney(double alpha)
	{
		money += alpha;
	}

	/**
	 * Decrease the money the farm has by the indicated difference (<code>alpha</code>).
	 * @param alpha The decrease of money.
	 */
	public void decreaseMoney(double alpha)
	{
		money -= alpha;
	}
	
	/**
	 * Returns the current available money for the farm.
	 * @return Money available.
	 */
	public double getMoney()
	{
		return money;
	}
	
	/**
	 * Returns the profit the farm has made by subtracting <code>initMoney</code> from <code>money</code>.
	 * @return The profit of the farm.
	 */
	public double getProfit()
	{
		return money - initMoney;
	}
	
	/**
	 * Returns the farm name from the <code>farmName</code> variable.
	 * @return The farm name.
	 */
	public String getFarmName() 
	{
		return farmName;
	}
	
	/**
	 * Returns the crops the farm currently has planted from the crops ArrayList.
	 * @returns An ArrayList of crops owned.
	 */
	public ArrayList<Crop> getCrops() 
	{
		return crops;
	}
	
	/**
	 * Returns the animals the farm currently owns from the animals ArrayList.
	 * @returns An ArrayList of animals owned.
	 */
	public ArrayList<Animal> getAnimals() 
	{
		return animals;
	}
	
	/**
	 * Returns the items the farm currently owns from the items ArrayList.
	 * @returns An ArrayList of items owned.
	 */
	public ArrayList<Item> getItems() 
	{
		return items;
	}
	
	/**
	 * Returns the total crop space the farm has.
	 * @return The crop space.
	 */
	public int getCropSpace() 
	{
		return cropSpace;
	}
	
	/**
	 * Returns true if any of the crops owned can be harvested, false otherwise. Used to determine whether to harvest crops or not.
	 * @return true if crops can be harvested, false otherwise.
	 */
	public boolean canHarvestCrops() 
	{
		for(Crop crop: crops)
		{
			if (crop.canHarvest())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * A function that harvests all crops that can be harvested.
	 * Loops through all crops owned and checks if they can be harvested. If they can, they get harvested.
	 * The function then returns the total money made from harvesting the crops.
	 * @return The total money made form harvesting the crops.
	 */
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
		
		for (Crop crop: cropsToRemove) 
		{
			crops.remove(crops.indexOf(crop));
		}
		
		return moneyMade;
	}
	
	/**
	 * A function to grow all of the crops owned, it does this by looping through all of the crops and calling their grow method.
	 */
	public void growCrops()
	{
		for(Crop crop: crops) 
		{
			crop.grow();
		}
	}
	
	/**
	 * Tends to specific crops with the name cropName and increases their growth time by daysToIncrease
	 * @param cropName The name of the crop.
	 * @param daysToIncrease The days to increase the crop growth by.
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
	 * Increases health of all animals by healthToIncrease, returns true if there are animals to feed and returns false if there are no animals.
	 * @param healthToIncrease The health increase for the animal.
	 * @return true if there are animals, false otherwise.
	 */
	public boolean increaseHappinessAllAnimals(double healthToIncrease)
	{
		int index = 0;
		for(Animal animal: animals)
		{
			index++;
			animal.increaseHealth(healthToIncrease);
		}
		if (index > 0) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * Increases happiness of all animals, returns true if there are animals to play with and returns false if there are no animals.
	 * @return true if there are animals, false otherwise.
	 */
	public boolean playWithAllAnimals()
	{
		int index = 0;
		for(Animal animal: animals)
		{
			index++;
			animal.increaseHappiness();
		}
		if (index > 0) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * A function that collects all money from animals daily profit and returns it. This function gets called when the player moves on to the next day.
	 * @return The daily profit for all animals owned summed together.
	 */
	public double collectAnimalMoney()
	{
		double moneyMade = 0.0;
		for(Animal animal: animals) 
		{
			moneyMade += animal.getDailyProfit();
		}
		return moneyMade;
	}
	
	/**
	 * Increase crops owned from the crop passed through the method  by adding it to the <code>crops</code> ArrayList.
	 * @param crop The crop being purchased.
	 */
	public void increaseCrops(Crop crop)
	{
		crops.add(new Crop(crop));//Creates a new instance of the crop as i was  having problems with crops linking to the same class
		money -= crop.getPurchasePrice();
	}
	
	/**
	 * Increase animals owned from the animal passed through the method  by adding it to the <code>animals</code> ArrayList.
	 * @param animal The animal being purchased.
	 */
	public void increaseAnimals(Animal animal)
	{
		animals.add(new Animal(animal));
		money -= animal.getPurchasePrice();
	}
	
	/**
	 * Increase items owned from the item passed through the method  by adding it to the <code>items</code> ArrayList.
	 * @param item The item being purchased.
	 */
	public void increaseItems(Item item)
	{
		items.add(new Item(item));
		money -= item.getPurchasePrice();
	}
	
	/**
	 * Removes an item from the <code>items</code> ArrayList by finding the item and then removing it.
	 * @param item The item to remove.
	 */
	public void decreaseItems(Item item)
	{
		items.remove(items.indexOf(item));
	}
	
	/**
	 * Returns the crops in String format by having one crop per line. adds it to the <code>cropString</code> passed in through the method.
	 * @param cropsString Initial String to be added to and returned.
	 * @param crops Crops to be added to the crop string.
	 * @return A String with the format of one crop per line.
	 */
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
	
	/**
	 * Returns the items in String format by having one item per line. adds it to the <code>itemString</code> passed in through the method.
	 * Only adds items with the specified <code>itemType</code>
	 * @param itemString Initial String to be added to and returned.
	 * @param itemType The type of the item.
	 * @param index Index to start counting from.
	 * @return A String with the format of one item per line.
	 */
	public String returnItemsString(String itemString, String itemType, int index) 
	{
		for(Item item: items) 
		{
			if (item.getType() == itemType)
			{
				index++;
				itemString += index + ". " + item.getName() + "\n";
			}
		}
		return itemString;
	}
	
	
	/**
	 * Returns the different types of crops owned by their Name.
	 * @return Crop ArrayList of crops.
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
	
}
