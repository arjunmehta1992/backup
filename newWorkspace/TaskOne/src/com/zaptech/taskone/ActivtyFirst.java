package com.zaptech.taskone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowAnimationFrameStats;

public class ActivtyFirst extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_first);
	}

}
