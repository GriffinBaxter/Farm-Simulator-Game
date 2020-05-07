package main;

public class Animal{

	private String animalName;
	private Double purchasePrice;
	private Double happiness;//Multiplier for money made
	private Double dailyMoneyMade;
	
	public Animal(String name, Double initPurchasePrice,Double initHappiness, Double initdailyMoneyMade)
	{
		animalName = name;
		purchasePrice = initPurchasePrice;
		happiness = initHappiness;
		dailyMoneyMade = initdailyMoneyMade;
	}
	
	public void increaseHappiness()
	{
		happiness++;
	}
	
	public Double dailyProfit() //Called at the end of each day
	{
		return happiness * dailyMoneyMade;
	}
	
	public String getAnimalName() {
		return animalName;
	}
	
	public Double getHappiness() {
		return happiness;
	}
	
	// Deprecated, now using getter methods from the GameEnvironment class, keeping here for now just in case testing is required
	/*
	public void printAnimal()
	{
		System.out.println(animalName + " has a happiness level of " 
	+ happiness + ". This equates to $" + returnDailyProfit() + " per day");
				
	}*/
	
}
