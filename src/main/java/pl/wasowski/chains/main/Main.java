package pl.wasowski.chains.main;

import java.util.Scanner;

import pl.wasowski.chains.dictionary.Dictionary;
import pl.wasowski.chains.factory.ModeFactory;
import pl.wasowski.chains.resolver.Resolver;
import pl.wasowski.chains.resolver.Solution;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Dictionary dictionary;
	
	public static void main(String[] args) {
		dictionary = ModeFactory.getLadderModeFactory().getDefaultDictionary();
		System.out.println("To exit this program type any number instead of the word.");
		
		while (true) {
			System.out.println("\nPlease type start word:");
			String start = readWord();
			
			System.out.println("Please type end word:");
			String end = readWord();
			
			Resolver resolver = dictionary.getResolver(start, end);
			Solution solution = resolver.getSolution();
			solution.print();
		}
	}
	
	static String readWord() {
		while (true) {
			String word = sc.next();
			
			try {
				Integer.parseInt(word);
				System.out.println("Program has been finished.");
				Thread.sleep(500);
				System.exit(0);
			} catch (Exception e) {}
			
			if (dictionary.hasWord(word)) {
				return word;
			} else {
				System.out.println("Word doesn't exist in dictionary, try another one");
			}
		}
	}
}
