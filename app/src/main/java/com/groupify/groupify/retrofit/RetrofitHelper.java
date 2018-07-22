package com.groupify.groupify.retrofit;


import android.content.Context;
import android.preference.PreferenceManager;

import com.groupify.groupify.PreferenceHelper;
import com.groupify.groupify.R;
import com.groupify.groupify.dto.AlbumList;
import com.groupify.groupify.dto.AllGroupsResponse;
import com.groupify.groupify.dto.GroupifyUser;
import com.groupify.groupify.dto.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Callback;
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
		groupifyService.createGroup(groupName, PreferenceHelper.Companion.getUserId(context));
	}

	public static void getGroups(Context context, Callback<AllGroupsResponse> groups) {
		groupifyService.getGroupsForUser(PreferenceHelper.Companion.getUserId(context)).enqueue(groups);
	}
}