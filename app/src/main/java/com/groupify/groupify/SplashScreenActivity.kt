package com.groupify.groupify

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import com.groupify.groupify.onboarding.OnBoardingActivity

class SplashScreenActivity : AppCompatActivity(), SpotifySessionHelper.LoginFinishedCallback {

    override fun onFinished() = goHome()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen)
        PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.spotify_token_pref), null)?.let {
            Log.e("Token", it)
            Log.e("Id", PreferenceHelper.getSpotifyUserId(this))
            goHome()
        } ?: SpotifySessionHelper.spotifyLogin(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!SpotifySessionHelper.loginFinished(this, this, requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun goHome() {
        if(PreferenceHelper.hasSeenOnBoarding(this))
            startActivity(Intent(this, HomeActivity::class.java))
        else
            startActivity(Intent(this, OnBoardingActivity::class.java))
        finish()
    }

}
