package com.example.amanelshark.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.adapters.ListenerUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.adapter.BottomBarAdapter;
import com.example.amanelshark.adapter.CarListAdapter;
import com.example.amanelshark.model.listcars.DataItemCarList;
import com.example.amanelshark.model.listcars.ListCars;
import com.example.amanelshark.model.profile.Profile;
import com.example.amanelshark.view.DetailsCarActivity;
import com.example.amanelshark.view.DetailsServicesActivity;
import com.example.amanelshark.view.MainHomeActivity;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements CarListAdapter.OnCarListlistener {

    @Inject
    ViewModelProvider.Factory viewModelProvider;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    TextView name, email, phone;
    RecyclerView recyclerView;
    String token;
    String name_s, email_s, phone_s;
    List<DataItemCarList> listCarsList;
    CarListAdapter carListAdapter;
    private AmanElsharkViewModel userViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPref = getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        ((BaseApplication) getActivity().getApplication()).getAppComponent().injectFragment(this);

        userViewModel = new ViewModelProvider(this, viewModelProvider).get(AmanElsharkViewModel.class);
        listCarsList = new ArrayList<>();
        profile();

        getlistCar();
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.name_profile);
        email = view.findViewById(R.id.email_profile);
        phone = view.findViewById(R.id.phone_profile);
        recyclerView = view.findViewById(R.id.recycleview_listcar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        carListAdapter = new CarListAdapter(this, listCarsList, getContext());
        recyclerView.setAdapter(carListAdapter);

    }


    @Override
    public void onCarListClick(int postion) {
        Toast.makeText(getContext(), "" + postion, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), DetailsCarActivity.class);
        intent.putExtra("id_car", listCarsList.get(postion).getId());

        getActivity().startActivity(intent);
    }

    private void getlistCar() {
        userViewModel.getLisCarsRequests(getActivity(), token).observe(getActivity(), new Observer<ListCars>() {
            @Override
            public void onChanged(ListCars listCars) {
                listCarsList.addAll(listCars.getData());
                recyclerView.setAdapter(carListAdapter);

                //  carListAdapter.notifyDataSetChanged();
            }
        });

//        phone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//           Intent     intent= new Intent(getActivity(), MainHomeActivity.class);
//                intent.putExtra("viewpager_position",1);
//                getActivity().finish();
//                startActivity(intent);
//            }
//        });
    }

    private void profile() {
        userViewModel.getpofileRequests(getActivity(), token).observe(Objects.requireNonNull(getActivity()), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                name_s = profile.getName();
                phone_s = profile.getMobile();
                email_s = profile.getEmail();
                name.setText(name_s);
                phone.setText(phone_s);
                email.setText(email_s);
            }
        });
    }

}
