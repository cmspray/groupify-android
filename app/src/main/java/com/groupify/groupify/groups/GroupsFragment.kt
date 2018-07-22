package com.groupify.groupify.groups

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.groupify.groupify.R
import com.groupify.groupify.dto.AllGroupsResponse
import com.groupify.groupify.retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupsFragment : Fragment(), Callback<AllGroupsResponse> {

    lateinit var groupListAdapter: GroupListAdapter
    lateinit var groupListRV: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_groups, container, false).apply {
                groupListRV = findViewById(R.id.group_list_rv)
            }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        groupListRV.setLayoutManager(LinearLayoutManager(activity))
        RetrofitHelper.getGroups(activity, this)
    }

    override fun onResponse(call: Call<AllGroupsResponse>?, response: Response<AllGroupsResponse>) {
        when (response.body()?.groups?.isNotEmpty()) {
            true -> {
                groupListAdapter.groups = response.body()!!.groups.toList()
                groupListAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onFailure(call: Call<AllGroupsResponse>?, t: Throwable?) {

    }
}