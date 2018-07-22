package com.groupify.groupify.onboarding;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.groupify.groupify.R;

public class OnBoardingFragment extends Fragment {

	private static final String POSITION_KEY = "position_key";

	public static OnBoardingFragment newInstance(int position) {
		Bundle args = new Bundle();
		args.putInt(POSITION_KEY, position);
		OnBoardingFragment frag = new OnBoardingFragment();
		frag.setArguments(args);
		return frag;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_onboard, container, false);
		view.findViewById(R.id.onboard_card).setBackgroundResource(getBackground(getArguments().getInt(POSITION_KEY)));
		return view;
	}

	@DrawableRes
	public int getBackground(int position) {
		return R.drawable.onboard_background_1;
	}
}
