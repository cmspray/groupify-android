package com.groupify.groupify.groups

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.groupify.groupify.HomeActivity
import com.groupify.groupify.R
import com.groupify.groupify.SpotifyHelper
import com.groupify.groupify.dto.AllGroupsResponse
import com.groupify.groupify.retrofit.RetrofitHelper
import com.spotify.sdk.android.player.ConnectionStateCallback
import com.spotify.sdk.android.player.Error
import com.spotify.sdk.android.player.Player
import com.spotify.sdk.android.player.PlayerEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GroupsFragment : Fragment(), Callback<AllGroupsResponse> {

    lateinit var groupListAdapter: GroupListAdapter
    lateinit var groupListRV: RecyclerView
    lateinit var addGroupButton: FloatingActionButton
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    val connectionStateCallback: ConnectionStateCallback = object: ConnectionStateCallback {
        override fun onLoggedOut() {
        }

        override fun onLoggedIn() {
            if(HomeActivity.playListId != null) {
                Log.e("Logged In", "Logged in")
                SpotifyHelper.player?.playUri(null, "spotify:playlist:" + HomeActivity.playListId, Random(System.currentTimeMillis()).nextInt() % 5, 0)
            }
        }

        override fun onConnectionMessage(p0: String?) {

            Log.e("ConnectMessage", p0 ?: "")
        }

        override fun onLoginFailed(p0: Error?) {
            Log.e("Error", p0?.name ?: "Nothing")
        }

        override fun onTemporaryError() {
            Log.e("Temp Erro", "what")
        }
    }

    val notificationCallback: Player.NotificationCallback = object : Player.NotificationCallback {
        override fun onPlaybackError(p0: Error?) {
            Log.e("Error", p0?.name ?: "none")

        }

        override fun onPlaybackEvent(p0: PlayerEvent?) {
            Log.e("Error", p0?.name ?: "none")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_groups, container, false).apply {
                groupListRV = findViewById(R.id.group_list_rv)
                addGroupButton = findViewById(R.id.add_group_button)
                addGroupButton.setOnClickListener {
                    showAddGroup()
                }
                swipeRefreshLayout = findViewById(R.id.refresh_layout)
                swipeRefreshLayout.setOnRefreshListener { refresh() }
            }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        groupListAdapter = GroupListAdapter(activity as GroupListAdapter.GroupClickCallback, activity!!, activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager, connectionStateCallback, notificationCallback)
        groupListRV.layoutManager = LinearLayoutManager(activity)
        swipeRefreshLayout.isRefreshing = true
        groupListRV.adapter = groupListAdapter
        RetrofitHelper.getGroups(activity, this)
    }

    override fun onResponse(call: Call<AllGroupsResponse>?, response: Response<AllGroupsResponse>) {
        swipeRefreshLayout.isRefreshing = false
        when (response.body()?.groups?.isNotEmpty()) {
            true -> {
                groupListAdapter.groups = response.body()!!.groups.toList()
                groupListAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onFailure(call: Call<AllGroupsResponse>?, t: Throwable?) {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun refresh() {
        swipeRefreshLayout.isRefreshing = true
        RetrofitHelper.getGroups(activity, this)
    }

    private fun showAddGroup() {
        AddGroupDialog().show(fragmentManager, AddGroupDialog.TAG)
    }
}