package pl.wasowski.chains.dictionary;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Word {
	private Set<Word> neighbours;
	private String value;
	
	public Word(String word) {
		value = word;
		neighbours = new HashSet<>();
	}
	
	public Set<Word> getNeighbours() {
		return Collections.unmodifiableSet(neighbours);
	}
	
	public int getLength() {
		return value.length();
	}
	
	public String getValue() {
		return value;
	}
	
	public void makeNeighbour(Word word) {
		if (isNeighbour(word)) {
			neighbours.add(word);
			word.neighbours.add(this);
		}
	}
	
	public int hashCode(int begin, int end) {
		return value.substring(begin, end).hashCode();
	}
	
	@Override
	public boolean equals(Object word) {
		return value.equals(word);
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	protected boolean isFlatNeighbour(Word word) {
		int differences = 0;
		
		for (int i = 0; i < getLength(); i++) {
			if (word.getValue().charAt(i) != getValue().charAt(i)) {
				differences++;
			}
		}
		
		return differences == 1;
	}
	
	protected boolean isNeighbour(Word word) {
		if (word.value.length() != value.length()) {
			return false;
		}
		
		return isFlatNeighbour(word);
	}
}
