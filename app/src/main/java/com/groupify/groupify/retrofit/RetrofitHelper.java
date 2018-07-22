package com.groupify.groupify.retrofit;


import android.content.Context;
import android.util.Log;

import com.groupify.groupify.PreferenceHelper;
import com.groupify.groupify.dto.AlbumList;
import com.groupify.groupify.dto.AllGroupsResponse;
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
		groupifyService.getUserInfo(PreferenceHelper.Companion.getAuth(context)).
				enqueue(callback);
	}

	public static void postUser(GroupifyUser groupifyUser, Callback<ResponseBody> postUserResponse) {
		groupifyService.postUser(groupifyUser.getUsername(),
				groupifyUser.getDisplayName(),
				groupifyUser.getEmail(),
				groupifyUser.getSpotifyUrl(),
				groupifyUser.getSpotifyUri(),
				groupifyUser.getSpotifyId()).enqueue(postUserResponse);
	}

	public static void addGroup(Context context, String groupName) {
		groupifyService.createGroup(new GroupRequest(groupName, PreferenceHelper.Companion.getSpotifyUserId(context))).enqueue(new Callback<ResponseBody>() {
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
		groupifyService.getGroupsForUser(PreferenceHelper.Companion.getSpotifyUserId(context)).enqueue(groups);
	}
}