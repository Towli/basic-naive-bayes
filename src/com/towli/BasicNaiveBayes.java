package com.towli;

import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import java.util.ArrayList;

/**
 * Created by Towli on 20/04/2017.
 */
public class BasicNaiveBayes implements Classifier {

    private int numClasses;
    private Instances instances;
    private ArrayList<DataAttribute> attributes;
    private ClassAttribute classAttribute;

    /**
     * Create the necessary data structures and count the required occurrences in the training data
     * @param instances
     * @throws Exception
     */
    @Override
    public void buildClassifier(Instances instances) throws Exception {
        this.numClasses = instances.numClasses();
        this.instances = instances;
        this.attributes = new ArrayList<>();

        createDataAttributes();
        createClassAttributes();

        for (DataAttribute attribute : attributes)
            calculateDistributions(attribute);
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        //int classValue = (int)instance.classValue();
        double[] distributions = distributionForInstance(instance);
        double classification = 0.0d;
        for (int i = 0; i < distributions.length; ++i)
            classification = distributions[i] > classification ? distributions[i] : classification;
        return classification;
    }

    /**
     * Work out the probabilities of class member-ship for a single instance
     * @param instance
     * @return
     * @throws Exception
     */
    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {
        double[] distribution = new double[this.numClasses];
        int classValue;
        for (int i = 0; i < distribution.length; i++) {
            classValue = Integer.parseInt(this.classAttribute.getValueByIndex(i));
            distribution[i] = calculateProbabilityDensity(instance, classValue);
        }
        return distribution;
    }

    /**
     *
     * @param instance
     * @param classValue
     * @return
     */
    private double calculateProbabilityDensity(Instance instance, int classValue) {
        int attributeValue;
        double classification = 1.0d;
        for (DataAttribute attribute : this.attributes) {
            attributeValue = (int)instance.value(attribute.getAttribute());
            classification *= attribute.getDistributionSets()[classValue].getDistributionByValue(attributeValue);
        }
        classification *= classAttribute.getMarginal(classValue, instances.numInstances());
        return classification;
    }

    @Override
    public Capabilities getCapabilities() {
        return null;
    }

    /**
     * Create DataAttribute objects from the training data set
     * -> Ignores the ClassAttribute
     */
    private void createDataAttributes() {
        for (int i = 0; i < this.instances.numAttributes(); ++i)
            if (i != instances.classIndex())
                this.attributes.add(new DataAttribute(instances.attribute(i)));
    }

    /**
     * Create ClassAttribute objects from the training data set
     * -> currently assumes only a single class attribute
     */
    private void createClassAttributes() {
        int[] nominalCounts = instances.attributeStats(instances.classIndex()).nominalCounts;
        this.classAttribute = new ClassAttribute(instances.classAttribute());
        this.classAttribute.setNominalCounts(nominalCounts);
    }

    /**
     * Calculates the distributions for an attribute
     * -> iterates for each value of the dataset's Class Attribute
     * @param attribute
     */
    private void calculateDistributions(DataAttribute attribute) {
        attribute.initDistributionSets(numClasses); //maybe reference classAttribute.numValues instead
        DistributionSet newDistributionSet;
        for (int i = 0; i < numClasses; ++i) {
            newDistributionSet = calculateConditionals(i, attribute);
            attribute.assignDistributionSet(i, newDistributionSet);
        }
    }

    /**
     * Returns whether a specified attribute in a single given instance has equal value to a particular value
     * of a DataAttribute
     * @param instance
     * @param attribute
     * @param attrValueIndex
     * @return true if equal, else false
     */
    private boolean attrValuesEqual(Instance instance, DataAttribute attribute, int attrValueIndex) {
        return instance.stringValue((attribute.getAttribute())).equalsIgnoreCase(attribute.getAttribute().value(attrValueIndex));
    }

    /**
     * Returns whether a specified class in a single given instances has equal value to a particular value
     * of a ClassAttribute
     * @param instance
     * @param classAttribute
     * @param classValueIndex
     * @return true if equal, else false
     */
    private boolean classValuesEqual(Instance instance, ClassAttribute classAttribute, int classValueIndex) {
        return String.valueOf((int) instance.classValue()).equalsIgnoreCase(classAttribute.getValueByIndex(classValueIndex));
    }

    /**
     * Iterate over the the set of training instances, counting the occurrences of each value of
     * the provided for each value of the Class attribute.
     * @param classValueIndex
     * @param attribute
     * @return
     */
    private DistributionSet calculateConditionals(int classValueIndex, DataAttribute attribute) {
        int counter = 0;
        int attributeValues = attribute.getAttribute().numValues();
        int classValueOccurrences = classAttribute.getNominalCounts()[classValueIndex];
        double[] conditionalDistributions = new double[attributeValues];
        Instance instance;

        for (int i = 0; i < attributeValues; ++i) {
            for (int j = 0; j < instances.numInstances(); ++j) {
                instance = instances.instance(j);
                if (attrValuesEqual(instance, attribute, i) &&
                        classValuesEqual(instance, classAttribute, classValueIndex)) {
                    counter++;
                }
            }
            conditionalDistributions[i] = (double)counter / (double)classValueOccurrences;
            counter = 0;
        }
        return new DistributionSet(conditionalDistributions);
    }
}
