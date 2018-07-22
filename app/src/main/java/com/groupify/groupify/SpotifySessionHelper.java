package com.groupify.groupify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.groupify.groupify.dto.GroupifyUser;
import com.groupify.groupify.dto.UserResponse;
import com.groupify.groupify.retrofit.RetrofitHelper;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotifySessionHelper {

	public static final int REQUEST_CODE = 12415;

	private static final String[] scopes = new String[]{"streaming", "user-library-read", "user-read-email", "user-read-private"};

	private static final Callback<ResponseBody> userPostCallback = new Callback<ResponseBody>() {
		@Override
		public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

		}

		@Override
		public void onFailure(Call<ResponseBody> call, Throwable t) {

		}
	};

	public static void spotifyLogin(Activity activity) {
		AuthenticationRequest request = new AuthenticationRequest.Builder(activity.getString(R.string.client_id), AuthenticationResponse.Type.TOKEN, buildUri(activity)).setScopes(scopes).build();
		AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request);
	}

	public static boolean loginFinished(Context context, LoginFinishedCallback loginFinishedCallback, int requestCode, int resultCode, Intent data) {
		if (requestCode == SpotifySessionHelper.REQUEST_CODE) {
			AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);
			if (response.getType() == AuthenticationResponse.Type.TOKEN) {
				Log.e("Token", response.getAccessToken());
				PreferenceManager.getDefaultSharedPreferences(context).edit().putString(context.getString(R.string.spotify_token_pref), response.getAccessToken()).apply();
				RetrofitHelper.getUserInformation(context, new UserInfoCallback(context));
				loginFinishedCallback.onFinished();
			}
			return true;
		}
		return false;
	}

	private static String buildUri(Context context) {
		return new Uri.Builder().scheme(context.getString(R.string.groupify_on_auth_host)).path(context.getString(R.string.groupify_on_auth_path)).build().toString();
	}

	private static class UserInfoCallback implements Callback<UserResponse> {

		private Context context;

		public UserInfoCallback(Context context) {
			this.context = context;
		}

		@Override
		public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
			GroupifyUser groupifyUser = new GroupifyUser(response.body().getUser());
			RetrofitHelper.postUser(groupifyUser, userPostCallback);
			Log.e("User ID", groupifyUser.getSpotifyId());
			PreferenceHelper.Companion.saveSpotifyUserId(context, groupifyUser.getSpotifyId());
		}

		@Override
		public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {

		}
	}

	public interface LoginFinishedCallback {
		void onFinished();
	}
}
