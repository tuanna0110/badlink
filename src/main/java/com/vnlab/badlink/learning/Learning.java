package com.vnlab.badlink.learning;

import java.io.InputStream;
import java.util.List;

import com.vnlab.badlink.utils.BLConstants;
import com.vnlab.badlink.learning.machine.LearningMachine;
import com.vnlab.badlink.model.LinkObject;

public class Learning {
	private LearningCrawlerController crawlController;
	private LearningMachine lm;
	
	public Learning(String lmClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.crawlController = new LearningCrawlerController();
		this.lm = (LearningMachine) Class.forName(BLConstants.LEARNING_MACHINE_DIR + "." + lmClassName).newInstance();
	}
	
	public void createTrainingData() throws Exception {
		//List<LinkObject> listObject = crawlController.crawlBadSite();
		//listObject.addAll(crawlController.crawlGoodSite());
		List<LinkObject> listObject = crawlController.crawlGoodSite();
		lm.createTrainingData(listObject);		
	}
	
	public void learn(InputStream input) throws Exception {
		lm.learn(input);
	}
	
	public int assessObject(LinkObject obj) {
		return lm.getTypeOfObj(obj);
	}
}
