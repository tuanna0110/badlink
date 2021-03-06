package com.vnlab.badlink;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import uk.org.lidalia.slf4jext.Logger;
import uk.org.lidalia.slf4jext.LoggerFactory;

import com.vnlab.badlink.crawler.LinkCrawlController;
import com.vnlab.badlink.learning.Learning;
import com.vnlab.badlink.utils.BLConfig;
import com.vnlab.badlink.utils.BLConstants;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class App {
	protected static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		
		System.out.println("--------READ CONFIG FILE----------");
		BLConfig blConfig = new BLConfig();
		if (args.length < 1) {
			System.out.println("You need pass training data");
			System.exit(0);
		}
		
		if (args.length >= 2) {
			blConfig.readConfigFile(args[1]);
		}		
		
		System.out.println("\n");
		System.out.println("--------LEARNING----------");
		Learning lm = null;
		try {
			lm = new Learning("ComplementNaiveBayes");			
		} catch (ClassNotFoundException | IllegalAccessException
				| InstantiationException e) {
			System.out.println("Cannot find class for learning machine");
			logger.error("Cannot find class for learning machine ", e);
			System.exit(0);
		}
		
		try {
			lm.learn(new FileInputStream(args[0]));
		} catch (Exception e) {
			System.out.println("Cannot find data for learning");
			logger.error("Cannot find data for learning ", e);
			System.exit(0);
		}

		System.out.println("\n");
		System.out.println("--------CRAWLING----------");
		try {
			List<String> urlList = crawl(lm, blConfig);
//			Path outPath = Paths.get(new File(".").getCanonicalPath() + "\\"
//					+ BLConstants.OUTPUT_FILE);
//			Files.write(outPath, urlList, StandardCharsets.UTF_8);
		} catch (Exception e) {
			System.out.println("Cannot create controller of crawler");
			System.exit(0);
		}

		System.out.println("\n");
		System.out.println("--------END----------");
		System.out.println("--------BADLINK.TXT FILE CREATED----------");
	}

	public static List<String> crawl(Learning lm, BLConfig blConfig) throws Exception {
		CrawlConfig config = new CrawlConfig();
		// config.set
		config.setCrawlStorageFolder(System.getProperty("user.dir") + "\\"
				+ BLConstants.CRAWL_TEMP_FOLDER);
		config.setMaxDepthOfCrawling(blConfig.getCrawlDepth());
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);

		LinkCrawlController controller = new LinkCrawlController(config,
				pageFetcher, robotstxtServer);
		controller.setLearning(lm);
		controller.addSeed(BLConfig.crawlUrl);
		controller.startWithBLCrawler(1, true);
		return controller.getLinkList();
	}
}
