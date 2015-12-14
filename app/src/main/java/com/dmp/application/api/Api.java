package com.dmp.application.api;

import com.dmp.model.Product;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by X-Dream on 13/12/15.
 */
public interface Api {
    @GET("products")
    Call<List<Product>> getProducts();
}
