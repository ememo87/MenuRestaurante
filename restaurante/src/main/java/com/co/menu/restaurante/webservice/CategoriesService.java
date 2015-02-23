package com.co.menu.restaurante.webservice;

import com.co.menu.restaurante.entities.CategoriesSp;
import com.co.menu.restaurante.entities.CategoriesSpResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by esandoval on 21/02/15.
 */
public interface CategoriesService {

    @GET("/category")
    @Headers({ "Accept:Application/json"})
            void category(Callback<List<CategoriesSpResponse>> callbackCallback);
}
