package main;

public class Animal implements StoreItem{

	private String animalName;
	private Double purchasePrice;
	private Double happiness;//Multiplier for money made
	private Double dailyMoneyMade;
	private int health;
	
	public Animal(String name, Double initPurchasePrice,Double initHappiness, Double initdailyMoneyMade)
	{
		animalName = name;
		purchasePrice = initPurchasePrice;
		happiness = initHappiness;
		dailyMoneyMade = initdailyMoneyMade;
		health = 1;
	}
	
	public void increaseHappiness()
	{
		happiness++;
	}
	
	public Double dailyProfit() //Called at the end of each day
	{
		return happiness * dailyMoneyMade;
	}
	
	public String getName() {
		return animalName;
	}
	
	public Double getHappiness() {
		return happiness;
	}
	
	public double getPurchasePrice()
	{
		return purchasePrice;
	}	
	
	public double getDailyMoneyMade()
	{
		return dailyMoneyMade;
	}
	
	public double getHealth()
	{
		return health;
	}
	
	// Deprecated, now using getter methods from the GameEnvironment class, keeping here for now just in case testing is required
	/*
	public void printAnimal()
	{
		System.out.println(animalName + " has a happiness level of " 
	+ happiness + ". This equates to $" + returnDailyProfit() + " per day");
				
	}*/
	
}
