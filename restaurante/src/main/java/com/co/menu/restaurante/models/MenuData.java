package com.co.menu.restaurante.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by esandoval on 21/02/15.
 */
public class MenuData implements Parcelable{

    String key;
    String value;
    String type;

    public MenuData() {
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

    private MenuData(Parcel obj){
        key= obj.readString();
        value= obj.readString();
        type= obj.readString();
    }

    public static final Creator<MenuData> CREATOR = new Creator<MenuData>() {
        @Override
        public MenuData createFromParcel(Parcel source) {
            return new MenuData(source);
        }

        @Override
        public MenuData[] newArray(int size) {
            return new MenuData[size];
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
