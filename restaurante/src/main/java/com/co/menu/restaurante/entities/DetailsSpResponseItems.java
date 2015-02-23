package com.co.menu.restaurante.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by esandoval on 21/02/15.
 */
public class DetailsSpResponseItems implements Parcelable{
    /*
    "id":"1",
    "name":"ANTIPASTO ARCHIE'S\u00ae",
    "description":"Encurtidos de berenjenas, piment\u00f3n, zucchini y tomates secos; esp\u00e1rragos, r\u00fagula, mozzarellinas, prosciutto y mini baguette. Acompa\u00f1ado de pesto y focaccia crocante",
    "img_path":"img\/items\/1.png",
    "left_img_path":"img\/items\/left\/no_pic.jpg",
    "right_img_path":"img\/items\/right\/no_pic.jpg",
    "subcategory_id":"1",
    "enabled":"1",
    "created_at":"0000-00-00 00:00:00",
    "updated_at":"2015-02-13 10:55:43"
    */
    String id;
    String name;
    String description;
    String img_path;
    String left_img_path;
    String right_img_path;
    String subcategory_id;
    String enabled;
    String created_at;
    String updated_at;

    public DetailsSpResponseItems() {
    }

    public DetailsSpResponseItems(String id, String name, String description, String img_path, String left_img_path, String right_img_path, String subcategory_id, String enabled, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_path = img_path;
        this.left_img_path = left_img_path;
        this.right_img_path = right_img_path;
        this.subcategory_id = subcategory_id;
        this.enabled = enabled;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public DetailsSpResponseItems(Parcel obj) {
        this.id = obj.readString();
        this.name = obj.readString();
        this.description = obj.readString();
        this.img_path = obj.readString();
        this.left_img_path = obj.readString();
        this.right_img_path = obj.readString();
        this.subcategory_id = obj.readString();
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
        dest.writeString(description);
        dest.writeString(img_path);
        dest.writeString(left_img_path);
        dest.writeString(right_img_path);
        dest.writeString(subcategory_id);
        dest.writeString(enabled);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }

    public static final Parcelable.Creator<DetailsSpResponseItems> CREATOR = new Parcelable.Creator<DetailsSpResponseItems>() {
        @Override
        public DetailsSpResponseItems createFromParcel(Parcel source) {
            return new DetailsSpResponseItems(source);
        }

        @Override
        public DetailsSpResponseItems[] newArray(int size) {
            return new DetailsSpResponseItems[size];
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getLeft_img_path() {
        return left_img_path;
    }

    public void setLeft_img_path(String left_img_path) {
        this.left_img_path = left_img_path;
    }

    public String getRight_img_path() {
        return right_img_path;
    }

    public void setRight_img_path(String right_img_path) {
        this.right_img_path = right_img_path;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
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
}
