package com.co.menu.restaurante.webservice;

import com.co.menu.restaurante.entities.DetailsSpResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by esandoval on 21/02/15.
 */
public interface DetailsService {
    @GET("/category/details/1")
    @Headers({ "Accept:Application/json"})
    void details( Callback<DetailsSpResponse> callbackCallback);
}
