package com.towli;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

import java.io.FileReader;
import java.util.Arrays;

/**
 * Driver class for processing BasicNaiveBayes experiments
 */
public class Main {

    public static void main(String[] args) throws Exception {
        /* Read in .arff files */
        String trainData = "resources/crime_train.arff";
        String testData = "resources/crime_test.arff";
        Instances trainingInstances = readData(trainData);
        trainingInstances = initInstances(trainingInstances);
        Instances testingInstances = readData(testData);
        testingInstances = initInstances(testingInstances);

        /* Build classifier */
        BasicNaiveBayes naiveBayes = new BasicNaiveBayes();
        naiveBayes.buildClassifier(trainingInstances);

        System.out.println("Classifying all instances:");
        for (int i = 0; i < testingInstances.numInstances(); ++i)
            System.out.println(naiveBayes.classifyInstance(testingInstances.get(i)));

        System.out.println("Distribution for all instances:");
        for (int i = 0; i < testingInstances.numInstances(); ++i)
            System.out.println(Arrays.toString(naiveBayes.distributionForInstance(testingInstances.get(i))));
    }

    /**
     * Get Instances from specified data file
     * @return Instances
     */
    public static Instances readData(String filePath) {
        Instances instances = null;
        try {
            FileReader reader = new FileReader(filePath);
            instances = new Instances(reader);
        } catch(Exception e) {
            System.out.println("Exception caught: "+e);
        }
        return instances;
    }

    public static Instances initInstances(Instances instances) {
        instances.setClassIndex(instances.numAttributes()-1);
        return instances;
    }

}
