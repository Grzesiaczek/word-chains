package pl.wasowski.chains.dictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.wasowski.chains.factory.ModeFactory;

public class LadderModeWordTest {
	ModeFactory factory;
	
	@Before
	public void setUp() {
		factory = ModeFactory.getLadderModeFactory();
	}
	
	@Test
	public void testWord() {
		Word butter = factory.getWord("sword");
		
		Assert.assertEquals(butter.getValue(), "sword");
		Assert.assertEquals(butter.getLength(), 5);
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
		Assert.assertEquals(dog.getNeighbours().size(), 2);
		Assert.assertEquals(dogs.getNeighbours().size(), 1);
		
		Assert.assertTrue(dog.getNeighbours().contains(dig));
		Assert.assertFalse(dog.getNeighbours().contains(big));
		Assert.assertTrue(dog.getNeighbours().contains(dogs));
	}
}