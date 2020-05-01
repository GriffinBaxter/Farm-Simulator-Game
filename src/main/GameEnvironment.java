package main;
import java.util.ArrayList;

import java.util.Scanner;

public class GameEnvironment {
	
	private Farm farm;
	private Farmer farmer;
	
	
	/**
	 * Starts a new game
	 */
	public void startGame() {
		Scanner scanner = new Scanner(System.in);
		
		int numDays = 0;//have to set it to something...
		do
		{//loops forever when you put a string in the input
			System.out.println("How many days would you like the game to last? (Enter a number between 5 and 10)");
			try 
			{
			numDays = scanner.nextInt();
			} 
			
			catch (java.util.InputMismatchException e) 
			{
	                System.out.println("That is not a number, please try again");
	        }
		}
		while (numDays < 5 || numDays > 10);
		
		
		//Getting farmer name
		String farmerName = null;
		do
		{
			System.out.println("What is your name? (must be between 3 and 15 characters and must not include numbers or special characters)");
			if (scanner.hasNext())
			{
				farmerName = scanner.nextLine();
			}
		}
		while (farmerName == null);

		
		
		String typeString = null;
		//Selecting farm type
		do
		{
			System.out.println("Please select a farm type from below by typing the corresponding number");
			System.out.println("1: More starting money, Slower crop growing speed");
			System.out.println("2: Less starting money, Faster crop growing speed");
			System.out.println("3: Normal, the default farm");
			if (scanner.hasNext())
			{
				typeString = scanner.nextLine();
			}
			
		}
		while(typeString != "1" || typeString != "2" || typeString != "3");
		
		System.out.println(typeString);
		
		String farmName = null;
		
		
		scanner.close();
		farmer = new Farmer(farmerName, 0);//Age is 0
		farm = new Farm(farmName, typeString, farmer);
		
	}
	
	public boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	
	public void nextDay() {
		
	}
	
	public void randomEvent() {
		
	}
	
	public static void main(String[] args) {
		System.out.println("SENG 201 Farm Simulator Project - By Griffin Baxter and Rutger van Kruiningen\n");
		GameEnvironment game = new GameEnvironment();
		game.startGame();

	}

}
