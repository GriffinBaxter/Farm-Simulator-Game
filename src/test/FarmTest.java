package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Farm;
import main.Animal;
import main.Crop;
import main.Item;

class FarmTest
{

	private Farm testFarm;
	private Animal testAnimal;
	private Crop testCrop;
	private Item testItem;

	@BeforeEach
	void setUp() throws Exception
	{
		testFarm = new Farm("Test Farm", "Large"); // Large farm type, $100 starting money, 20 crop spaces
		testAnimal = new Animal("Test Animal", 10.0, 1.0, 5.0); // Test animal, $10 purchase price, 1.0 initial happiness, $5 daily money made
		testCrop = new Crop("Test Crop", 5.0, 10.0, 2); // Test crop, $5 purchase price, $10 sell price, 2 days to grow
		testItem = new Item("Test Item", "Test", 15.0, 5.0); // Test item, type "Test", $15 purchase price, 5.0 bonus
	}

	@Test
	final void testTendFarm()
	{
		testFarm.increaseAnimals(testAnimal);
		assertEquals(20, testFarm.getCropSpace());
		for (Animal animal: testFarm.getAnimals()) {
			assertEquals(1.0, animal.getHappiness());
		}
		testFarm.tendFarm();
		assertEquals(21, testFarm.getCropSpace());
		for (Animal animal: testFarm.getAnimals()) {
			assertEquals(1.1, animal.getHappiness());
		}
	}

	@Test
	final void testCalculateFreeSpace()
	{
		assertEquals(20, testFarm.calculateFreeSpace());
		testFarm.increaseCrops(testCrop);
		assertEquals(19, testFarm.calculateFreeSpace());
	}

	@Test
	final void testIncreaseMoney()
	{
		assertEquals(0, testFarm.getProfit());
		assertEquals(100, testFarm.getMoney());
		testFarm.increaseMoney(10);
		assertEquals(10, testFarm.getProfit());
		assertEquals(110, testFarm.getMoney());
	}

	@Test
	final void testDecreaseMoney()
	{
		assertEquals(0, testFarm.getProfit());
		assertEquals(100, testFarm.getMoney());
		testFarm.decreaseMoney(10);
		assertEquals(-10, testFarm.getProfit());
		assertEquals(90, testFarm.getMoney());
	}

	@Test
	final void testCanHarvestCrops()
	{
		assertFalse(testFarm.canHarvestCrops());
		testFarm.increaseCrops(testCrop);
		testFarm.growCrops();
		assertFalse(testFarm.canHarvestCrops());
		testFarm.growCrops();
		assertTrue(testFarm.canHarvestCrops());
	}

	@Test
	final void testHarvestAvailableCrops()
	{
		assertEquals(0.0, testFarm.harvestAvailableCrops());
		testFarm.increaseCrops(testCrop);
		assertEquals(0.0, testFarm.harvestAvailableCrops());
		testFarm.growCrops();
		assertEquals(0.0, testFarm.harvestAvailableCrops());
		testFarm.growCrops();
		assertEquals(10.0, testFarm.harvestAvailableCrops());
	}

	@Test
	final void testGrowCrops()
	{
		testFarm.increaseCrops(testCrop);
		for (Crop crop: testFarm.getCrops()) {
			assertEquals(0, crop.getDaysGrown());
		}
		testFarm.growCrops();
		for (Crop crop: testFarm.getCrops()) {
			assertEquals(1, crop.getDaysGrown());
		}
	}

	@Test
	final void testTendSpecificCrops()
	{
		testFarm.increaseCrops(testCrop);
		for (Crop crop: testFarm.getCrops()) {
			assertEquals(0, crop.getDaysGrown());
		}
		testFarm.tendSpecificCrops("Not Test Crop", 1);
		for (Crop crop: testFarm.getCrops()) {
			assertEquals(0, crop.getDaysGrown());
		}
		testFarm.tendSpecificCrops("Test Crop", 1);
		for (Crop crop: testFarm.getCrops()) {
			assertEquals(1, crop.getDaysGrown());
		}
	}

	@Test
	final void testIncreaseHealthAllAnimals()
	{
		assertFalse(testFarm.increaseHealthAllAnimals(0.1));
		testFarm.increaseAnimals(testAnimal);
		for (Animal animal: testFarm.getAnimals()) {
			assertEquals(1.0, animal.getHealth());
		}
		assertTrue(testFarm.increaseHealthAllAnimals(0.1));
		for (Animal animal: testFarm.getAnimals()) {
			assertEquals(1.1, animal.getHealth());
		}
	}

