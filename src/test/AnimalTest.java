package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Animal;

/**
 * Test for the main Animal class.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
class AnimalTest
{

	/**
	 * Animal for the tests.
	 */
	private Animal testAnimal;

	/**
	 * Constructs a new animal for every test.
	 */
	@BeforeEach
	void setUp()
	{
		testAnimal = new Animal("Test Animal", 10.0, 1.0, 10.0); // Test Animal, $10 initial purchase price, 1.0 initial happiness, $10 initial daily money made
	}

	/**
	 * Test for the increaseHappiness method of the Animal class.
	 */
	@Test
	final void testIncreaseHappiness()
	{
		assertEquals(1.0, testAnimal.getHappiness());
		testAnimal.increaseHappiness();
		assertEquals(1.1, testAnimal.getHappiness());
	}

	/**
	 * Test for the increaseHealth method of the Animal class.
	 */
	@Test
	final void testIncreaseHealth()
	{
		assertEquals(1.0, testAnimal.getHealth());
		testAnimal.increaseHealth(1.5);
		assertEquals(2.5, testAnimal.getHealth());
	}

}
