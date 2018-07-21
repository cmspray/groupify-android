package com.groupify.groupify.albumslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.groupify.groupify.R;
import com.groupify.groupify.dto.AlbumList;

public class AlbumListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private AlbumList albums;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new AlbumListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_list_item_view, parent, false));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((AlbumListViewHolder)holder).albumName.setText(albums.getAlbums()[holder.getAdapterPosition()].getAlbum().getName());
	}

	@Override
	public int getItemCount() {
		return albums == null ? 0 : albums.getAlbums() == null ? 0 : albums.getAlbums().length;
	}

	public void setAlbums(AlbumList albums) {
		this.albums = albums;
		notifyDataSetChanged();
	}

	private static class AlbumListViewHolder extends RecyclerView.ViewHolder {

		TextView albumName;

		AlbumListViewHolder(View itemView) {
			super(itemView);
			albumName = itemView.findViewById(R.id.album_list_album_name);
		}
	}
}
