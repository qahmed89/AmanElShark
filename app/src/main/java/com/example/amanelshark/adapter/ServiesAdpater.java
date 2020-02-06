package com.example.amanelshark.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amanelshark.R;
import com.example.amanelshark.model.center.DataItemCenters;
import com.example.amanelshark.view.DetailsServicesActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class ServiesAdpater extends RecyclerView.Adapter<ServiesAdpater.ServiesHolder> {
    List<DataItemCenters> requestsItems;
    Context context;
    private OnServieslistener onServieslistener;
    int SHIMMER_ITEM_NUMBER=5;
    boolean showShimmer = true;

    public boolean isShowShimmer() {
        return showShimmer;
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    public ServiesAdpater(List<DataItemCenters> requestsItems, Context context, OnServieslistener onServieslistener) {
        this.requestsItems = requestsItems;
        this.context = context;
        this.onServieslistener = onServieslistener;
    }

    @NonNull
    @Override
    public ServiesAdpater.ServiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ServiesAdpater.ServiesHolder(view, onServieslistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiesAdpater.ServiesHolder holder, int position) {

        if (showShimmer){
            holder.shimmerFrameLayout.startShimmer();
        }else {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);
            holder.image_servies.setImageResource(R.drawable.imageadapter);
            holder.image_servies.setBackground(null);
            holder.name_servies.setText(requestsItems.get(position).getName());
            holder.name_servies.setBackground(null);
            holder.description_servies.setText(requestsItems.get(position).getDescription());
            holder.description_servies.setBackground(null);
            holder.ratingBar.setRating(requestsItems.get(position).getRate());
            holder.ratingBar.setBackground(null);

        }
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailsServicesActivity.class);
//                intent.putExtra("id_center", requestsItems.get(position).getId());
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return showShimmer?SHIMMER_ITEM_NUMBER:requestsItems.size();
    }

    public interface OnServieslistener {
        void onServiesClick(int postion);
    }

    class ServiesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image_servies;
        TextView name_servies, description_servies;
        CardView cardView;
        RatingBar ratingBar;
        OnServieslistener onServieslistener;
        ShimmerFrameLayout shimmerFrameLayout;


        public ServiesHolder(@NonNull View itemView, OnServieslistener onServieslistener) {
            super(itemView);
            name_servies = itemView.findViewById(R.id.name_service);
            description_servies = itemView.findViewById(R.id.description_service);
            image_servies = itemView.findViewById(R.id.image_service);
            cardView = itemView.findViewById(R.id.card_servies);
            ratingBar = itemView.findViewById(R.id.ratingbar_service);
            shimmerFrameLayout=itemView.findViewById(R.id.shimmer);
            this.onServieslistener = onServieslistener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onServieslistener.onServiesClick(getAdapterPosition());
        }
    }
}
