package com.vnlab.badlink.learning;

import java.io.IOException;
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
	
	public void learn() throws IOException {
		if (BLConstants.FORCE_REBUILD_DATA || !lm.hasLearned()) {
			List<LinkObject> listObject = crawlController.crawlBadSite();
			listObject.addAll(crawlController.crawlGoodSite());
			lm.learn(listObject);
		}
	}
	
	public int assessObject(LinkObject obj) {
		return lm.getTypeOfObj(obj);
	}
}
