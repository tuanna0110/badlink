package com.vnlab.badlink.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import uk.org.lidalia.slf4jext.Logger;
import uk.org.lidalia.slf4jext.LoggerFactory;

public class BLConfig {
	private String crawlUrl;
	private int crawlDepth;
	public static int outputType;
	
	private static final Logger logger = LoggerFactory.getLogger(BLConfig.class);
	
	public BLConfig() {
		try {
			Properties prop = new Properties();
			prop.load(getClass().getResourceAsStream("/defaultConfig"));
			this.setCrawlUrl(prop.getProperty("url"));
			this.setCrawlDepth(new Integer(prop.getProperty("depth")).intValue());
			BLConfig.outputType = new Integer(prop.getProperty("output")).intValue();
		} catch (Exception e) {
			System.out.println("Default config file had error");
			logger.error("Default config file had error", e);
			System.exit(0);		
		}
	}
	
	public String getCrawlUrl() {
		return crawlUrl;
	}

	public void setCrawlUrl(String crawlUrl) {
		this.crawlUrl = crawlUrl;
	}

	public int getCrawlDepth() {
		return crawlDepth;
	}

	public void setCrawlDepth(int crawlDepth) {
		this.crawlDepth = crawlDepth;
	}

	public int getOutputType() {
		return outputType;
	}

	public void readConfigFile(String configFile) {
		String backupUrl = this.crawlUrl;
		int backupDepth = this.crawlDepth;
		int backupOutput = BLConfig.outputType;
		FileInputStream fi = null;

		try {
			Properties prop = new Properties();
			fi = new FileInputStream("configFile"); 
			prop.load(fi);
			this.setCrawlUrl(prop.getProperty("url"));
			this.setCrawlDepth(new Integer(prop.getProperty("depth")).intValue());
			BLConfig.outputType = new Integer(prop.getProperty("output")).intValue();
		} catch (Exception e) {
			System.out.println("Error when read config file. Load config from default file");
			this.crawlUrl = backupUrl;
			this.crawlDepth = backupDepth;
			BLConfig.outputType = backupOutput;			
		}  finally {
			if (fi != null) {
				try {
					fi.close();
				} catch (IOException e) {
					logger.error("Error when close input stream ", e);
				}
			}
		}		
	}
}
