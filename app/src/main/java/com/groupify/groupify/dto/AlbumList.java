package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;

public class AlbumList {

	@Expose
	private AlbumUserData[] albums;

	public AlbumUserData[] getAlbums() {
		return albums;
	}

	public void setAlbums(AlbumUserData[] albums) {
		this.albums = albums;
	}
}
