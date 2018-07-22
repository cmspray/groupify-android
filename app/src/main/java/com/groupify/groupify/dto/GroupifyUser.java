package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.groupify.groupify.R;

public class GroupifyUser {

	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("displayName")
	@Expose
	private String displayName;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("spotifyUrl")
	@Expose
	private String spotifyUrl;
	@SerializedName("spotifyUri")
	@Expose
	private String spotifyUri;
	@SerializedName("spotifyId")
	@Expose
	private String spotifyId;

	public GroupifyUser() {

	}

	public GroupifyUser(User user) {
		this.displayName = user.getDisplayName();
		this.username = user.getEmail();
		this.email = user.getEmail();
		this.spotifyId = user.getId();
		this.setSpotifyUrl(buildSpotifyUrl(user.getId()));
		this.setSpotifyUri(buildSpotifyUri(user.getId()));
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

	private String buildSpotifyUrl(String id) {
		return String.format("https://open.spotify.com/user/%s", id);
	}

	private String buildSpotifyUri(String id) {
		return String.format("spotify:user:%s", id);
	}

}