package com.example.adapter;

import com.example.tabspager.GamesFragment;
import com.example.tabspager.MoviesFragment;
import com.example.tabspager.TopRatedFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		switch (index) {
        case 0:
            // Top Rated fragment activity
            return new TopRatedFragment();
            
        case 1:
            // Games fragment activity
            return new GamesFragment();
        case 2:
            // Movies fragment activity
            return new MoviesFragment();
        }
 
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
