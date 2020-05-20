package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Animal;

class AnimalTest
{

	private Animal testAnimal;

	@BeforeEach
	void setUp() throws Exception
	{
		testAnimal = new Animal("Test Animal", 10.0, 1.0, 10.0); // Test Animal, $10 initial purchase price, 1.0 initial happiness, $10 initial daily money made
	}

	@Test
	final void testIncreaseHappiness()
	{
		assertEquals(1.0, testAnimal.getHappiness());
		testAnimal.increaseHappiness();
		assertEquals(1.1, testAnimal.getHappiness());
	}

	@Test
	final void testIncreaseHealth()
	{
		assertEquals(1.0, testAnimal.getHealth());
		testAnimal.increaseHealth(1.5);
		assertEquals(2.5, testAnimal.getHealth());
	}

}
