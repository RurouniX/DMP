package com.dmp.api;

import com.dmp.model.Payment;
import com.dmp.model.Product;
import com.dmp.model.ResponsePayment;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by X-Dream on 13/12/15.
 */
public interface Api {
    @GET("products")
    Call<List<Product>> getProducts();

    @POST("payment")
    Call<ResponsePayment> makePayment(@Body Payment payment);
}
