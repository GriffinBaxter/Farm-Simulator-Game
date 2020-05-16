package main;

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
