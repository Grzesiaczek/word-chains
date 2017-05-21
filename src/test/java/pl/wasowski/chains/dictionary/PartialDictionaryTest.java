package pl.wasowski.chains.dictionary;

import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.wasowski.chains.factory.ModeFactory;

public class PartialDictionaryTest {
	ModeFactory factory;
	
	@Before
	public void setUp() {
		factory = ModeFactory.getFlatModeFactory();
	}
	
	@Test
	public void testAddWord() {
		PartialDictionary dict = new PartialDictionary(4);
		Consumer<String> adder = word -> dict.addWord(factory.getWord(word));
		
		adder.accept("dogs");
		adder.accept("cat");
		adder.accept("ruby");
		
		Assert.assertEquals(dict.getWords().size(), 2);
		Assert.assertEquals(dict.getWord("dogs"), "dogs");
		Assert.assertNull(dict.getWord("cat"));
	}
}
