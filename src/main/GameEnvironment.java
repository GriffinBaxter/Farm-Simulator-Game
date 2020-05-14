/*
 * To-Do
 * Edit tendToCrops and feedAnimals doc-strings to make sense for GUI implementation
 */


package main;
import java.util.ArrayList;
import java.lang.Math;
import java.util.ArrayList;
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
	private SetupScreen setupWindow;
	private MainScreen mainWindow;
	private StoreScreen storeWindow;
	private TendCropsScreen tendCropsWindow;
	private FeedAnimalsScreen feedAnimalsWindow;
	
	/**
	 * Starts a new game by getting number of days, farmer name, farm type, and farm name
	 */
	public void setupGame(int initNumDays, String farmerName, String farmType, String farmName) 
	{
		numDays = initNumDays;
		
		if ((farmerName.length() < 3 || farmerName.length() > 15 || !isAlpha(farmerName)) && (farmName.length() < 3 || farmName.length() > 15 || !isAlpha(farmName))) {
			setupWindow.setWarningText("farmer name and farm name are");
		}
		else if (farmerName.length() < 3 || farmerName.length() > 15 || !isAlpha(farmerName)) {
			setupWindow.setWarningText("farmer name is");
		}
		else if (farmName.length() < 3 || farmName.length() > 15 || !isAlpha(farmName)) {
			setupWindow.setWarningText("farm name is");
		}
		else {
			setupWindow.setWarningText("");
			farmer = new Farmer(farmerName, 1); //Age is 1, first day
			farm = new Farm(farmName, farmType, farmer);
			store = farm.getStore();
			closeSetupScreen(setupWindow);
		}
		
	}
	
	
	
	/**
	 * ARCHIVED from printOptions method
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

	
	
	/**
	 * ARCHIVED from MainGame method
	 * The main command line application, here the player can control the game and manage their farm.
	 * Gets an input from the user
	 */
	
	
	/**
	 * ARCHIVED from VisitStore method
	 * The general store in the game.
	 * The player goes here to buy crops, animals and items.
	 * Gets an input from the user.
	 */
	
	
	/**
	 * ARCHIVED from Purchase method
	 * Called when the user wants to purchase an item from the store.
	 * Asks what item the user wants to purchase (can still say they don't want to purchase anything).
	 * The program then increases the farms ArrayList for crops, animals or items depending on what was brought.
	 * If the user has no space available then the program outputs a message.
	 * @param purchaseCategory
	 */
	
	
	/**
	 * A simple function to check whether a string has only alphabetical letters.
	 * Returns false if it does not.
	 * @param name
	 * @return
	 */
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z ]+");
	}
	
	/**
	 * Returns a double as a string with two decimal places, for use with dollars and cents.
	 */
	public String returnDollarsCents(double amount) {
		return String.format("%.2f", amount);
	}
	
	/**
	 * A function for moving on to the next day, Called when the player sleeps.
	 * This function increases the farmers age, reduces the actions performed to 0 and grows the crops owned.
	 * If the farmers age is equal to the number of days set during startup, the finishing sequence begins.
	 */
	public void nextDay() {
		//if the game is finished
		//System.out.println(farmer.getFarmerName() + " has slept.\n");
		farmer.increaseAge();
		actionsPerformed = 0;
		if (farmer.getAge() != numDays + 1)
		{
			//Grow crops
			farm.growCrops();
			farm.increaseMoney(farm.collectAnimalMoney());
		}
	}
	
	/**
	 * Returns a String array with the name of all of the crops currently owned
	 */
	public String[] returnCropTypeArray() {
		ArrayList<Crop> differentCrops = farm.returnDifferentCropsOwned();
		ArrayList<String> differentCropNames = new ArrayList<String>();
		for(Crop crop: differentCrops) {
			differentCropNames.add(crop.getName());
		}
		String[] cropArray = differentCropNames.toArray(new String[0]);
		return cropArray;
	}
	
	/**
	 * Returns a String array with the name of all of the items currently owned given the correct String <code>itemType</code>
	 */
	public String[] returnCurrentItemsArray(String itemType) {
		ArrayList<String> currentItems = new ArrayList<String>();
		
		if (itemType == "Crop") {
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
	 * Asks the user what type of crops they would like to tend to and what item they would want to use on it.
	 * the program then tends to all of the crops owned with the specified name
	 */
	public String tendToCrops(int cropIndex, int itemIndex) { // Need to edit the above doc-string for this method!
		if (actionsPerformed >= 2) {
			return "You cannot do this as you have no actions left";
		}
		else {
			String cropName = returnCropTypeArray()[cropIndex];
			actionsPerformed++; //Increase the actions performed by 1 after the player has chosen to tend to their crops
			if (itemIndex == 0) //Watered crops
			{
				farm.tendSpecificCrops(cropName, 1.0);
				return "Tended to every " + cropName + " by watering them";
			}
			else //If used an item on the crop
			{
				Item itemUsed = farm.getItems().get(itemIndex - 1);
				farm.tendSpecificCrops(cropName, itemUsed.getBonus());
				farm.decreaseItems(itemUsed); //Remove the item from the users inventory
				return "Tended to every " + cropName + " by using " + itemUsed.getName() + " on them";
			}
		}
	}
	
	/**
	 * This program feed all animals owned by first asking the user what item they would like to feed the animals 
	 * and then calling the increaseHappinessAllAnimals function in the Farm class.
	 */
	public String feedAnimals(int itemIndex) { // Need to edit the above doc-string for this method!
		if (actionsPerformed >= 2) {
			return "You cannot do this as you have no actions left";
		}
		else {
			Item itemUsed = farm.getItems().get(itemIndex + returnCropItemSize());
				
			farm.increaseHappinessAllAnimals(itemUsed.getBonus());
			actionsPerformed++;
			farm.decreaseItems(itemUsed); //Remove the item from the users inventory
			return "Fed every animal with " + itemUsed.getName();
		}
	}
	
	/**
	 * a function that allows the user to play with the animals owned, doing this will increase their happiness.
	 */
	public String playWithAnimals() {
		if (actionsPerformed >= 2) {
			return "You cannot do this as you have no actions left";
		}
		else if (farm.playWithAllAnimals()) {
			actionsPerformed++;
			return "Played with every animal (and increased their happiness by doing so)";
		}
		else {
			return "You have no animals to play with, so no actions were used";
		}
	}
	
	/**
	 * A function that will harvest the crops that can be harvested, 
	 * it does this by calling the harvestAvailableCrops function in the Farm class.
	 */
	public String harvestCrops() {
		if (actionsPerformed >= 2) {
			return "You cannot do this as you have no actions left";
		}
		else if (farm.canHarvestCrops()) {
			actionsPerformed++;
			double moneyMade = farm.harvestAvailableCrops();
			farm.increaseMoney(moneyMade);
			return "You made $" + returnDollarsCents(moneyMade) + " from harvesting all your crops";
		}
		else {
			return "You have no harvestable crops, so no actions were used";
		}
	}
	
	/**
	 * A function to tend to the farm land. tending to the farm land will increase the number of available slots for planting crops.
	 */
	public String tendFarmLand() {
		if (actionsPerformed >= 2) {
			return "You cannot do this as you have no actions left";
		}
		else {
			actionsPerformed++;
			String returnString = "Farm land has been tended to, 1 more crop is able to be grown";
			if (farm.tendFarm()) {
				returnString += ", plus all of your animals now have increased health!";
			}
			return returnString;
		}
	}
	
	/**
	 * The finishGame function, called when the farmers age has reached the numDays set during startup.
	 * Prints out the farmers name, the number of days passed, the money made and the score.
	 */
	public String finishGame()
	{
		String profitString;
		double scoreProfit = farm.getProfit();
		double scoreAge = (15 - farmer.getAge());
		double scoreCropSize = farm.getCrops().size() + 1;
		double scoreCropSpace = farm.getCropSpace();
		
		if (scoreProfit <= 0.0) {
			scoreProfit = 0;
			profitString = " made no profit!\r\n";
		}
		else {
			profitString = " made $" + returnDollarsCents(farm.getProfit()) + " in profit.\r\n";
		}
		
		double score = scoreProfit * scoreAge * (scoreCropSize / scoreCropSpace);
		
		for (Animal animal: farm.getAnimals()) {
			score += animal.getHappiness() * animal.getHealth();
		}
		
		 String returnString = "The game has finished!\n"
				+ "Stats for " + farmer.getFarmerName() + " on the farm " + farm.getFarmName() + ":\r\n"
				+ (farmer.getAge() - 1) + " days have passed.\r\n"
				+ farmer.getFarmerName() + profitString
				+ "Total Score: " + Math.round(score) + "\r\n"
				+ "Score is able to be increased by the profit, the healthiness and happiness of animals, the percentage of crop slots utilised, and by choosing a lower number of days!"; //rounds score to 0dp
		 return returnString;
	}
	
	/**
	 * A method to launch the main screen where the user controls the game
	 */
	public void launchMainScreen() {
		mainWindow = new MainScreen(this);
	}
	
	/**
	 * A method to close the main screen
	 */
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	
	/**
	 * A method to launch the setup screen where the user sets up the game
	 */
	public void launchSetupScreen() {
		setupWindow = new SetupScreen(this);
	}
	
	/**
	 * A method to close the setup screen
	 */
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		launchMainScreen(); // Only here for closing setup screen, as this is used once
	}
	
	/**
	 * A method to launch the store screen where the user buys crops, animals and items
	 */
	public void launchStoreScreen() {
		storeWindow = new StoreScreen(this);
	}
	
	/**
	 * A method to close the store screen
	 */
	public void closeStoreScreen(StoreScreen storeWindow) {
		storeWindow.closeWindow();
	}
	
	public void launchTendCropsScreen() {
		tendCropsWindow = new TendCropsScreen(this);
	}
	
	public void closeTendCropsScreen(TendCropsScreen tendCropsWindow) {
		tendCropsWindow.closeWindow();
	}
	
	public void launchFeedAnimalsScreen() {
		feedAnimalsWindow = new FeedAnimalsScreen(this);
	}
	
	public void closeFeedAnimalsScreen(FeedAnimalsScreen feedAnimalsWindow) {
		feedAnimalsWindow.closeWindow();
	}
	
	public String returnStatusCropsAnimals()
	{
		String returnString = "";
		returnString += farm.getFarmName() + " has " + farm.getCrops().size() + " crops\r\n";
		for(Crop crop: farm.getCrops()) 
		{
			if (crop.getDaysLeftToGrow() > 0) {
				returnString += crop.getName() + " Has been growing for " + crop.getDaysGrown() 
				+ " days, it needs " + (crop.getDaysLeftToGrow())
				+ " more days to be harvested\r\n";
			}
			else {
				returnString += crop.getName() + " Has been growing for " + crop.getDaysGrown() 
				+ " days, it is ready to harvest!\r\n";
			}
		}
		
		returnString += "\r\n" + farm.getFarmName() + " has " + farm.getAnimals().size() + " animals\r\n";
		for(Animal animal: farm.getAnimals()) 
		{
			returnString += animal.getName() + " has a happiness level of " + String.format("%.1f", animal.getHappiness())
				+ " and a healthiness level of " + String.format("%.1f", animal.getHealth())
				+ ", which equates to $" + returnDollarsCents(animal.dailyProfit()) + " per day\r\n";
		}
		//String test = "l\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\n" + "l\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\nl\r\n";
		return returnString;
	}
	
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
	
	
	public String returnMoneyString()
	{
		return returnDollarsCents(farm.getMoney());
	}
	
	public int returnFreeCropSpace()
	{
		return farm.calculateFreeSpace();
	}
	
	public int returnDays()
	{
		return farmer.getAge();
	}
	
	public int getNumDays()
	{
		return numDays;
	}
	
	public int getActionsPerformed() {
		return actionsPerformed;
	}
	
	public String[] returnCropArray() {
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
	
	public String purchaseCrop(int purchaseOption) {
		String purchaseCropString = "";
			if (farm.calculateFreeSpace() > 0) {
				if (farm.getMoney() < store.getCropsForSale().get(purchaseOption).getPurchasePrice()) {
					purchaseCropString = "You don't have enough money to buy " + store.getCropsForSale().get(purchaseOption).getName() + "!" ;
				}
				else
				{
					farm.increaseCrops(store.getCropsForSale().get(purchaseOption));
					purchaseCropString = store.getCropsForSale().get(purchaseOption).getName() + " bought!";
				}
			}
			else {
				purchaseCropString = "You have no space available for new crops!\n"
						+ "To purchase crops again, you need to either tend to the farm land to increase crop space or harvest crops to remove current crops";
			}
		return purchaseCropString;
	}
	
	public String[] returnAnimalArray() {
		ArrayList<String> animalArrayList = new ArrayList<String>();
		for(Animal animal: store.getAnimalsForSale()) 
		{
			animalArrayList.add(animal.getName()
			+ ", Purchase price: $" + returnDollarsCents(animal.getPurchasePrice())
			+ ", Daily profit at base Happiness: $" + returnDollarsCents(animal.dailyProfit()));
		}
		String[] animalArray = animalArrayList.toArray(new String[0]);
		return animalArray;
	}
	
	public String purchaseAnimal(int purchaseOption) {
		String purchaseAnimalString = "";
			if (farm.getMoney() < store.getAnimalsForSale().get(purchaseOption).getPurchasePrice()) {
				purchaseAnimalString = "You don't have enough money to buy " + store.getAnimalsForSale().get(purchaseOption).getName() + "!" ;
			}
			else
			{
				farm.increaseAnimals(store.getAnimalsForSale().get(purchaseOption));
				purchaseAnimalString = store.getAnimalsForSale().get(purchaseOption).getName() + " bought!";
			}
		return purchaseAnimalString;
	}
	
	public String[] returnItemArray() {
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
	
	public String purchaseItem(int purchaseOption) {
		String purchaseItemString = "";
		if (farm.getMoney() < store.getItemsForSale().get(purchaseOption).getPurchasePrice()) {
			purchaseItemString = "You don't have enough money to buy " + store.getItemsForSale().get(purchaseOption).getName() + "!" ;
		}
		else
		{
			farm.increaseItems(store.getItemsForSale().get(purchaseOption));
			purchaseItemString = store.getItemsForSale().get(purchaseOption).getName() + " bought!";
		}
	return purchaseItemString;
	}
	
	public boolean gameFinishing()
	{
		return farmer.getAge() == numDays + 1;
	}
	
	public ArrayList<Crop> getCrops() {
		return farm.getCrops();
	}
	
	public ArrayList<Animal> getAnimals() {
		return farm.getAnimals();
	}
	
	public int returnCropItemSize() {
		int size = 0;
		for (Item item: farm.getItems()) {
			if (item.getType() == "Crop") {
				size++;
			}
		}
		return size;
	}
	
	public int returnAnimalItemSize() {
		int size = 0;
		for (Item item: farm.getItems()) {
			if (item.getType() == "Animal") {
				size++;
			}
		}
		return size;
	}
	
	/**
	 * main function of the program. this is where the game is started by calling the startGame and mainGame methods.
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("SENG 201 Farm Simulator Project - By Griffin Baxter and Rutger van Kruiningen\n");
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
	}
	


}
