package com.groupify.groupify.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.groupify.groupify.HomeActivity;
import com.groupify.groupify.PreferenceHelper;
import com.groupify.groupify.R;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class OnBoardingActivity extends AppCompatActivity {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN, SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		setContentView(R.layout.activity_onboard);
		Button button = findViewById(R.id.got_it_button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PreferenceHelper.Companion.setHasSeenOnBoarding(OnBoardingActivity.this);
				startActivity(new Intent(OnBoardingActivity.this, HomeActivity.class));
			}
		});

		ViewPager viewPager = findViewById(R.id.onboarding_view_pager);
		viewPager.setAdapter(new OnBoardingAdapter(getSupportFragmentManager()));
	}
}
