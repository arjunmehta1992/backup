package com.zaptech.religiousapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ViewPagerAdapter extends PagerAdapter {

	Context context;
	int[] flag;
	LayoutInflater inflater;

	public ViewPagerAdapter(Context context, int[] flag) {

		this.context = context;
		this.flag = flag;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return flag.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((RelativeLayout) arg1);
	}

	@Override
	public Object instantiateItem(View container, int position) {

		ImageView imgflag;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_item,
				(ViewGroup) container, false);

		imgflag = (ImageView) itemView.findViewById(R.id.flag);
		imgflag.setImageResource(flag[position]);
		((ViewPager) container).addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		((ViewPager) container).removeView((RelativeLayout) object);
	}

}
