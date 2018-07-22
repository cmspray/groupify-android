package com.groupify.groupify;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.groupify.groupify.dto.GroupifyImage;
import com.groupify.groupify.dto.User;
import com.groupify.groupify.dto.UserResponse;
import com.groupify.groupify.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements Callback<UserResponse> {

    private TextView displayName;
    private TextView email;
    private ImageView profileImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        displayName = findViewById(R.id.user_display_name);
        email = findViewById(R.id.user_email);
        profileImage = findViewById(R.id.user_profile_image);

        RetrofitHelper.getUserInformation(this, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        User user = response.body().getUser();
        displayName.setText(user.getDisplayName());
        email.setText(user.getEmail());
        profileImage.setImageURI(Uri.parse(user.getImages().get(0).getImageUrl()));//this doesn't work
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {

    }
}
