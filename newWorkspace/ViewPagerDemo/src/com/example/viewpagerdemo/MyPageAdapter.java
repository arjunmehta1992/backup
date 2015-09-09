package com.example.viewpagerdemo;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;

	public MyPageAdapter(FragmentManager fm, List<Fragment> fragments2) {
		super(fm);
		// TODO Auto-generated constructor stub

		this.fragments = fragments2;

	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.fragments.size();
	}

}
