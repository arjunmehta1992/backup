package com.zaptech.religiousapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class Splash extends ActionBarActivity {

	private static final long SPLASH_DISPLAY_LENGTH = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the Menu-Activity. */
				Intent mainIntent = new Intent(Splash.this, HomeActivity.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);

	}

}
