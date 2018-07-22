package com.groupify.groupify.groups

import android.app.PendingIntent.getActivity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.groupify.groupify.PreferenceHelper
import com.groupify.groupify.R
import com.groupify.groupify.SpotifyHelper
import com.groupify.groupify.dto.Group
import com.spotify.sdk.android.player.ConnectionStateCallback
import com.spotify.sdk.android.player.Player
import java.util.*

class GroupListAdapter(val groupClickCallback: GroupClickCallback, context: Context,
                       val clipboardManager: ClipboardManager,
                       val connectionStateCallback: ConnectionStateCallback,
                       val notificationCallback: Player.NotificationCallback,
                       val spotifyHelper: SpotifyHelper = SpotifyHelper().apply { initConfig(context, connectionStateCallback, notificationCallback) }) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_GROUP = 0
    val TYPE_NONE = 1

    var groups: List<Group>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_GROUP) {
            (holder as GroupViewHolder).apply {
                nameView.text = groups!![holder.adapterPosition].name
                itemView.setOnClickListener {
                    val group = groups!![holder.adapterPosition]
                    if(SpotifyHelper.player.isLoggedIn) {
                        SpotifyHelper.player.playUri(null, "spotify:playlist:" + group.playlistId, Random(System.currentTimeMillis()).nextInt() % 5, 0)
                    } else {
                        Log.e("Not Logged in", "Not logged in")
                        groupClickCallback.playingId(group.playlistId)
                    }
                }
                itemView.setOnLongClickListener {
                    val group = groups!![holder.adapterPosition]
                    val clip: ClipData = ClipData.newPlainText("label", String.format("groupify://group/%d", group.id))
                    clipboardManager.primaryClip = clip
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        TYPE_GROUP -> {
            GroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.group_list_item_view, parent, false))
        }
        else -> {
            NoGroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.group_list_empty_item_view, parent, false))
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

    inner class NoGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface GroupClickCallback {
        fun groupClicked(groupId: Int, playlistId: String, groupName: String)
        fun playingId(playListId: String)
    }
}