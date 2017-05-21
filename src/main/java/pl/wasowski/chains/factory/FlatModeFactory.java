package pl.wasowski.chains.factory;

import pl.wasowski.chains.dictionary.Dictionary;
import pl.wasowski.chains.dictionary.Word;

public class FlatModeFactory extends ModeFactory{
	@Override
	public Word getWord(String value) {
		return new FlatModeWord(value);
	}

	@Override
	public Dictionary getDefaultDictionary() {
		Dictionary dictionary = new FlatModeDictionary();
		dictionary.initializeDefault(this);
		return dictionary;
	}

	@Override
	public Dictionary getEmptyDictionary() {
		Dictionary dictionary = new FlatModeDictionary();
		dictionary.initializeEmpty(this);
		return dictionary;
	}
}