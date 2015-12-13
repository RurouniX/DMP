package com.dmp.application.api;

import android.widget.Toast;

import com.dmp.bus.TstBus;

/**
 * Created by X-Dream on 13/12/15.
 */
public class ApiService {


    public void onEvent(TstBus bus){
        Toast.makeText(bus.context, "Paçoca é 1 real", Toast.LENGTH_SHORT).show();
    }

}
