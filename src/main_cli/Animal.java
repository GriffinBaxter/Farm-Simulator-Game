package main_cli;

public class Animal implements StoreItem{

	private String animalName;
	private Double purchasePrice;
	private Double happiness;//Multiplier for money made
	private Double dailyMoneyMade;
	private Double health;
	
	public Animal(String name, Double initPurchasePrice,Double initHappiness, Double initdailyMoneyMade)
	{
		animalName = name;
		purchasePrice = initPurchasePrice;
		happiness = initHappiness;
		dailyMoneyMade = initdailyMoneyMade;
		health = 1.0;
	}
	
	/**
	 * for copying an animal class (this is used when you buy an animal)
	 * @param animal
	 */
	public Animal(Animal animal)
	{
		animalName = animal.getName();
		purchasePrice = animal.getPurchasePrice();
		happiness = animal.getHappiness();
		dailyMoneyMade = animal.dailyProfit();
		health = 1.0;
	}
	
	public void increaseHappiness()
	{
		happiness += 0.1;
	}
	
	public void increaseHealth(double healthToIncrease)
	{
		health += healthToIncrease;
	}
	
	public Double dailyProfit() //Called at the end of each day
	{
		return happiness * health * dailyMoneyMade;
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
	
	public double getHealth()
	{
		return health;
	}
	
}
