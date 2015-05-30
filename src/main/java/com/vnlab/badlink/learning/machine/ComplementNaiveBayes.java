package com.vnlab.badlink.learning.machine;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import uk.org.lidalia.slf4jext.Logger;
import uk.org.lidalia.slf4jext.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import com.vnlab.badlink.Test;
import com.vnlab.badlink.model.LinkObject;
import com.vnlab.badlink.tokenizer.JapaneseTokenizer;

public class ComplementNaiveBayes implements LearningMachine {
	
	protected static final Logger logger = LoggerFactory.getLogger(ComplementNaiveBayes.class);

	private final String DATA_WRITE_PATH = "./data/trainingdata.arff";
	private Classifier classifier;
	private StringToWordVector filter;
	private Instances dataFormat;
	
	public ComplementNaiveBayes() {
		super();
		this.classifier = new weka.classifiers.bayes.ComplementNaiveBayes();
		this.filter = this.createStringFilter();
		this.dataFormat = this.createFormatData();
	}
	
	@Override
	public void createTrainingData(List<LinkObject> listObjects) throws Exception{
		String[] linkClass = new String[2];
		linkClass[0] = "good";
		linkClass[1] = "bad";
		Instances dataFormat = this.createFormatData();
		for (LinkObject linkObj: listObjects) {
			 Instance instance = new Instance(2);
		     instance.setDataset(dataFormat);
		     instance.setValue(0, linkObj.getWords());
		     instance.setValue(1, linkClass[linkObj.getType()]);		     
		     dataFormat.add(instance);
		}
		this.filter.setInputFormat(dataFormat);
	    Instances filterData = Filter.useFilter(dataFormat, filter);
	    this.classifier.buildClassifier(filterData);
	    ArffSaver saver = new ArffSaver();
	    
	    saver.setInstances(filterData);
	    saver.setFile(new File(DATA_WRITE_PATH));
	    saver.writeBatch();
	}

	@Override
	public void learn(InputStream input) throws Exception{
		 DataSource source = new DataSource(input);
		 Instances data = source.getDataSet();
		 data.setClassIndex(0);
		 this.classifier.buildClassifier(data);
		 this.filter.setInputFormat(this.dataFormat);
    	 Filter.useFilter(this.dataFormat, filter);
	}

	@Override
	public int getTypeOfObj(LinkObject listObjects) {
		 Instance instance = new Instance(2);
	     instance.setDataset(this.dataFormat);
	     instance.setValue(0,listObjects.getWords());
	     try {	    	 	    	 
	    	 this.filter.input(instance);
	    	 return (int) this.classifier.classifyInstance(filter.output());
	     } catch (Exception e) {
	    	 logger.error("Classifier return error", e);
	    	 return 0;
	     }	    
	}

	@Override
	public boolean hasLearned() {
		return false;
	}
	
	private StringToWordVector createStringFilter() {
		 StringToWordVector filter = new StringToWordVector();
	     filter.setTokenizer(new JapaneseTokenizer());
	     filter.setIDFTransform(true);
	     filter.setTFTransform(true);
	     filter.setLowerCaseTokens(true);
	     filter.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));
	     
	     filter.setStopwords(new File(Test.class.getResource("/jaStopWord").getPath()));
	     filter.setUseStoplist(true);
	     return filter;
	}
	
	private Instances createFormatData() {		 
	     Attribute docContent = new Attribute("docContent", (FastVector) null);
	     FastVector classValues = new FastVector(2);
	     classValues.addElement("good");
	     classValues.addElement("bad");
	     Attribute docClass = new Attribute("docClass", classValues);	     
	     FastVector atts = new FastVector();		     
	     atts.addElement(docContent);
	     atts.addElement(docClass);
	     Instances data = new Instances("docFormatData", atts, 0);
	     data.setClassIndex(1);
	     return data;
	}
}
