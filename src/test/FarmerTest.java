package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Farmer;

class FarmerTest
{

	private Farmer testFarmer;

	@BeforeEach
	void setUp() throws Exception
	{
		testFarmer = new Farmer("Test Farmer", 5, 20); // 5 days have passed
	}

	@Test
	final void testIncreaseDaysPassed()
	{
		testFarmer.increaseDaysPassed();
		assertEquals(6, testFarmer.getDaysPassed());
	}

}
