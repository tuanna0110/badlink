package com.vnlab.badlink.model;

public class LinkObject {
	private String url;
	private int type;
	private String words;	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
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
