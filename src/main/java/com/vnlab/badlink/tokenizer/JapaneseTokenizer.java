package com.vnlab.badlink.tokenizer;

import java.util.Iterator;

import weka.core.tokenizers.Tokenizer;
import org.atilika.kuromoji.Token;

public class JapaneseTokenizer extends Tokenizer {

	private static final long serialVersionUID = -644771651784950589L;
	
	protected transient Iterator<Token> wordList;
	
	@Override
	public String getRevision() {
		return "1.0";
	}

	@Override
	public String globalInfo() {
		// TODO Auto-generated method stub
		return "A Japanese Tokenizer";
	}

	@Override
	public boolean hasMoreElements() {
		return wordList.hasNext();
	}

	@Override
	public Object nextElement() {
		return wordList.next().getSurfaceForm();
	}

	@Override
	public void tokenize(String arg0) {
		org.atilika.kuromoji.Tokenizer tokenizer = org.atilika.kuromoji.Tokenizer.builder().build();
		this.wordList = tokenizer.tokenize(arg0).iterator();
	}
}
