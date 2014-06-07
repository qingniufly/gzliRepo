package com.simon.crawler;

public interface LinkFilter {
	public boolean accept(String url);
}
