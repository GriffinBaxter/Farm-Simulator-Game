package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Farm;
import main.Animal;

class FarmTest {

	private Farm testFarm;
	private Animal testAnimal;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testFarm = new Farm("Test Farm", "Large"); // Large farm type
		testAnimal = new Animal("Test Animal", 10.0, 1.0, 5.0); // Test animal, $10 purchase price, 1.0 initial happiness, $5 daily money made
		testFarm.increaseAnimals(testAnimal);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testTendFarm() {
		assertEquals(20, testFarm.getCropSpace());
		for (Animal e: testFarm.getAnimals()) {
			assertEquals(1.0, e.getHappiness());
		}
		testFarm.tendFarm();
		assertEquals(21, testFarm.getCropSpace());
		for (Animal e: testFarm.getAnimals()) {
			System.out.println(e.getHappiness());
			assertEquals(1.1, e.getHappiness());
		}
	}

	@Test
	final void testCalculateFreeSpace() {
		fail("Not yet implemented");
	}

	@Test
	final void testIncreaseMoney() {
		fail("Not yet implemented");
	}

	@Test
	final void testDecreaseMoney() {
		fail("Not yet implemented");
	}

	@Test
	final void testCanHarvestCrops() {
		fail("Not yet implemented");
	}

	@Test
	final void testHarvestAvailableCrops() {
		fail("Not yet implemented");
	}

	@Test
	final void testGrowCrops() {
		fail("Not yet implemented");
	}

	@Test
	final void testTendSpecificCrops() {
		fail("Not yet implemented");
	}

	@Test
	final void testIncreaseHealthAllAnimals() {
		fail("Not yet implemented");
	}

	@Test
	final void testIncreaseHappinessAllAnimals() {
		fail("Not yet implemented");
	}

	@Test
	final void testCollectAnimalMoney() {
		fail("Not yet implemented");
	}

	@Test
	final void testIncreaseCrops() {
		fail("Not yet implemented");
	}

	@Test
	final void testIncreaseAnimals() {
		fail("Not yet implemented");
	}

	@Test
	final void testIncreaseItems() {
		fail("Not yet implemented");
	}

	@Test
	final void testDecreaseItems() {
		fail("Not yet implemented");
	}

	@Test
	final void testReturnCropsString() {
		fail("Not yet implemented");
	}

	@Test
	final void testReturnItemsString() {
		fail("Not yet implemented");
	}

}
