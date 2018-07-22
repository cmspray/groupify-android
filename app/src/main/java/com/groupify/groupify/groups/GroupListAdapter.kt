package com.groupify.groupify.groups

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.groupify.groupify.R
import com.groupify.groupify.dto.Group

class GroupListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_GROUP = 0
    val TYPE_NONE = 1

    var groups: List<Group>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GroupViewHolder).apply {
            if (itemViewType == TYPE_GROUP) {
                nameView.text = groups!![holder.adapterPosition].name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        TYPE_GROUP -> {
            GroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.group_list_item_view, parent, false))
        }
        else -> {
            GroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.group_list_empty_item_view, parent, false))
        }
    }

    override fun getItemCount(): Int {
        val size = groups?.size ?: 0
        if (size > 0) {
            return size
        }
        return 1;
    }

    override fun getItemViewType(position: Int): Int {
        if (groups?.isNotEmpty() == true)
            return TYPE_GROUP
        return TYPE_NONE
    }

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.group_name)
    }
}