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
		animalsForSale.add(new Animal("Sheep", 30.0, initAnimalHappiness, 20.0));
		animalsForSale.add(new Animal("Cow", 50.0, initAnimalHappiness, 70.0));
		animalsForSale.add(new Animal("Goat", 25.0, initAnimalHappiness, 30.0));
	
		
		//Items
		//The bonus for crops is an increase in the days grown.
		itemsForSale.add(new Item("Organic Fertiliser", "Crop", 10.0, 2.0));
		itemsForSale.add(new Item("Inorganic Fertiliser", "Crop", 5.0, 1.0));
		itemsForSale.add(new Item("Compost", "Crop", 100.0, 3.0));
		//The bonus for Animals is health given from food.
		itemsForSale.add(new Item("Apples", "Animal", 2.0, 0.2));
		itemsForSale.add(new Item("Hay Bales", "Animal", 10.0, 0.4));
		itemsForSale.add(new Item("Lucerne", "Animal", 20.0, 0.6));
		//5 others
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
