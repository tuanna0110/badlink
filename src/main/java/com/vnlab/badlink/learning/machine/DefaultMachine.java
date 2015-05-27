package com.vnlab.badlink.learning.machine;

import java.util.List;

import com.vnlab.badlink.utils.BLConstants;
import com.vnlab.badlink.model.LinkObject;

public class DefaultMachine implements LearningMachine {

	public void learn(List<LinkObject> listObjects) {
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
}
