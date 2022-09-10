package com.nextapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.nextapp.dto.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class activity_home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button manage;
    Button customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Admin Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        User user = (User) getIntent().getSerializableExtra("user");

        manage = findViewById(R.id.btnmanage);
        customer = findViewById(R.id.btncust);

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), activity_manage_pests.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_view_customer_complains.class);
                i.putExtra("user", user);
                startActivity(i);
            }
        });

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

}