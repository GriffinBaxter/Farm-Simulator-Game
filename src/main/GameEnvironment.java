package main;
//import java.util.ArrayList; // Re-add this if needed, otherwise will delete
import java.util.Scanner;

public class GameEnvironment {
	
	private Farm farm;
	private Farmer farmer;
	private Scanner scanner;
	private int numDays = 0;
	private String farmerName = "";
	private int typeInt = 0;
	private String farmType;
	private String farmName = null;
	
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
	}
	
	public void mainGame()
	{
		//Command line Application
		int option = printOptions();
		//System.out.println("you have selected option " + option);//for testing
		//use a switch to carry out selected option
		switch(option)
		{
		case 1:
			//buy some cool items
			break;
		case 2:
			nextDay();
			break;
		}
	}
	
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	
	public void nextDay() {
		//if the game is finished
		System.out.println("You have slept.");
		farmer.increaseAge();
		if (farmer.getAge() == numDays)
		{
			finishGame();
		}
		else
		{
			//Grow crops
			farm.growCrops();
			farm.increaseMoney(farm.collectAnimalMoney());
			mainGame();
		}
	}

	public void finishGame()
	{
		System.out.println("The game has finished!\n"
				+ farmerName + "\n"
				+ farmer.getAge() + " days have passed.\n"
				+ "You ended with " + farm.getProfit() + "\n"
				+ "Score: ");
	}
	
	public int printOptions()
	{
		int option = -1;//nothing chosen yet
		int numOptions = 2;//change this depending on the number of options available
		do
		{
			scanner = new Scanner(System.in);
			System.out.println("Please chose an option from below:\n"
					+ "1. Buy items\n"
					+ "2. Sleep");

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
	
	
	public static void main(String[] args) {
		System.out.println("SENG 201 Farm Simulator Project - By Griffin Baxter and Rutger van Kruiningen\n");
		GameEnvironment game = new GameEnvironment();
		game.startGame();
		game.mainGame();

	}

}
