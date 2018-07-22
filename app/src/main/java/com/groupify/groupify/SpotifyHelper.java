package com.groupify.groupify;

import android.content.Context;
import android.preference.PreferenceManager;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

public class SpotifyHelper {

	private static Config playerConfig;
	private static SpotifyPlayer player;

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
				player.addConnectionStateCallback(connectionCallback);
				player.addNotificationCallback(notificationCallback);
				SpotifyHelper.player = player;
			}

			@Override
			public void onError(Throwable throwable) {

			}
		});
	}

}
