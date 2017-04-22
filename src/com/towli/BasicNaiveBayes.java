package com.towli;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

import java.util.Enumeration;

/**
 * Created by Towli on 20/04/2017.
 */
public class BasicNaiveBayes implements Classifier {

    private int numClasses;
    private Instances instances;

    /**
     * Create the necessary data structures and count the required occurrences in the training data
     * @param instances
     * @throws Exception
     */
    @Override
    public void buildClassifier(Instances instances) throws Exception {
        this.numClasses = instances.numClasses();
        this.instances = instances;

        System.out.println(instances.attributeStats(0));

        // loop over instances and calculate the distribution for each instance
        for (int i = 0; i < instances.numInstances(); ++i) {
            distributionForInstance(instances.get(i));
        }
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        // call distributionForInstance() and simply return the prediction as the class with the largest probability
        return 0;
    }

    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {
        // work out the probabilities of class member-ship for a single instance
        double distributions[] = new double[this.numClasses];

        Enumeration attributeEnumeration = instance.enumerateAttributes();

        // Enumerate attributes
        for(int i = 0; attributeEnumeration.hasMoreElements(); ++i) {
            Attribute attribute = (Attribute)attributeEnumeration.nextElement();
            for (int j = 0; i < this.numClasses; ++j) {
                // estimate conditional probability

            }
        }

        return new double[0];
    }

    @Override
    public Capabilities getCapabilities() {
        return null;
    }
}
