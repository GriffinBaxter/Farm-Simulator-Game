package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Crop;

/**
 * Test for the main Crop class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
class CropTest
{
	
	/**
	 * Crop for the tests.
	 */
	private Crop testCrop;

	/**
	 * Constructs a new crop for every test.
	 */
	@BeforeEach
	void setUp()
	{
		testCrop = new Crop("Test Crop", 10.0, 20.0, 2); // Test Crop, $10 purchase price, $20 sell price, 2 days to grow
	}

	/**
	 * Test for the canHarvest method of the Crop class.
	 */
	@Test
	final void testCanHarvest()
	{
		assertFalse(testCrop.canHarvest());
		testCrop.grow();
		assertFalse(testCrop.canHarvest());
		testCrop.grow();
		assertTrue(testCrop.canHarvest());
	}

	/**
	 * Test for the grow method of the Crop class.
	 */
	@Test
	final void testGrow()
	{
		assertEquals(0, testCrop.getDaysGrown());
		testCrop.grow();
		assertEquals(1, testCrop.getDaysGrown());
		testCrop.grow();
		assertEquals(2, testCrop.getDaysGrown());
	}

	/**
	 * Test for the tend method of the Crop class.
	 */
	@Test
	final void testTend()
	{
		assertEquals(0, testCrop.getDaysGrown());
		testCrop.tend(2);
		assertEquals(2, testCrop.getDaysGrown());
		testCrop.tend(2);
		assertEquals(2, testCrop.getDaysGrown());
	}

}
