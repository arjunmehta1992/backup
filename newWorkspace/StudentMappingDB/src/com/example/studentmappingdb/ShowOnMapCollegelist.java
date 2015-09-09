package com.example.studentmappingdb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowOnMapCollegelist extends Activity implements
		OnMapReadyCallback {

	Fragment mapFragment;
	Intent i;
	String Detail, lat, longs, clgName, no, clgDetail;
	Double latDouble, longDouble;
	String number;
	int stdNo;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_on_map_collegelist);

		mapFragment = getFragmentManager().findFragmentById(R.id.fragment1);

		((MapFragment) mapFragment).getMapAsync(this);

	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		// TODO Auto-generated method stub

		i = getIntent();
		clgName = i.getStringExtra("Clgname");
		no = String.valueOf(stdNo);
		number = i.getStringExtra("Stdno");
		clgDetail = i.getStringExtra("clgDetail");
		lat = i.getStringExtra("Lat");
		longs = i.getStringExtra("Long");
		latDouble = Double.parseDouble(lat);
		longDouble = Double.parseDouble(longs);
		Toast.makeText(ShowOnMapCollegelist.this, "" + number,
				Toast.LENGTH_SHORT).show();
		Toast.makeText(ShowOnMapCollegelist.this, " " + latDouble + longDouble,
				Toast.LENGTH_SHORT).show();
		LatLng abad = new LatLng(latDouble, longDouble);
		String snippetString = "";
		arg0.setMyLocationEnabled(true);
		arg0.moveCamera(CameraUpdateFactory.newLatLngZoom(abad, 13));

		arg0.addMarker(new MarkerOptions()
				.title(clgName)
				.snippet(
						"Total no of Students:" + number
								+ "\n College Details:" + clgDetail)
				.position(abad));

	}

}
