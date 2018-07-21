package com.groupify.groupify

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager

class SplashScreenActivity : AppCompatActivity(), SpotifySessionHelper.LoginFinishedCallback {

    override fun onFinished() = goHome()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.spotify_token_pref), null)?.let {
            goHome()
        } ?: SpotifySessionHelper.spotifyLogin(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!SpotifySessionHelper.loginFinished(this, this, requestCode, resultCode, data)){
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun goHome() = startActivity(Intent(this, HomeActivity::class.java)).apply { finish() }

}
