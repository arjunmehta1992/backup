package com.example.googlemaps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

	GoogleMap map;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
 
        LatLng latLng = new LatLng(13.05241, 80.25082);
        map.addMarker(new MarkerOptions().position(latLng).title("Arjun"));
	 
	}
	
	
	
}
