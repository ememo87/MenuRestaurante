package com.co.menu.restaurante.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by esandoval on 21/02/15.
 */
public class DetailsSpResponseSubcategory implements Parcelable{
    /*
        "id":"1",
        "name":"ANTIPASTI",
        "category_id":"1",
        "addition_enable":"1",
        "enabled":"1",
        "created_at":"0000-00-00 00:00:00",
        "updated_at":"0000-00-00 00:00:00",
        "items":
     */
    String id;
    String name;
    String category_id;
    String addition_enable;
    String enabled;
    String created_at;
    String updated_at;
    List<DetailsSpResponseItems> items;

    public DetailsSpResponseSubcategory() {
    }

    public DetailsSpResponseSubcategory(String id, String name, String category_id, String addition_enable, String enabled, String created_at, String updated_at, List<DetailsSpResponseItems> items) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.addition_enable = addition_enable;
        this.enabled = enabled;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.items = items;
    }

    public DetailsSpResponseSubcategory(Parcel obj) {
        this.id = obj.readString();
        this.name = obj.readString();
        this.category_id = obj.readString();
        this.addition_enable = obj.readString();
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
        dest.writeString(category_id);
        dest.writeString(addition_enable);
        dest.writeString(enabled);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeValue(items);
    }

    public static final Creator<DetailsSpResponseSubcategory> CREATOR = new Creator<DetailsSpResponseSubcategory>() {
        @Override
        public DetailsSpResponseSubcategory createFromParcel(Parcel source) {
            return new DetailsSpResponseSubcategory(source);
        }

        @Override
        public DetailsSpResponseSubcategory[] newArray(int size) {
            return new DetailsSpResponseSubcategory[size];
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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getAddition_enable() {
        return addition_enable;
    }

    public void setAddition_enable(String addition_enable) {
        this.addition_enable = addition_enable;
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

    public List<DetailsSpResponseItems> getItems() {
        return items;
    }

    public void setItems(List<DetailsSpResponseItems> items) {
        this.items = items;
    }
}
