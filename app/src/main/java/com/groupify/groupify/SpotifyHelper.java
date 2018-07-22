package com.groupify.groupify;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

public class SpotifyHelper {

	public static Config playerConfig;
	public static SpotifyPlayer player;

	public void initConfig(Context context, final ConnectionStateCallback connectionCallback, final Player.NotificationCallback notificationCallback) {
		if(null == player) {
			playerConfig = new Config(context, PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.spotify_token_pref), ""), context.getString(R.string.client_id));
			initPlayer(connectionCallback, notificationCallback);
		}
	}

	public void initPlayer(final ConnectionStateCallback connectionCallback, final Player.NotificationCallback notificationCallback) {
		player  = Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
			@Override
			public void onInitialized(SpotifyPlayer spotifyPlayer) {
				spotifyPlayer.addConnectionStateCallback(connectionCallback);
				spotifyPlayer.addNotificationCallback(notificationCallback);
			}

			@Override
			public void onError(Throwable throwable) {
				Log.e("Couln't init", throwable.getMessage());
			}
		});
	}

}
