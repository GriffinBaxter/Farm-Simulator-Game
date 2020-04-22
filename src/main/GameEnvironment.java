package main;

import java.util.Scanner;

public class GameEnvironment {
	
	/**
	 * Starts a new game
	 */
	public void startGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many days would you like the game to last? (Enter a number between 5 and 10)");
		String numDays = scanner.nextLine();
		scanner.close();
		//Farm();
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
