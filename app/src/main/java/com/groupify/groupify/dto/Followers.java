package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Followers {

	@SerializedName("href")
	@Expose
	private Object href;
	@SerializedName("total")
	@Expose
	private int total;

	public Object getHref() {
		return href;
	}

	public void setHref(Object href) {
		this.href = href;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
