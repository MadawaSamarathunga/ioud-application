package com.nextapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.nextapp.adaptor.ComplainListAdapter;
import com.nextapp.dto.AuthCallResponse;
import com.nextapp.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_view_customer_complains extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_complains);
        getSupportActionBar().setTitle("Customer Complains");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listViewComplain);

        loadComplains();
    }

    public void loadComplains() {
        List<AuthCallResponse> complainList = new ArrayList<>();

        AuthCallResponse authCallResponse = new AuthCallResponse();

        Call<List<AuthCallResponse>> call = RetrofitClient.getInstance().getMyApi().getAuthViews();

        call.enqueue(new Callback<List<AuthCallResponse>>() {
            @Override
            public void onResponse(Call<List<AuthCallResponse>> call, Response<List<AuthCallResponse>> response) {

                complainList = response.body();
                System.out.println("--> " + complainList);
                ComplainListAdapter complainListAdapter = new ComplainListAdapter(getApplicationContext(), R.layout.complain_list, complainList);
                listView.setAdapter(complainListAdapter);
            }

            @Override
            public void onFailure(Call<List<AuthCallResponse>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }

        });

    }
}