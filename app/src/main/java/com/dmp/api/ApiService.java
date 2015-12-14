package com.dmp.api;

import com.dmp.bus.RequestPaymentBus;
import com.dmp.bus.RequestProductsBus;
import com.dmp.bus.ResponsePaymentBus;

/**
 * Created by X-Dream on 13/12/15.
 */
public class ApiService {


    public void onEvent(RequestProductsBus bus) {

        HttpClient.getInstance(bus.context).requestProducts();

    }

    public void onEvent(RequestPaymentBus requestPaymentBus) {
        HttpClient.getInstance(requestPaymentBus.context).requestPayment(requestPaymentBus.payment);
    }

}
