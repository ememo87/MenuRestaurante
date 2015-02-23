package com.co.menu.restaurante.webservice;

import com.co.menu.restaurante.entities.MenuSpResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by esandoval on 21/02/15.
 */
public interface MenuService {

    @GET("/collections/2a9f34a396ef5cd2822a")
    @Headers({ "Accept:Application/json"})
    void menu( Callback<MenuSpResponse> callbackCallback);

}
