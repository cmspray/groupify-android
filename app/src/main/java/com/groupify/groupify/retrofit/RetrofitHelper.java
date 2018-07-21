package com.groupify.groupify.retrofit;


import android.content.Context;
import android.preference.PreferenceManager;

import com.groupify.groupify.R;
import com.groupify.groupify.dto.AlbumList;

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
		groupifyService.getAlbums(String.format("Bearer %s",
				PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.spotify_token_pref), ""))).
				enqueue(callback);
	}
}
