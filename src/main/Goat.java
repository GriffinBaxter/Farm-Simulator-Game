package main;

/**
 * Goat class extending the Animal class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Goat extends Animal
{
	
	/**
	 * Goat constructor which takes an initial animal happiness.
	 * @param initAnimalHappiness The goat's initial happiness
	 */
	public Goat(double initAnimalHappiness)
	{
		super("Goat", 25.0, initAnimalHappiness, 30.0); //Name, purchase price, initial happiness, initial daily money made
	}

}
