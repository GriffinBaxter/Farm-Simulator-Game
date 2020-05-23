package main_cli;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import main.Animal;
import main.Crop;
import main.Farm;
import main.Farmer;
import main.Item;
import main.Store;

/**
 * Game Environment class command line form. This is the main Class of the command line program.
 * In this screen the user can play the farm simulator game.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class GameEnvironmentCLI 
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
	 * The scanner, used for getting keyboard input from the user.
	 */
	private Scanner scanner;
	
	/**
	 * The number of days the user wants to play for.
	 */
	private int numDays = 0;
	
	/**
	 * The farmer name
	 */
	private String farmerName = "";
	
	/**
	 * The type of farm in Integer form.
	 */
	private int typeInt = 0;
	
	/**
	 * The type of farm in String form.
	 */
	private String farmType;
	
	/**
	 * The farm name.
	 */
	private String farmName = null;
	
	/**
	 * The actions performed.
	 */
	private int actionsPerformed = 0;
	
	/**
	 * Sets the number of days via input from the user.
	 */
	public void inputNumDays() 
	{
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("How many days would you like the game to last? (Enter a number between 5 and 10)");
			
			if (scanner.hasNextInt()) 
			{
				numDays = scanner.nextInt();
				
				if (numDays < 5 || numDays > 10) 
				{
					System.out.println("That number is not between 5 and 10, please try again");
				}
			}
			else 
			{
				System.out.println("That is not an integer number, please try again");
			}
		}
		while (numDays < 5 || numDays > 10);
	}
	
	/**
	 * Sets the farmer's name via input from the user.
	 */
	public void inputFarmerName() 
	{
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("What is your name? (must be between 3 and 15 characters and must not include numbers or special characters)");
			
			if (scanner.hasNextLine())
			{
				farmerName = scanner.nextLine();
				if ((farmerName.length() < 3 || farmerName.length() > 15) && !isAlpha(farmerName)) 
				{
					System.out.println("That name is not between 3 and 15 characters and includes numbers or special characters, please try again");
				}
				else if (farmerName.length() < 3 || farmerName.length() > 15) 
				{
					System.out.println("That name is not between 3 and 15 characters, please try again");
				}
				else if (!isAlpha(farmerName)) 
				{
					System.out.println("That name includes numbers or special characters, please try again");
				}
			}
		}
		while (farmerName.length() < 3 || farmerName.length() > 15 || !isAlpha(farmerName));
	}
	
	/**
	 * Sets the farm type via input from the user.
	 */
	public void inputFarmType() 
	{
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("Please select a farm type from below by typing the corresponding number\n" + 
					"1: Normal farm: $150 starting money, average animal happiness, 10 crop spaces, the default farm.\n" +
					"2: Rich farm: $200 starting money, low animal happiness, 10 crop spaces.\n" + 
					"3: Happy farm: $100 starting money, high animal happiness, 10 crop spaces.\n" + 
					"4: Large farm: $100 starting money, low animal happiness, but hey, at least it has 20 crop spaces!");

			if (scanner.hasNextInt())
			{
				typeInt = scanner.nextInt();
				if (typeInt != 1 && typeInt != 2 && typeInt != 3 && typeInt != 4) 
				{
					System.out.println("That number is not between 1 and 4, please try again");
				}
			}
			else 
			{
				System.out.println("That is not an integer number, please try again");
			}
		}
		while(typeInt != 1 && typeInt != 2 && typeInt != 3 && typeInt != 4);
		
		if (typeInt == 1) 
		{ 
			farmType = "Normal";
		}
		else if (typeInt == 2) 
		{
			farmType = "Rich";
		}
		else if (typeInt == 3) 
		{
			farmType = "Happy";
		}
		else 
		{
			farmType = "Large";
		}
	}
	
	/**
	 * Sets the farm's name via input from the user.
	 */
	public void inputFarmName() 
	{
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("What is your farm's name?");
			
			if (scanner.hasNextLine())
			{
				farmName = scanner.nextLine();
			}
		}
		while (farmName == null);
	}
	
	/**
	 * Starts a new game by getting number of days, farmer name, farm type, and farm name.
	 */
	public void startGame() 
	{
		inputNumDays();
		inputFarmerName();
		inputFarmType();
		inputFarmName();
		
		farmer = new Farmer(farmerName, 1, 20); // First day, Default age is 20
		farm = new Farm(farmName, farmType);
		
		//Create store
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
		
	}
	
	/**
	 * A modular method that takes a String <code>optionString</code> and an int <code>numOptions</code>,
	 * Prints out the <code>optionString</code> and takes in an input of an int from the user.
	 * the optionString must have format: 
	 * 0. exit
	 * 1. option1
	 * 2. option2
	 * etc.
	 * the integer inputed by the user must be an option from the <code>optionString</code>,
	 * if it is not the method will ask again.
	 * @param optionsString String of options the user has.
	 * @param numOptions Number of options in optionString.
     */
	public int printOptions(String optionsString, int numOptions)
	{
		int option = -1; // No option chosen
		do
		{
			scanner = new Scanner(System.in);
			System.out.println(optionsString);

			if (scanner.hasNextInt())
			{
				option = scanner.nextInt();
				if (option < 0 || option > numOptions) 
				{
					System.out.println("That was not an option, please try again");
				}
			}
			else 
			{
				System.out.println("That is not an integer number, please try again");
			}
		}
		while(option < 0 || option > numOptions);
		
		return option;
	}
	
	/**
	 * The main command line application, here the player can control the game and manage their farm.
	 * Gets an input from the user.
	 */
	public void mainGame()
	{
		String optionsString = "Please chose an option from below:\n"
				+ "0. Exit Game\n"
				+ "1. View status of Crops and Animals\n"
				+ "2. View status of Farm\n"
				+ "3. Visit Store\n"
				+ "4. Sleep (Move to the next day)\n"
				+ "5. Tend to Crops\n"
				+ "6. Feed Animals\n"
				+ "7. Play with Animals\n"
				+ "8. Harvest Crops\n"
				+ "9. Tend to the Farm land";
		int numOptions = 9;
		
		int option = printOptions(optionsString, numOptions);
		switch(option)
		{
		case 0: // Exit game
			System.exit(0);
		case 1: // View status of Crops and Animals
			
			System.out.println("\n" + farm.getFarmName() + " has " + farm.getCrops().size() + " crops");
			for(Crop crop: farm.getCrops()) 
			{
				if (crop.getDaysLeftToGrow() > 0) 
				{
					System.out.println(crop.getName() + " Has been growing for " + crop.getDaysGrown() 
					+ " days, it needs " + (crop.getDaysLeftToGrow())
					+ " more days to be harvested");
				}
				else 
				{
					System.out.println(crop.getName() + " Has been growing for " + crop.getDaysGrown() 
					+ " days, it is ready to harvest!");
				}
			}
			
			System.out.println("\n" + farm.getFarmName() + " has " + farm.getAnimals().size() + " animals");
			for(Animal animal: farm.getAnimals()) 
			{
				System.out.println(animal.getName() + " has a happiness level of " + String.format("%.1f", animal.getHappiness())
					+ " and a healthiness level of " + String.format("%.1f", animal.getHealth())
					+ ", which equates to $" + returnDollarsCents(animal.getDailyProfit()) + " per day");
			}
			System.out.println("");
			
			break;
			
		case 2: // View status of Farm
			System.out.println(farm.getFarmName() + " currently has $" + returnDollarsCents(farm.getMoney()) + " available and " + farm.calculateFreeSpace() + " free spaces for new crops");
			break;
		case 3: // Visit Store
			visitStore();
			break;
		case 4: // Sleep
			nextDay();
			break;
		case 5: // Tend to Crops
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
			}
			else 
			{
				tendToCrops();
			}
			break;
		case 6: // Feed Animals
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
			}
			else 
			{
				feedAnimals();
			}
			break;
		case 7: // Play with Animals
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
			}
			else 
			{
				playWithAnimals();
			}
			break;
		case 8: // Harvest Crops
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
			}
			else 
			{
				harvestCrops();
			}
			break;
		case 9: // Tend to the Farm land
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
			}
			else 
			{
				tendFarmLand();
			}
			break;
		}
		System.out.println("");
		if (farmer.getDaysPassed() != numDays)
		{
			mainGame();
		}
	}
	
	/**
	 * The general store in the game.
	 * The player goes here to buy crops, animals and items.
	 * Gets an input from the user.
	 */
	public void visitStore()
	{
		boolean stayInStore = true;
		String optionsString = "You are at the General Store. Please chose an option from below:\n"
				+ "0. Exit Store\n"
				+ "1. View Crops for sale\n"
				+ "2. View Animals for sale\n"
				+ "3. View Items for sale\n"
				+ "4. View currently owned items\n"
				+ "5. Purchase Crops\n"
				+ "6. Purchase Animals\n"
				+ "7. Purchase Items\n";
		int numOptions = 7;
		
		int option = printOptions(optionsString, numOptions);
		switch(option)
		{
		case 0: // Exit store
			stayInStore = false;
			break;
		case 1: //View Crops for sale
			System.out.println("Crops for sale:");
			for(Crop crop: store.getCropsForSale()) 
			{
				System.out.println("\n" + crop.getName());
				System.out.println("Purchase price: $" + returnDollarsCents(crop.getPurchasePrice()));
				System.out.println("Sell price: $" + returnDollarsCents(crop.getSellPrice()));
				System.out.println("Days to grow: " + crop.getDaysToGrow());
			}
			System.out.println("");
			break;
		case 2: //View Animals for sale
			System.out.println("Animals for sale:");
			for(Animal animal: store.getAnimalsForSale()) 
			{
				System.out.println("\n" + animal.getName());
				System.out.println("Purchase price: $" + returnDollarsCents(animal.getPurchasePrice()));
				System.out.println("Daily profit at base Happiness: $" + returnDollarsCents(animal.getDailyProfit()));
			}
			System.out.println("");
			break;
		case 3: //View Items for sale
			System.out.println("Items for sale:");
			for(Item item: store.getItemsForSale()) 
			{
				System.out.println("\n" + item.getName());
				System.out.println("Purchase price: $" + returnDollarsCents(item.getPurchasePrice()));
				if (item.getType() == "Crop")
				{
					System.out.println("Benefit: Increases growth speed of a chosen type of crop by " 
					+ (item.getBonus()*100) + "% when used");
				}
				if (item.getType() == "Animal")
				{
					System.out.println("Benefit: Increases the health of all animals by " 
					+ (item.getBonus()*100) + "% when used");
				}
			}
			System.out.println("");
			break;
		case 4: //View currently owned items
			System.out.println("You own " + farm.getItems().size() + " items. They are:");
			for(Item item: store.getItemsForSale()) 
			{
				int numOwned = 0;
				for(Item checkItem: farm.getItems()) 
				{
					if (item.getName() == checkItem.getName())
					{
						numOwned++;
					}
				}
				if (numOwned != 0) // If the item is already owned
				{
					System.out.println(item.getName());
					System.out.println("You own " + numOwned + " of these");
					if (item.getType() == "Crop")
					{
						System.out.println("Benefit: Speeds up crop growth speed by " 
						+ (item.getBonus()*100) + "%");
					}
					if (item.getType() == "Animal")
					{
						System.out.println("Benefit: Increases an animal's health by " 
						+ (item.getBonus()*100) + "%");
					}
				}
			}
			break;
		case 5: //Purchase crops
			purchase("crop");
			break;
		case 6: //Purchase animals
			purchase("animal");
			break;
		case 7: //Purchase items
			purchase("item");
			break;
		}
		if (stayInStore)
		{
			visitStore();
		}
	}
	
	/**
	 * Called when the user wants to purchase an item from the store.
	 * Asks what item the user wants to purchase (can still say they don't want to purchase anything).
	 * The program then increases the farms ArrayList for crops, animals or items depending on what was brought.
	 * If the user has no space available then the program outputs a message.
	 * @param purchaseCategory The purchase category.
	 */
	public void purchase(String purchaseCategory) 
	{
		System.out.println("Please select what " + purchaseCategory + " you would like to buy:");
		int purchaseIndex = 0;
		String purchaseString = purchaseIndex + ". Don't buy anything\n";
		
		
		if (purchaseCategory == "crop") 
		{
			
			if (farm.calculateFreeSpace() > 0) 
			{
				int purchaseOption = printOptions(farm.returnCropsString("0. Don't buy anything\n", store.getCropsForSale()), store.getCropsForSale().size());
				
				if (farm.getMoney() < store.getCropsForSale().get(purchaseOption - 1).getPurchasePrice()) 
				{
					System.out.println("You don't have enough money to buy " + store.getCropsForSale().get(purchaseOption - 1).getName() + "!");
				}
				else if (purchaseOption != 0) // If the player did not choose None
				{
					farm.increaseCrops(store.getCropsForSale().get(purchaseOption - 1));
					System.out.println(store.getCropsForSale().get(purchaseOption - 1).getName() + " bought!");
				}
			}
			else 
			{
				System.out.println("You have no space available for new crops!\n"
						+ "To purchase crops again, you need to either tend to the farm land to increase crop space or harvest crops to remove current crops\n");
			}
			
		}
		
		else if (purchaseCategory == "animal") 
		{
			for(Animal animal: store.getAnimalsForSale()) 
			{
				purchaseIndex++;
				purchaseString += purchaseIndex + ". " + animal.getName() + "\n";
			}
			int purchaseOption = printOptions(purchaseString, purchaseIndex);
			
			if (farm.getMoney() < store.getAnimalsForSale().get(purchaseOption - 1).getPurchasePrice()) 
			{
				System.out.println("You don't have enough money to buy " + store.getAnimalsForSale().get(purchaseOption - 1).getName() + "!");
			}
			else if (purchaseOption != 0) // If the player did not choose None
			{
				farm.increaseAnimals(store.getAnimalsForSale().get(purchaseOption - 1));
				System.out.println(store.getAnimalsForSale().get(purchaseOption - 1).getName() + " bought!");
			}
		}
		
		else if (purchaseCategory == "item") 
		{
			for(Item item: store.getItemsForSale()) 
			{
				purchaseIndex++;
				purchaseString += purchaseIndex + ". " + item.getName() + "\n";
			}
			int purchaseOption = printOptions(purchaseString, purchaseIndex);
			
			if (farm.getMoney() < store.getItemsForSale().get(purchaseOption - 1).getPurchasePrice()) 
			{
				System.out.println("You don't have enough money to buy " + store.getItemsForSale().get(purchaseOption - 1).getName() + "!");
			}
			else if (purchaseOption != 0) // If the player did not choose None
			{
				farm.increaseItems(store.getItemsForSale().get(purchaseOption - 1));
				System.out.println(store.getItemsForSale().get(purchaseOption - 1).getName() + " bought!");
			}
		}
	}
	
	/**
	 * A simple function to check whether a string has only alphabetical letters.
	 * Returns false if it does not.
	 * @param name Name to be tested for only Alpha characters.
	 * @return true or false depending on <code>name</code>.
	 */
	public boolean isAlpha(String name) 
	{
	    return name.matches("[a-zA-Z]+");
	}
	
	/**
	 * Returns a double as a string with two decimal places, for use with dollars and cents.
	 * @param amount The amount of money wanting to be converted to 2dp.
	 */
	public String returnDollarsCents(double amount) 
	{
		return String.format("%.2f", amount);
	}
	
	/**
	 * A function for moving on to the next day, Called when the player sleeps.
	 * This function increases the farmers age, reduces the actions performed to 0 and grows the crops owned.
	 * If the farmers age is equal to the number of days set during startup, the finishing sequence begins.
	 */
	public void nextDay() 
	{
		System.out.println(farmer.getFarmerName() + " has slept.\n");
		farmer.increaseDaysPassed();
		actionsPerformed = 0;
		if (farmer.getDaysPassed() != numDays)
		{
			farm.growCrops();
			farm.increaseMoney(farm.collectAnimalMoney());
		}
		else
		{
			finishGame();
		}
	}
	
	/**
	 * Asks the user what type of crops they would like to tend to and what item they would want to use on it.
	 * the program then tends to all of the crops owned with the specified name.
	 */
	public void tendToCrops()
	{
		System.out.println("Please select the type of crop you would like to tend to:");
		ArrayList<Crop> differentCrops = returnDifferentCropsOwned();
		int tendOption = printOptions(farm.returnCropsString("0. Don't tend to anything\n", differentCrops), farm.getCrops().size());
		if (tendOption != 0)
		{
			String cropName = differentCrops.get(tendOption - 1).getName();
			System.out.println("Please select the item you would like to tend all of your " + cropName + " with:");
			String itemString = "0. Don't use anything\n"
					+ "1. Water (free)\n";
			
			ArrayList <Item> availCropItems = new ArrayList<Item>();
			
			for (Item item: farm.getItems())
			{
				if (item.getType() == "Crop")
					{
					availCropItems.add(item);
					}
			}
			
			int itemOption = printOptions(farm.returnItemsString(itemString, "Crop", 1), availCropItems.size() + 1);
			
			if (itemOption != 0)
			{
				actionsPerformed++;
				if (itemOption == 1) // Watered crops
				{
					farm.tendSpecificCrops(cropName, 1.0);
					System.out.println("Tended to every " + differentCrops.get(tendOption-1).getName() 
							+ " by watering them");
				}
				else // If an item was used on the crop
				{
					Item itemUsed = availCropItems.get(itemOption - 2);
					farm.tendSpecificCrops(cropName, itemUsed.getBonus());
					farm.decreaseItems(itemUsed);
					System.out.println("Tended to every " + differentCrops.get(tendOption-1).getName() 
							+ " by using " + itemUsed.getName() + " on them");
				}
			}
		}
	}
	
	/**
	 * This program feed all animals owned by first asking the user what item they would like to feed the animals 
	 * and then calling the increaseHappinessAllAnimals function in the Farm class.
	 */
	public void feedAnimals() 
	{
		System.out.println("Please select the item you would like to feed all of your animals with:");
		ArrayList <Item> availAnimalItems = new ArrayList<Item>();
		for (Item item: farm.getItems())
		{
			if (item.getType() == "Animal")
			{
				availAnimalItems.add(item);
			}
		}
		int itemOption = printOptions(farm.returnItemsString("0. Don't feed animals\n", "Animal", 0), availAnimalItems.size());
		if (itemOption != 0)
		{
			Item itemUsed = availAnimalItems.get(itemOption - 1);
			if (farm.increaseHealthAllAnimals(itemUsed.getBonus())) 
			{
				actionsPerformed++;
				farm.decreaseItems(itemUsed);
				System.out.println("Fed every animal with " + itemUsed.getName());
			}
			else 
			{
				System.out.println("You have no animals to feed!");
			}
		}
	}
	
	/**
	 * a function that allows the user to play with the animals owned, doing this will increase their happiness.
	 */
	public void playWithAnimals() 
	{
		if (farm.increaseHappinessAllAnimals()) 
		{
			actionsPerformed++;
			System.out.println("Played with every animal (and increased their happiness by doing so)");
		}
		else 
		{
			System.out.println("You have no animals to play with!");
		}
	}
	
	/**
	 * A function that will harvest the crops that can be harvested, 
	 * it does this by calling the harvestAvailableCrops function in the Farm class.
	 */
	public void harvestCrops() 
	{
		if (farm.canHarvestCrops()) 
		{
			actionsPerformed++;
			double moneyMade = farm.harvestAvailableCrops();
			farm.increaseMoney(moneyMade);
			System.out.println("You made $" + returnDollarsCents(moneyMade) + " from harvesting all your crops");
		}
		else 
		{
			System.out.println("You have no harvestable crops!");
		}
	}
	
	/**
	 * A function to tend to the farm land. tending to the farm land will increase the number of available slots for planting crops.
	 */
	public void tendFarmLand() 
	{
		actionsPerformed++;
		System.out.print("Farm land has been tended to, 1 more crop is able to be grown");
		if (farm.tendFarm()) 
		{
			System.out.print(", plus all of your animals now have increased happiness!");
		}
		System.out.println();
	}
	
	/**
	 * The finishGame function, called when the farmers age has reached the numDays set during startup.
	 * Prints out the farmers name, the number of days passed, the money made and the score.
	 */
	public void finishGame()
	{
		String profitString;
		double scoreProfit = farm.getProfit();
		double scoreAge = (15 - farmer.getDaysPassed());
		double scoreCropSize = farm.getCrops().size() + 1;
		double scoreCropSpace = farm.getCropSpace();
		
		if (scoreProfit <= 0.0) 
		{
			scoreProfit = 0;
			profitString = " made no profit!\n";
		}
		else 
		{
			profitString = " made $" + returnDollarsCents(farm.getProfit()) + " in profit.\n";
		}
		
		double score = scoreProfit * scoreAge * (scoreCropSize / scoreCropSpace);
		
		for (Animal animal: farm.getAnimals()) 
		{
			score += animal.getHappiness() * animal.getHealth();
		}
		
		System.out.println("The game has finished!\n"
				+ "Stats for " + farmer.getFarmerName() + " on the farm " + farm.getFarmName() + ":\n"
				+ farmer.getDaysPassed() + " days have passed.\n"
				+ farmer.getFarmerName() + profitString
				+ "Total Score: " + Math.round(score) + "\n" //rounds score to 0dp
				+ "Score is able to be increased by the profit, the healthiness and happiness of animals, the percentage of crop slots utilised, and by choosing a lower number of days!");
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
	 * main function of the program. this is where the game is started by calling the startGame and mainGame methods.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Farm Simulator!\n");
		GameEnvironmentCLI game = new GameEnvironmentCLI();
		game.startGame();
		game.mainGame();

	}
}
