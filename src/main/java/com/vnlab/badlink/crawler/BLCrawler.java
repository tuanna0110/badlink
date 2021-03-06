package com.vnlab.badlink.crawler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.vnlab.badlink.learning.Learning;
import com.vnlab.badlink.model.LinkObject;
import com.vnlab.badlink.utils.BLConfig;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class BLCrawler extends WebCrawler {
	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	protected List<String> myLocalData;
	protected Learning lm;

	public BLCrawler(Learning lm) {
		this.myLocalData = new ArrayList<String>();
		this.lm = lm;
	}

	/**
	 * Specify whether the given url should be crawled or not (based on your
	 * crawling logic).
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.contains(BLConfig.crawlUrl);
	}

	@Override
	public Object getMyLocalData() {
		return this.myLocalData;
	}

	@Override
	public void visit(Page page) {
		LinkObject linkObj = new LinkObject();
		linkObj.setURL(page.getWebURL().getURL());

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			linkObj.setWords(htmlParseData.getText());
		}
		;
//		try {
//			Document doc = Jsoup.connect(page.getWebURL().getURL()).get();
//			String text = doc.body().text();
//			linkObj.setWords(text);
//		} catch (IOException e) {
//
//		}
		if (this.lm == null
				|| this.lm.assessObject(linkObj) == BLConfig.outputType) {
			logger.info("Accept Link: " + linkObj.getURL());
			try (PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("badlink.txt",
							true), StandardCharsets.UTF_8)))) {
				out.println(page.getWebURL().getURL());
				out.close();
			} catch (IOException e) {
				logger.error("Write bad link to file failed", e);
			}
			this.myLocalData.add(linkObj.getURL());
		} else {
			logger.info("Discard link: " + linkObj.getURL());
		}

	}
}
