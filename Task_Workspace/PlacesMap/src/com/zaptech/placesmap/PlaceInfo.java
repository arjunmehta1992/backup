package com.zaptech.placesmap;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PlaceInfo extends ActionBarActivity {

	private TextView txt_PlaceName;
	private TextView txt_PlaceAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_info);

		txt_PlaceName = (TextView) findViewById(R.id.txt_PlaceDetails);
		txt_PlaceAddress = (TextView) findViewById(R.id.txt_PlaceAddress);

		Intent intent = getIntent();
		txt_PlaceName.append(intent.getStringExtra("place"));

	}

}
