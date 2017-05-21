package pl.wasowski.chains.dictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.wasowski.chains.factory.ModeFactory;

public class FlatModeWordTest {
	ModeFactory factory;
	
	@Before
	public void setUp() {
		factory = ModeFactory.getFlatModeFactory();
	}
	
	@Test
	public void testWord() {
		Word butter = factory.getWord("butter");
		
		Assert.assertEquals(butter.getValue(), "butter");
		Assert.assertEquals(butter.getLength(), 6);
	}
	
	@Test
	public void testMakingNeighbours() {
		Word big = factory.getWord("big");
		Word dog = factory.getWord("dog");
		Word dig = factory.getWord("dig");
		Word dogs = factory.getWord("dogs");
		
		big.makeNeighbour(dog);
		big.makeNeighbour(dig);
		big.makeNeighbour(dogs);
		dog.makeNeighbour(dig);
		dog.makeNeighbour(dogs);
		dig.makeNeighbour(dogs);
		
		Assert.assertEquals(big.getNeighbours().size(), 1);
		Assert.assertEquals(dig.getNeighbours().size(), 2);
		Assert.assertEquals(dog.getNeighbours().size(), 1);
		Assert.assertEquals(dogs.getNeighbours().size(), 0);
		
		Assert.assertTrue(dog.getNeighbours().contains(dig));
		Assert.assertFalse(dog.getNeighbours().contains(big));
		Assert.assertFalse(dog.getNeighbours().contains(dogs));
	}
}