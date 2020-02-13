package com.example.amanelshark.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amanelshark.R;
import com.example.amanelshark.model.packages.DataItemPackages;
import com.example.amanelshark.model.packages.Packages;

import java.util.List;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageHolder> {
    Onpackagelistener onpackagelistener;
    List<DataItemPackages> dataItemPackagesList;
    Context context;

    public PackageAdapter(Onpackagelistener onpackagelistener, List<DataItemPackages> dataItemPackagesList, Context context) {
        this.onpackagelistener = onpackagelistener;
        this.dataItemPackagesList = dataItemPackagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public PackageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.packages_item, parent, false);
        return new PackageAdapter.PackageHolder(view, onpackagelistener);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageHolder holder, int position) {
        holder.period_name.setText(dataItemPackagesList.get(position).getName().toString());
        holder.km.setText(String.valueOf(dataItemPackagesList.get(position).getKm()));

        holder.price.setText(String.valueOf(dataItemPackagesList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return dataItemPackagesList.size();
    }

    public interface Onpackagelistener {
        void onPackageClick(int postion);
    }

    class PackageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView period_name, km, price;
        Onpackagelistener onpackagelistener;

        public PackageHolder(@NonNull View itemView, Onpackagelistener onpackagelistener) {
            super(itemView);
            period_name = itemView.findViewById(R.id.name_package);
            km = itemView.findViewById(R.id.km_package);
            price = itemView.findViewById(R.id.price_package);
            this.onpackagelistener = onpackagelistener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onpackagelistener.onPackageClick(getAdapterPosition());

        }
    }
}
