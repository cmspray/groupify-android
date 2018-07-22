package com.groupify.groupify

import android.content.Context
import android.preference.PreferenceManager

class PreferenceHelper {

    companion object {
        fun getDefaultPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)
        fun getUserId(context: Context) = getDefaultPreferences(context).getString(context.getString(R.string.spotify_user_id_pref), "")
        fun getAuthToken(context: Context) = getDefaultPreferences(context).getString(context.getString(R.string.spotify_token_pref), "")
        fun getAuth(context: Context) = String.format("Bearer %s", getAuthToken(context))
        fun saveUserId(context: Context, userId: String) = getDefaultPreferences(context).edit().putString(context.getString(R.string.spotify_user_id_pref), userId).apply()
    }
}