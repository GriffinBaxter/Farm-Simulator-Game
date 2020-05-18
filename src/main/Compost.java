package main;

/**
 * Compost class extending the Item class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Compost extends Item
{
	
	/**
	 * Compost (crop item) constructor.
	 */
	public Compost()
	{
		super("Compost", "Crop", 100.0, 3.0); //Name, type (crop or animal), price, bonus
	}

}
