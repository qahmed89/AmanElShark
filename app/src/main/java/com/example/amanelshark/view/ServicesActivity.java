package com.example.amanelshark.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.amanelshark.BaseApplication;
import com.example.amanelshark.R;
import com.example.amanelshark.adapter.ServiesAdpater;
import com.example.amanelshark.databinding.ActivityServicesBinding;
import com.example.amanelshark.model.center.Centers;
import com.example.amanelshark.model.center.DataItemCenters;
import com.example.amanelshark.viewmodel.AmanElsharkViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ServicesActivity extends AppCompatActivity implements ServiesAdpater.OnServieslistener {
    List<DataItemCenters> itemCentersList;
    ActivityServicesBinding servicesBinding;
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    AmanElsharkViewModel userViewModel;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ServiesAdpater serviesAdpater;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        servicesBinding = DataBindingUtil.setContentView(this, R.layout.activity_services);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(AmanElsharkViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        servicesBinding.recycleview.setLayoutManager(layoutManager);
        servicesBinding.recycleview.setHasFixedSize(true);
        itemCentersList = new ArrayList<>();
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString(getString(R.string.token), "null");
        servicesBinding.shimmer.startShimmer();
        userViewModel.getCentersRequests(getApplicationContext(), token).observe(this, new Observer<Centers>() {
            @Override
            public void onChanged(Centers centers) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        servicesBinding.shimmer.setVisibility(View.GONE);
                        itemCentersList.addAll(centers.getData());

                        serviesAdpater = new ServiesAdpater(itemCentersList, getApplicationContext(), ServicesActivity.this::onServiesClick);

                        servicesBinding.recycleview.setAdapter(serviesAdpater);
                        serviesAdpater.setShowShimmer(false);
                        serviesAdpater.notifyDataSetChanged();

                        runLayoutAnimation(servicesBinding.recycleview);
                    }
                }, 1000);

            }
        });

//new Handler().postDelayed(new Runnable() {
//    @Override
//    public void run() {
//if(!itemCentersList.isEmpty()) {
//    serviesAdpater.setShowShimmer(false);
//    serviesAdpater.notifyDataSetChanged();
//}else {
//    serviesAdpater.notifyDataSetChanged();}
//    }
//},1000);

    }
    @Override
    public void onServiesClick(int postion) {
       String id = String.valueOf( itemCentersList.get(postion).getId());
       Intent intent = new Intent(this,DetailsServicesActivity.class);
       intent.putExtra("id_event",id);
       startActivity(intent);

    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
