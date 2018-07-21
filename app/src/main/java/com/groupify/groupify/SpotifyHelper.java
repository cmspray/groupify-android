package com.groupify.groupify;

import android.content.Context;
import android.preference.PreferenceManager;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

public class SpotifyHelper {

	private Config playerConfig;
	private SpotifyPlayer player;

	public void init(Context context, final ConnectionStateCallback connectionCallback, final Player.NotificationCallback notificationCallback) {
		playerConfig = new Config(context, PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.spotify_token_pref), ""), context.getString(R.string.client_id));
		Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {

			@Override
			public void onInitialized(SpotifyPlayer spotifyPlayer) {
				player.addConnectionStateCallback(connectionCallback);
				player.addNotificationCallback(notificationCallback);
				SpotifyHelper.this.player = player;
			}

			@Override
			public void onError(Throwable throwable) {

			}
		});
	}

	public Config getPlayerConfig() {
		return playerConfig;
	}

	public void setPlayerConfig(Config playerConfig) {
		this.playerConfig = playerConfig;
	}

	public SpotifyPlayer getPlayer() {
		return player;
	}

	public void setPlayer(SpotifyPlayer player) {
		this.player = player;
	}
}