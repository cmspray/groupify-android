package com.groupify.groupify

import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.text.TextUtils.replace
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import com.groupify.groupify.dto.Group
import com.groupify.groupify.groupdetails.GroupDetailsFragment
import com.groupify.groupify.groups.GroupListAdapter
import com.groupify.groupify.groups.GroupsFragment
import com.groupify.groupify.retrofit.RetrofitHelper
import com.groupify.groupify.retrofit.RetrofitHelper.addGroup
import com.spotify.sdk.android.player.Error
import com.spotify.sdk.android.player.Player
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), GroupListAdapter.GroupClickCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN, SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_main)

        val lastPathSegment = intent.data?.lastPathSegment
        if (null != lastPathSegment) {
            RetrofitHelper.getGroupById(this, lastPathSegment, object : Callback<Group> {

                override fun onResponse(call: Call<Group>?, response: Response<Group>?) {
                    AlertDialog.Builder(this@HomeActivity).setTitle(response!!.body()!!.name).setMessage(getString(R.string.share_message, response!!.body()!!.name)).setNegativeButton(android.R.string.no, { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()
                    }).setPositiveButton(android.R.string.yes, { dialog, which -> addGroup(lastPathSegment) }).create().show()
                }

                override fun onFailure(call: Call<Group>?, t: Throwable?) {
                }

            })
        }
        findViewById<View>(R.id.action_profile).setOnClickListener { goToProfile() }
        showGroupList(supportFragmentManager.beginTransaction().addToBackStack("list"))
        findViewById<View>(R.id.home).setOnClickListener { stopSong() }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val lastPathSegment = intent.data?.lastPathSegment
        if (null != lastPathSegment) {
            RetrofitHelper.getGroupById(this, lastPathSegment, object : Callback<Group> {

                override fun onResponse(call: Call<Group>?, response: Response<Group>?) {
                    AlertDialog.Builder(this@HomeActivity).setTitle(response!!.body()!!.name).setMessage(getString(R.string.share_message, response!!.body()!!.name)).setNegativeButton(android.R.string.no, { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()
                    }).setPositiveButton(android.R.string.yes, { dialog, which -> addGroup(lastPathSegment) }).create().show()
                }

                override fun onFailure(call: Call<Group>?, t: Throwable?) {
                }

            })
        }
    }

    override fun groupClicked(groupId: Int, groupName: String, playlistId: String) {
        supportFragmentManager.beginTransaction().addToBackStack("detail").replace(R.id.home_container, GroupDetailsFragment.newInstance(groupId, groupName, playlistId)).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = groupName
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount <= 1) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setTitle(R.string.app_name)
        }
    }

    private fun showGroupList(ft: FragmentTransaction) {
        ft.replace(R.id.home_container, GroupsFragment()).commit()
    }

    private fun addGroup(groupId: String) {
        RetrofitHelper.addUserToGroup(this, groupId, object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

            }
        })
    }

    override fun playingId(playListId: String) {
        HomeActivity.playListId = playListId
    }

    private fun goToProfile() = startActivity(Intent(this, ProfileActivity::class.java))

    private fun stopSong() {
        SpotifyHelper.player.pause(object : Player.OperationCallback {
            override fun onSuccess() {
            }

            override fun onError(p0: Error?) {
            }

        })
        if(HomeActivity.playListId != null) {

        } else {
            HomeActivity.playListId = null
        }
    }

    companion object {
        var playListId: String? = null
    }
}
