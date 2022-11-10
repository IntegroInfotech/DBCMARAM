package com.integro.dbcmaram.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.integro.dbcmaram.fragments.HomeFragment;
import com.integro.dbcmaram.fragments.NewsFragment;
import com.integro.dbcmaram.fragments.NotificationFragment;
import com.integro.dbcmaram.fragments.WebFragment;

public class SliderAdapter extends FragmentPagerAdapter {

    public SliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        if (i == 0) {
            fragment = new HomeFragment();
        }
        if (i == 1) {
            fragment = new NewsFragment();
        }
        if (i == 2) {
            fragment = new NotificationFragment();
        }
        if (i == 3) {
            fragment = new WebFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
