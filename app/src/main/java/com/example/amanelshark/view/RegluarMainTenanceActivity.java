package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.amanelshark.R;
import com.example.amanelshark.adapter.KMadpater;

import java.util.ArrayList;
import java.util.List;

public class RegluarMainTenanceActivity extends AppCompatActivity {
RecyclerView recyclerView;
List<String>x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regluarmain);
        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        x= new ArrayList<>();
        x.add("110,000 KM Services");
        x.add("120,000 KM Services");
        x.add("130,000 KM Services");
        x.add("140,000 KM Services");
        x.add("150,000 KM Services");
        x.add("160,000 KM Services");
        x.add("170,000 KM Services");
        x.add("180,000 KM Services");
        x.add("190,000 KM Services");
        KMadpater kMadpater= new KMadpater(x, this);
        recyclerView.setAdapter(kMadpater);


    }
}
