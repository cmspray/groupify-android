package com.groupify.groupify.retrofit;

import com.groupify.groupify.dto.AlbumList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GroupifyService {

	@GET("library/albums")
	Call<AlbumList> getAlbums(@Header("Authorization") String authToken);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimit(@Header("Authorization") String authToken, @Query("limit") int limit);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithOffset(@Header("Authorization") String authToken, @Query("offset") int offset);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimitAndOffset(@Header("Authorization") String authToken, @Query("limit") int limit, @Query("offset") int offset);

}
