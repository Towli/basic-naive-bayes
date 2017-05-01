package com.towli;

import weka.core.Attribute;

/**
 * Created by Towli on 26/04/2017.
 */
public class ClassAttribute {

    private Attribute attribute;
    private int[] nominalCounts;

    public ClassAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getValueByIndex(int i) {
        return attribute.value(i);
    }

    public int[] getNominalCounts() {
        return nominalCounts;
    }

    public void setNominalCounts(int[] nominalCounts) {
        this.nominalCounts = nominalCounts;
    }

    public double getMarginal(int classValueIndex, int numInstances) {
        return (double)nominalCounts[classValueIndex] / (double)numInstances;
    }

}
