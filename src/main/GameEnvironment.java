package main;
import java.util.ArrayList;
//import java.util.ArrayList; // Re-add this if needed, otherwise will delete
import java.util.Scanner;

public class GameEnvironment {
	
	private Farm farm;
	private Farmer farmer;
	private Store store;
	
	private Scanner scanner;
	private int numDays = 0;
	private String farmerName = "";
	private int typeInt = 0;
	private String farmType;
	private String farmName = null;
	private int actionsPerformed = 0;
	
	/**
	 * Sets the number of days via input from the user
	 */
	public void inputNumDays() {
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("How many days would you like the game to last? (Enter a number between 5 and 10)");
			
			if (scanner.hasNextInt()) {
				numDays = scanner.nextInt();
				
				if (numDays < 5 || numDays > 10) {
					System.out.println("That number is not between 5 and 10, please try again");
				}
			}
			else {
				System.out.println("That is not an integer number, please try again");
			}
		}
		while (numDays < 5 || numDays > 10);
	}
	
	/**
	 * Sets the farmer's name via input from the user
	 */
	public void inputFarmerName() {
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("What is your name? (must be between 3 and 15 characters and must not include numbers or special characters)");
			
			if (scanner.hasNextLine())
			{
				farmerName = scanner.nextLine();
				if ((farmerName.length() < 3 || farmerName.length() > 15) && !isAlpha(farmerName)) {
					System.out.println("That name is not between 3 and 15 characters and includes numbers or special characters, please try again");
				}
				else if (farmerName.length() < 3 || farmerName.length() > 15) {
					System.out.println("That name is not between 3 and 15 characters, please try again");
				}
				else if (!isAlpha(farmerName)) {
					System.out.println("That name includes numbers or special characters, please try again");
				}
			}
		}
		while (farmerName.length() < 3 || farmerName.length() > 15 || !isAlpha(farmerName));
	}
	
	/**
	 * Sets the farm type via input from the user
	 */
	public void inputFarmType() { // We need 4 farm types as per the specifications, 4th one will be added later
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("Please select a farm type from below by typing the corresponding number\n" + 
					"1: More starting money, Slower crop growing speed.\n" + 
					"2: Less starting money, Faster crop growing speed.\n" + 
					"3: Normal, the default farm.");

			if (scanner.hasNextInt())
			{
				typeInt = scanner.nextInt();
				if (typeInt != 1 && typeInt != 2 && typeInt != 3) {
					System.out.println("That number is not between 1 and 3, please try again");
				}
			}
			else {
				System.out.println("That is not an integer number, please try again");
			}
		}
		while(typeInt != 1 && typeInt != 2 && typeInt != 3);
		
		// This is temporary, farmType will be a readable string once the names of each farm type are agreed upon
		if (typeInt == 1) { 
			farmType = "1";
		}
		else if (typeInt == 2) {
			farmType = "2";
		}
		else {
			farmType = "3";
		}
	}
	
	/**
	 * Sets the farm's name via input from the user
	 */
	public void inputFarmName() {
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
	 * Starts a new game
	 */
	public void startGame() {
		
		// Might use a single function for some of these with varying inputs, as their functions are quite similar to each other
		inputNumDays();
		inputFarmerName();
		inputFarmType();
		inputFarmName();
		
		farmer = new Farmer(farmerName, 1); //Age is 1, first day
		farm = new Farm(farmName, farmType, farmer);
		store = new Store();
	}
	
	
	
	/**
	 * A modular method that takes a String <code>optionString</code> and an int <code>numOptions</code>,
	 * prints out the <code>optionString</code> and takes in an input of an int from the user
	 * the optionString must have format: 
	 * 0. exit
	 * 1. option1
	 * 2. option2
	 * etc.
	 * the int inputed by the user must be an option from the <code>optionString</code>,
	 * if it is not the method will ask again
     */
	public int printOptions(String optionsString, int numOptions)
	{
		int option = -1; //nothing chosen yet
		do
		{
			scanner = new Scanner(System.in);
			System.out.println(optionsString);

			if (scanner.hasNextInt())
			{
				option = scanner.nextInt();
				if (option < 0 || option > numOptions) {
					System.out.println("That was not an option, please try again");
				}
			}
			else {
				System.out.println("That is not an integer number, please try again");
			}
		}
		while(option < 0 || option > numOptions);
		
		return option;
	}
	
	public void mainGame()
	{

		//Command line Application
		String optionsString = "Please chose an option from below:\n"
				+ "0. Exit Game\n"
				+ "1. View status of Crops and Animals\n"
				+ "2. View status of Farm\n"
				+ "3. Visit Store\n"
				+ "4. Sleep (Move to the next day)\n"
				//Actions required
				+ "5. Tend to Crops\n"
				+ "6. Feed Animals\n"
				+ "7. Play with Animals\n"
				+ "8. Harvest Crops\n"
				+ "9. Tend to Farm land";
		int numOptions = 9;
		
		int option = printOptions(optionsString, numOptions);
		//System.out.println("you have selected option " + option);//for testing
		//use a switch to carry out selected option
		switch(option)
		{
		case 0: // Exit game
			System.exit(0);
		case 1: //View status of Crops and Animals
			
			System.out.println(farm.getFarmName() + " has " + farm.getCrops().size() + " crops");
			for(Crop crop: farm.getCrops()) 
			{
				System.out.println(crop.getName() + " Has been growing for " + crop.getDaysGrown() 
						+ " days, it needs " + (crop.getDaysToGrow() - crop.getDaysGrown()) // Need to modify this so that it says ready to harvest when ready, rather than saying it needs 0 or a negative number of days to harvest
						+ " more days to be harvested");
			}
			
			System.out.println(farm.getFarmName() + " has " + farm.getAnimals().size() + " animals");
			for(Animal animal: farm.getAnimals()) 
			{
				System.out.println(animal.getName() + " has a happiness level of " 
						+ animal.getHappiness() + ". This equates to $" + animal.dailyProfit() + " per day"); // Also need to add animal health to this
			}
			System.out.println("");
			
			break;
			
		case 2: //View status of Farm
			System.out.println(farm.getFarmName() + " currently has $" + farm.getMoney() + " available"); // Probably will fix this to format dollars and cents properly
			break;
		case 3: //Visit Store
			visitStore();
			break;
		case 4: //Sleep
			nextDay();
			break;
		case 5: //Tend to Crops
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
			}
			else {
				tendToCrops(); //actionsPerformed is incremented in the tendToCrops() function
			}
			break;
		case 6: //Feed Animals
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			else {
				feedAnimals(); //actionsPerformed is incremented in the feedAnimals() function
			}
			break;
		case 7: //Play with Animals
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			else {
				playWithAnimals(); //actionsPerformed is incremented in the playWithAnimals() function
			}
			break;
		case 8: //Harvest Crops
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			else {
				harvestCrops(); //actionsPerformed is incremented in the harvestCrops() function
			}
			//actionsPerformed++;
			break;
		case 9: //Tend to Farm land
			if(actionsPerformed >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			//Do something here
			actionsPerformed++;
			break;
			
		}
		System.out.println("");
		if (farmer.getAge() != numDays)
		{
			mainGame();
		}
	}
	
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
		case 0: //Exit store
			stayInStore = false;
			break;
		case 1: //View Crops for sale
			System.out.println("Crops for sale:");
			for(Crop crop: store.getCropsForSale()) 
			{
				System.out.println(crop.getName());
				System.out.println("Purchase price: " + crop.getPurchasePrice());
				System.out.println("Sell price: " + crop.getSellPrice());
				System.out.println("Days to grow: " + crop.getDaysToGrow());
			}
			System.out.println("");
			break;
		case 2: //View Animals for sale
			System.out.println("Animals for sale:");
			for(Animal animal: store.getAnimalsForSale()) 
			{
				System.out.println(animal.getName());
				System.out.println("Purchase price: " + animal.getPurchasePrice());
				System.out.println("Daily profit at base Happiness: " + animal.getDailyMoneyMade());
			}
			System.out.println("");
			break;
		case 3: //View Items for sale
			System.out.println("Items for sale:");
			for(Item item: store.getItemsForSale()) 
			{
				System.out.println(item.getName());
				System.out.println("Purchase price: " + item.getPurchasePrice());
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
				//Getting the quantities of items brought
				
				//Make new function in farm class that checks quantities and returns a list with items, maybe use sets or something
				int numOwned = 0;
				for(Item checkItem: farm.getItems()) 
				{
					if (item.getName() == checkItem.getName())
					{
						numOwned++;
					}
				}
				if (numOwned != 0)//If you own this item
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
	
	public void purchase(String purchaseCategory) {
		System.out.println("Please select what " + purchaseCategory + " you would like to buy:");
		int purchaseIndex = 0;
		String purchaseString = purchaseIndex + ". Don't buy anything\n";
		
		
		if (purchaseCategory == "crop") {
			
			int purchaseOption = printOptions(farm.returnCropsString("0. Don't buy anything\n", store.getCropsForSale()), store.getCropsForSale().size());
			
			if (purchaseOption != 0) //If the player did not choose None
			{
				farm.increaseCrops(store.getCropsForSale().get(purchaseOption - 1));
				System.out.println(store.getCropsForSale().get(purchaseOption - 1).getName() + " bought!");
			}
			else //If the player chose None
			{
				//visitStore();
			}
		}
		
		else if (purchaseCategory == "animal") {
			for(Animal animal: store.getAnimalsForSale()) 
			{
				purchaseIndex++;
				purchaseString += purchaseIndex + ". " + animal.getName() + "\n";
			}
			int purchaseOption = printOptions(purchaseString, purchaseIndex);
			
			if (purchaseOption != 0) //If the player did not choose None
			{
				farm.increaseAnimals(store.getAnimalsForSale().get(purchaseOption - 1));
				System.out.println(store.getAnimalsForSale().get(purchaseOption - 1).getName() + " bought!");
			}
			else //If the player chose None
			{
				//visitStore();
			}
		}
		
		else if (purchaseCategory == "item") {
			for(Item item: store.getItemsForSale()) 
			{
				purchaseIndex++;
				purchaseString += purchaseIndex + ". " + item.getName() + "\n";
			}
			int purchaseOption = printOptions(purchaseString, purchaseIndex);
			
			if (purchaseOption != 0) //If the player did not choose None
			{
				farm.increaseItems(store.getItemsForSale().get(purchaseOption - 1));
				System.out.println(store.getItemsForSale().get(purchaseOption - 1).getName() + " bought!");
			}
			else //If the player chose None
			{
				//visitStore();
			}
		}
	}
	
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	public void nextDay() {
		//if the game is finished
		System.out.println("You have slept.");
		farmer.increaseAge();
		actionsPerformed = 0;
		if (farmer.getAge() != numDays)
		{
			//Grow crops
			farm.growCrops();
			farm.increaseMoney(farm.collectAnimalMoney());
		}
		else
		{
			finishGame();
		}
	}

	public void finishGame()
	{
		System.out.println("The game has finished!\n"
				+ farmerName + "\n"
				+ farmer.getAge() + " days have passed.\n"
				+ "You made $" + farm.getProfit() + "\n"
				+ "Score: ");
	}
	
	public void tendToCrops()
	{
		System.out.println("Please select the type of crop you would like to tend to:");
		ArrayList<Crop> differentCrops = farm.returnDifferentCropsOwned();
		int tendOption = printOptions(farm.returnCropsString("0. Don't tend to anything\n", differentCrops), farm.getCrops().size());
		if (tendOption != 0)
		{
			String cropName = differentCrops.get(tendOption-1).getName();
			System.out.println("Please select the item you would like to tend all of your " + cropName + " with:");
			String itemString = "0. Don't use anything\n"
					+ "1. Water (free)\n";
			
			int itemOption = printOptions(farm.returnItemsString(itemString, "Crop", 1), farm.getItems().size() + 1);
			
			if (itemOption != 0)
			{
				actionsPerformed++;//Increase the actions performed by 1 after the player has chosen to tend to their crops
				if (itemOption == 1)//Watered crops
				{
					farm.tendSpecificCrops(cropName, 1.0);
					System.out.println("Tended to every " + differentCrops.get(tendOption-1).getName() 
							+ " by watering them");
				}
				else//If used an item on the crop
				{
					Item itemUsed = farm.getItems().get(itemOption-2);
					farm.tendSpecificCrops(cropName, itemUsed.getBonus());
					//Remove the item from the users inventory
					farm.decreaseItems(itemUsed);

					System.out.println("Tended to every " + differentCrops.get(tendOption-1).getName() 
							+ " by using " + itemUsed.getName() + " on them");
				}
			}
			//Get what item the user would like to use on the type of crop, 
			//then call farm.tendSpecificCrops(String cropName, double daysToIncrease)
			//The days to increase would be the bonus for the item and if the crop is just watered then make daysToIncrease 1?
			
		}
	}
	
	public void feedAnimals() {
		System.out.println("Please select the item you would like to feed all of your animals with:");
		int itemOption = printOptions(farm.returnItemsString("0. Don't feed animals\n", "Animal", 0), farm.getItems().size());
		
		if (itemOption != 0)
		{
			
			Item itemUsed = farm.getItems().get(itemOption-1);
			
			if (farm.feedAllAnimals(itemUsed.getBonus())) {
				actionsPerformed++;
				farm.decreaseItems(itemUsed); //Remove the item from the users inventory
				System.out.println("Fed every animal with " + itemUsed.getName());
			}
			else {
				System.out.println("You have no animals to feed!");
			}
		}
	}
	
	public void playWithAnimals() {
		if (farm.playWithAllAnimals()) {
			actionsPerformed++;
			System.out.println("Played with every animal (and increased their happiness by doing so)");
		}
		else {
			System.out.println("You have no animals to play with!");
		}
	}
	
	public void harvestCrops() {
		if (farm.canHarvestCrops()) {
			actionsPerformed++;
			double moneyMade = farm.harvestAvailableCrops();
			farm.increaseMoney(moneyMade);
			System.out.println("You made $" + moneyMade + " from harvesting all your crops");
		}
		else {
			System.out.println("You have no harvestable crops!");
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("SENG 201 Farm Simulator Project - By Griffin Baxter and Rutger van Kruiningen\n");
		GameEnvironment game = new GameEnvironment();
		game.startGame();
		game.mainGame();

	}
	


}
