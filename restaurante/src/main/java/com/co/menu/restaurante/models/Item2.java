package com.co.menu.restaurante.models;

public class Item2 {

	private String mTitle;
	private int mIconRes;

	public Item2(String title, int iconRes) {
		setmTitle(title);
		setmIconRes(iconRes);
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public int getmIconRes() {
		return mIconRes;
	}

	public void setmIconRes(int mIconRes) {
		this.mIconRes = mIconRes;
	}
}