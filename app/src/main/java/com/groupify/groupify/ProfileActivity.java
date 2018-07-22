package com.groupify.groupify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.groupify.groupify.dto.GroupifyImage;
import com.groupify.groupify.dto.PutUserRequest;
import com.groupify.groupify.dto.User;
import com.groupify.groupify.dto.UserResponse;
import com.groupify.groupify.retrofit.RetrofitHelper;

import java.io.InputStream;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class ProfileActivity extends AppCompatActivity implements Callback<UserResponse> {

    private TextView displayName;
    private TextView email;
    private TextView firstName;
    private TextView lastName;
    private TextView phoneNumber;
    private ImageView profileImage;
    private User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN, SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        displayName = findViewById(R.id.user_display_name);
        email = findViewById(R.id.user_email);
        profileImage = findViewById(R.id.user_profile_image);
        firstName = findViewById(R.id.user_first_name);
        lastName = findViewById(R.id.user_last_name);
        phoneNumber = findViewById(R.id.user_phone);

        RetrofitHelper.getUser(this, this);
        RetrofitHelper.getUserInformation(this,this);
        final Button saveButton = findViewById(R.id.save_profile);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               saveProfile();
            }
        });
    }

    public void saveProfile() {
        PutUserRequest user = new PutUserRequest();
        user.setFirstName(firstName.getEditableText().toString());
        user.setLastName(lastName.getEditableText().toString());
        user.setPhoneNumber(phoneNumber.getEditableText().toString());

        RetrofitHelper.putUser(this, user, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Log.e("Blah blah","No way");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
        user = response.body().getUser();
        displayName.setText(user.getDisplayName());
        if(user.getEmail() != null) {
            email.setText(user.getEmail());
        }
        if(user.getFirstName() != null) {
            firstName.setText(user.getFirstName());
        }
        if(user.getLastName() != null) {
            lastName.setText(user.getLastName());
        }
        if(user.getPhoneNumber() != null) {
            phoneNumber.setText(user.getPhoneNumber());
        }
        if(!user.getImages().isEmpty()) {
            final String imageUrl = user.getImages().get(0).getImageUrl();

            new DownLoadImageTask(profileImage).execute(imageUrl);
        }
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }
            logo = transform(logo);
            return logo;
        }
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
        public Bitmap transform(Bitmap bitmap) {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);
            final float roundPx = 100;

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

            return output;
        }
    }
}
