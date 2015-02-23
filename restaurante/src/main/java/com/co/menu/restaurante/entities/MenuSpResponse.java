package com.co.menu.restaurante.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esandoval on 21/02/15.
 */
public class MenuSpResponse implements Parcelable {

    String id;
    String name;
    Long timestamp;
    List<MenuSpResponseRequests> requests;

    public MenuSpResponse(String id, String name, Long timestamp, List<MenuSpResponseRequests> requests) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.requests = requests;
    }


    public List<MenuSpResponseRequests> getRequests() {
        return requests;
    }

    public void setRequests(List<MenuSpResponseRequests> requests) {
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

    public MenuSpResponse() {

    }

    private MenuSpResponse(Parcel obj){
        id= obj.readString();
        name= obj.readString();
        timestamp= obj.readLong();
        requests = (List<MenuSpResponseRequests>) obj.readValue(MenuSpResponseRequests.class.getClassLoader());
    }

    public static final Creator<MenuSpResponse> CREATOR = new Creator<MenuSpResponse>() {
        @Override
        public MenuSpResponse createFromParcel(Parcel source) {
            return new MenuSpResponse(source);
        }

        @Override
        public MenuSpResponse[] newArray(int size) {
            return new MenuSpResponse[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeLong(timestamp);
        dest.writeValue(requests);

    }
}
