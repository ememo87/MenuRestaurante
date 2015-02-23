package com.co.menu.restaurante.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by esandoval on 21/02/15.
 */
public class MenuSpResponseData implements Parcelable{

    String key;
    String value;
    String type;

    public MenuSpResponseData() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private MenuSpResponseData(Parcel obj){
        key= obj.readString();
        value= obj.readString();
        type= obj.readString();
    }

    public static final Creator<MenuSpResponseData> CREATOR = new Creator<MenuSpResponseData>() {
        @Override
        public MenuSpResponseData createFromParcel(Parcel source) {
            return new MenuSpResponseData(source);
        }

        @Override
        public MenuSpResponseData[] newArray(int size) {
            return new MenuSpResponseData[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(value);
        dest.writeString(type);
    }
}
