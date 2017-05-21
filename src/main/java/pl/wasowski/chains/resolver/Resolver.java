package pl.wasowski.chains.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Resolver {
	private Solution solution;
	
	private ResolvedWord start;
	private ResolvedWord end;
	
	public Resolver(ResolvedWord start, ResolvedWord end) {
		this.start = start;
		this.end = end;
	}
	
	public Solution getSolution() {
		if(solution == null) {
			resolve();
		}
		
		return solution;
	}
	
	private void resolve() {
		List<ResolvedWord> words = new ArrayList<>();
		words.add(start);
		
		while(!end.hasPrevious() && words.size() > 0) {
			words.forEach(ResolvedWord::visit);
			words = words.stream()
					.map(ResolvedWord::getNeighbours)
					.flatMap(Set::stream)
					.distinct()
					.collect(Collectors.toList());
		}
		
		solution = new Solution(end);
	}
}
