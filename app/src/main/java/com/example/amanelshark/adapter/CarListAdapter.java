package com.example.amanelshark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amanelshark.R;
import com.example.amanelshark.model.listcars.DataItemCarList;
import com.example.amanelshark.model.listcars.ListCars;
import com.example.amanelshark.model.packages.DataItemPackages;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarListholder> {
     public  OnCarListlistener onCarListlistener;
    List<DataItemCarList> listCarsList;
    Context context;

    public CarListAdapter(OnCarListlistener onCarListlistener, List<DataItemCarList> listCarsList, Context context) {
        this.onCarListlistener = onCarListlistener;
        this.listCarsList = listCarsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CarListholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carlist_item, parent, false);
        return new CarListAdapter.CarListholder(view, onCarListlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarListholder holder, int position) {
        if(listCarsList.get(position).getWarranty() == null) {
            holder.warrenty.setText("Not Yet");


        }else {
            holder.warrenty.setText(listCarsList.get(position).getWarranty().getPackages().getName());
        }

        holder.name.setText(listCarsList.get(position).getModel());

//
//        holder.price.setText(String.valueOf(listCarsList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return listCarsList.size();
    }

    public interface OnCarListlistener {
        void onCarListClick(int postion);
    }

    class CarListholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, warrenty;
        OnCarListlistener onCarListlistener;

        public CarListholder(@NonNull View itemView, OnCarListlistener onCarListlistener) {
            super(itemView);
            name = itemView.findViewById(R.id.carname_listcar);
            warrenty = itemView.findViewById(R.id.warranty_listcar);
            this.onCarListlistener = onCarListlistener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCarListlistener.onCarListClick(getAdapterPosition());

        }
    }
}
