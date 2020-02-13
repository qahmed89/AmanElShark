package com.example.amanelshark.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;

import com.example.amanelshark.R;
import com.example.amanelshark.adapter.BottomBarAdapter;
import com.example.amanelshark.databinding.ActivityMainHomeBinding;
import com.example.amanelshark.view.fragment.ProfileFragment;
import com.example.amanelshark.view.fragment.RequestFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainHomeActivity extends AppCompatActivity {
    ProfileFragment profileFragment = new ProfileFragment();
    RequestFragment requestFragment= new RequestFragment();
    ActivityMainHomeBinding activityMainHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        activityMainHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_home);

        BottomBarAdapter bottomBarAdapter = new BottomBarAdapter(getSupportFragmentManager());
        bottomBarAdapter.addFragments(profileFragment);
        bottomBarAdapter.addFragments(requestFragment);

        activityMainHomeBinding.viewPager2.setAdapter(bottomBarAdapter);
        activityMainHomeBinding.viewPager2.setPagingEnabled(false);
        activityMainHomeBinding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        activityMainHomeBinding.viewPager2.setCurrentItem(0);

                        return true;
                    case R.id.request:
                        activityMainHomeBinding.viewPager2.setCurrentItem(1);
                        return true;
//                    case R.id.tab3:
//                        Intent intent = new Intent(getApplicationContext() , Main2Activity.class);
//                        startActivity(intent);
//                        return true;


                }
                return false;
            }
        });
    }
}
