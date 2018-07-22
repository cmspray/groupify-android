package com.groupify.groupify.groupdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groupify.groupify.R;
import com.groupify.groupify.dto.Group;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupDetailsFragment extends Fragment {

	private static final String GROUP_ID_KEY = "group_id_key";
	private static final String PLAYLIST_ID_KEY = "playlist_id_key";
	private static final String GROUP_NAME_ID = "group_name_id";

	public static GroupDetailsFragment newInstance(int id, String name, String playlistId) {
		Bundle args = new Bundle();
		args.putInt(GROUP_ID_KEY, id);
		args.putString(PLAYLIST_ID_KEY, playlistId);
		args.putString(GROUP_NAME_ID, name);
		GroupDetailsFragment frag = new GroupDetailsFragment();
		frag.setArguments(args);
		return frag;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_group_details, container, false);

		return view;
	}
}
