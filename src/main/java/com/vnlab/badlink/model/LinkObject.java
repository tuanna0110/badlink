package com.vnlab.badlink.model;

import java.util.ArrayList;
import java.util.List;

public class LinkObject {
	private String url;
	private int type;
	private List<String> words;
	
	public LinkObject() {
		this.words = new ArrayList<String>();
	}
	
	public void addWord(String word) {
		this.words.add(word);
	}
	
	public List<String> getWords() {
		return this.words;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public String getURL() {
		return this.url;
	}
}
