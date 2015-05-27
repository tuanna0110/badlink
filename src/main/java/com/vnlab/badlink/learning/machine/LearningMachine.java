package com.vnlab.badlink.learning.machine;

import java.util.List;

import com.vnlab.badlink.model.LinkObject;

public interface LearningMachine {
	public void learn(List<LinkObject> listObjects);
	public int getTypeOfObj(LinkObject listObjects);
	public boolean hasLearned();
}
