package com.nextapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.nextapp.dto.Pest;
import com.nextapp.dto.Weather;
import com.nextapp.dto.MLModelResponse;
import com.nextapp.dto.User;
import com.nextapp.service.RetrofitClient;
import com.nextapp.service.WeatherClient;
import com.squareup.okhttp.MediaType;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_image_capture extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;

    private FirebaseAuth mAuth;

    ImageView imageView;
    Button btnSelectImage, btnUpload;
    TextView txtWeather;
    double weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);

        mAuth = FirebaseAuth.getInstance();

        imageView = findViewById(R.id.img);
        imageView.setDrawingCacheEnabled(true);
        btnSelectImage = findViewById(R.id.uploader);
        btnUpload = findViewById(R.id.processBtn);
        txtWeather = findViewById(R.id.txttime3);

        User user = (User) getIntent().getSerializableExtra("user");

        getWeatherInfo();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MLModelResponse mlModelResponse = new MLModelResponse();
                Pest pest = new Pest();

                pest.setPestImage("https://www.farminguk.com/images/News/18854_1.jpg");
                mlModelResponse.setPestInfo(pest);
                mlModelResponse.setRecordId(123);

                uploadImage();

                Intent i = new Intent(activity_image_capture.this, activity_result.class);
                i.putExtra("user", user);
                i.putExtra("response", mlModelResponse);
                i.putExtra("weather", weather);
                startActivity(i);
            }
        });


        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inGallery = new Intent(Intent.ACTION_PICK);
                inGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(inGallery, GALLERY_REQ_CODE);

            }
        });
    }

    public void getWeatherInfo() {
        Call<Weather> weatherInfo = WeatherClient.getInstance().getMyApi().getWeatherInfo();
        weatherInfo.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                System.out.println(response.body());
                weather = response.body().getCurrent().getTemp_c();
                txtWeather.setText(String.valueOf(weather) + " °C");
            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                imageView.setImageURI(data.getData());
                btnUpload.setEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnLogout: logOutClickEvent(); return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logOutClickEvent() {
        mAuth.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void uploadImage() {
        File file = new File("");
//        RequestBody uploadImage = RequestBody.create(MediaType.parse("image/*"), file);

    }
}