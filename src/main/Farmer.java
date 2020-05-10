package main;

public class Farmer {

	private String farmerName;
	private int farmerAge;//age starts at 0 and goes up every day until
	
	public Farmer(String name, int age)
	{
		farmerName = name;
		farmerAge = age;	
	}
	public void increaseAge()
	{
		farmerAge ++;
	}
	
	public int getAge()
	{
		return farmerAge;
	}
	
	public String getFarmerName() {
		return farmerName;
	}
}
