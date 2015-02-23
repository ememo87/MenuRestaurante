package com.co.menu.restaurante.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.co.menu.restaurante.entities.MenuSpResponseData;

import java.util.List;

/**
 * Created by esandoval on 21/02/15.
 */
public class MenuRequests implements Parcelable {

    String collectionId;
    String id;
    String name;
    String description;
    String url;
    String method;
    String headers;
    List<MenuData> data;
    //MenuSpResponseData data;
    String dataMode;
    Long timestamp;
    Long version;

    public MenuRequests() {
    }

    public List<MenuData> getData() {
        return data;
    }

    public void setData(List<MenuData> data) {
        this.data = data;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

//    public MenuSpResponseData getData() {
//        return data;
//    }
//
//    public void setData(MenuSpResponseData data) {
//        this.data = data;
//    }

    public String getDataMode() {
        return dataMode;
    }

    public void setDataMode(String dataMode) {
        this.dataMode = dataMode;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }




    private MenuRequests(Parcel obj) {
        collectionId= obj.readString();
        id= obj.readString();
        name= obj.readString();
        description= obj.readString();
        url= obj.readString();
        method= obj.readString();
        headers= obj.readString();
        //data = (MenuData) obj.readValue(MenuData.class.getClassLoader());
        dataMode= obj.readString();
        timestamp= obj.readLong();
        version= obj.readLong();

    }

    public static final Creator<MenuRequests> CREATOR = new Creator<MenuRequests>() {
        public MenuRequests createFromParcel(Parcel in) {
            return new MenuRequests(in);
        }

        public MenuRequests[] newArray(int size) {
            return new MenuRequests[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(collectionId);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(method);
        dest.writeString(headers);
        dest.writeValue(data);
        dest.writeString(dataMode);
        dest.writeLong(timestamp);
        dest.writeLong(version);

    }
}
