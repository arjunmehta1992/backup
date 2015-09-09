package com.example.locationalarm.activity;

import java.io.IOException;

import android.content.Intent;
import android.content.res.Resources;
import android.net.MailTo;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.locationalarm.R;
import com.example.locationalarm.database.DatabaseHelper;

public class TabActivity extends android.app.TabActivity {

	private DatabaseHelper helper;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost);

		helper = new DatabaseHelper(getApplicationContext());
		try {
			helper.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resources ressources = getResources();
		TabHost tabHost = getTabHost();

		// Android tab
		Intent intentAndroid = new Intent().setClass(TabActivity.this,
				BusStopAlarmActivity.class);
		TabSpec tabSpecAndroid = tabHost
				.newTabSpec("Android")
				.setIndicator("",
						ressources.getDrawable(R.drawable.ic_launcher))
				.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(TabActivity.this,
				BrtsAllRoute.class);
		TabSpec tabSpecApple = tabHost
				.newTabSpec("Apple")
				.setIndicator(
						"",
						ressources
								.getDrawable(R.drawable.common_signin_btn_icon_disabled_focus_light))
				.setContent(intentApple);

		Intent intentRate = new Intent().setClass(TabActivity.this,
				BrtsRate.class);
		TabSpec tabSpecRate = tabHost
				.newTabSpec("Rate")
				.setIndicator("",
						ressources.getDrawable(R.drawable.ic_launcher))
				.setContent(intentRate);

		// Windows tab

		// add all tabs
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabSpecRate);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(2);
	}

}
