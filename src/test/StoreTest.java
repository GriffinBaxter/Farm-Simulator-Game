package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Store;
import main.Animal;
import main.Crop;
import main.Item;


/**
 * Test for the main Store class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
class StoreTest {

	/**
	 * Store for the tests.
	 */
	private Store testStore;
	
	/**
	 * Constructs a new store with an initialAnimalHappiness of 1.0.
	 */
	@BeforeEach
	void setUp() throws Exception 
	{
		testStore = new Store(1.0);//New store with 1.0 initialAnimalHappiness
	}

	/**
	 * Test for the getCropsForSale method of the Store class.
	 */
	@Test
	void testGetCropsForSale() 
	{
		assertEquals("Wheat", testStore.getCropsForSale().get(0).getName());
		assertEquals("Corn", testStore.getCropsForSale().get(1).getName());
		assertEquals("Potato", testStore.getCropsForSale().get(2).getName());
		assertEquals("Parsnip", testStore.getCropsForSale().get(3).getName());
		assertEquals("Strawberry", testStore.getCropsForSale().get(4).getName());
		assertEquals("Pumpkin", testStore.getCropsForSale().get(5).getName());
	}
	
	/**
	 * Test for the getAnimalsForSale method of the Store class.
	 */
	@Test
	void testGetAnimalsForSale() 
	{
		assertEquals("Sheep", testStore.getAnimalsForSale().get(0).getName());
		assertEquals("Cow", testStore.getAnimalsForSale().get(1).getName());
		assertEquals("Goat", testStore.getAnimalsForSale().get(2).getName());
	}
	
	/**
	 * Test for the getItemsForSale method of the Store class.
	 */
	@Test
	void testGetItemsForSale() 
	{
		assertEquals("Organic Fertiliser", testStore.getItemsForSale().get(0).getName());
		assertEquals("Inorganic Fertiliser", testStore.getItemsForSale().get(1).getName());
		assertEquals("Compost", testStore.getItemsForSale().get(2).getName());
		assertEquals("Apples", testStore.getItemsForSale().get(3).getName());
		assertEquals("Hay Bales", testStore.getItemsForSale().get(4).getName());
		assertEquals("Lucerne", testStore.getItemsForSale().get(5).getName());
	}
	
	/**
	 * Test for the buyCrops method of the Store class.
	 */
	@Test
	void testBuyCrops() 
	{
		assertEquals(testStore.getCropsForSale().get(0), testStore.buyCrops(0));
		assertEquals(testStore.getCropsForSale().get(1), testStore.buyCrops(1));
		assertEquals(testStore.getCropsForSale().get(2), testStore.buyCrops(2));
		assertEquals(testStore.getCropsForSale().get(3), testStore.buyCrops(3));
		assertEquals(testStore.getCropsForSale().get(4), testStore.buyCrops(4));
		assertEquals(testStore.getCropsForSale().get(5), testStore.buyCrops(5));
	}
	
	/**
	 * Test for the buyAnimals method of the Store class.
	 */
	@Test
	void testBuyAnimals() 
	{
		assertEquals(testStore.getAnimalsForSale().get(0), testStore.buyAnimals(0));
		assertEquals(testStore.getAnimalsForSale().get(1), testStore.buyAnimals(1));
		assertEquals(testStore.getAnimalsForSale().get(2), testStore.buyAnimals(2));
	}
	
	/**
	 * Test for the buyItems method of the Store class.
	 */
	@Test
	void testBuyItems() 
	{
		assertEquals(testStore.getItemsForSale().get(0), testStore.buyItems(0));
		assertEquals(testStore.getItemsForSale().get(1), testStore.buyItems(1));
		assertEquals(testStore.getItemsForSale().get(2), testStore.buyItems(2));
		assertEquals(testStore.getItemsForSale().get(3), testStore.buyItems(3));
		assertEquals(testStore.getItemsForSale().get(4), testStore.buyItems(4));
		assertEquals(testStore.getItemsForSale().get(5), testStore.buyItems(5));
	}
}
