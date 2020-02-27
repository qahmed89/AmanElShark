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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.adapter.ResponseRequstAdapter;
import com.example.amanelshark.model.responsrequest.DataItemResponseRequest;
import com.example.amanelshark.model.responsrequest.ResponsRequest;
import com.example.amanelshark.view.DetailsPaymentActivity;
import com.example.amanelshark.view.DetailsRequestActivity;
import com.example.amanelshark.view.DetailsServicesActivity;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment implements ResponseRequstAdapter.OnResponseRequstlistener {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String token;
    List<DataItemResponseRequest> responseRequestList;
    RecyclerView recyclerView;
    private AmanElsharkViewModel userViewModel;

    public RequestFragment() {
        // Required empty public constructor
    }

    public static RequestFragment newInstance() {
        return new RequestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPref = getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        ((BaseApplication) getActivity().getApplication()).getAppComponent().injectFragment(this);

        userViewModel = new ViewModelProvider(this, viewModelProvider).get(AmanElsharkViewModel.class);
        responseRequestList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycleview_request);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        userViewModel.getRespnseRequests(getContext(), token).observe(Objects.requireNonNull(getActivity()), responsRequest -> {
            responseRequestList.addAll(responsRequest.getData());
            ResponseRequstAdapter responseRequstAdapter = new ResponseRequstAdapter(this::onResponseRequstClick, responseRequestList, getContext());
            recyclerView.setAdapter(responseRequstAdapter);
        });

    }

    @Override
    public void onResponseRequstClick(int postion) {

        if (!(responseRequestList.get(postion).getStatus().equals("accepted"))) {
            Intent intent = new Intent(Objects.requireNonNull(getContext()).getApplicationContext(), DetailsRequestActivity.class);
            intent.putExtra("id_event", responseRequestList.get(postion).getCarCenterId());
            intent.putExtra("id_request", responseRequestList.get(postion).getId());
            intent.putExtra("package_id", responseRequestList.get(postion).getPackageId());
            intent.putExtra("status", responseRequestList.get(postion).getStatus());

            startActivity(intent);

        } else {
            Intent intent = new Intent(Objects.requireNonNull(getContext()).getApplicationContext(), DetailsPaymentActivity.class);
            intent.putExtra("id_event", responseRequestList.get(postion).getCarCenterId());
            intent.putExtra("id_request", responseRequestList.get(postion).getId());
            intent.putExtra("package_id", responseRequestList.get(postion).getPackageId());
            intent.putExtra("status", responseRequestList.get(postion).getStatus());

            startActivity(intent);
        }
    }
}
