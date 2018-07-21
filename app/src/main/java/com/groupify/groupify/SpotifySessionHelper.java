package com.groupify.groupify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.groupify.groupify.dto.UserResponse;
import com.groupify.groupify.retrofit.RetrofitHelper;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpotifySessionHelper {

	public static final int REQUEST_CODE = 12415;

	private static final String[] scopes = new String[]{"streaming", "user-library-read", "user-read-email", "user-read-private"};
	private static final Callback<UserResponse> userInfoCallback = new Callback<UserResponse>() {
		@Override
		public void onResponse(Call<UserResponse> all, Response<UserResponse> response) {
			//todo post user info
		}

		@Override
		public void onFailure(Call<UserResponse> call, Throwable t) {

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
				PreferenceManager.getDefaultSharedPreferences(context).edit().putString(context.getString(R.string.spotify_token_pref), response.getAccessToken()).apply();
				RetrofitHelper.getUserInformation(context, userInfoCallback);
				loginFinishedCallback.onFinished();
			}
			return true;
		}
		return false;
	}

	private static String buildUri(Context context) {
		return new Uri.Builder().scheme(context.getString(R.string.groupify_on_auth_host)).path(context.getString(R.string.groupify_on_auth_path)).build().toString();
	}

	public interface LoginFinishedCallback {
		void onFinished();
	}
}