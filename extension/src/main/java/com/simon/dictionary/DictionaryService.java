package com.simon.dictionary;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import com.simon.dictionary.spi.Dictionary;

public class DictionaryService {

	private static DictionaryService service;
	
	private ServiceLoader<Dictionary> loader;
	
	private DictionaryService() {
		loader = ServiceLoader.load(Dictionary.class);
	}
	
	public static synchronized DictionaryService getInstance() {
		if (service == null) {
			service = new DictionaryService();
		}
		return service;
	}
	
	public String getDefinition(String word) {
		String definition = null;
		try {
			Iterator<Dictionary> it = loader.iterator();
			while (definition == null && it.hasNext()) {
				Dictionary dic = it.next();
				definition = dic.getDefinition(word);
			}
		} catch (ServiceConfigurationError sce) {
			definition = null;
			sce.printStackTrace();
		}
		return definition;
	}
	
}
