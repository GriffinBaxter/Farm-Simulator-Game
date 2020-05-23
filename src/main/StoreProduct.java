package main;

/**
 * Store Product interface.
 * This interface specifies what methods classes with StoreProduct implemented should have. These classes are Crop, Animal and Item.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public interface StoreProduct 
{

	/**
	 * A method to get the purchase price of a store product. Must be implemented in all of the store product classes (Crop, Animal and Item).
	 * @return The purchase price of a store item.
	 */
	double getPurchasePrice();
	
	/**
	 * A method to get the name of a store product. Must be implemented in all of the store product classes (Crop, Animal and Item).
	 * @return The name of a store item.
	 */
	String getName();
}
