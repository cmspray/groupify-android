package com.groupify.groupify.groupdetails;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class GroupDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private static final int GROUP_NAME_TYPE = 0;
	private static final int SONG_NAME_TYPE = 1;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 1;
	}

	@Override
	public int getItemViewType(int position) {
		if(position == 0)
			return GROUP_NAME_TYPE;
		return SONG_NAME_TYPE;
	}

	private static class GroupDetailsViewHolder extends RecyclerView.ViewHolder {

		public GroupDetailsViewHolder(View itemView) {
			super(itemView);
		}
	}
}
