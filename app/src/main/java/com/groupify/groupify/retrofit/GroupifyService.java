package com.groupify.groupify.retrofit;

import com.groupify.groupify.dto.AlbumList;
import com.groupify.groupify.dto.AllGroupsResponse;
import com.groupify.groupify.dto.Group;
import com.groupify.groupify.dto.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GroupifyService {

	//USERS
	@FormUrlEncoded
	@POST("user")
	Call<ResponseBody> postUser(@Field("username") String username,
								@Field("displayName") String displayName,
								@Field("email") String email,
								@Field("spotifyUrl") String spotifyUrl,
								@Field("spotifyUri") String spotifyUri,
								@Field("spotifyId") String spotifyId);

	@GET("user/me")
	Call<UserResponse> getUserInfo(@Header("Authorization") String authToken);

	//GROUPS
	@GET("group")
	Call<AllGroupsResponse> getAllGroups();

	@GET("group/{id}")
	Call<AllGroupsResponse> getGroupsForUser(@Path("id") String userId);

	@GET("group")
	Call<Group> getGroup();

	@FormUrlEncoded
	@POST("group")
	Call<ResponseBody> createGroup(@Field("name") String groupName, @Field("user_id") String userId);

	//ALBUMS
	@GET("library/albums")
	Call<AlbumList> getAlbums(@Header("Authorization") String authToken);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimit(@Header("Authorization") String authToken, @Query("limit") int limit);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithOffset(@Header("Authorization") String authToken, @Query("offset") int offset);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimitAndOffset(@Header("Authorization") String authToken, @Query("limit") int limit, @Query("offset") int offset);

}
