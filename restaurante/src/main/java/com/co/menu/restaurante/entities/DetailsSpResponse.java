package com.co.menu.restaurante.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by esandoval on 21/02/15.
 */
public class DetailsSpResponse implements Parcelable{
    /*
        "id":"1",
        "name":"ANTIPASTI",
        "img_path":"img\/categories\/1.jpg",
        "type_id":"1",
        "enabled":"1",
        "created_at":"0000-00-00 00:00:00",
        "updated_at":"0000-00-00 00:00:00",
        "subcategory":
    */
    String id;
    String name;
    String img_path;
    String type_id;
    String enabled;
    String created_at;
    String updated_at;
    List<DetailsSpResponseSubcategory> subcategory;

    public DetailsSpResponse() {
    }

    public DetailsSpResponse(String id, String name, String img_path, String type_id, String enabled, String created_at, String updated_at, List<DetailsSpResponseSubcategory> subcategory) {
        this.id = id;
        this.name = name;
        this.img_path = img_path;
        this.type_id = type_id;
        this.enabled = enabled;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.subcategory = subcategory;
    }

    public DetailsSpResponse(Parcel obj) {
        this.id = obj.readString();
        this.name = obj.readString();
        this.img_path = obj.readString();
        this.type_id = obj.readString();
        this.enabled = obj.readString();
        this.created_at = obj.readString();
        this.updated_at = obj.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(img_path);
        dest.writeString(type_id);
        dest.writeString(enabled);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeValue(subcategory);
    }

    public static final Creator<DetailsSpResponse> CREATOR = new Creator<DetailsSpResponse>() {
        @Override
        public DetailsSpResponse createFromParcel(Parcel source) {
            return new DetailsSpResponse(source);
        }

        @Override
        public DetailsSpResponse[] newArray(int size) {
            return new DetailsSpResponse[size];
        }
    };

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

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<DetailsSpResponseSubcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<DetailsSpResponseSubcategory> subcategory) {
        this.subcategory = subcategory;
    }
}
