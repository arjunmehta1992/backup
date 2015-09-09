package com.example.locationalarm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.locationalarm.R;

public class MainScreen extends Activity {

	private Button brts, places;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
		brts = (Button) findViewById(R.id.button1);
		places = (Button) findViewById(R.id.button2);
		brts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainScreen.this, TabActivity.class);
				startActivity(intent);

			}
		});

		places.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainScreen.this, PlaceDetail.class);
				startActivity(intent);

			}
		});
	}

}
