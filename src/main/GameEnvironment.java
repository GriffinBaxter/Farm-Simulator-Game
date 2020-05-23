package main;

import gui.FeedAnimalsScreen;
import gui.MainScreen;
import gui.SetupScreen;
import gui.StoreScreen;
import gui.TendCropsScreen;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Game Environment class. This is the main Class of the program.
 * In this screen the user can play the farm simulator game.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class GameEnvironment 
{
	
	/**
	 * The farm Class.
	 */
	private Farm farm;
	
	/**
	 * The farmer Class.
	 */
	private Farmer farmer;
	
	/**
	 * The store Class.
	 */
	private Store store;
	
	/**
	 * The number of days the user wants to play for.
	 */
	private int numDays = 0;
	
	/**
	 * The actions performed.
	 */
	private int actionsPerformed = 0;
	
	/**
	 * The setup window.
	 */
	private SetupScreen setupWindow;
	
	/**
	 * The main window.
	 */
	private MainScreen mainWindow;
	
	/**
	 * The store window.
	 */
	private StoreScreen storeWindow;
	
	/**
	 * The tend to crops window.
	 */
	private TendCropsScreen tendCropsWindow;
	
	/**
	 * The feed animals window.
	 */
	private FeedAnimalsScreen feedAnimalsWindow;
	
	/**
	 * Starts a new game by getting number of days, farmer name, farm type, and farm name.
	 * @param initNumDays the number of days the user wants to play for.
	 * @param farmerName the name of the farmer.
	 * @param farmerAge the age of the farmer.
	 * @param farmType the type of the farm.
	 * @param farmName the name of the farm.
	 */
	public void setupGame(int initNumDays, String farmerName, int farmerAge, String farmType, String farmName) 
	{
		numDays = initNumDays;
		
		if ((farmerName.length() < 3 || farmerName.length() > 15 || !isAlpha(farmerName)) && (farmName.length() < 3 || farmName.length() > 15 || !isAlpha(farmName))) 
		{
			setupWindow.setWarningText("farmer's name and farm's name are");
		}
		else if (farmerName.length() < 3 || farmerName.length() > 15 || !isAlpha(farmerName)) 
		{
			setupWindow.setWarningText("farmer's name is");
		}
		else if (farmName.length() < 3 || farmName.length() > 15 || !isAlpha(farmName)) 
		{
			setupWindow.setWarningText("farm's name is");
		}
		else 
		{
			setupWindow.setWarningText("");
			farmer = new Farmer(farmerName, 1, farmerAge); // First day
			farm = new Farm(farmName, farmType);
			
			// Create store
			if (farmType == "Normal") 
			{
				store = new Store(1.1); // Average animal happiness
			}
			else if (farmType == "Rich") 
			{
				store = new Store(1.0); // Low animal happiness
			}
			else if (farmType == "Happy") 
			{
				store = new Store(1.2); // High animal happiness
			}
			else if (farmType == "Large") 
			{
				store = new Store(1.0); // Low animal happiness
			}
			closeSetupScreen(setupWindow);
		}
		
	}
	
	/**
	 * A function for moving on to the next day, Called when the player sleeps.
	 * This function increases the farmers age, reduces the actions performed to 0 and grows the crops owned.
	 * If the farmers age is equal to the number of days set during startup, the finishing sequence begins.
	 */
	public void nextDay() 
	{
		farmer.increaseDaysPassed();
		actionsPerformed = 0;
		if (farmer.getDaysPassed() != numDays + 1)
		{
			//Grow crops
			farm.growCrops();
			farm.increaseMoney(farm.collectAnimalMoney());
		}
	}
	
	/**
	 * A function for testing if the game can finish or not by returning true if the farmers age is equal to the number of days + 1, the +1 to have the game finish after the numberOfDays day.
	 * @return true or false depending on the farmers age.
	 */
	public boolean gameFinishing()
	{
		return farmer.getDaysPassed() == numDays + 1;
	}
	
	/**
	 * Tends to all of the crops owned with the specified <code>cropIndex</code> and <code>itemName</code>.
	 * @param cropIndex The crop index
	 * @param itemName The item name
	 * @return String identifying the action performed.
	 */
	public String tendToCrops(int cropIndex, String itemName)
	{
		if (actionsPerformed >= 2) 
		{
			return "You cannot do this as you have no actions left";
		}
		else
		{
			String cropName = returnCropTypeArray()[cropIndex];
			actionsPerformed++;
			if (itemName == "Water (free)") // Watered crops
			{
				farm.tendSpecificCrops(cropName, 1.0);
				return "Tended to every " + cropName + " by watering them";
			}
			else // If an item was used on the crop
			{
				int count = 0;
				int index = 0;
				for (Item item: farm.getItems())
				{
					if (item.getName() == itemName)
					{
						index = count;
					}
					count++;
				}
				Item itemUsed = farm.getItems().get(index);
				farm.tendSpecificCrops(cropName, itemUsed.getBonus());
				farm.decreaseItems(itemUsed);
				return "Tended to every " + cropName + " by using " + itemUsed.getName() + " on them";
			}
		}
	}
	
	/**
	 * Feed to all of the animals owned with the item <code>itemName</code>.
	 * @param itemName The name of the item used.
	 * @return String identifying the action performed.
	 */
	public String feedAnimals(String itemName)
	{
		if (actionsPerformed >= 2) 
		{
			return "You cannot do this as you have no actions left";
		}
		else 
		{
			int count = 0;
			int index = 0;
			for (Item item: farm.getItems())
			{
				if (item.getName() == itemName)
				{
					index = count;
				}
				count++;
			}
			
			Item itemUsed = farm.getItems().get(index);
				
			farm.increaseHealthAllAnimals(itemUsed.getBonus());
			actionsPerformed++;
			farm.decreaseItems(itemUsed);
			return "Fed every animal with " + itemUsed.getName();
		}
	}
	
	/**
	 * a function that allows the user to play with the animals owned, doing this will increase their happiness.
	 * @return String identifying the action performed.
	 */
	public String playWithAnimals()
	{
		if (actionsPerformed >= 2) 
		{
			return "You cannot do this as you have no actions left";
		}
		else if (farm.increaseHappinessAllAnimals()) 
		{
			actionsPerformed++;
			return "Played with every animal (and increased their happiness by doing so)";
		}
		else 
		{
			return "You have no animals to play with, so no actions were used";
		}
	}
	
	/**
	 * A function that will harvest the crops that can be harvested, 
	 * it does this by calling the harvestAvailableCrops function in the Farm class.
	 * @return String identifying the action performed.
	 */
	public String harvestCrops()
	{
		if (actionsPerformed >= 2) 
		{
			return "You cannot do this as you have no actions left";
		}
		else if (farm.canHarvestCrops()) 
		{
			actionsPerformed++;
			double moneyMade = farm.harvestAvailableCrops();
			farm.increaseMoney(moneyMade);
			return "You made $" + returnDollarsCents(moneyMade) + " from harvesting all your crops";
		}
		else 
		{
			return "You have no harvestable crops, so no actions were used";
		}
	}
	
	/**
	 * A function to tend to the farm land. tending to the farm land will increase the number of available slots for planting crops.
	 * @return String identifying the action performed.
	 */
	public String tendFarmLand()
	{
		if (actionsPerformed >= 2) 
		{
			return "You cannot do this as you have no actions left";
		}
		else 
		{
			actionsPerformed++;
			String returnString = "Farm land has been tended to, 1 more crop is able to be grown";
			if (farm.tendFarm()) 
			{
				returnString += ", plus all of your animals now have increased happiness!";
			}
			return returnString;
		}
	}
	
	/**
	 * The finishGame function, called when the farmers age has reached the numDays set during startup.
	 * Prints out the farmers name, the number of days passed, the money made and the score.
	 * @return finish game dialog String
	 */
	public String finishGame()
	{
		String profitString;
		double scoreProfit = farm.getProfit();
		double scoreAge = (15 - farmer.getDaysPassed());
		double scoreCropSize = farm.getCrops().size() + 1;
		double scoreCropSpace = farm.getCropSpace();
		
		if (scoreProfit <= 0.0) 
		{
			scoreProfit = 0;
			profitString = " made no profit!\r\n";
		}
		else 
		{
			profitString = " made $" + returnDollarsCents(farm.getProfit()) + " in profit.\r\n";
		}
		
		double score = scoreProfit * scoreAge * (scoreCropSize / scoreCropSpace);
		
		for (Animal animal: farm.getAnimals()) 
		{
			score += animal.getHappiness() * animal.getHealth();
		}
		
		 String returnString = "The game has finished!\n"
				+ "Stats for " + farmer.getFarmerName() + ", age: " + farmer.getFarmerAge() + ", on the farm " + farm.getFarmName() + ":\r\n"
				+ (farmer.getDaysPassed() - 1) + " days have passed.\r\n"
				+ farmer.getFarmerName() + profitString
				+ "Total Score: " + Math.round(score) + "\r\n" //rounds score to 0dp
				+ "Score is able to be increased by the profit, the healthiness and happiness of animals, the percentage of crop slots utilised, and by choosing a lower number of days!";
		 return returnString;
	}
	
	/**
	 * A method to launch the main screen where the user controls the game.
	 */
	public void launchMainScreen()
	{
		mainWindow = new MainScreen(this);
	}
	
	/**
	 * A method to close the main screen
	 */
	public void closeMainScreen(MainScreen mainWindow)
	{
		mainWindow.closeWindow();
	}
	
	/**
	 * A method to launch the setup screen where the user sets up the game.
	 */
	public void launchSetupScreen()
	{
		setupWindow = new SetupScreen(this);
	}
	
	/**
	 * A method to close the setup screen.
	 */
	public void closeSetupScreen(SetupScreen setupWindow)
	{
		setupWindow.closeWindow();
		launchMainScreen(); // Only here for closing the setup screen, as this is used once.
	}
	
	/**
	 * A method to launch the store screen where the user buys crops, animals and items.
	 */
	public void launchStoreScreen()
	{
		storeWindow = new StoreScreen(this);
	}
	
	/**
	 * A method to close the store screen
	 */
	public void closeStoreScreen(StoreScreen storeWindow)
	{
		storeWindow.closeWindow();
	}
	/**
	 * A method to launch the tend to crops screen where the user tends to crops by using items.
	 */
	public void launchTendCropsScreen()
	{
		tendCropsWindow = new TendCropsScreen(this);
	}
	
	/**
	 * A method to close the tend to crops screen
	 */
	public void closeTendCropsScreen(TendCropsScreen tendCropsWindow)
	{
		tendCropsWindow.closeWindow();
	}
	
	/**
	 * A method to launch the feed animals screen where the user feeds animals by feeding them items.
	 */
	public void launchFeedAnimalsScreen()
	{
		feedAnimalsWindow = new FeedAnimalsScreen(this);
	}
	
	/**
	 * A method to close the feed animals screen
	 */
	public void closeFeedAnimalsScreen(FeedAnimalsScreen feedAnimalsWindow)
	{
		feedAnimalsWindow.closeWindow();
	}
	
	/**
	 * Returns a String array with the name of all of the crops currently owned.
	 * @return String Array containing names of Animals
	 * 
	 */
	public String[] returnCropTypeArray() 
	{
		ArrayList<Crop> differentCrops = returnDifferentCropsOwned();
		ArrayList<String> differentCropNames = new ArrayList<String>();
		for(Crop crop: differentCrops) 
		{
			differentCropNames.add(crop.getName());
		}
		String[] cropArray = differentCropNames.toArray(new String[0]);
		return cropArray;
	}
	
	/**
	 * Returns a String array with the name of all of the items currently owned given the correct String <code>itemType</code>.
	 * @return String Array containing names of items
	 */
	public String[] returnCurrentItemsArray(String itemType) 
	{
		ArrayList<String> currentItems = new ArrayList<String>();
		
		if (itemType == "Crop") 
		{
				currentItems.add("Water (free)");
		}
		
		for(Item item: farm.getItems()) 
		{
			if (item.getType() == itemType)
			{
				currentItems.add(item.getName());
			}
		}
		
		String[] currentItemsArray = currentItems.toArray(new String[0]);
		return currentItemsArray;
	}
	
	/**
	 * Returns a String formated correctly for displaying each crop and animal and its status.
	 * @return string containing status of crops and animals
	 */
	public String returnStatusCropsAnimals()
	{
		String returnString = "";
		returnString += farm.getFarmName() + " has " + farm.getCrops().size() + " crops\r\n";
		for(Crop crop: farm.getCrops()) 
		{
			if (crop.getDaysLeftToGrow() > 0) 
			{
				returnString += crop.getName() + " Has been growing for " + crop.getDaysGrown() 
				+ " days, it needs " + (crop.getDaysLeftToGrow())
				+ " more days to be harvested\r\n";
			}
			else 
			{
				returnString += crop.getName() + " Has been growing for " + crop.getDaysGrown() 
				+ " days, it is ready to harvest!\r\n";
			}
		}
		
		returnString += "\r\n" + farm.getFarmName() + " has " + farm.getAnimals().size() + " animals\r\n";
		for(Animal animal: farm.getAnimals()) 
		{
			returnString += animal.getName() + " has a happiness level of " + String.format("%.1f", animal.getHappiness())
				+ " and a healthiness level of " + String.format("%.1f", animal.getHealth())
				+ ", which equates to $" + returnDollarsCents(animal.getDailyProfit()) + " per day\r\n";
		}
		return returnString;
	}
	
	/**
	 * A function for returning the items the user currently owns in a string format with one item per line.
	 * @return string of items
	 */
	public String returnItemsString()
	{
		String returnString;
		if (farm.getItems().size() == 0)
		{
			returnString = farm.getFarmName() + " owns " + farm.getItems().size() + " Items.";
		}
		else
		{
			returnString = farm.getFarmName() + " owns " + farm.getItems().size() + " Items, They are:\r\n";
		}
		
		for(Item item: farm.getItems()) 
		{
			returnString += "- " + item.getName() + "\r\n";
		}
		return returnString;
	}
	
	/**
	 * Returns the money the farm currently has in string format to have it to 2dp.
	 * @return farm money
	 */
	public String returnMoneyString()
	{
		return returnDollarsCents(farm.getMoney());
	}
	
	/**
	 * Returns the crop space available, used when the user wants to buy more crops.
	 * @return free crop space
	 */
	public int returnFreeCropSpace()
	{
		return farm.calculateFreeSpace();
	}
	
	/**
	 * Returns the age of the farmer. Used to check the number of.
	 * @return Age of farmer
	 */
	public int returnDaysPassed()
	{
		return farmer.getDaysPassed();
	}
	
	/**
	 * Returns String Array of crops with the crops formated so that each crop has its details and price on one line.
	 * @return String Array of crops
	 */
	public String[] returnCropArray()
	{
		ArrayList<String> cropArrayList = new ArrayList<String>();
		for(Crop crop: store.getCropsForSale()) 
		{
			cropArrayList.add(crop.getName()
			+ ", Purchase price: $" + returnDollarsCents(crop.getPurchasePrice())
			+ ", Harvest sell price: $" + returnDollarsCents(crop.getSellPrice())
			+ ", Days to grow: " + crop.getDaysToGrow()); 
		}
		String[] cropArray = cropArrayList.toArray(new String[0]);
		return cropArray;
	}
	
	/**
	 * Takes a purchaseOption index and purchases the Crop at that index in the farm crops ArrayList.
	 * @param purchaseOption Crop the user chose to buy.
	 * @return String detailing what the user did.
	 */
	public String purchaseCrop(int purchaseOption)
	{
		String purchaseCropString = "";
			if (farm.calculateFreeSpace() > 0) 
			{
				if (farm.getMoney() < store.getCropsForSale().get(purchaseOption).getPurchasePrice()) 
				{
					purchaseCropString = "You don't have enough money to buy " + store.getCropsForSale().get(purchaseOption).getName() + "!" ;
				}
				else
				{
					farm.increaseCrops(store.getCropsForSale().get(purchaseOption));
					purchaseCropString = store.getCropsForSale().get(purchaseOption).getName() + " bought!";
				}
			}
			else 
			{
				purchaseCropString = "You have no space available for new crops!\n"
						+ "To purchase crops again, you need to either tend to the farm land to increase crop space or harvest crops to remove current crops";
			}
		return purchaseCropString;
	}
	
	/**
	 * Returns String Array of animals with the animals formated so that each animal has its details and price on one line.
	 * @return String Array of animals.
	 */
	public String[] returnAnimalArray()
	{
		ArrayList<String> animalArrayList = new ArrayList<String>();
		for(Animal animal: store.getAnimalsForSale()) 
		{
			animalArrayList.add(animal.getName()
			+ ", Purchase price: $" + returnDollarsCents(animal.getPurchasePrice())
			+ ", Daily profit at base Happiness: $" + returnDollarsCents(animal.getDailyProfit()));
		}
		String[] animalArray = animalArrayList.toArray(new String[0]);
		return animalArray;
	}
	
	/**
	 * Takes a purchaseOption index and purchases the Animal at that index in the farm animals ArrayList.
	 * @param purchaseOption Animal the user chose to buy.
	 * @return String detailing what the user did.
	 */
	public String purchaseAnimal(int purchaseOption)
	{
		String purchaseAnimalString = "";
			if (farm.getMoney() < store.getAnimalsForSale().get(purchaseOption).getPurchasePrice()) 
			{
				purchaseAnimalString = "You don't have enough money to buy " + store.getAnimalsForSale().get(purchaseOption).getName() + "!" ;
			}
			else
			{
				farm.increaseAnimals(store.getAnimalsForSale().get(purchaseOption));
				purchaseAnimalString = store.getAnimalsForSale().get(purchaseOption).getName() + " bought!";
			}
		return purchaseAnimalString;
	}
	
	/**
	 * Returns String Array of items with the items formated so that each item has its details and price on one line.
	 * @return String Array of items
	 */
	public String[] returnItemArray()
	{
		ArrayList<String> itemArrayList = new ArrayList<String>();
		for(Item item: store.getItemsForSale()) 
		{
			String tempString = item.getName() + ", Purchase price: $" + returnDollarsCents(item.getPurchasePrice());
			if (item.getType() == "Crop")
			{
				tempString += ", Benefit when used: +" + Math.round(item.getBonus()) + " day growth increase for chosen crop type";
			}
			if (item.getType() == "Animal")
			{
				tempString += ", Benefit when used: +" + Math.round(item.getBonus() * 100) + "% health for all animals";
			}
			
			itemArrayList.add(tempString);
			
		}
		String[] itemArray = itemArrayList.toArray(new String[0]);
		return itemArray;
	}
	
	/**
	 * Takes a purchaseOption index and purchases the Item at that index in the farm items ArrayList.
	 * @param purchaseOption Item the user chose to buy.
	 * @return String detailing what the user did.
	 */
	public String purchaseItem(int purchaseOption)
	{
		String purchaseItemString = "";
		if (farm.getMoney() < store.getItemsForSale().get(purchaseOption).getPurchasePrice()) 
		{
			purchaseItemString = "You don't have enough money to buy " + store.getItemsForSale().get(purchaseOption).getName() + "!" ;
		}
		else
		{
			farm.increaseItems(store.getItemsForSale().get(purchaseOption));
			purchaseItemString = store.getItemsForSale().get(purchaseOption).getName() + " bought!";
		}
	return purchaseItemString;
	}
	
	/**
	 * Returns the number of items the user has that have the type "Crop".
	 * @return integer of the number of items of type "Crop".
	 */
	public int returnCropItemSize()
	{
		int size = 0;
		for (Item item: farm.getItems()) 
		{
			if (item.getType() == "Crop") 
			{
				size++;
			}
		}
		return size;
	}
	
	/**
	 * Returns the number of items the user has that have the type "Animal".
	 * @return Integer of the number of items of type "Animal".
	 */
	public int returnAnimalItemSize()
	{
		int size = 0;
		for (Item item: farm.getItems()) 
		{
			if (item.getType() == "Animal") 
			{
				size++;
			}
		}
		return size;
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
			for(Crop cropCheck: farm.getCrops()) 
			{
				if (crop.getName() == cropCheck.getName() && !differentCrops.contains(crop))
				{
					differentCrops.add(crop);
				}
			}
		}
		return differentCrops;
	}
	
	/**
	 * A simple function to check whether a string has only alphabetical letters.
	 * Returns false if it does not.
	 * @param name The name being tested for only Alpha characters.
	 * @return True or false depending on the characters in <code>name</code>
	 */
	public boolean isAlpha(String name)
	{
	    return name.matches("[a-zA-Z ]+");
	}
	
	/**
	 * Returns a double as a string with two decimal places, for use with dollars and cents.
	 * @param amount The amount of money owning.
	 * @return String format of a double with 2dp
	 */
	public String returnDollarsCents(double amount)
	{
		return String.format("%.2f", amount);
	}
	
	/**
	 * Returns the ArrayList crops from the farm class.
	 * @return ArrayList of crops owned.
	 */
	public ArrayList<Crop> getCrops()
	{
		return farm.getCrops();
	}
	
	/**
	 * Returns the ArrayList animals from the farm class.
	 * @return ArrayList of animals owned.
	 */
	public ArrayList<Animal> getAnimals()
	{
		return farm.getAnimals();
	}
	
	/**
	 * Returns the number of days user wants to play for.
	 * @return Number of days
	 */
	public int getNumDays()
	{
		return numDays;
	}
	
	/**
	 * Returns the actions performed.
	 * @return current actions performed
	 */
	public int getActionsPerformed()
	{
		return actionsPerformed;
	}
	
	/**
	 * main function of the program. this is where the game is started by calling the startGame and mainGame methods.
	 * @param args
	 */
	public static void main(String[] args)
	{
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
	}

}
