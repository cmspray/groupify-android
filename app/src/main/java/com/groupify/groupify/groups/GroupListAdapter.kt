package com.groupify.groupify.groups

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.groupify.groupify.R
import com.groupify.groupify.dto.Group

class GroupListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var groups: List<Group>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GroupViewHolder).apply {
            nameView.text = groups!![holder.adapterPosition].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            GroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.group_list_item_view, parent, false))

    override fun getItemCount(): Int = groups?.size ?: 0

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.group_name)
    }
}