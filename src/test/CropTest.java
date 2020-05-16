package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Crop;

class CropTest {
	
	private Crop testCrop;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testCrop = new Crop("Test Crop", 10.0, 20.0, 2); // Test Crop, $10 purchase price, $20 sell price, 2 days to grow
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testCanHarvest() {
		assertEquals(false, testCrop.canHarvest());
		testCrop.grow();
		assertEquals(false, testCrop.canHarvest());
		testCrop.grow();
		assertEquals(true, testCrop.canHarvest());
	}

	@Test
	final void testGrow() {
		assertEquals(0, testCrop.getDaysGrown());
		testCrop.grow();
		assertEquals(1, testCrop.getDaysGrown());
		testCrop.grow();
		assertEquals(2, testCrop.getDaysGrown());
	}

	@Test
	final void testTend() {
		assertEquals(0, testCrop.getDaysGrown());
		testCrop.tend(2);
		assertEquals(2, testCrop.getDaysGrown());
	}

}
