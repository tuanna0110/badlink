package com.vnlab.badlink.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import uk.org.lidalia.slf4jext.Logger;
import uk.org.lidalia.slf4jext.LoggerFactory;

import com.vnlab.badlink.utils.BLConstants;
import com.vnlab.badlink.model.LinkObject;

public class LearningCrawlerController {
	
	protected static final Logger logger = LoggerFactory.getLogger(LearningCrawlerController.class);
	
	private final String BAD_FILE = "/bad";
	private final String GOOD_FILE = "/good";

	public LearningCrawlerController() {
	}

	private LinkObject crawl(String url) throws IOException {
		LinkObject linkObj = new LinkObject();
		linkObj.setURL(url);
		Document doc = Jsoup.connect(url).get();
		String text = doc.body().text();
		linkObj.setWords(text);
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
			try {
				LinkObject linkObj = this.crawl(line);
				linkObj.setType(type);
				linkObjectList.add(linkObj);
			} catch (IOException e) {
				logger.error("Cannot get info from url: " + line, e);
				continue;
			}			
		}
		br.close();
		return linkObjectList;
	}

}
