package pl.wasowski.chains.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.wasowski.chains.dictionary.Dictionary;
import pl.wasowski.chains.factory.ModeFactory;
import pl.wasowski.chains.resolver.Resolver;
import pl.wasowski.chains.resolver.Solution;

public class FlatModeTest {
	Dictionary dictionary;
	
	@Before
	public void setUp() {
		dictionary = ModeFactory.getFlatModeFactory().getDefaultDictionary();
	}

	@Test
	public void testCatDog() {
		Resolver resolver = dictionary.getResolver("cat", "dog");
		Solution solution = resolver.getSolution();
		
		Assert.assertTrue(solution.hasSolution());
		Assert.assertEquals(solution.length(), 4);
	}
	
	@Test
	public void testLeadGold() {
		Resolver resolver = dictionary.getResolver("lead", "gold");
		Solution solution = resolver.getSolution();
		
		Assert.assertTrue(solution.hasSolution());
		Assert.assertEquals(solution.length(), 4);
	}
	
	@Test
	public void testRubyCode() {
		Resolver resolver = dictionary.getResolver("ruby", "code");
		Solution solution = resolver.getSolution();
		
		Assert.assertTrue(solution.hasSolution());
		Assert.assertEquals(solution.length(), 6);
	}
	
	@Test
	public void testCodeRuby() {
		Resolver resolver = dictionary.getResolver("code", "ruby");
		Solution solution = resolver.getSolution();
		
		Assert.assertTrue(solution.hasSolution());
		Assert.assertEquals(solution.length(), 6);
	}
	
	@Test
	public void testMountedHorse() {
		Resolver resolver = dictionary.getResolver("mounted", "horse");
		Solution solution = resolver.getSolution();
		
		Assert.assertFalse(solution.hasSolution());
		Assert.assertEquals(solution.length(), 0);
	}
}