	@Test
	final void testIncreaseHappinessAllAnimals()
	{
		assertFalse(testFarm.increaseHappinessAllAnimals());
		testFarm.increaseAnimals(testAnimal);
		for (Animal animal: testFarm.getAnimals()) {
			assertEquals(1.0, animal.getHappiness());
		}
		assertTrue(testFarm.increaseHappinessAllAnimals());
		for (Animal animal: testFarm.getAnimals()) {
			assertEquals(1.1, animal.getHappiness());
		}
	}

	@Test
	final void testCollectAnimalMoney()
	{
		assertEquals(0.0, testFarm.collectAnimalMoney());
		testFarm.increaseAnimals(testAnimal);
		assertEquals(5.0, testFarm.collectAnimalMoney());
		testFarm.increaseHealthAllAnimals(0.1);
		assertEquals(5.5, testFarm.collectAnimalMoney());
		testFarm.increaseAnimals(testAnimal);
		assertEquals(10.5, testFarm.collectAnimalMoney());
	}

	@Test
	final void testIncreaseCrops()
	{
		assertEquals(0, testFarm.getCrops().size());
		assertEquals(100, testFarm.getMoney());
		testFarm.increaseCrops(testCrop);
		assertEquals(1, testFarm.getCrops().size());
		assertEquals(95, testFarm.getMoney());
	}

	@Test
	final void testIncreaseAnimals()
	{
		assertEquals(0, testFarm.getAnimals().size());
		assertEquals(100, testFarm.getMoney());
		testFarm.increaseAnimals(testAnimal);
		assertEquals(1, testFarm.getAnimals().size());
		assertEquals(90, testFarm.getMoney());
	}

	@Test
	final void testIncreaseItems()
	{
		assertEquals(0, testFarm.getItems().size());
		assertEquals(100, testFarm.getMoney());
		testFarm.increaseItems(testItem);
		assertEquals(1, testFarm.getItems().size());
		assertEquals(85, testFarm.getMoney());
	}

	@Test
	final void testDecreaseItems()
	{
		assertEquals(0, testFarm.getItems().size());
		testFarm.increaseItems(testItem);
		assertEquals(1, testFarm.getItems().size());
		testFarm.decreaseItems(testFarm.getItems().get(0));
		assertEquals(0, testFarm.getItems().size());
		
	}

	@Test
	final void testReturnCropsString()
	{
		String noCropString = "0. No Crops\n";
		assertEquals(noCropString, testFarm.returnCropsString(noCropString, testFarm.getCrops()));
		testFarm.increaseCrops(testCrop);
		assertEquals(noCropString + "1. Test Crop\n", testFarm.returnCropsString(noCropString, testFarm.getCrops()));
	}

	@Test
	final void testReturnItemsString()
	{
		String noItemString = "0. No Items\n";
		assertEquals(noItemString, testFarm.returnItemsString(noItemString, "Test", 0));
		testFarm.increaseItems(testItem);
		assertEquals(noItemString + "1. Test Item\n", testFarm.returnItemsString(noItemString, "Test", 0));
		assertEquals(noItemString, testFarm.returnItemsString(noItemString, "Not Test", 0));
	}
	
	@Test
	final void testSetFarmType()
	{
		Farm testNormalFarm = new Farm("Test Normal Farm", "Normal"); // Normal farm type, $150 starting money, 10 crop spaces
		testNormalFarm.setFarmType("Normal");
		assertEquals("Test Normal Farm", testNormalFarm.getFarmName());
		assertEquals(150, testNormalFarm.getMoney());
		assertEquals(10, testNormalFarm.getCropSpace());
		
		Farm testRichFarm = new Farm("Test Rich Farm", "Rich"); // Rich farm type, $200 starting money, 10 crop spaces
		testRichFarm.setFarmType("Rich");
		assertEquals("Test Rich Farm", testRichFarm.getFarmName());
		assertEquals(200, testRichFarm.getMoney());
		assertEquals(10, testRichFarm.getCropSpace());
		
		Farm testHappyFarm = new Farm("Test Happy Farm", "Happy"); // Happy farm type, $100 starting money, 10 crop spaces
		testHappyFarm.setFarmType("Happy");
		assertEquals("Test Happy Farm", testHappyFarm.getFarmName());
		assertEquals(100, testHappyFarm.getMoney());
		assertEquals(10, testHappyFarm.getCropSpace());
	}

}
