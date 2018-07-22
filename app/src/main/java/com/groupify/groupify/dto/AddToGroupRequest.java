package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToGroupRequest {

	@SerializedName("user_id")
	@Expose
	private int id;

	public AddToGroupRequest() {

	}

	public AddToGroupRequest(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
