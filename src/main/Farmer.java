package main;

/**
 * Farmer class where the users farmer is.
 * Here the users farmer gets set up and where the program gets the users age from.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Farmer 
{

	/**
	 * The name of the farmer.
	 */
	private String farmerName;
	
	/**
	 * The age of the farmer.
	 */
	private int farmerAge;
	
	/**
	 * The days passed of the farmer on the farm, starts at 1.
	 */
	private int farmerDaysPassed;
	
	/**
	 * Constructor function for Farmer Class, this constructor initialises variables <code>farmerName</code>, <code>farmerAge</code>.
	 * @param name Name of the farmer.
	 * @param daysPassed The days passed for the farmer.
	 * @param age Age of the farmer.
	 */
	public Farmer(String name, int daysPassed, int age)
	{
		farmerName = name;
		farmerDaysPassed = daysPassed;
		farmerAge = age;
	}
	
	/**
	 * Function to increase the days passed of the farmer on the farm, increases the days passed by 1.
	 */
	public void increaseDaysPassed()
	{
		farmerDaysPassed++;
	}
	
	/**
	 * Returns the farmer's age.
	 * @return The farmer's age.
	 */
	public int getFarmerAge()
	{
		return farmerAge;
	}
	
	/**
	 * Returns the days passed for the farmer.
	 * @return The days passed for the farmer.
	 */
	public int getDaysPassed()
	{
		return farmerDaysPassed;
	}
	
	/**
	 * Returns the farmer's name.
	 * @return The farmer's name.
	 */
	public String getFarmerName() {
		return farmerName;
	}
}
