package com.groupify.groupify

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils.replace
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.groupify.groupify.groupdetails.GroupDetailsFragment
import com.groupify.groupify.groups.GroupListAdapter
import com.groupify.groupify.groups.GroupsFragment

class HomeActivity : AppCompatActivity(), GroupListAdapter.GroupClickCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        showGroupList(supportFragmentManager.beginTransaction().addToBackStack("list"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.android, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_settings -> {
                    goToSettings()
                    true
                }
                R.id.action_profile -> {
                    goToProfile()
                    true
                }
                android.R.id.home -> {
                    onBackPressed()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
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

    fun showGroupList(ft: FragmentTransaction) {
        ft.replace(R.id.home_container, GroupsFragment()).commit()
    }

    private fun goToSettings() = startActivity(Intent(this, SettingsActivity::class.java))
    private fun goToProfile() = startActivity(Intent(this, ProfileActivity::class.java))

}
