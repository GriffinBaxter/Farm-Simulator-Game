package main;

/**
 * Organic Fertiliser class extending the Item class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class OrganicFertiliser extends Item
{
	
	/**
	 * Organic Fertiliser (crop item) constructor.
	 */
	public OrganicFertiliser()
	{
		super("Organic Fertiliser", "Crop", 10.0, 2.0); //Name, type (crop or animal), price, bonus
	}

}
