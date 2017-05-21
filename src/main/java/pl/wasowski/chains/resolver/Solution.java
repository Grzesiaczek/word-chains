package pl.wasowski.chains.resolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	private List<ResolvedWord> chain;
	
	public Solution(ResolvedWord end) {
		chain = new ArrayList<>();
		resolve(end);
	}
	
	public int length() {
		return chain.size();
	}
	
	public boolean hasSolution() {
		return !chain.isEmpty();
	}
	
	public void print() {
		System.out.print('\n');
				
		if (chain.size() < 2) {
			System.out.println("Solution doesn't exist");
			return;
		}
		
		System.out.println("Word chain was found with " + length() + " elements:");
		
		for (ResolvedWord word : chain) {
			System.out.println(word.getValue());
		}
	}
	
	private void resolve(ResolvedWord end) {
		chain.clear();
		
		if (end.hasPrevious()) {
			chain.add(end);
			ResolvedWord word = end;
			
			while (word.hasPrevious()) {
				word = word.getPrevious();
				chain.add(word);
			}
			
			Collections.reverse(chain);
		}
	}
}
