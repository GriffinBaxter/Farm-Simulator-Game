package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Crop;

class CropTest
{
	
	private Crop testCrop;

	@BeforeEach
	void setUp() throws Exception
	{
		testCrop = new Crop("Test Crop", 10.0, 20.0, 2); // Test Crop, $10 purchase price, $20 sell price, 2 days to grow
	}

	@Test
	final void testCanHarvest()
	{
		assertFalse(testCrop.canHarvest());
		testCrop.grow();
		assertFalse(testCrop.canHarvest());
		testCrop.grow();
		assertTrue(testCrop.canHarvest());
	}

	@Test
	final void testGrow()
	{
		assertEquals(0, testCrop.getDaysGrown());
		testCrop.grow();
		assertEquals(1, testCrop.getDaysGrown());
		testCrop.grow();
		assertEquals(2, testCrop.getDaysGrown());
	}

	@Test
	final void testTend()
	{
		assertEquals(0, testCrop.getDaysGrown());
		testCrop.tend(2);
		assertEquals(2, testCrop.getDaysGrown());
	}

}
