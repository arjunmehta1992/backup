package com.zaptech.placesmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;

public class HomeActivity extends FragmentActivity implements LocationListener {

	private GoogleMap googleMap;
	private Spinner spinnerCategory, spinnerKilometer;
	private DrawerLayout mydrawer;
	private static final String GOOGLE_API_KEY = "AIzaSyCPF03hvTSZT8L5nEsCPnhVE2CDtdf_cF0";
	private static final String BY994NK3R9C86MFKJV5G = "BY994NK3R9C86MFKJV5G";
	private ListView drawer;
	private ImageView img_Menu;
	double latitude = 0;
	double longitude = 0;
	public String getPlaceType;
	View CustomMarker;
	private int PROXIMITY_RADIUS = 1000;

	public static double currentLattitude;
	private StringBuilder urlString;
	public static double currentLongitude;

	public Context context;

	String str, kilometer;

	List<List<HashMap<String, String>>> routes;
	ArrayList<LatLng> points = new ArrayList<LatLng>();
	List<HashMap<String, String>> path;
	Polyline polyline;
	RelativeLayout relative_mapview;
	LocationManager locationManager;
	Location location;
	RelativeLayout viewContainer;
	String bestProvider;
	boolean statusOfGPS;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!isGooglePlayServicesAvailable()) {
			finish();
		}
		setContentView(R.layout.activity_home);
		init();

		if (netCheckin() == true) {
			FlurryAgent.setLogEnabled(false);
			// init Flurry
			FlurryAgent.init(HomeActivity.this, BY994NK3R9C86MFKJV5G);

			Toast.makeText(HomeActivity.this,
					"Internet connection is available", Toast.LENGTH_SHORT)
					.show();

			SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.googleMap);

			googleMap = fragment.getMap();
			googleMap.setMyLocationEnabled(true);

			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			boolean statusOfGPS = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			Criteria criteria = new Criteria();
			String bestProvider = locationManager.getBestProvider(criteria,
					true);

			// String bestProvider = LocationManager.GPS_PROVIDER;

			Location location = locationManager
					.getLastKnownLocation(bestProvider);

			if (location != null) {
				onLocationChanged(location);
			}
			locationManager
					.requestLocationUpdates(bestProvider, 20000, 0, this);

			Toast.makeText(HomeActivity.this, "Gps status" + CheckEnableGPS(),
					Toast.LENGTH_SHORT).show();

			googleMap.getUiSettings().setZoomControlsEnabled(true);
			if (CheckEnableGPS() == true) {
				currentLattitude = location.getLatitude();
				currentLongitude = location.getLongitude();
				LatLng latLng = new LatLng(latitude, longitude);
				googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

				Toast.makeText(HomeActivity.this,
						"Latittude=" + latitude + "\t Longitude=" + longitude,
						Toast.LENGTH_SHORT).show();
				final String[] arrayKilometer = getResources().getStringArray(
						R.array.place_kilometer);
				spinnerKilometer
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {

								kilometer = arrayKilometer[position];
								kilometer = kilometer.concat("000");

								Toast.makeText(HomeActivity.this,
										"Meter" + kilometer, Toast.LENGTH_SHORT)
										.show();

								FlurryAgent.logEvent("Spinner Selected");
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});

				final String[] array = getResources().getStringArray(
						R.array.place_type);
				spinnerCategory
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {

								int position = spinnerCategory
										.getSelectedItemPosition();
								Toast.makeText(getApplicationContext(),
										"You have selected " + array[position],
										Toast.LENGTH_LONG).show();

								getPlaceType = array[position];

								StringBuilder googlePlacesUrl = new StringBuilder(
										"https://maps.googleapis.com/maps/api/place/search/json?location=");
								googlePlacesUrl.append(latitude + ","
										+ longitude);
								googlePlacesUrl.append("&radius=" + kilometer);
								googlePlacesUrl
										.append("&types=" + getPlaceType);
								googlePlacesUrl.append("&sensor=true");
								googlePlacesUrl
										.append("&key=" + GOOGLE_API_KEY);

								GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();

								Object[] toPass = new Object[3];
								toPass[0] = googleMap;
								toPass[1] = googlePlacesUrl.toString();
								toPass[2] = spinnerCategory.getItemAtPosition(
										position).toString();

								googlePlacesReadTask.execute(toPass);

							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {

							}

						});

				googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

					@Override
					public boolean onMarkerClick(Marker arg0) {

						if (polyline != null) {
							polyline.remove();
						}

						int zoom = (int) googleMap.getCameraPosition().zoom;

						Log.d("Lat Long>>>>>>>>>", "++++++++cur lat"
								+ HomeActivity.currentLattitude + "cur long"
								+ HomeActivity.currentLongitude);

						LatLng lat = arg0.getPosition();

						Log.i("hello", "++++++++++++++++++++++++++++++++" + lat);

						urlString = new StringBuilder();

						urlString
								.append("http://maps.googleapis.com/maps/api/directions/json");
						urlString.append("?origin=");// from
						urlString.append(HomeActivity.currentLattitude);
						urlString.append(",");
						urlString.append(HomeActivity.currentLongitude);
						urlString.append("&destination=");// to
						urlString.append(lat.latitude);
						urlString.append(",");
						urlString.append(lat.longitude);
						Log.i("", "URL=====" + urlString);

						// Start downloading json data from Google Directions
						// API

						return false;

					}

				});

				googleMap
						.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

							@Override
							public void onInfoWindowClick(Marker arg0) {

								Intent intent = new Intent(HomeActivity.this,
										PlacesDetails.class);
								intent.putExtra("getTitle", arg0.getTitle());

								startActivity(intent);

							}
						});

				googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {

					@Override
					public View getInfoWindow(Marker arg0) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public View getInfoContents(Marker arg0) {
						// TODO Auto-generated method stub

						LayoutInflater inflater = (LayoutInflater) context
								.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

						final View infoView = inflater.inflate(
								R.layout.activity_place_info, null);

						TextView tv_placeName = (TextView) infoView
								.findViewById(R.id.txt_PlaceDetails);

						TextView tv_placeAddress = (TextView) infoView
								.findViewById(R.id.txt_PlaceAddress);

						Button btn_ShowDetail = (Button) infoView
								.findViewById(R.id.btn_ShowDetail);
						Button btn_HideMe = (Button) infoView
								.findViewById(R.id.btn_HideMe);

						System.err
								.println("++++++++++++++++++INFO WINDOW=========="
										+ arg0.getTitle());

						String PlaceName = arg0.getTitle();

						String[] separated = PlaceName.split(":");

						tv_placeName.append(separated[0]);
						tv_placeAddress.append(separated[1]);

						return infoView;

					}
				});
			}

		} else {
			Toast.makeText(HomeActivity.this,
					"Internet connection is not enable", Toast.LENGTH_SHORT)
					.show();
		}

	}

	public void init() {
		spinnerCategory = (Spinner) findViewById(R.id.spinner_Category);
		spinnerKilometer = (Spinner) findViewById(R.id.spinner_Kilometer);

		context = HomeActivity.this;
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

	public void onLocationChanged(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		// googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
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

	// Route Code

	private boolean netCheckin() {
		try {
			ConnectivityManager nInfo = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			nInfo.getActiveNetworkInfo().isConnectedOrConnecting();

			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnectedOrConnecting()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	void movingOnCameraLocation() {
		CameraPosition cameraPosition = new CameraPosition.Builder().zoom(12)
				.build();

		googleMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));
	}

	private boolean CheckEnableGPS() {
		String provider = Settings.Secure.getString(getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if (!provider.equals("")) {

			Toast.makeText(HomeActivity.this, "GPS Enabled: " + provider,
					Toast.LENGTH_LONG).show();
			return true;
		} else {
			Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			startActivity(intent);
			return false;
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
		FlurryAgent.onStartSession(HomeActivity.this, BY994NK3R9C86MFKJV5G);
		FlurryAgent.logEvent("Started");
		FlurryAgent.setLogEnabled(true);
		FlurryAgent.setLogEvents(true);
		FlurryAgent.setLogLevel(Log.VERBOSE);
	}

	@Override
	protected void onStop() {

		super.onStop();
		FlurryAgent.logEvent("Closed");
		FlurryAgent.onEndSession(HomeActivity.this);
	}
}
