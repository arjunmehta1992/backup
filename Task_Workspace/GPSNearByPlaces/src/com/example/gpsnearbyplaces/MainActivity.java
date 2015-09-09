package com.example.gpsnearbyplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	RelativeLayout raltiveHotel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		raltiveHotel = (RelativeLayout) findViewById(R.id.rel1);
		raltiveHotel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						SearchHotelActivity.class);
				startActivity(i);
			}
		});

	}
}
