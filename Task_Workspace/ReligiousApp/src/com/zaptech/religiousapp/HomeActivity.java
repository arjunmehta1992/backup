package com.zaptech.religiousapp;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class HomeActivity extends Activity {

	int[] flag = new int[] { R.drawable.m1, R.drawable.m2, R.drawable.m3,
			R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7 };

	Timer timer;
	int seconds;
	int page = 1;
	ViewPager viewPager;
	PagerAdapter adapter;

	MediaPlayer mPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		viewPager = (ViewPager) findViewById(R.id.pager);
		// Pass results to ViewPagerAdapter Class
		adapter = new ViewPagerAdapter(HomeActivity.this, flag);
		// Binds the Adapter to the ViewPager
		viewPager.setAdapter(adapter);
		// pageSwitcher(seconds);

		mPlayer = MediaPlayer.create(HomeActivity.this,
				R.raw.maha_mrityunjaya_mantra);

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			int item_count = 0;

			@Override
			public void run() {
				viewPager.setCurrentItem(item_count);
				if (item_count == 5) {
					item_count = 0;
				} else {
					item_count++;	
				}
				handler.postDelayed(this, 3000);
			}
		}, 3000);

		mPlayer.start();

	}

	@Override
	protected void onDestroy() {
		mPlayer.stop();
		super.onDestroy();
	}
}