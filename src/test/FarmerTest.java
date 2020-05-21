package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Farmer;

/**
 * Test for the main Farmer class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
class FarmerTest
{

	/**
	 * Farmer for the tests.
	 */
	private Farmer testFarmer;

	/**
	 * Constructs a new farmer for every test.
	 */
	@BeforeEach
	void setUp()
	{
		testFarmer = new Farmer("Test Farmer", 5, 20); // 5 days have passed, Age 20
	}

	/**
	 * Test for the increaseDaysPassed method of the Farmer class.
	 */
	@Test
	final void testIncreaseDaysPassed()
	{
		testFarmer.increaseDaysPassed();
		assertEquals(6, testFarmer.getDaysPassed());
	}
	
	/**
	 * Test for the getFarmerAge and getFarmerName methods of the Farmer class.
	 */
	@Test
	final void testFarmerAgeName()
	{
		assertEquals(20, testFarmer.getFarmerAge());
		assertEquals("Test Farmer", testFarmer.getFarmerName());
	}

}
