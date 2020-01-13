package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.amanelshark.R;
import com.example.amanelshark.adapter.Imageadpater;
import com.example.amanelshark.adapter.KMadpater;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {
RecyclerView recyclerView;
List<Integer>x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        x= new ArrayList<>();
        x.add(R.drawable.cell1);
        x.add(R.drawable.cell2);
        x.add(R.drawable.cell3);
        x.add(R.drawable.cell4);
        x.add(R.drawable.cell5);
        x.add(R.drawable.cell1);
        x.add(R.drawable.cell2);
        x.add(R.drawable.cell3);

        Imageadpater imageadpater= new Imageadpater(x, this);
        recyclerView.setAdapter(imageadpater);

    }
}
