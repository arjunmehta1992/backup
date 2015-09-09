package com.example.relative_layout_demo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ZoomControls;

public class Activity_FourthScreen extends Activity {

	ZoomControls zoom;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth_screen);

		init();
		zoomInOut();
	}

	void init() {
		zoom = (ZoomControls) findViewById(R.id.zoomControls1);
		img = (ImageView) findViewById(R.id.imageView1);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	void zoomInOut() {
		zoom.setOnZoomInClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				float x = img.getScaleX();
				float y = img.getScaleY();

				img.setScaleX((float) (x + 1));
				img.setScaleY((float) (y + 1));
			}
		});

		zoom.setOnZoomOutClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				float x = img.getScaleX();
				float y = img.getScaleY();

				img.setScaleX((float) (x - 1));
				img.setScaleY((float) (y - 1));

			}
		});
	}

}
