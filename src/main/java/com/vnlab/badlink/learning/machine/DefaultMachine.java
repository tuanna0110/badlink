package com.vnlab.badlink.learning.machine;

import java.io.InputStream;
import java.util.List;

import com.vnlab.badlink.utils.BLConstants;
import com.vnlab.badlink.model.LinkObject;

public class DefaultMachine implements LearningMachine {

	public void learn(String fileName) {
		// TODO Auto-generated method stub
		
	}

	public int getTypeOfObj(LinkObject listObjects) {
		// TODO Auto-generated method stub
		return BLConstants.GOOD_TYPE;
	}

	public boolean hasLearned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createTrainingData(List<LinkObject> listObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void learn(InputStream input) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
