package main;
//import java.util.ArrayList; // Re-add this if needed, otherwise will delete
import java.util.Scanner;
import java.util.ArrayList;

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
	 * Sets the number of days
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
	 * Sets the farmer's name
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
	 * Sets the farm type
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
	 * Sets the farm's name
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
	 * 1. option1
	 * 2. option2
	 * ect.
	 * the int inputed by the user must be an option from the <code>optionString</code>,
	 * if it is not the method will ask again
     */
	public int printOptions(String optionsString, int numOptions)
	{
		int option = -1;//nothing chosen yet
		do
		{
			scanner = new Scanner(System.in);
			System.out.println(optionsString);

			if (scanner.hasNextInt())
			{
				option = scanner.nextInt();
				if (option < 1 || option > numOptions) {
					System.out.println("That was not an option, please try again");
				}
			}
			else {
				System.out.println("That is not an integer number, please try again");
			}
		}
		while(option < 1 || option > numOptions);
		
		return option;
	}
	
	public void mainGame()
	{

		//Command line Application
		String optionsString = "Please chose an option from below:\n"
				+ "1. View status of Crops and Animals\n"
				+ "2. View status of Farm\n"
				+ "3. Visit Store\n"
				+ "4. Sleep\n"
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
		case 1: //View status of Crops and Animals
			
			System.out.println(farm.getFarmName() + " has " + farm.getCrops().size() + " crops");
			for(Crop crop: farm.getCrops()) 
			{
				System.out.println(crop.getName() + " Has been growing for " + crop.getDaysGrown() 
						+ " days, it needs " + (crop.getDaysToGrow() - crop.getDaysGrown())
						+ " more days to be harvested");
			}
			
			System.out.println(farm.getFarmName() + " has " + farm.getAnimals().size() + " animals");
			for(Animal animal: farm.getAnimals()) 
			{
				System.out.println(animal.getName() + " has a happiness level of " 
						+ animal.getHappiness() + ". This equates to $" + animal.dailyProfit() + " per day");
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
			if(actionsPerformed++ >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			//Do something here
			break;
		case 6: //Feed Animals
			if(actionsPerformed++ >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			//Do something here
			break;
		case 7: //Play with Animals
			if(actionsPerformed++ >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			//Do something here
			break;
		case 8: //Harvest Crops
			if(actionsPerformed++ >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			//Do something here
			break;
		case 9: //Tend to Farm land
			if(actionsPerformed++ >= 2)
			{
				System.out.println("You can not do this as you have no actions left");
				break;
			}
			//Do something here
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
				+ "1. View Crops for sale\n"
				+ "2. View Animals for sale\n"
				+ "3. View Items for sale\n"
				+ "4. View currently owned items\n"
				+ "5. Purchase Crops\n"
				+ "6. Purchase Animals\n"
				+ "7. Purchase Items\n"
				+ "8. Exit Store";
		int numOptions = 8;
		
		int option = printOptions(optionsString, numOptions);
		switch(option)
		{
		case 1:
			//View Crops for sale
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
		case 2:
			//View Animals for sale
			System.out.println("Animals for sale:");
			for(Animal animal: store.getAnimalsForSale()) 
			{
				System.out.println(animal.getName());
				System.out.println("Purchase price: " + animal.getPurchasePrice());
				System.out.println("Daily profit at base Happiness: " + animal.getDailyMoneyMade());
			}
			System.out.println("");
			break;
		case 3:
			//View Items for sale
			System.out.println("Items for sale:");
			for(Item item: store.getItemsForSale()) 
			{
				System.out.println(item.getName());
				System.out.println("Purchase price: " + item.getPurchasePrice());
				if (item.getType() == "Crop")
				{
					System.out.println("Benifit: Speeds up crop growth speed by " 
					+ (item.getBonus()*100) + "%");
				}
				if (item.getType() == "Animal")
				{
					System.out.println("Benifit: Increases an animals health by " 
					+ (item.getBonus()*100) + "%");
				}
			}
			System.out.println("");
			break;
		case 4:
			//View currently owned items
			System.out.println("You own " + farm.getItems().size() + " types of items. They are:");
			for(Item item: farm.getItems()) 
			{
				System.out.println(item.getName());
				System.out.println("You own " + item.getNumOwned() + " of these");
				if (item.getType() == "Crop")
				{
					System.out.println("Benifit: Speeds up crop growth speed by " 
					+ (item.getBonus()*100) + "%");
				}
				if (item.getType() == "Animal")
				{
					System.out.println("Benifit: Increases an animals health by " 
					+ (item.getBonus()*100) + "%");
				}
			}
			break;
		case 5:
			//Purchase crops
			System.out.println("Please select what crop you would like to buy:");
			String cropsString = "";
			int cropIndex = 1;
			for(Crop crop: store.getCropsForSale()) 
			{
				cropsString += cropIndex + ". " + crop.getName() + "\n";
				cropIndex++;
			}
			cropsString += cropIndex + ". None";
			
			int cropOption = printOptions(cropsString, cropIndex);
			if (cropOption <= store.getCropsForSale().size())//If the player did not choose None
			{
				farm.increaseCrops(store.getCropsForSale().get(cropOption - 1));
				System.out.println(store.getCropsForSale().get(cropOption - 1).getName() + " brought!");
			}
			else//If the player chose None
			{
				visitStore();
			}
			
			
			
			break;
		case 6:
			//Purchase animals
			System.out.println("Please select what animal you would like to buy:");
			String animalString = "";
			int animalIndex = 1;
			for(Animal animal: store.getAnimalsForSale()) 
			{
				animalString += animalIndex + ". " + animal.getName() + "\n";
				animalIndex++;
			}
			animalString += animalIndex + ". None";
			
			int animalOption = printOptions(animalString, animalIndex);
			if (animalOption <= store.getAnimalsForSale().size())//If the player did not choose None
			{
				farm.increaseAnimals(store.getAnimalsForSale().get(animalOption - 1));
				System.out.println(store.getAnimalsForSale().get(animalOption - 1).getName() + " brought!");
			}
			else//If the player chose None
			{
				visitStore();
			}
			break;
		case 7:
			//Purchase items
			System.out.println("Please select what item you would like to buy:");
			String itemsString = "";
			int itemIndex = 1;
			for(Item item: store.getItemsForSale()) 
			{
				itemsString += itemIndex + ". " + item.getName() + "\n";
				itemIndex++;
			}
			itemsString += itemIndex + ". None";
			
			int itemOption = printOptions(itemsString, itemIndex);
			if (itemOption <= store.getItemsForSale().size())//If the player did not choose None
			{
				farm.increaseItems(store.getItemsForSale().get(itemOption - 1));
				System.out.println(store.getItemsForSale().get(itemOption - 1).getName() + " brought!");
			}
			else//If the player chose None
			{
				visitStore();
			}
			break;
		case 8:
			//Exit store
			stayInStore = false;
			break;
		}
		if (stayInStore)
		{
			visitStore();
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
		if (farmer.getAge() == numDays)
		{
			finishGame();
		}
		else
		{
			//Grow crops
			farm.growCrops();
			farm.increaseMoney(farm.collectAnimalMoney());
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
	
	public static void main(String[] args) {
		System.out.println("SENG 201 Farm Simulator Project - By Griffin Baxter and Rutger van Kruiningen\n");
		GameEnvironment game = new GameEnvironment();
		game.startGame();
		game.mainGame();

	}
	


}
