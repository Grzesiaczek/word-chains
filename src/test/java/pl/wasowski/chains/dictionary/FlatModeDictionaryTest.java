package pl.wasowski.chains.dictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.wasowski.chains.factory.ModeFactory;
import pl.wasowski.chains.resolver.Resolver;
import pl.wasowski.chains.resolver.Solution;

public class FlatModeDictionaryTest {
	ModeFactory factory;
	Dictionary dictionary;
	
	@Before
	public void setUp() {
		factory = ModeFactory.getFlatModeFactory();
		dictionary = factory.getEmptyDictionary();
	}
	
	@Test
	public void testAddWord() {
		dictionary.addWord("dog");
		
		Assert.assertTrue(dictionary.hasWord("dog"));
		Assert.assertFalse(dictionary.hasWord("cat"));
	}
	
	@Test
	public void testPositiveSolution() {
		dictionary.addWord("big");
		dictionary.addWord("dog");
		dictionary.addWord("dig");
		dictionary.addWord("dogs");
		dictionary.makeNeighbours();
		
		Resolver resolver = dictionary.getResolver("big", "dog");
		Solution solution = resolver.getSolution();
		
		Assert.assertTrue(solution.hasSolution());
		Assert.assertEquals(solution.length(), 3);
	}
	
	@Test
	public void testNegativeSolution() {
		dictionary.addWord("big");
		dictionary.addWord("dog");
		dictionary.addWord("dig");
		dictionary.addWord("dogs");
		dictionary.makeNeighbours();
		
		Resolver resolver = dictionary.getResolver("big", "dogs");
		Solution solution = resolver.getSolution();
		
		Assert.assertFalse(solution.hasSolution());
		Assert.assertEquals(solution.length(), 0);
	}
}