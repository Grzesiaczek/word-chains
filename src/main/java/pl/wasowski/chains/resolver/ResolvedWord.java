package pl.wasowski.chains.resolver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.wasowski.chains.dictionary.Word;

public class ResolvedWord {
	private Word word;
	private Set<ResolvedWord> neighbours;
	private ResolvedWord previous;
	
	public ResolvedWord(Word word) {
		this.word = word;
		neighbours = new HashSet<>();
	}
	
	public void addNeighbours(Stream<ResolvedWord> words) {
		neighbours.addAll(words.collect(Collectors.toList()));
	}
	
	public Set<ResolvedWord> getNeighbours() {
		return Collections.unmodifiableSet(neighbours);
	}
	
	public ResolvedWord getPrevious() {
		return previous;
	}
	
	public String getValue() {
		return word.getValue();
	}
	
	public boolean hasPrevious() {
		return previous != null;
	}
	
	public void visit() {
		neighbours = neighbours.stream()
				.filter(n -> !n.hasPrevious())
				.collect(Collectors.toSet());
		
		for(ResolvedWord word : neighbours) {
			word.neighbours.remove(this);
			
			if (!word.hasPrevious()) {
				word.previous = this;
			}
		}
	}
}
