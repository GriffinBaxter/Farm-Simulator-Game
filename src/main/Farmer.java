package main;

public class Farmer {

	/**
	 * The name of the farmer.
	 */
	private String farmerName;
	
	/**
	 * The age of the farmer, starts at 1.
	 */
	private int farmerAge;
	
	/**
	 * Constructor function for Farmer Class, this constructor initialises variables <code>farmerName</code>, <code>farmerAge</code>.
	 * @param name Name of the farmer.
	 * @param age Age of the farmer.
	 */
	public Farmer(String name, int age)
	{
		farmerName = name;
		farmerAge = age;	
	}
	
	/**
	 * Function to increase the age of the farmer, increases the farmers age by 1.
	 */
	public void increaseAge()
	{
		farmerAge ++;
	}
	
	/**
	 * Returns the farmers age.
	 * @return The farmers age.
	 */
	public int getAge()
	{
		return farmerAge;
	}
	
	/**
	 * Returns the farmers name.
	 * @return The farmers name.
	 */
	public String getFarmerName() {
		return farmerName;
	}
}
