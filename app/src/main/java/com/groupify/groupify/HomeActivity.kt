package com.groupify.groupify

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils.replace
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ImageView
import com.groupify.groupify.groupdetails.GroupDetailsFragment
import com.groupify.groupify.groups.GroupListAdapter
import com.groupify.groupify.groups.GroupsFragment

class HomeActivity : AppCompatActivity(), GroupListAdapter.GroupClickCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        showGroupList(supportFragmentManager.beginTransaction().addToBackStack("list"))

        val profileImage: ImageView = findViewById(R.id.action_profile)
        profileImage.setOnClickListener { goToProfile() }
    }

    override fun groupClicked(groupId: Int, playlistId: String, groupName: String) {
        supportFragmentManager.beginTransaction().addToBackStack("detail").
                replace(R.id.home_container, GroupDetailsFragment.newInstance(groupId, groupName, playlistId)).
                commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.group_details)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount <= 1) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setTitle(R.string.app_name)
        }
    }

    fun showGroupList(ft: FragmentTransaction) {
        ft.replace(R.id.home_container, GroupsFragment()).commit()
    }

    private fun goToProfile() = startActivity(Intent(this,ProfileActivity::class.java))

}
