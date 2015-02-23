package com.co.menu.restaurante.models;

import com.co.menu.restaurante.entities.MenuSpResponseData;
import com.co.menu.restaurante.entities.MenuSpResponseRequests;

import java.util.List;

/**
 * Created by esandoval on 21/02/15.
 */
public class MenuItems {
    String id;
    String name;
    Long timestamp;
    List<MenuRequests> requests;
    //List<MenuData> data;

    public MenuItems() {
    }

    public MenuItems(String id, String name, Long timestamp, List<MenuRequests> requests) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.requests = requests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<MenuRequests> getRequests() {
        return requests;
    }

    public void setRequests(List<MenuRequests> requests) {
        this.requests = requests;
    }

//    public List<MenuData> getData() {
//        return data;
//    }
//
//    public void setData(List<MenuData> data) {
//        this.data = data;
//    }
}
