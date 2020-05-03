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
	public void setNumDays() {
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
	public void setFarmerName() {
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
	public void setFarmType() { // We need 4 farm types as per the specifications, 4th one will be added later
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
	public void setFarmName() {
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
		setNumDays();
		setFarmerName();
		setFarmType();
		setFarmName();
		
		farmer = new Farmer(farmerName, 0); //Age is 0
		farm = new Farm(farmName, farmType, farmer);
	}
	
	public void mainGame()
	{
		
	}
	
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	
	public void nextDay() {
		farmer.increaseAge();
		//Grow crops
		farm.growCrops();
		farm.increaseMoney(farm.collectAnimalMoney());
	}
	
	public void randomEvent() {
		
	}
	
	public static void main(String[] args) {
		System.out.println("SENG 201 Farm Simulator Project - By Griffin Baxter and Rutger van Kruiningen\n");
		GameEnvironment game = new GameEnvironment();
		game.startGame();
		game.mainGame();

	}

}
