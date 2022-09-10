package com.nextapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.nextapp.dto.Pest;
import com.nextapp.dto.Weather;
import com.nextapp.dto.MLModelResponse;
import com.nextapp.dto.User;
import com.nextapp.service.RetrofitClient;
import com.nextapp.service.WeatherClient;
import com.nextapp.util.RealPathUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_image_capture extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    private static final int CAMERA_REQUEST = 1888;

    private FirebaseAuth mAuth;
    private String imageUrl;
    private User user;

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

        user = (User) getIntent().getSerializableExtra("user");

        getWeatherInfo();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageAndNavigate();
            }
        });


        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_image_capture.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity_image_capture.this, new String[]{Manifest.permission.CAMERA}, 100);
                    } else {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                } else if (options[item].equals("Choose from Gallery")) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Intent inGallery = new Intent(Intent.ACTION_PICK);

                        inGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(inGallery, GALLERY_REQ_CODE);
                    } else {
                        ActivityCompat.requestPermissions(activity_image_capture.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
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
            if (requestCode == GALLERY_REQ_CODE || requestCode == CAMERA_REQUEST) {
//                imageView.setImageURI(data.getData());
//                imageUrl = RealPathUtil.getRealPath(activity_image_capture.this, data.getData());

                Uri uri = data.getData();
                Context context = activity_image_capture.this;
                imageUrl = RealPathUtil.getRealPath(context, uri);
                Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
                imageView.setImageBitmap(bitmap);
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
            case R.id.btnLogout:
                logOutClickEvent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logOutClickEvent() {
        mAuth.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void uploadImageAndNavigate() {
        System.out.println(imageUrl);
        try {
            File file = new File(imageUrl);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part parts = MultipartBody.Part.createFormData("Uploadimage", file.getName(), requestBody);

            RequestBody userType = RequestBody.create(MediaType.parse("text/plain"), user.getUserType());
            RequestBody requestBy = RequestBody.create(MediaType.parse("text/plain"), user.getName());
            RequestBody contactNo = RequestBody.create(MediaType.parse("text/plain"), user.getContactNo());
            RequestBody unknown = RequestBody.create(MediaType.parse("text/plain"), "");
            RequestBody temp = RequestBody.create(MediaType.parse("text/plain"), weather + " °C");

            Call<MLModelResponse> mlModelResponseCall = RetrofitClient.getInstance().getMyApi().processImage(parts, userType, requestBy, contactNo, unknown, temp);
            mlModelResponseCall.enqueue(new Callback<MLModelResponse>() {
                @Override
                public void onResponse(Call<MLModelResponse> call, Response<MLModelResponse> response) {
                    if (response.isSuccessful()) {
                        MLModelResponse mlModelResponse = response.body();

                        Intent i = new Intent(activity_image_capture.this, activity_result.class);
                        i.putExtra("user", user);
                        i.putExtra("response", mlModelResponse);
                        i.putExtra("uploadedImage", file);
                        i.putExtra("weather", weather);
                        startActivity(i);
                    } else {
                        Toast.makeText(activity_image_capture.this, "Internal Server Error",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MLModelResponse> call, Throwable t) {
                    Toast.makeText(activity_image_capture.this, "Something went wrong",
                            Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(activity_image_capture.this, "Something went wrong",
                    Toast.LENGTH_SHORT).show();
        }
    }
}