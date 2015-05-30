package com.vnlab.badlink;

import java.io.File;

import com.vnlab.badlink.tokenizer.JapaneseTokenizer;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.ComplementNaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class Test {
	   public static void main(String[] args) throws Exception {
		     FastVector      atts;
		     Instances       data;
		 
		     // 1. set up attributes
		     Attribute docContent = new Attribute("docContent", (FastVector) null);
		     FastVector classValues = new FastVector(2);
		     classValues.addElement("good");
		     classValues.addElement("bad");
		     Attribute docClass = new Attribute("docClass", classValues);
		     
		     
		     atts = new FastVector();		     
		     atts.addElement(docContent);
		     atts.addElement(docClass);
		     		 
		     // 2. create Instances object
		     data = new Instances("MyRelation", atts, 0);
		     data.setClassIndex(1);
		     Instance instanceA = new Instance(2);
		     instanceA.setDataset(data);
		     instanceA.setValue(0, "このフィルムが嫌い");
		     instanceA.setValue(1, "good");		     
		     data.add(instanceA);
		     
		     Instance instanceB = new Instance(2);
		     instanceB.setDataset(data);
		     instanceB.setValue(0, "あの寿司が多い");
		     instanceB.setValue(1, "bad");		     
		     data.add(instanceB);
		 	 
		     // 4. output data
		     System.out.println(data);
		     StringToWordVector filter = new StringToWordVector();
//		     String[] options = new String[4];
//		     options[0] = "-T";
//		     options[1] = "-I";
//		     options[2] = "-L";
//		     options[3] = "-tokenizer com.vnlab.badlink.tokenizer.JapaneseTokenizer";
		     filter.setTokenizer(new JapaneseTokenizer());
		     filter.setIDFTransform(true);
		     filter.setTFTransform(true);
		     filter.setLowerCaseTokens(true);
		     filter.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));
		     
		     filter.setStopwords(new File(Test.class.getResource("/jaStopWord").getPath()));
		     filter.setUseStoplist(true);
		     filter.setInputFormat(data);
		     Instances filterData = Filter.useFilter(data, filter);
		     
		     System.out.println(filterData);
		     
		     filter = new StringToWordVector();
//		     String[] options = new String[4];
//		     options[0] = "-T";
//		     options[1] = "-I";
//		     options[2] = "-L";
//		     options[3] = "-tokenizer com.vnlab.badlink.tokenizer.JapaneseTokenizer";
		     filter.setTokenizer(new JapaneseTokenizer());
		     filter.setIDFTransform(true);
		     filter.setTFTransform(true);
		     filter.setLowerCaseTokens(true);
		     filter.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));
		     
		     filter.setStopwords(new File(Test.class.getResource("/jaStopWord").getPath()));
		     filter.setUseStoplist(true);
		     filter.setInputFormat(data);
		     Instance instanceC = new Instance(2);
		     instanceC.setDataset(data);
		     instanceC.setValue(0, "このフィルムが嫌い");
		     
		     Filter.useFilter(data, filter);
		     filter.input(instanceC);
		     Instance fileter = filter.output();
		     
		     Classifier classifier = new ComplementNaiveBayes();
		     classifier.buildClassifier(filterData);
		     System.out.println(filterData.classIndex());
		     double predicted = classifier.classifyInstance(fileter);
		     System.out.println(predicted);
		     System.out.println(filterData.classAttribute().value((int)predicted));
		     
		     Instance instanceD = new Instance(2);
		     instanceD.setDataset(data);
		     instanceD.setValue(0, "あの寿司が多い");
		     filter.input(instanceD);
		     predicted = classifier.classifyInstance(filter.output());
		     System.out.println(predicted);
		     System.out.println(filterData.classAttribute().value((int)predicted));
		   }
}
