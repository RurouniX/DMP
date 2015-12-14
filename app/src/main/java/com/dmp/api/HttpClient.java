package com.dmp.api;

import android.content.Context;

import com.dmp.bus.ResponseErrorPaymentBus;
import com.dmp.bus.ResponseErrorProductBus;
import com.dmp.bus.ResponsePaymentBus;
import com.dmp.bus.ResponseProductsBus;
import com.dmp.model.Payment;
import com.dmp.model.Product;
import com.dmp.model.ResponsePayment;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by X-Dream on 13/12/15.
 */
public class HttpClient {

    static HttpClient httpClient;
    static Api api;
    static Context mContext;
    static String URL = "http://demo1350761.mockable.io/";
    EventBus bus = EventBus.getDefault();

    public static HttpClient getInstance(Context context) {

        if (httpClient == null) {

            mContext = context;

            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).
                    build();

            api = retrofit.create(Api.class);
            return new HttpClient();

        } else {
            return httpClient;
        }
    }


    public void requestProducts() {

        Call<List<Product>> call = api.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Response<List<Product>> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    ResponseProductsBus responseProductsBus = new ResponseProductsBus();
                    responseProductsBus.products = response.body();
                    bus.post(responseProductsBus);
                } else {
                    ResponseErrorProductBus responseErrorProductBus = new ResponseErrorProductBus();
                    bus.post(responseErrorProductBus);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ResponseErrorProductBus responseErrorProductBus = new ResponseErrorProductBus();
                bus.post(responseErrorProductBus);
            }
        });

    }

    public void requestPayment(Payment payment) {
        Call<ResponsePayment> responsePaymentCall = api.makePayment(payment);
        responsePaymentCall.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Response<ResponsePayment> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    ResponsePaymentBus responsePaymentBus = new ResponsePaymentBus();
                    responsePaymentBus.response = response.body();
                    bus.post(responsePaymentBus);
                } else {
                    bus.post(new ResponseErrorPaymentBus());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                bus.post(new ResponseErrorPaymentBus());
            }
        });
    }

}
