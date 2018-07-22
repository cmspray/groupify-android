package com.groupify.groupify.onboarding;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class OnBoardingAdapter extends FragmentStatePagerAdapter {

	public OnBoardingAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return OnBoardingFragment.newInstance(position);
	}

	@Override
	public int getCount() {
		return 4;
	}
}
