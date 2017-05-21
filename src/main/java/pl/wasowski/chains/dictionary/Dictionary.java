package pl.wasowski.chains.dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.wasowski.chains.factory.ModeFactory;
import pl.wasowski.chains.resolver.ResolvedWord;
import pl.wasowski.chains.resolver.Resolver;

public abstract class Dictionary {
	private Map<Integer, PartialDictionary> partials = new HashMap<>();
	private ModeFactory factory;
	
	protected Dictionary() {}
	
	public void initializeDefault(ModeFactory factory) {
		this.factory = factory;
		String path = Dictionary.class.getResource("/corncob.txt").getPath();
		loadData(path);
		makeNeighbours();
	}
	
	public void initializeEmpty(ModeFactory factory) {
		this.factory = factory;
	}
	
	public Resolver getResolver(String start, String end) {
		Map<Word, ResolvedWord> mapper = partials.values().stream()
				.map(PartialDictionary::getWords)
				.flatMap(List::stream)
				.collect(Collectors.toMap(a -> a, b -> new ResolvedWord(b)));
		
		mapper.forEach((w, rw) -> {
			Stream<ResolvedWord> stream = w.getNeighbours().stream().map(a -> mapper.get(a));
			rw.addNeighbours(stream);
		});
		
		ResolvedWord resolvedStart = mapper.get(getWord(start));
		ResolvedWord resolvedEnd = mapper.get(getWord(end));
		
		return new Resolver(resolvedStart, resolvedEnd);
	}
	
	public void addWord(String word) {
		PartialDictionary partial = getDictionary(word.length());
		partial.addWord(factory.getWord(word));
	}
	
	public boolean hasWord(String word) {
		return getWord(word) != null;
	}
	
	public void refresh() {
		makeNeighbours();
	}	
	
	protected Map<Integer, PartialDictionary> getPartials() {
		return partials;
	}
	
	private PartialDictionary getDictionary(int length) {
		if (partials.containsKey(length)) {
			return partials.get(length);
		} else {
			PartialDictionary dictionary = new PartialDictionary(length);
			partials.put(length, dictionary);
			return dictionary;
		}
	}
	
	private PartialDictionary getPartialDictionary(String word) {
		return getDictionary(word.length());
	}
	
	private Word getWord(String word) {
		return getPartialDictionary(word).getWord(word);
	}
	
	private void loadData(String path) {		
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		       addWord(line);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void makeNeighbours();
}
