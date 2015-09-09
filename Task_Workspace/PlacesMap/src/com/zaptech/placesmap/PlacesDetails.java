package com.zaptech.placesmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PlacesDetails extends Activity {

	Intent getDetailIntent;
	String strTitle;
	TextView txtPlaceDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_places_details);
		init();

		getDetailIntent = getIntent();
		strTitle = getDetailIntent.getStringExtra("getTitle");
		txtPlaceDetails.setText(strTitle);
	}

	public void init() {
		txtPlaceDetails = (TextView) findViewById(R.id.txtPlaceDetails);

	}
}
