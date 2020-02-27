package com.example.amanelshark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amanelshark.R;
import com.example.amanelshark.model.notifications.DataItemNotifications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {
    List<DataItemNotifications>  dataItemNotificationsList;
    OnNotificationlistener onNotificationlistener;

    Context context;
    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationAdapter.NotificationHolder(view, onNotificationlistener);    }

    public NotificationAdapter(List<DataItemNotifications> dataItemNotificationsList, OnNotificationlistener onNotificationlistener, Context context) {
        this.dataItemNotificationsList = dataItemNotificationsList;
        this.onNotificationlistener = onNotificationlistener;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
      //  Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date toDate= null;
        String xx="";
        try {
            toDate = df.parse(dataItemNotificationsList.get(position).getTime());
           xx = df.format(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.car_name.setText(dataItemNotificationsList.get(position).getCarName().getBrandName()+" "+dataItemNotificationsList.get(position).getCarName().getModelName());
holder.description.setText(dataItemNotificationsList.get(position).getDescription());
        holder.status.setText(dataItemNotificationsList.get(position).getStatus());
        holder.time.setText(xx);

    }
    public interface OnNotificationlistener {
        void onNotificationClick(int postion);
    }
    @Override
    public int getItemCount() {
        return dataItemNotificationsList.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {
        OnNotificationlistener onNotificationlistener;
        TextView car_name, description,status ,time;
        public NotificationHolder(@NonNull View itemView,OnNotificationlistener onNotificationlistener)  {
            super(itemView);
            car_name=itemView.findViewById(R.id.carname_notification);
            description=itemView.findViewById(R.id.description_notification);
            status=itemView.findViewById(R.id.status_notification);
            time= itemView.findViewById(R.id.time_notification);
            this.onNotificationlistener = onNotificationlistener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
onNotificationlistener.onNotificationClick(getAdapterPosition());
        }
    }
}
