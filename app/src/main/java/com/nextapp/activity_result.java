package com.nextapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nextapp.dto.MLModelResponse;
import com.nextapp.dto.User;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.Serializable;

public class activity_result extends AppCompatActivity {

    TextView txtWeather, txtPestName, txtRecordId, txtStatus;
    ImageView selectedImage, pestImage;
    Button btnProcessNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtWeather = findViewById(R.id.txttemp);
        txtPestName = findViewById(R.id.resultPestName);
        txtRecordId = findViewById(R.id.resultRecordId);
        txtStatus = findViewById(R.id.resultStatus);
        btnProcessNew = findViewById(R.id.btnanother);
        selectedImage = findViewById(R.id.imageView1);
        pestImage = findViewById(R.id.resultPestImage);

        User user = (User) getIntent().getSerializableExtra("user");
        Serializable response = getIntent().getSerializableExtra("response");
        File uploadedImage = (File) getIntent().getSerializableExtra("uploadedImage");
        double weather = getIntent().getDoubleExtra("weather", 0.0);
        MLModelResponse mlModelResponse = (MLModelResponse) response;


        txtWeather.setText(String.valueOf(weather) + " °C");
        Picasso.get().load(uploadedImage).into(selectedImage);
        txtRecordId.setText(String.valueOf(mlModelResponse.getRecordID()));
        Picasso.get().load(mlModelResponse.getPestInfo().get(0).getPestImage()).into(pestImage);
        txtPestName.setText(mlModelResponse.getPestInfo().get(0).getPestName());
        txtStatus.setText(mlModelResponse.getAuthStatus());

        btnProcessNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_image_capture.class);
                startActivity(i);
            }
        });
    }
}