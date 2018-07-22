package com.groupify.groupify;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.groupify.groupify.dto.CreateUserResponse;
import com.groupify.groupify.dto.GroupifyUser;
import com.groupify.groupify.dto.UserResponse;
import com.groupify.groupify.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupifyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		if(!PreferenceHelper.Companion.getAuthToken(this).equals("")) {
			RetrofitHelper.getUser(this, updateUserResponse);
		}
	}

	public final Callback<UserResponse> userInfoCallback = new Callback<UserResponse>() {
		@Override
		public void onResponse(@NonNull Call<UserResponse> call, Response<UserResponse> response) {
			GroupifyUser groupifyUser = new GroupifyUser(response.body().getUser());
			RetrofitHelper.postUser(GroupifyApplication.this, groupifyUser, createUserCallback);
			Log.e("spotify_2 ID", groupifyUser.getSpotifyId());
			PreferenceHelper.Companion.saveSpotifyUserId(GroupifyApplication.this, groupifyUser.getSpotifyId());
		}

		@Override
		public void onFailure(Call<UserResponse> call, Throwable t) {
			Log.e("Failure", "huh");
		}
	};

	public final Callback<UserResponse> updateUserResponse = new Callback<UserResponse>() {
		@Override
		public void onResponse(@NonNull Call<UserResponse> call, Response<UserResponse> response) {
			PreferenceHelper.Companion.saveUserId(GroupifyApplication.this, response.body().getUser().getUserId());
		}

		@Override
		public void onFailure(Call<UserResponse> call, Throwable t) {
			Log.e("Failure", "huh");
		}
	};

	public final Callback<CreateUserResponse> createUserCallback = new Callback<CreateUserResponse>() {
		@Override
		public void onResponse(@NonNull Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
			Log.e("User ID", "" + response.body().getId());
			PreferenceHelper.Companion.saveUserId(GroupifyApplication.this, response.body().getId());
			RetrofitHelper.getUser(GroupifyApplication.this, updateUserResponse);
		}

		@Override
		public void onFailure(@NonNull Call<CreateUserResponse> call, @NonNull Throwable t) {

		}
	};
}
