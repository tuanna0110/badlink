package com.vnlab.badlink.learning.machine;

import java.io.InputStream;
import java.util.List;

import com.vnlab.badlink.model.LinkObject;

public interface LearningMachine {	
	public void createTrainingData(List<LinkObject> listObjects) throws Exception;
	public void learn(InputStream input) throws Exception;
	public int getTypeOfObj(LinkObject listObjects);
	public boolean hasLearned();
}
