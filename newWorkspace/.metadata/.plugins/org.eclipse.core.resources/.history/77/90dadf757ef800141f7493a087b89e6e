package com.example.broadcastrecdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}

	public void broadcastIntent(View view) {
		Intent intent = new Intent();
		intent.setAction("hiii");
		sendBroadcast(intent);
	}
}
