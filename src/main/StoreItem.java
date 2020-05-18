package main;

/**
 * Store Item interface.
 * This interface specifies what methods classes with StoreItem implemented should have. These classes are Crop, Animal and Item.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public interface StoreItem 
{

	/**
	 * A method to get the purchase price of a store item. Must be implemented in all of the store item Classes.
	 * @return The purchase price of a store item.
	 */
	double getPurchasePrice();
	
	/**
	 * A method to get the name of a store item. Must be implemented in all of the store item Classes.
	 * @return The name of a store item.
	 */
	String getName();
}
