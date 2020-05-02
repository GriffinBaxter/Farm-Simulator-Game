package main;

public class Animal extends Store{

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
	
	public Double makeMoney()//Called at the end of each day
	{
		return happiness * dailyMoneyMade;
	}
	
}
