package com.dmp.dao;

import com.dmp.bus.DAOSavePaymentBus;
import com.dmp.bus.DAOSaveProductsBus;
import com.dmp.model.Product;

import java.util.List;

/**
 * Created by X-Dream on 14/12/15.
 */
public class DAOService {

    public void onEvent(DAOSaveProductsBus daoSaveProductsBus) {
        List<Product> productList = daoSaveProductsBus.products;

        for (Product product : productList) {
            product.save();
        }


    }

    public void onEvent(DAOSavePaymentBus daoSavePaymentBus) {

        daoSavePaymentBus.payment.save();

    }

}
