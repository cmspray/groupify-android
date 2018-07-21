package com.groupify.groupify.albumslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groupify.groupify.R;
import com.groupify.groupify.dto.AlbumList;
import com.groupify.groupify.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsFragment extends Fragment implements Callback<AlbumList> {

	public final String TAG = AlbumsFragment.class.getSimpleName();

	private RecyclerView albumListRV;
	private AlbumListAdapter adapter = new AlbumListAdapter();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_album_list, container, false);
		albumListRV = view.findViewById(R.id.album_list_rv);
		albumListRV.setAdapter(adapter);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		albumListRV.setLayoutManager(new LinearLayoutManager(getActivity()));
		RetrofitHelper.getAlbums(getActivity(), this);
	}

	@Override
	public void onResponse(Call<AlbumList> call, Response<AlbumList> response) {
		adapter.setAlbums(response.body());
	}

	@Override
	public void onFailure(Call<AlbumList> call, Throwable t) {

	}
}
