package com.dmp.model;

import com.orm.SugarRecord;

/**
 * Created by X-Dream on 13/12/15.
 */
public class Payment extends SugarRecord {

    public String cardName;
    public String dtCard;
    public int cardFlagId;
    public String cardFlagName;
    public String cvv;
    public double price;
    public String cardNumber;

}
