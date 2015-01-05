package com.simon.dictionary;

public class DictionaryServiceTest {

	public static void main(String[] args) {
		System.out.println(lookup("book"));
		System.out.println(lookup("editor"));
		System.out.println(lookup("xml"));
		System.out.println(lookup("REST"));
	}
	
	public static String lookup(String word) {
		String outputString = word + ": ";
		String definition = DictionaryService.getInstance().getDefinition(word); 
		if (definition == null) {
			return outputString + "Cannot find definition for this word";
		} else {
			return outputString + definition;
		}
	}
	
}
