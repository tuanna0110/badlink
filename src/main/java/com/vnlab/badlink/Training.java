package com.vnlab.badlink;

import com.vnlab.badlink.learning.Learning;

public class Training {

	public static void main(String[] args) {
		try {
			Learning lm = new Learning("ComplementNaiveBayes");
			lm.createTrainingData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
