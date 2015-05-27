package com.vnlab.badlink.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.vnlab.badlink.utils.BLConstants;
import com.vnlab.badlink.model.LinkObject;

public class LearningCrawlerController {
	private final String BAD_FILE = "/bad";
	private final String GOOD_FILE = "/good";

	public LearningCrawlerController() {
	}

	private LinkObject crawl(String url) throws IOException {
		LinkObject linkObj = new LinkObject();
		linkObj.setURL(url);
		Document doc = Jsoup.connect(url).get();
		String text = doc.body().text();
		Tokenizer tokenizer = Tokenizer.builder().build();
		for (Token token : tokenizer.tokenize(text)) {
			linkObj.addWord(token.getSurfaceForm());
		}
		return linkObj;
	}

	public List<LinkObject> crawlGoodSite() throws IOException {
		return this.crawlFromFile(GOOD_FILE, BLConstants.GOOD_TYPE);
	}

	public List<LinkObject> crawlBadSite() throws IOException {
		return this.crawlFromFile(BAD_FILE, BLConstants.BAD_TYPE);
	}

	private List<LinkObject> crawlFromFile(String fileName, int type)
			throws IOException {
		List<LinkObject> linkObjectList = new ArrayList<LinkObject>();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				getClass().getResourceAsStream(fileName)));
		String line = null;
		while ((line = br.readLine()) != null) {
			LinkObject linkObj = this.crawl(line);
			linkObj.setType(type);
			linkObjectList.add(linkObj);
		}
		br.close();
		return linkObjectList;
	}

}
