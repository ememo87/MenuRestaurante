package com.co.menu.restaurante.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by esandoval on 22/02/15.
 */
public class CategoriesSp implements Parcelable{
    List<CategoriesSpResponse> Response;

    public CategoriesSp() {
    }

    public CategoriesSp(List<CategoriesSpResponse> response) {
        Response = response;
    }

    public CategoriesSp(Parcel obj) {
        Response = (List<CategoriesSpResponse>) obj.readValue(CategoriesSpResponse.class.getClassLoader());
    }

    public static final Parcelable.Creator<CategoriesSp> CREATOR = new Parcelable.Creator<CategoriesSp>() {
        @Override
        public CategoriesSp createFromParcel(Parcel source) {
            return new CategoriesSp(source);
        }

        @Override
        public CategoriesSp[] newArray(int size) {
            return new CategoriesSp[size];
        }
    };

    public List<CategoriesSpResponse> getResponse() {
        return Response;
    }

    public void setResponse(List<CategoriesSpResponse> response) {
        Response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Response);
    }
}
