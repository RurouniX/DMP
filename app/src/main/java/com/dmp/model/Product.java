package com.dmp.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by X-Dream on 13/12/15.
 */
public class Product extends SugarRecord implements Serializable {

    public String productName;
    public double price;
    public String thumb;

}
