package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUserResponse {

	@SerializedName("created_at")
	@Expose
	private String createdAt;
	@SerializedName("updated_at")
	@Expose
	private String updatedAt;
	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("display_name")
	@Expose
	private String displayName;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("spotify_url")
	@Expose
	private String spotifyUrl;
	@SerializedName("spotify_uri")
	@Expose
	private String spotifyUri;
	@SerializedName("spotify_id")
	@Expose
	private String spotifyId;

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpotifyUrl() {
		return spotifyUrl;
	}

	public void setSpotifyUrl(String spotifyUrl) {
		this.spotifyUrl = spotifyUrl;
	}

	public String getSpotifyUri() {
		return spotifyUri;
	}

	public void setSpotifyUri(String spotifyUri) {
		this.spotifyUri = spotifyUri;
	}

	public String getSpotifyId() {
		return spotifyId;
	}

	public void setSpotifyId(String spotifyId) {
		this.spotifyId = spotifyId;
	}
}