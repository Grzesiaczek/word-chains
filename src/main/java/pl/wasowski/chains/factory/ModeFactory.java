package pl.wasowski.chains.factory;

import pl.wasowski.chains.dictionary.Dictionary;
import pl.wasowski.chains.dictionary.Word;

public abstract class ModeFactory {
	public static ModeFactory getFlatModeFactory() {
		return new FlatModeFactory();
	}
	
	public static ModeFactory getLadderModeFactory() {
		return new LadderModeFactory();
	}
		
	public abstract Word getWord(String value);
	
	public abstract Dictionary getDefaultDictionary();
	
	public abstract Dictionary getEmptyDictionary();
}
