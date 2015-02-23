package com.co.menu.restaurante.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by esandoval on 25/01/15.
 */
public class SubCategories implements Parcelable, Item{

    private String nameSubCategory;
    private String idSubCategory;

    public SubCategories() {
    }

    public SubCategories(String nameSubCategory, String idSubCategory){
        this.nameSubCategory = nameSubCategory;
        this.idSubCategory = idSubCategory;
    }

    public String getNameSubCategory() {
        return nameSubCategory;
    }

    public void setNameSubCategory(String nameSubCategory) {
        this.nameSubCategory = nameSubCategory;
    }

    public String getIdSubCategory() {
        return idSubCategory;
    }

    public void setIdSubCategory(String idSubCategory) {
        this.idSubCategory = idSubCategory;
    }

    public SubCategories(Parcel source){
        readFromParcel(source);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubCategories> CREATOR = new Creator<SubCategories>() {
        @Override
        public SubCategories createFromParcel(Parcel source) {
            return new SubCategories(source);
        }

        @Override
        public SubCategories[] newArray(int size) {
            return new SubCategories[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameSubCategory);
        dest.writeString(idSubCategory);
    }

    public void readFromParcel(Parcel source) {
        nameSubCategory = source.readString();
        idSubCategory = source.readString();
    }

    @Override
    public boolean isSection() {
        return false;
    }

}
