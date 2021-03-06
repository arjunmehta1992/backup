package com.zaptech.androidtablayoutactivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
	/** Called when the activity is first created. */
	Button btnAdd;
	TabHost tabHost;
	TabSpec songspec;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_tab_layout);

		tabHost = getTabHost();

		TabSpec photospec = tabHost.newTabSpec("Photos");

		photospec.setIndicator("",
				getResources().getDrawable(R.drawable.photoes));
		Intent photosIntent = new Intent(this, PhotoesActivity.class);
		photospec.setContent(photosIntent);

		songspec = tabHost.newTabSpec("Songs");
		songspec.setIndicator("Songs",
				getResources().getDrawable(R.drawable.songs));
		Intent songsIntent = new Intent(this, SongsActivity.class);
		songspec.setContent(songsIntent);

		TabSpec videospec = tabHost.newTabSpec("Videos");
		videospec.setIndicator("Videos",
				getResources().getDrawable(R.drawable.videos));
		Intent videosIntent = new Intent(this, VideosActivity.class);
		videospec.setContent(videosIntent);

		tabHost.addTab(photospec); // Adding photos tab
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				tabHost.addTab(songspec);
			}
		});

	}
}