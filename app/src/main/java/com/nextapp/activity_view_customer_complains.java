package com.nextapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nextapp.adaptor.ComplainListAdapter;
import com.nextapp.dto.AuthCallResponse;
import com.nextapp.dto.UpdateAuthResponse;
import com.nextapp.dto.UpdateAuthStatus;
import com.nextapp.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_view_customer_complains extends AppCompatActivity {
    static ListView listView;
    static List<AuthCallResponse> complainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_complains);
        getSupportActionBar().setTitle("Customer Complains");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listViewComplain);

        loadComplains(getApplicationContext());
    }

    public static void loadComplains(android.content.Context thisContext) {
        Call<List<AuthCallResponse>> call = RetrofitClient.getInstance().getMyApi().getAuthViews();

        call.enqueue(new Callback<List<AuthCallResponse>>() {
            @Override
            public void onResponse(Call<List<AuthCallResponse>> call, Response<List<AuthCallResponse>> response) {
                if (response.isSuccessful()) {

                    complainList = response.body();

                    for (AuthCallResponse authCallResponse1 : complainList) {
                        authCallResponse1.setBtnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Call<UpdateAuthResponse> completedCall = RetrofitClient.getInstance().getMyApi().updateAuthStatus(new UpdateAuthStatus(String.valueOf(authCallResponse1.getDetectionId()), "Completed"));

                                completedCall.enqueue(new Callback<UpdateAuthResponse>() {
                                    @Override
                                    public void onResponse(Call<UpdateAuthResponse> call, Response<UpdateAuthResponse> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(thisContext, response.body().getResponse(), Toast.LENGTH_LONG).show();
                                            loadComplains(thisContext);
                                        } else {
                                            Toast.makeText(thisContext, "Something went wrong", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UpdateAuthResponse> call, Throwable t) {
                                        Toast.makeText(thisContext, "An error has occurred", Toast.LENGTH_LONG).show();
                                        t.printStackTrace();
                                    }
                                });
                            }
                        });
                    }

                    ComplainListAdapter complainListAdapter = new ComplainListAdapter(thisContext, R.layout.complain_list, complainList);
                    listView.setAdapter(complainListAdapter);
                } else {
                    Toast.makeText(thisContext, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AuthCallResponse>> call, Throwable t) {
                Toast.makeText(thisContext, "An error has occurred", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }

        });
    }

    public static void updateListView(android.content.Context thisContext) {
        BaseAdapter adapter = (BaseAdapter)listView.getAdapter();
        loadComplains(thisContext);
    }

}