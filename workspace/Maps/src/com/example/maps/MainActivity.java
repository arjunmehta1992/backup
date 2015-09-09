package com.example.maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements OnClickListener,
		LocationListener {

	private GoogleMap googleMap;
	/*
	 * private LatLngBounds AUSTRALIA = new LatLngBounds(new LatLng(-44, 113),
	 * new LatLng(-10, 154));
	 */
	Marker mMarker;
	Button zoom_in, zoom_out;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		googleMap = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		googleMap.setMyLocationEnabled(true);
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String bestProvider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(bestProvider);
		if (location != null) {
			onLocationChanged(location);
		}
		locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);

		GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
			@Override
			public void onMyLocationChange(Location arg0) {
				// TODO Auto-generated method stub
				LatLng location = new LatLng(arg0.getLatitude(),
						arg0.getLongitude());
				/*mMarker = googleMap.addMarker(new MarkerOptions()
						.position(location));*/
				if (googleMap != null) {
					googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
							location, 16.0f));
				}
			}
		};

		zoom_in.setOnClickListener(this);
		zoom_out.setOnClickListener(this);
	}

	private FragmentManager getSupportFragmentManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init() {
		zoom_in = (Button) findViewById(R.id.Zoom_in);
		zoom_out = (Button) findViewById(R.id.Zoom_out);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.Zoom_in:

			googleMap.animateCamera(CameraUpdateFactory.zoomIn());

			break;
		case R.id.Zoom_out:

			googleMap.animateCamera(CameraUpdateFactory.zoomOut());

			break;

		default:
			break;
		}

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		googleMap.addMarker(new MarkerOptions().position(latLng));
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	private boolean isGooglePlayServicesAvailable() {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (ConnectionResult.SUCCESS == status) {
			return true;
		} else {
			GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
			return false;
		}
	}

}
