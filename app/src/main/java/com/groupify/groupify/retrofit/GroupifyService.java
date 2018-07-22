package com.groupify.groupify.retrofit;

import com.groupify.groupify.dto.AddToGroupRequest;
import com.groupify.groupify.dto.AlbumList;
import com.groupify.groupify.dto.AllGroupsResponse;
import com.groupify.groupify.dto.CreateUserResponse;
import com.groupify.groupify.dto.Group;
import com.groupify.groupify.dto.GroupRequest;
import com.groupify.groupify.dto.GroupifyUser;
import com.groupify.groupify.dto.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GroupifyService {

	//USERS
	@POST("user")
	Call<CreateUserResponse> postUser(@Header("Authorization") String authToken, @Body GroupifyUser groupifyUser);

	@GET("user/{spotifyId}")
	Call<UserResponse> getUser(@Header("Authorization") String authToken, @Path("spotifyId") String spotifyId);

	@GET("user/me")
	Call<UserResponse> getUserInfo(@Header("Authorization") String authToken);

	//GROUPS
	@GET("group")
	Call<AllGroupsResponse> getAllGroups(@Header("Authorization") String authToken);

	@GET("group/{id}")
	Call<Group> getGroupById(@Header("Authorization") String authToken, @Path("id") String groupId);

	@POST("group/{id}/member")
	Call<ResponseBody> addUserToGroup(@Header("Authorization") String authToken, @Path("id") String groupId, @Body AddToGroupRequest addToGroupRequest);

	@GET("group")
	Call<Group> getGroup();

	@POST("group")
	Call<ResponseBody> createGroup(@Header("Authorization") String authToken, @Body GroupRequest groupRequest);

	//ALBUMS
	@GET("library/albums")
	Call<AlbumList> getAlbums(@Header("Authorization") String authToken);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimit(@Header("Authorization") String authToken, @Query("limit") int limit);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithOffset(@Header("Authorization") String authToken, @Query("offset") int offset);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimitAndOffset(@Header("Authorization") String authToken, @Query("limit") int limit, @Query("offset") int offset);

	//PLAYLISTS
	@GET("playlist/{playlistId}/user/{userId}")
	Call<ResponseBody> getPlaylistById(@Header("Authorization") String authToken, @Path("playlistId") String playlistId,  @Path("userId") String userId);

}
