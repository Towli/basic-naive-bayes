package com.towli;

import weka.core.Attribute;

/**
 * Created by Towli on 23/04/2017.
 */
public class DataAttribute {

    private Attribute attribute;
    private DistributionSet[] distributionSets;

    public DataAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public void initDistributionSets(int size) {
        distributionSets = new DistributionSet[size];
    }

    public DistributionSet[] getDistributionSets() {
        return this.distributionSets;
    }

    public void assignDistributionSet(int index, DistributionSet distributions) {
        distributionSets[index] = distributions;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

}
