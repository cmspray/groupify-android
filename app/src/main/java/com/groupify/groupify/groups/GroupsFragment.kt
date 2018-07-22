package com.groupify.groupify.groups

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
import com.groupify.groupify.R
import com.groupify.groupify.dto.AllGroupsResponse
import com.groupify.groupify.retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupsFragment : Fragment(), Callback<AllGroupsResponse> {

    lateinit var groupListAdapter: GroupListAdapter
    lateinit var groupListRV: RecyclerView
    lateinit var addGroupButton: FloatingActionButton
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

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
        groupListAdapter = GroupListAdapter(activity as GroupListAdapter.GroupClickCallback)
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