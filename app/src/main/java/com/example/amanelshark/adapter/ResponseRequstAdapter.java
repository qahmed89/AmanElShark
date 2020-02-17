package com.example.amanelshark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amanelshark.R;
import com.example.amanelshark.model.packages.DataItemPackages;
import com.example.amanelshark.model.responsrequest.DataItemResponseRequest;

import java.util.List;

public class ResponseRequstAdapter extends RecyclerView.Adapter<ResponseRequstAdapter.ResponseRequstHolder> {
    ResponseRequstAdapter.OnResponseRequstlistener onResponseRequstlistener;
    List<DataItemResponseRequest> dataItemResponseRequestList;
    Context context;

    public ResponseRequstAdapter(ResponseRequstAdapter.OnResponseRequstlistener onResponseRequstlistener, List<DataItemResponseRequest> dataItemResponseRequestList, Context context) {
        this.onResponseRequstlistener = onResponseRequstlistener;
        this.dataItemResponseRequestList = dataItemResponseRequestList;
        this.context = context;
    }

    @NonNull
    @Override
    public ResponseRequstAdapter.ResponseRequstHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new ResponseRequstAdapter.ResponseRequstHolder(view, onResponseRequstlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseRequstAdapter.ResponseRequstHolder holder, int position) {
        holder.car_name.setText(new StringBuilder().append(dataItemResponseRequestList.get(position).getCar().getBrand()).append(" ").append(dataItemResponseRequestList.get(position).getCar().getModel()).toString());
        holder.center_name.setText(String.valueOf(dataItemResponseRequestList.get(position).getCenterName()));

        holder.status.setText(String.valueOf(dataItemResponseRequestList.get(position).getStatus()));

    }

    @Override
    public int getItemCount() {
        return dataItemResponseRequestList.size();
    }

    public interface OnResponseRequstlistener {
        void onResponseRequstClick(int postion);
    }

    class ResponseRequstHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView car_name, center_name, status;
        ResponseRequstAdapter.OnResponseRequstlistener onResponseRequstlistener;

        public ResponseRequstHolder(@NonNull View itemView, ResponseRequstAdapter.OnResponseRequstlistener onResponseRequstlistener) {
            super(itemView);
            car_name = itemView.findViewById(R.id.carname_request);
            center_name = itemView.findViewById(R.id.centername_request);
            status = itemView.findViewById(R.id.status_request);
            this.onResponseRequstlistener = onResponseRequstlistener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onResponseRequstlistener.onResponseRequstClick(getAdapterPosition());

        }
    }
}
