package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupRequest {

	@SerializedName("spotify_id")
	@Expose
	private String spotifyId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("user_id")
	@Expose
	private int id;

	public GroupRequest(String name, String spotifyId, int id) {
		this.spotifyId = spotifyId;
		this.name = name;
		this.id = id;
	}

	public String getSpotifyId() {
		return spotifyId;
	}

	public void setSpotifyId(String id) {
		this.spotifyId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
