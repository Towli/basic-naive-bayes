package com.towli;

import com.sun.tools.doclint.HtmlTag;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by Towli on 20/04/2017.
 */
public class BasicNaiveBayes implements Classifier {

    private int numClasses;
    private Instances instances;
    private ArrayList<DataAttribute> attributes;

    /**
     * Create the necessary data structures and count the required occurrences in the training data
     * @param instances
     * @throws Exception
     */
    @Override
    public void buildClassifier(Instances instances) throws Exception {
        this.numClasses = instances.numClasses();
        this.instances = instances;

        int attIndex = 0;
        for(Enumeration enu = this.instances.enumerateAttributes(); enu.hasMoreElements(); ++attIndex) {
            calculateConditionalDistributions((Attribute)enu.nextElement());
        }
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        // call distributionForInstance() and simply return the prediction as the class with the largest probability
        distributionForInstance(instance);
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

    private void initDataAttributes() {
        this.attributes = new ArrayList<>();
        int numDataAttributes = this.instances.numAttributes();
        for (int i = 0; i < numDataAttributes; ++i) {
            this.attributes.add(new DataAttribute(this.instances.attributeStats(i).nominalCounts));
        }
    }

    // Test function
    private void calculateConditionalDistributions(Attribute attribute) {
        // split instances by the class values..
        // e.g. if class = crime {0,1}, the instances get split into two separate lists for {0,1} respectively
        int numerator = 0, denominator = 0;
        if (!attribute.name().equalsIgnoreCase("class")) {
            for (int i = 0; i < attribute.numValues(); ++i) {
                for (int j = 0; j < this.numClasses; ++j) {
                    for (int k = 0; k < this.instances.numInstances(); ++k) {
                        if (this.instances.get(k).classValue() == j) {
                            denominator++;
                            if (this.instances.get(k).value(attribute) == i)
                                numerator++;
                        }
                    }
                    String conditionalDistribution = numerator + "/" + denominator;
                    System.out.println(conditionalDistribution);
                    numerator = 0;
                    denominator = 0;
                }
            }
        }
    }
}
