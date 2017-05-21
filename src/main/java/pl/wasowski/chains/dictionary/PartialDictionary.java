package pl.wasowski.chains.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartialDictionary {
	private int wordLength;
	private Map<String, Word> wordMap;
	
	public PartialDictionary(int wordLength) {
		this.wordLength = wordLength;
		wordMap = new HashMap<>();
	}
	
	public void addWord(Word word) {
		if(word.getLength() == wordLength) {
			wordMap.put(word.getValue(), word);
		}
	}
	
	public Word getWord(String value) {
		return wordMap.get(value);
	}
	
	public List<Word> getWords() {
		return new ArrayList<>(wordMap.values());
	}
	
	public void makeNeighbours() {
		List<List<Word>> first = new ArrayList<>();
		List<List<Word>> second = new ArrayList<>();
		
		int size = 10000;
		
		for (int i = 0; i < size; i++) {
			first.add(new ArrayList<>());
			second.add(new ArrayList<>());
		}
		
		int start1 = 0;
		int end1 = 0;
		int start2 = 0;
		int end2 = 0;
		
		if (wordLength > 2) {
			end1 = 3;
			start2 = 3;
			end2 = wordLength;
		}
		
		if (wordLength > 6) {
			end2 = 6;
		}
		
		for (Word word : wordMap.values()) {
			first.get(word.hashCode(start1, end1) % size).add(word);
			second.get(word.hashCode(start2, end2) % size).add(word);
		}
		
		makeNeighbours(first);
		makeNeighbours(second);
	}
	
	public void makeNeighbours(PartialDictionary dictionary) {
		for (Word word : wordMap.values()) {
			String value = word.getValue();
			
			for (int i = 0; i < value.length(); i++) {
				StringBuilder builder = new StringBuilder(value);
				String seek = builder.deleteCharAt(i).toString();
				
				Word found = dictionary.getWord(seek);
				
				if (found != null) {
					word.makeNeighbour(found);
				}
			}
		}
	}
	
	private void makeNeighbours(List<List<Word>> list) {
		for (List<Word> words : list) {
			for (int i = 0; i < words.size(); i++) {
				for (int j = i + 1; j < words.size(); j++) {
					words.get(i).makeNeighbour(words.get(j));
				}
			}
		}
	}
}
