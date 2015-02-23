package com.co.menu.restaurante.models;

import android.graphics.Bitmap;

public class CategoryItem implements Item{

    private String nameCategory;
    private String urlImage;
    private Bitmap image;
	
	public CategoryItem(String nameCategory, String urlImage) {
		this.nameCategory = nameCategory;
        this.urlImage = urlImage;
	}
	
	public String getNameCategory(){
		return nameCategory;
	}

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
	public boolean isSection() {
		return true;
	}

}
