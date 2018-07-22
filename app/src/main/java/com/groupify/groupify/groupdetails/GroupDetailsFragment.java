package com.groupify.groupify.groupdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groupify.groupify.R;
import com.groupify.groupify.dto.Group;
import com.groupify.groupify.retrofit.RetrofitHelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupDetailsFragment extends Fragment implements Callback<ResponseBody> {

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

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String playlistId = getArguments().getString(PLAYLIST_ID_KEY);

		RetrofitHelper.getPlaylist(getActivity(), playlistId, this);

	}

	@Override
	public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
	}

	@Override
	public void onFailure(Call<ResponseBody> call, Throwable t) {

	}
}
