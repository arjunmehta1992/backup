package com.example.locationalarm.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.example.locationalarm.R;
import com.example.locationalarm.service.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FamousPlaceDetail extends FragmentActivity {

	private TextView nameset, addressset, phonenumberset, phone, dset;
	int phonenumber1;
	private GPSTracker gpsTracker;
	private double latitude;
	private double longitude;
	private Button routeButton;
	private ImageView imageView;
	String[] filelist;
	GoogleMap map;
	SupportMapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.placedetail);
		mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map1);

		routeButton = (Button) findViewById(R.id.ButtonMap);
		dset = (TextView) findViewById(R.id.txtdescription);
		imageView = (ImageView) findViewById(R.id.imageView1);
		nameset = (TextView) findViewById(R.id.txtname);
		addressset = (TextView) findViewById(R.id.txtaddress);
		phonenumberset = (TextView) findViewById(R.id.txtphonenumber);
		phone = (TextView) findViewById(R.id.phone);

		phone.setVisibility(View.INVISIBLE);

		// Getting reference to the Google Map
		map = mapFragment.getMap();
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);

		final LatLng latLng = new LatLng(location.getLatitude(),
				location.getLongitude());
		Log.e("Latlang", "" + latLng);
		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		map.animateCamera(CameraUpdateFactory.zoomTo(10));
		MarkerOptions markerOptions = new MarkerOptions();

		markerOptions.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
		Marker marker = map.addMarker(markerOptions.position(latLng));

		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String name1 = name.replace(" ", "") + ".jpg";
		name1=name1.replace("/", "");
		name1=name1.replace("'", "");
		String address = intent.getStringExtra("address");
		Double lat = intent.getDoubleExtra("latitude", 0);
		Double lng = intent.getDoubleExtra("longitude", 0);
		final LatLng latLng2 = new LatLng(lat, lng);
		Log.i("latlang222222", "|||||||||||||||" + latLng2);

		String description = intent.getStringExtra("description");

		try {
			AssetManager am = this.getAssets();
			filelist = am.list("image");

			if (filelist == null) {
				// dir does not exist or is not a directory
			} else {
				for (int i = 0; i < filelist.length; i++) {
					// Get filename of file or directory
					String filename = filelist[i];
					if (filename.equalsIgnoreCase(name1)) {
						Log.i("filaname", "|||||||||||" + filename);
						InputStream bitmap = getAssets().open(
								"image/" + filename);
						Bitmap bit = BitmapFactory.decodeStream(bitmap);
						imageView.setImageBitmap(bit);
					}

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// /markerOptions.position(latLng2);
		markerOptions.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_RED));
		marker = map.addMarker(markerOptions.position(latLng2));
		marker.setTitle(name);

		routeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = getDirectionsUrl(latLng, latLng2);

				DownloadTask downloadTask = new DownloadTask();

				// Start downloading json data from Google Directions API
				downloadTask.execute(url);

			}
		});

		nameset.setText(name);
		addressset.setText(address);
		dset.setText(description);

	}

	private String getDirectionsUrl(LatLng latLng, LatLng latLng2) {
		// TODO Auto-generated method stub
		String str_origin = "origin=" + latLng.latitude + ","
				+ latLng.longitude;

		// Destination of route
		String str_dest = "destination=" + latLng2.latitude + ","
				+ latLng2.longitude;

		// Sensor enabled
		String sensor = "sensor=false";

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&" + sensor;

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;

		Log.e("url", "" + url);

		return url;

	}

	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String> {

		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			ParserTask parserTask = new ParserTask();
			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);

		}
	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				DirectionsJSONParser parser = new DirectionsJSONParser();

				// Starts parsing data
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();

			// Traversing through all the routes
			for (int i = 0; i < result.size(); i++) {
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				// Fetching i-th route
				List<HashMap<String, String>> path = result.get(i);

				// Fetching all the points in i-th route
				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);

					points.add(position);
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(2);
				lineOptions.color(Color.RED);

			}

			// Drawing polyline in the Google Map for the i-th route
			map.addPolyline(lineOptions);
		}
	}

}
