package com.groupify.groupify

import android.content.Context
import android.preference.PreferenceManager

class PreferenceHelper {

    companion object {
        fun getDefaultPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)
        //UserId
        fun getSpotifyUserId(context: Context) = getDefaultPreferences(context).getString(context.getString(R.string.spotify_user_id_pref), "")
        fun saveSpotifyUserId(context: Context, userId: String) = getDefaultPreferences(context).edit().putString(context.getString(R.string.spotify_user_id_pref), userId).apply()
        fun getUserId(context: Context) = getDefaultPreferences(context).getInt(context.getString(R.string.user_id_pref), -1)
        fun saveUserId(context: Context, userId: Int) = getDefaultPreferences(context).edit().putInt(context.getString(R.string.user_id_pref), userId).apply()
        //Auth
        fun getAuthToken(context: Context) = getDefaultPreferences(context).getString(context.getString(R.string.spotify_token_pref), "")
        fun getAuth(context: Context) = String.format("Bearer %s", getAuthToken(context))
        //OnBoarding
        fun setHasSeenOnBoarding(context: Context) = getDefaultPreferences(context).edit().putBoolean(context.getString(R.string.spotify_onboard_pref), true).apply()
        fun hasSeenOnBoarding(context: Context) = getDefaultPreferences(context).getBoolean(context.getString(R.string.spotify_onboard_pref), false)
    }
}