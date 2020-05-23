package animals;

import main.Animal;

/**
 * Cow class extending the Animal class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Cow extends Animal
{
	
	/**
	 * Cow constructor which takes an initial animal happiness.
	 * @param initAnimalHappiness The cow's initial happiness
	 */
	public Cow(double initAnimalHappiness)
	{
		super("Cow", 50.0, initAnimalHappiness, 70.0); //Name, purchase price, initial happiness, initial daily money made
	}

}
