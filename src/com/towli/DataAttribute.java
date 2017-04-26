package com.towli;

import weka.core.Attribute;

/**
 * Created by Towli on 23/04/2017.
 */
public class DataAttribute {

    private Attribute attribute;
    private DistributionSet[] sets;

    public DataAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

}
