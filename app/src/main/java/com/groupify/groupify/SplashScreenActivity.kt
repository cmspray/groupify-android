package com.groupify.groupify

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

class SplashScreenActivity : AppCompatActivity() {

    private val REQUEST_CODE = 12415

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.spotify_token_pref), null)?.let {
            goHome()
        } ?: requestLogin()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, data)
            if (response.type == AuthenticationResponse.Type.TOKEN) {
                PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
                    putString(getString(R.string.spotify_token_pref), response.accessToken)
                }.apply()
                goHome()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun goHome() = startActivity(Intent(this, HomeActivity::class.java)).apply { finish() }

    private fun requestLogin() = AuthenticationClient.openLoginActivity(this, REQUEST_CODE,
            AuthenticationRequest.Builder(getString(R.string.client_id), AuthenticationResponse.Type.TOKEN, buildUri()).apply {
                setScopes(arrayOf("streaming", "user-library-read", "user-read-email", "user-read-private"))
            }.build())

    private fun buildUri() = Uri.Builder().scheme(getString(R.string.groupify_on_auth_host)).path(getString(R.string.groupify_on_auth_path)).build().toString()
}
