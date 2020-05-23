package main;

/**
 * Animal class where animals are extended from.
 * Here the user can increase the animal happiness, increase health and get daily profit.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Animal implements StoreProduct 
{

	/**
	 * The name of an animal.
	 */
	private String animalName;
	
	/**
	 * The purchase price of an animal.
	 */
	private Double purchasePrice;
	
	/**
	 * The happiness of an animal.
	 */
	private Double happiness;
	
	/**
	 * The daily money made of an animal.
	 */
	private Double dailyMoneyMade;
	
	/**
	 * The health of an animal.
	 */
	private Double health;
	
	/**
	 * A constructor for the Animal class, for initialising all animals from the store.
	 * @param name The animal's name.
	 * @param initPurchasePrice The animals's initial purchase price.
	 * @param initHappiness The animals's initial happiness.
	 * @param initdailyMoneyMade The animals's initial daily money made.
	 */
	public Animal(String name, Double initPurchasePrice, Double initHappiness, Double initdailyMoneyMade)
	{
		animalName = name;
		purchasePrice = initPurchasePrice;
		happiness = initHappiness;
		dailyMoneyMade = initdailyMoneyMade;
		health = 1.0;
	}
	
	/**
	 * A constructor for the Animal class for purchasing a new animal, creates a copy of the Animal class.
	 * @param animal The animal being purchased.
	 */
	public Animal(Animal animal)
	{
		animalName = animal.getName();
		purchasePrice = animal.getPurchasePrice();
		happiness = animal.getHappiness();
		dailyMoneyMade = animal.getDailyProfit();
		health = 1.0;
	}
	
	/**
	 * Increases the happiness of an animal by 10%.
	 */
	public void increaseHappiness()
	{
		happiness += 0.1;
	}
	
	/**
	 * Increases the health of an animal by healthToIncrease.
	 * @param healthToIncrease The health to increase the animal by.
	 */
	public void increaseHealth(double healthToIncrease)
	{
		health += healthToIncrease;
	}
	
	/**
	 * Calculates and returns the daily profit of an animal by multiplying its daily money made by its happiness and health, then returns it. This is called at the end of each day.
	 * @return The animal's daily profit.
	 */
	public double getDailyProfit()
	{
		return happiness * health * dailyMoneyMade;
	}
	
	/**
	 * Returns the name of an animal.
	 * @return The animal's name.
	 */
	public String getName() {
		return animalName;
	}
	
	/**
	 * Returns the purchase price of an animal.
	 * @return The animal's purchase price.
	 */
	public double getPurchasePrice()
	{
		return purchasePrice;
	}	
	
	/**
	 * Returns the happiness of an animal.
	 * @return The animal's happiness.
	 */
	public double getHappiness() {
		return happiness;
	}
	
	/**
	 * Returns the health of an animal.
	 * @return The animal's health.
	 */
	public double getHealth()
	{
		return health;
	}
	
}
