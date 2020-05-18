package main;
import java.util.ArrayList;

/**
 * Store class where the user can purchase Crops, Animals and Items from.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class Store
{

	/**
	 * The crops the store has for sale.
	 */
	private ArrayList<Crop> cropsForSale = new ArrayList<Crop>();
	
	/**
	 * The animals the store has for sale.
	 */
	private ArrayList<Animal> animalsForSale = new ArrayList<Animal>();
	
	/**
	 * The items the store has for sale.
	 */
	private ArrayList<Item> itemsForSale = new ArrayList<Item>();
	
	/**
	 * Constructor function for Store Class, this constructor takes a variable <code>initAnimalHappiness</code> and gives all animals that base happiness.
	 * This Constructor also fills the crops, animals and items for sale Array Lists with values.
	 * @param initAnimalHappiness The initial animal happiness.
	 */
	public Store(double initAnimalHappiness)
	{
		//Crops
		cropsForSale.add(new Wheat());
		cropsForSale.add(new Corn());
		cropsForSale.add(new Potato());
		cropsForSale.add(new Parsnip());
		cropsForSale.add(new Strawberry());
		cropsForSale.add(new Pumpkin());
		
		
		//Animals
		animalsForSale.add(new Sheep(initAnimalHappiness));
		animalsForSale.add(new Cow(initAnimalHappiness));
		animalsForSale.add(new Goat(initAnimalHappiness));
		
		//Items
		//The bonus for crops is an increase in the days grown.
		itemsForSale.add(new OrganicFertiliser());
		itemsForSale.add(new InorganicFertiliser());
		itemsForSale.add(new Compost());
		//The bonus for Animals is health given from food.
		itemsForSale.add(new Apples());
		itemsForSale.add(new HayBales());
		itemsForSale.add(new Lucerne());
	}
	
	/**
	 * Returns the cropsForSale ArrayList.
	 * @return The cropsForSale.
	 */
	public ArrayList<Crop> getCropsForSale()
	{
		return cropsForSale;
	}
	
	/**
	 * Returns the animalsForSale ArrayList.
	 * @return The animalsForSale.
	 */
	public ArrayList<Animal> getAnimalsForSale()
	{
		return animalsForSale;
	}
	
	/**
	 * Returns the itemsForSale ArrayList.
	 * @return The itemsForSale.
	 */
	public ArrayList<Item> getItemsForSale()
	{
		return itemsForSale;
	}
	
	/**
	 * return Crop at <code>index</code> to be purchased.
	 * @param index Index of crop to buy.
	 * @return The Crop at specified <code>index</code>.
	 */
	public Crop buyCrops(int index)
	{
		return cropsForSale.get(index);
	}
	
	/**
	 * return Animal at <code>index</code> to be purchased.
	 * @param index Index of animal to buy.
	 * @return The Animal at specified <code>index</code>.
	 */
	public Animal buyAnimals(int index)
	{
		return animalsForSale.get(index);
	}
	
	/**
	 * return Item at <code>index</code> to be purchased.
	 * @param index Index of item to buy.
	 * @return The Item at specified <code>index</code>.
	 */
	public Item buyItems(int index)
	{
		return itemsForSale.get(index);
	}
	
}
