package com.example.amanelshark.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.adapter.NotificationAdapter;
import com.example.amanelshark.model.detailsrequest.DetailsRequest;
import com.example.amanelshark.model.notifications.DataItemNotifications;
import com.example.amanelshark.model.notifications.Notifications;
import com.example.amanelshark.view.DetailsPaymentActivity;
import com.example.amanelshark.view.DetailsRequestActivity;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;
import com.google.android.gms.common.internal.SignInButtonImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment implements NotificationAdapter.OnNotificationlistener {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    List<DataItemNotifications> notificationsList;
    NotificationAdapter notificationAdapter;
    RecyclerView recyclerView;
    private AmanElsharkViewModel userViewModel;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseApplication) getActivity().getApplicationContext()).getAppComponent().injectFragment(this);
        userViewModel = new ViewModelProvider(this, viewModelProvider).get(AmanElsharkViewModel.class);
        sharedPref = getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        String token = sharedPref.getString(getString(R.string.token), "null");
        recyclerView = view.findViewById(R.id.recycleview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        userViewModel.getNotifications(getContext(), token).observe(getActivity(), notifications -> {
            notificationsList = new ArrayList<>();
            notificationsList.addAll(notifications.getData());
            notificationAdapter = new NotificationAdapter(notificationsList, NotificationFragment.this, getContext());
            recyclerView.setAdapter(notificationAdapter);


        });
    }

    @Override
    public void onNotificationClick(int postion) {
        Toast.makeText(getContext(), "asd", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        if (notificationsList.get(postion).getStatus().equals("waiting")) {
            intent = new Intent(getActivity(), DetailsRequestActivity.class);

            intent.putExtra("id_request", notificationsList.get(postion).getRequestId());
            intent.putExtra("package_id", notificationsList.get(postion).getJsonMemberPackage().getId());
            intent.putExtra("status", notificationsList.get(postion).getStatus());
            startActivity(intent);
        }
        if (notificationsList.get(postion).getStatus().equals("accepted")) {
            intent = new Intent(getActivity(), DetailsPaymentActivity.class);

            intent.putExtra("id_request", notificationsList.get(postion).getId());
            intent.putExtra("package_id", notificationsList.get(postion).getJsonMemberPackage().getId());
            intent.putExtra("status", notificationsList.get(postion).getStatus());
            startActivity(intent);

        }

    }


}
