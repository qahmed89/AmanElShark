package com.example.amanelshark.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amanelshark.R;
import com.example.amanelshark.view.DetailsServicesActivity;

import java.util.List;

public class Imageadpater extends RecyclerView.Adapter<Imageadpater. ImageHolder> {
    List<Integer> requestsItems;
    Context context;

    public Imageadpater(List<Integer> requestsItems, Context context) {
        this.requestsItems = requestsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public Imageadpater.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new Imageadpater.ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Imageadpater.ImageHolder holder, int position) {
holder.requesttype_textview.setImageResource(requestsItems.get(position));
holder.requesttype_textview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent( context, DetailsServicesActivity.class);
        context.startActivity(intent);
    }
});

    }

    @Override
    public int getItemCount() {
        return requestsItems.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        ImageView requesttype_textview;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            requesttype_textview = itemView.findViewById(R.id.cell);



        }
    }
}
