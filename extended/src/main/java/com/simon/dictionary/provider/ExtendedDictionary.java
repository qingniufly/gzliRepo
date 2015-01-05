package com.simon.dictionary.provider;

import java.util.SortedMap;
import java.util.TreeMap;

import com.simon.dictionary.spi.Dictionary;

public class ExtendedDictionary implements Dictionary {

	private SortedMap<String, String> map;
	 
    /**
     * Creates a new instance of ExtendedDictionary
     */
    public ExtendedDictionary() {
        map = new TreeMap<String, String>();
        map.put(
            "xml",
            "a document standard often used in web services, among other " +
                "things");
        map.put(
            "REST",
            "an architecture style for creating, reading, updating, " +
                "and deleting data that attempts to use the common " +
                "vocabulary of the HTTP protocol; Representational State " +
                "Transfer");
    }
 
    @Override
    public String getDefinition(String word) {
        return map.get(word);
    }

}
