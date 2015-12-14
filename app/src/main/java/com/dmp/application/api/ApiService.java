package com.dmp.application.api;

import android.widget.Toast;

import com.dmp.bus.RequestProductsBus;

/**
 * Created by X-Dream on 13/12/15.
 */
public class ApiService {


    public void onEvent(RequestProductsBus bus){

        HttpClient.getInstance(bus.context).requestProducts();

    }

}
