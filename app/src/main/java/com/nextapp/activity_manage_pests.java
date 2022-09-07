package com.nextapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.nextapp.dto.Pest;
import com.nextapp.adaptor.PestListAdapter;
import com.nextapp.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_manage_pests extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pests);

        getSupportActionBar().setTitle("Manage Pests");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.customListView);

        getPestListFromServer();
    }

    private void getPestListFromServer() {
        Call<List<Pest>> call = RetrofitClient.getInstance().getMyApi().getPestList();

        call.enqueue(new Callback<List<Pest>>() {
            @Override
            public void onResponse(Call<List<Pest>> call, Response<List<Pest>> response) {

                List<Pest> pestList = response.body();
                PestListAdapter customPestAdapter = new PestListAdapter(getApplicationContext(), R.layout.pest_list, pestList);
                listView.setAdapter(customPestAdapter);
            }

            @Override
            public void onFailure(Call<List<Pest>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }

        });

//        Pest pest = new Pest();
//        pest.setPestId(1);
//        pest.setPestName("Test");
//        pest.setPestScientificName("Test Scientific");
//        pest.setPestClassification("Test Classification");
//        pest.setPestDescription("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here,");
//        pest.setPestImage("https://foodtank.com/wp-content/uploads/2019/03/Climate-change-exacerbates-the-challenge-of-plant-pests.jpg");
//
//        Pest pest2 = new Pest();
//        pest2.setPestId(2);
//        pest2.setPestName("2");
//        pest2.setPestScientificName("Test2 Scientific");
//        pest2.setPestClassification("Test 2Classification");
//        pest2.setPestImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQD5MeS5fq_IDAtTmGx8YqD4dvv3U7JO0eifGschZfdxQEsQ4dHAPsjxzT9kNKtIIF8MNo&usqp=CAU");
//
//        ArrayList<Pest> list = new ArrayList<>();
//        list.add(pest);
//        list.add(pest2);;

//        PestListAdapter customPestAdapter = new PestListAdapter(getApplicationContext(), R.layout.pest_list, getPestListFromServer());
//        listView.setAdapter(customPestAdapter);
    }
}