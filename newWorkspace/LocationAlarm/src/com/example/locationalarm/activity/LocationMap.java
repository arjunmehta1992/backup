package com.example.locationalarm.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

import com.example.locationalarm.R;
import com.example.locationalarm.domain.BusRoute;
import com.example.locationalarm.service.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationMap extends FragmentActivity {

	LinearLayout linearLayout;
	GoogleMap map;
	SupportMapFragment mapFragment;

	ArrayList<BusRoute> myList;
	ArrayList<LatLng> latlngArray;

	GPSTracker gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		LatLng latLng = new LatLng(23.0300, 72.5800);

		myList = (ArrayList<BusRoute>) getIntent().getSerializableExtra(
				"mylist");

		mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map1);

		// Getting reference to the Google Map
		map = mapFragment.getMap();
		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		map.animateCamera(CameraUpdateFactory.zoomTo(10));

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();

		Intent intent = getIntent();

		int position = intent.getIntExtra("position", -1);

		for (int i = 0; i < myList.size(); i++) {

			BitmapDescriptor icon = BitmapDescriptorFactory
					.fromResource(R.drawable.image3);
			LatLng latLng2 = new LatLng(myList.get(i).getLATITUDE(), myList
					.get(i).getLONGITUDE());
			Marker marker = map
					.addMarker(new MarkerOptions().position(latLng2));
			marker.setTitle(myList.get(i).getSTATION());

			marker.setIcon(icon);

		}

		if (position != -1) {

			BitmapDescriptor icon = BitmapDescriptorFactory
					.fromResource(R.drawable.image3);
			LatLng latLng2 = new LatLng(myList.get(position).getLATITUDE(),
					myList.get(position).getLONGITUDE());
			Marker marker = map
					.addMarker(new MarkerOptions().position(latLng2));
			marker.setTitle(myList.get(position).getSTATION());
			map.moveCamera(CameraUpdateFactory.newLatLng(latLng2));
			map.animateCamera(CameraUpdateFactory.zoomTo(20));

			marker.setIcon(icon);
			marker.showInfoWindow();

		}

		String provider = locationManager.getBestProvider(criteria, true);

	}

}
