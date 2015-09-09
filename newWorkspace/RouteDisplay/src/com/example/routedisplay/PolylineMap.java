package com.example.routedisplay;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

public class PolylineMap extends Activity implements OnMapReadyCallback{

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_polyline);
		 MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment1);
		 mapFragment.getMapAsync(this);
		 
		
		
	}
	
	
	@Override
	public void onMapReady(GoogleMap map) {
		// TODO Auto-generated method stub
		
		 LatLng mapCenter = new LatLng(41.889, -87.622);

	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));

	        // Flat markers will rotate when the map is rotated,
	        // and change perspective when the map is tilted.
	        map.addMarker(new MarkerOptions()
	                .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow))
	                .position(mapCenter)
	                .flat(true)
	                .rotation(245));

	        CameraPosition cameraPosition = CameraPosition.builder()
	                .target(mapCenter)
	                .zoom(13)
	                .bearing(90)
	                .build();

	        // Animate the change in camera view over 2 seconds
	        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
	                2000, null);

	}

	
}
