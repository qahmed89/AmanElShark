package com.example.amanelshark.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amanelshark.R;
import com.example.amanelshark.view.ServicesActivity;

import java.util.List;

public class KMadpater extends RecyclerView.Adapter<KMadpater.KMHolder> {
    List<String> requestsItems;
    Context context;

    public KMadpater(List<String> requestsItems, Context context) {
        this.requestsItems = requestsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public KMHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.km_layout, parent, false);
        return new KMHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KMHolder holder, int position) {

        holder.requesttype_textview.setText("" + requestsItems.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ServicesActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestsItems.size();
    }

    class KMHolder extends RecyclerView.ViewHolder {
        TextView requesttype_textview;
        CardView cardView;

        public KMHolder(@NonNull View itemView) {
            super(itemView);
            requesttype_textview = itemView.findViewById(R.id.km);
            cardView = itemView.findViewById(R.id.cardview);


        }
    }
}
