package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupRequest {

	@SerializedName("spotify_user_id")
	@Expose
	private String id;
	@SerializedName("name")
	@Expose
	private String name;

	public GroupRequest(String name, String id) {
		this.id = id;
		this.name = name;
	}

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
}
