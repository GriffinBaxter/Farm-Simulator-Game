package main_cli;
import java.util.ArrayList;

public class Store{

	
	private ArrayList<Crop> cropsForSale = new ArrayList<Crop>();
	private ArrayList<Animal> animalsForSale = new ArrayList<Animal>();
	private ArrayList<Item> itemsForSale = new ArrayList<Item>();
	
	public Store(double initAnimalHappiness)
	{
		//Name, buy price, sell price, days to grow(harvestDays)
		cropsForSale.add(new Crop("Wheat", 10.0, 20.0, 1));
		cropsForSale.add(new Crop("Corn", 10.0, 25.0, 3));
		cropsForSale.add(new Crop("Potato", 20.0, 50.0, 3));
		cropsForSale.add(new Crop("Parsnip", 15.0, 40.0, 2));
		cropsForSale.add(new Crop("Strawberry", 40.0, 90.0, 3));
		cropsForSale.add(new Crop("Pumpkin", 20.0, 100.0, 5));
		
		
		//Name, purchase price, initial happiness, initial daily money made
		animalsForSale.add(new Animal("Sheep", 30.0, initAnimalHappiness, 20.0));
		animalsForSale.add(new Animal("Cow", 50.0, initAnimalHappiness, 70.0));
		animalsForSale.add(new Animal("Goat", 25.0, initAnimalHappiness, 30.0));
		
		//Name, Type(for crops or animals), price,  Bonus
		//The bonus for crops is an increased growth speed.
		itemsForSale.add(new Item("Organic Fertiliser", "Crop", 10.0, 2.0));
		itemsForSale.add(new Item("Inorganic Fertiliser", "Crop", 5.0, 1.0));
		itemsForSale.add(new Item("Compost", "Crop", 100.0, 3.0));
		//The bonus for Animals is health given from food.
		itemsForSale.add(new Item("Apples", "Animal", 2.0, 0.2));
		itemsForSale.add(new Item("Hay Bales", "Animal", 10.0, 0.4));
		itemsForSale.add(new Item("Lucerne", "Animal", 20.0, 0.6));
		//5 others
	}
	

	public ArrayList<Crop> getCropsForSale()
	{
		return cropsForSale;
		
	}
	
	public ArrayList<Animal> getAnimalsForSale()
	{
		return animalsForSale;
	}
	
	public ArrayList<Item> getItemsForSale()
	{
		return itemsForSale;
	}
	
	
	public Crop buyCrops(int index)
	{
		return cropsForSale.get(index);
	}
	
	public Animal buyAnimals(int index)
	{
		return animalsForSale.get(index);
	}
	
	public Item buyItems(int index)
	{
		return itemsForSale.get(index);
	}
	
}