package com.example.amanelshark.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.amanelshark.view.fragment.RequestFragment;
import com.example.amanelshark.view.fragment.SmartFragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomBarAdapter extends SmartFragmentStatePagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();

    public BottomBarAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    // Our custom method that populates this Adapter with Fragments
    public void addFragments(Fragment fragment) {
        fragments.add(fragment);
    }
    public void setFragmentsFragments(Fragment fragment, int index) {
        fragments.set(index,fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getRegisteredFragment(int position) {
        return super.getRegisteredFragment(position);
    }
}