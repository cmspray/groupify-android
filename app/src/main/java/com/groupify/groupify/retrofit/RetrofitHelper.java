package com.groupify.groupify.retrofit;


import android.content.Context;
import android.util.Log;

import com.groupify.groupify.PreferenceHelper;
import com.groupify.groupify.dto.AlbumList;
import com.groupify.groupify.dto.AllGroupsResponse;
import com.groupify.groupify.dto.CreateUserResponse;
import com.groupify.groupify.dto.Group;
import com.groupify.groupify.dto.GroupRequest;
import com.groupify.groupify.dto.GroupifyUser;
import com.groupify.groupify.dto.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHelper {

	private static final Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://groupify-dev.herokuapp.com")
			.addConverterFactory(GsonConverterFactory.create())
			.build();

	private static final GroupifyService groupifyService = retrofit.create(GroupifyService.class);

	private RetrofitHelper() {

	}

	public static void getAlbums(Context context, Callback<AlbumList> callback) {
		groupifyService.getAlbums(PreferenceHelper.Companion.getAuth(context)).
				enqueue(callback);
	}

	public static void getUserInformation(Context context, Callback<UserResponse> callback) {
		groupifyService.getUserInfo(PreferenceHelper.Companion.getAuth(context)).	enqueue(callback);
	}

	public static void postUser(Context context, GroupifyUser groupifyUser, Callback<CreateUserResponse> postUserResponse) {
		groupifyService.postUser(PreferenceHelper.Companion.getAuth(context), groupifyUser).enqueue(postUserResponse);
	}

	public static void addGroup(Context context, String groupName) {
		groupifyService.createGroup(PreferenceHelper.Companion.getAuth(context), new GroupRequest(groupName, PreferenceHelper.Companion.getSpotifyUserId(context), PreferenceHelper.Companion.getUserId(context))).enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				Log.e("Add Group Response", "" + response.isSuccessful());
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("Add Group fail", "" + t.getMessage());
			}
		});
	}

	public static void getGroups(Context context, Callback<AllGroupsResponse> groups) {
		groupifyService.getAllGroups(PreferenceHelper.Companion.getAuth(context)).enqueue(groups);
	}

	public static void getGroupById(Context context, int groupId, Callback<Group> callback) {
		groupifyService.getGroupById(PreferenceHelper.Companion.getAuth(context), groupId).enqueue(callback);
	}

	public static void getPlaylist(Context context, String playlistId, Callback<ResponseBody> callback) {
		groupifyService.getPlaylistById(PreferenceHelper.Companion.getAuth(context), playlistId, PreferenceHelper.Companion.getSpotifyUserId(context)).enqueue(callback);
	}
}