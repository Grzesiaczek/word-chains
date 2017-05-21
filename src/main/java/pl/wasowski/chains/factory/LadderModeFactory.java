package pl.wasowski.chains.factory;

import pl.wasowski.chains.dictionary.Dictionary;
import pl.wasowski.chains.dictionary.PartialDictionary;
import pl.wasowski.chains.dictionary.Word;

public class LadderModeFactory extends ModeFactory{
	@Override
	public Word getWord(String value) {
		return new LadderModeWord(value);
	}

	@Override
	public Dictionary getDefaultDictionary() {
		Dictionary dictionary = new LadderModeDictionary();
		dictionary.initializeDefault(this);
		return dictionary;
	}

	@Override
	public Dictionary getEmptyDictionary() {
		Dictionary dictionary = new LadderModeDictionary();
		dictionary.initializeEmpty(this);
		return dictionary;
	}
}

class FlatModeWord extends Word {
	public FlatModeWord(String word) {
		super(word);
	}
}

class LadderModeWord extends Word {
	public LadderModeWord(String word) {
		super(word);
	}

	@Override
	protected boolean isNeighbour(Word word) {
		if (getLength() != word.getLength()) {
			return isLadderNeighbour(word);
		}
		
		return isFlatNeighbour(word);
	}
	
	private boolean isLadderNeighbour(Word word) {
		if (Math.abs(getLength() - word.getLength()) != 1) {
			return false;
		}
		
		String longer = getLength() > word.getLength() ? getValue() : word.getValue();
		String shorter = getLength() < word.getLength() ? getValue() : word.getValue();
		
		int index = -1;
		
		for (int i = 0; i < shorter.length(); i++) {
			if (longer.charAt(i) != shorter.charAt(i)) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {
			return true;
		}
		
		return shorter.substring(index).equals(longer.substring(index + 1));
	}
}

class FlatModeDictionary extends Dictionary {	
	@Override
	protected void makeNeighbours() {
		for (PartialDictionary partial : getPartials().values()) {
			partial.makeNeighbours();
		}
	}
}

class LadderModeDictionary extends Dictionary {
	@Override
	protected void makeNeighbours() {
		for (PartialDictionary partial : getPartials().values()) {
			partial.makeNeighbours();
		}
		
		for (int index : getPartials().keySet()) {
			int previous = index - 1;
			
			if(getPartials().keySet().contains(previous)) {
				getPartials().get(index).makeNeighbours(getPartials().get(previous));
			}
		}
	}
}