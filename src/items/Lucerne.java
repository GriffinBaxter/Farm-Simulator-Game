package items;

import main.Item;

/**
 * Lucerne class extending the Item class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Lucerne extends Item
{
	
	/**
	 * Lucerne (animal item) constructor.
	 */
	public Lucerne()
	{
		super("Lucerne", "Animal", 20.0, 0.6); //Name, type (crop or animal), price, bonus
	}

}
