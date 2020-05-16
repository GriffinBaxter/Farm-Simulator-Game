package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Farmer;

class FarmerTest {

	private Farmer testFarmer;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testFarmer = new Farmer("Test Farmer", 5, 20); // 5 days have passed
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testIncreaseDaysPassed() {
		testFarmer.increaseDaysPassed();
		assertEquals(6, testFarmer.getDaysPassed());
	}

}
