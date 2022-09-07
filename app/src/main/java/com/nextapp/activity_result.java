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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class activity_result extends AppCompatActivity {

    TextView txtWeather;
    ImageView imageView;
    Button btnProcessNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtWeather = findViewById(R.id.txttemp);
        btnProcessNew = findViewById(R.id.btnanother);
        imageView = findViewById(R.id.imageView1);

        User user = (User) getIntent().getSerializableExtra("user");
        Serializable response = getIntent().getSerializableExtra("response");
        double weather = getIntent().getDoubleExtra("weather", 0.0);
        MLModelResponse mlModelResponse = (MLModelResponse) response;


        Picasso.get().load(mlModelResponse.getPestInfo().getPestImage()).into(imageView);
        txtWeather.setText(String.valueOf(weather) + " °C");


        btnProcessNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_image_capture.class);
                startActivity(i);
            }
        });
    }
}