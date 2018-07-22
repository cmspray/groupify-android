package com.groupify.groupify.retrofit;

import com.groupify.groupify.dto.AddToGroupRequest;
import com.groupify.groupify.dto.AlbumList;
import com.groupify.groupify.dto.AllGroupsResponse;
import com.groupify.groupify.dto.CreateUserResponse;
import com.groupify.groupify.dto.Group;
import com.groupify.groupify.dto.GroupRequest;
import com.groupify.groupify.dto.GroupifyUser;
import com.groupify.groupify.dto.PutUserRequest;
import com.groupify.groupify.dto.User;
import com.groupify.groupify.dto.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GroupifyService {

	//USERS
	@POST("user")
	Call<CreateUserResponse> postUser(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken, @Body GroupifyUser groupifyUser);

	@GET("user/{spotifyId}")
	Call<UserResponse> getUser(@Header("X-Spotify-ID") String spotifyIdHeader, @Header("Authorization") String authToken, @Path("spotifyId") String spotifyId);

	@GET("user/me")
	Call<UserResponse> 	getUserInfo(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken);

	@PUT("user/{id}")
	Call<ResponseBody> putUser(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken,@Path("id") int userId, @Body PutUserRequest groupifyUser);

	//GROUPS
	@GET("group")
	Call<AllGroupsResponse> getAllGroups(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken);

	@GET("group/{id}")
	Call<Group> getGroupById(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken, @Path("id") String groupId);

	@POST("group/{id}/member")
	Call<ResponseBody> addUserToGroup(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken, @Path("id") String groupId, @Body AddToGroupRequest addToGroupRequest);

	@GET("group")
	Call<Group> getGroup();

	@POST("group")
	Call<ResponseBody> createGroup(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken, @Body GroupRequest groupRequest);

	//ALBUMS
	@GET("library/albums")
	Call<AlbumList> getAlbums(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimit(@Header("Authorization") String authToken, @Query("limit") int limit);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithOffset(@Header("Authorization") String authToken, @Query("offset") int offset);

	@GET("library/albums")
	Call<AlbumList> getAlbumsWithLimitAndOffset(@Header("Authorization") String authToken, @Query("limit") int limit, @Query("offset") int offset);

	//PLAYLISTS
	@GET("playlist/{playlistId}/user/{userId}")
	Call<ResponseBody> getPlaylistById(@Header("X-Spotify-ID") String spotifyId, @Header("Authorization") String authToken, @Path("playlistId") String playlistId,  @Path("userId") String userId);

}
