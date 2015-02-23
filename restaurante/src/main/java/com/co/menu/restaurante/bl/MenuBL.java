package com.co.menu.restaurante.bl;


import com.co.menu.restaurante.app.Application;
import com.co.menu.restaurante.entities.MenuSpResponse;
import com.co.menu.restaurante.entities.MenuSpResponseData;
import com.co.menu.restaurante.entities.MenuSpResponseRequests;
import com.co.menu.restaurante.models.MenuData;
import com.co.menu.restaurante.models.MenuItems;
import com.co.menu.restaurante.models.MenuRequests;
import com.co.menu.restaurante.tools.ConnectionDetector;
import com.co.menu.restaurante.webservice.MenuService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by esandoval on 21/02/15.
 */
public class MenuBL {

    private menuBlListener listener;
    private List<MenuRequests> requestsList = new ArrayList<MenuRequests>();
    private List<MenuData> dataList;

    public void RequestMenu(){

        //if (ConnectionDetector.isConnectingToInternet(Application.getContext())) {
            MenuService service = Application.getRestAdapterrestAdapterMenu().create(MenuService.class);
            service.menu(new Callback<MenuSpResponse>() {
                @Override
                public void success(MenuSpResponse menuSpResponse, Response response) {

                    MenuItems items = new MenuItems();
                    items.setId(menuSpResponse.getId());
                    items.setName(menuSpResponse.getName());
                    items.setTimestamp(menuSpResponse.getTimestamp());

                    int sizeRequests = menuSpResponse.getRequests().size();
                    for (int i = 0; i < sizeRequests; i++) {
                        dataList = new ArrayList<MenuData>();
                        MenuRequests requests = new MenuRequests();
                        requests.setCollectionId(menuSpResponse.getRequests().get(i).getCollectionId());
                        requests.setId(menuSpResponse.getRequests().get(i).getId());
                        requests.setName(menuSpResponse.getRequests().get(i).getName());
                        requests.setDescription(menuSpResponse.getRequests().get(i).getDescription());
                        requests.setUrl(menuSpResponse.getRequests().get(i).getUrl());
                        requests.setMethod(menuSpResponse.getRequests().get(i).getMethod());
                        requests.setHeaders(menuSpResponse.getRequests().get(i).getHeaders());

                        int sizeData = menuSpResponse.getRequests().get(i).getData().size();
                        for (int j=0; j < sizeData; j++){
                            MenuData data = new MenuData();
                            data.setKey(menuSpResponse.getRequests().get(i).getData().get(j).getKey());
                            data.setValue(menuSpResponse.getRequests().get(i).getData().get(j).getValue());
                            data.setType(menuSpResponse.getRequests().get(i).getData().get(j).getType());
                            dataList.add(data);
                            requests.setData(dataList);
                        }
                        requests.setDataMode(menuSpResponse.getRequests().get(i).getDataMode());
                        requests.setTimestamp(menuSpResponse.getRequests().get(i).getTimestamp());
                        requestsList.add(requests);
                        items.setRequests(requestsList);
                    }

                    listener.successMenu(items);
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    listener.errorMenu(retrofitError.getUrl().toString());
                }
            });
//        }
//        else{
//            System.out.println("Sin conexion a la red");
//        }
    }

    public interface menuBlListener{
        void errorMenu(String error);
        void successMenu(MenuItems items);
    }

    public void setListener(menuBlListener listener) {
        this.listener = listener;
    }
}
