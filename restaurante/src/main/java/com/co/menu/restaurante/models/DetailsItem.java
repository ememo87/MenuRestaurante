package com.co.menu.restaurante.models;

import android.graphics.Bitmap;

/**
 * Created by esandoval on 22/02/15.
 */
public class DetailsItem {

    private String name_details;
    private String description;
    private String urlImage_items;
    private String urlImage_left;
    private String urlImage_right;
    private Bitmap bitmapItems;
    private Bitmap bitmapLeft;
    private Bitmap bitmapRight;

    public DetailsItem() {
    }

    public DetailsItem(String name_details, String description, String urlImage_items, String urlImage_left, String urlImage_right) {
        this.name_details = name_details;
        this.description = description;
        this.urlImage_items = urlImage_items;
        this.urlImage_left = urlImage_left;
        this.urlImage_right = urlImage_right;
    }

    public String getName_details() {
        return name_details;
    }

    public void setName_details(String name_details) {
        this.name_details = name_details;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage_items() {
        return urlImage_items;
    }

    public void setUrlImage_items(String urlImage_items) {
        this.urlImage_items = urlImage_items;
    }

    public String getUrlImage_left() {
        return urlImage_left;
    }

    public void setUrlImage_left(String urlImage_left) {
        this.urlImage_left = urlImage_left;
    }

    public String getUrlImage_right() {
        return urlImage_right;
    }

    public void setUrlImage_right(String urlImage_right) {
        this.urlImage_right = urlImage_right;
    }

    public Bitmap getBitmapItems() {
        return bitmapItems;
    }

    public void setBitmapItems(Bitmap bitmapItems) {
        this.bitmapItems = bitmapItems;
    }

    public Bitmap getBitmapLeft() {
        return bitmapLeft;
    }

    public void setBitmapLeft(Bitmap bitmapLeft) {
        this.bitmapLeft = bitmapLeft;
    }

    public Bitmap getBitmapRight() {
        return bitmapRight;
    }

    public void setBitmapRight(Bitmap bitmapRight) {
        this.bitmapRight = bitmapRight;
    }
}
