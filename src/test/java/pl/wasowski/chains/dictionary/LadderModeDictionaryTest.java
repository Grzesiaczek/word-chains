package pl.wasowski.chains.dictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.wasowski.chains.factory.ModeFactory;
import pl.wasowski.chains.resolver.Resolver;
import pl.wasowski.chains.resolver.Solution;

public class LadderModeDictionaryTest {
	ModeFactory factory;
	Dictionary dictionary;
	
	@Before
	public void setUp() {
		factory = ModeFactory.getLadderModeFactory();
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
		dictionary.addWord("brig");
		dictionary.addWord("brim");
		dictionary.addWord("brims");
		dictionary.makeNeighbours();
		
		Resolver resolver = dictionary.getResolver("brims", "dogs");
		Solution solution = resolver.getSolution();
		
		Assert.assertTrue(solution.hasSolution());
		Assert.assertEquals(solution.length(), 7);	
	}
	
	@Test
	public void testNegativeSolution() {
		dictionary.addWord("big");
		dictionary.addWord("dog");
		dictionary.addWord("dig");
		dictionary.addWord("dogs");
		dictionary.addWord("bugs");
		dictionary.makeNeighbours();
		
		Resolver resolver = dictionary.getResolver("bugs", "dogs");
		Solution solution = resolver.getSolution();
		
		Assert.assertFalse(solution.hasSolution());
		Assert.assertEquals(solution.length(), 0);
	}
}