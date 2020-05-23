package animals;

import main.Animal;

/**
 * Sheep class extending the Animal class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Sheep extends Animal
{
	
	/**
	 * Sheep constructor which takes an initial animal happiness.
	 * @param initAnimalHappiness The sheep's initial happiness
	 */
	public Sheep(double initAnimalHappiness)
	{
		super("Sheep", 30.0, initAnimalHappiness, 20.0); //Name, purchase price, initial happiness, initial daily money made
	}

}
