package com.example.locationalarm.service;

import java.util.List;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.locationalarm.activity.BusStopAlarmActivity;
import com.example.locationalarm.database.DatabaseHelper;
import com.example.locationalarm.domain.BusRoute;
import com.google.android.gms.location.LocationListener;

public class GPSTracker extends Service implements LocationListener {

	private final Context mContext;

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	Location location;
	Location location2;// location
	double latitude; // latitude
	double longitude; // longitude
	double first = 23.0391688;
	double second = 72.5053849;

	double third = 23.0391688;
	double fourth = 72.5053849;

	private BusStopAlarmActivity activity;
	private DatabaseHelper helper;

	Boolean set = true;

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000; // 1 minute

	private static final int RQS_1 = 0;
	private Intent intentTemplate = null;
	private Runnable onTimeout = null;

	// Declaring a Location Manager
	protected LocationManager locationManager;

	public GPSTracker(Context context) {
		this.mContext = context;

		getLocation();

	}

	public Location getLocation() {
		try {
			locationManager = (LocationManager) mContext
					.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				this.canGetLocation = true;
				// First get location from Network Provider
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES,
							(android.location.LocationListener) this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES,
								(android.location.LocationListener) this);
						Log.d("GPS Enabled", "GPS Enabled");

						Toast.makeText(mContext, "" + location.getLatitude(),
								Toast.LENGTH_SHORT).show();
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	/**
	 * Stop using GPS listener Calling this function will stop using GPS in your
	 * app
	 * */
	public void stopUsingGPS() {
		if (locationManager != null) {
			locationManager
					.removeUpdates((android.location.LocationListener) GPSTracker.this);
		}
	}

	/**
	 * Function to get latitude
	 * */
	public double getLatitude() {
		if (location != null) {
			latitude = location.getLatitude();
		}

		// return latitude
		return latitude;
	}

	/**
	 * Function to get longitude
	 * */
	public double getLongitude() {
		if (location != null) {
			longitude = location.getLongitude();
		}

		// return longitude
		return longitude;
	}

	/**
	 * Function to check GPS/wifi enabled
	 * 
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return this.canGetLocation;
	}

	/**
	 * Function to show settings alert dialog On pressing Settings button will
	 * lauch Settings Options
	 * */
	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

		// Setting Dialog Title
		alertDialog.setTitle("GPS is settings");

		// Setting Dialog Message
		alertDialog
				.setMessage("GPS is not enabled. Do you want to go to settings menu?");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent();
						mContext.startActivity(intent);
					}
				});

		// on pressing cancel button
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Message
		alertDialog.show();
	}

	@Override
	public void onLocationChanged(Location location) {

		// Toast.makeText(mContext, ""+location.getLatitude(),
		// Toast.LENGTH_SHORT).show();
		helper = new DatabaseHelper(mContext);

		List<BusRoute> stops = helper.getQuestUserData();

		for (BusRoute stop : stops) {

		}

		/*
		 * if (set == true) { if (23.03 == 23.03 && 72.50 == 72.50) { Calendar
		 * cal = Calendar.getInstance(); Intent intent = new Intent(mContext,
		 * AlarmReceiver.class); PendingIntent pendingIntent =
		 * PendingIntent.getBroadcast( mContext, RQS_1, intent, 0); AlarmManager
		 * alarmManager = (AlarmManager) mContext
		 * .getSystemService(Context.ALARM_SERVICE);
		 * alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(),
		 * pendingIntent); set = false; } }
		 */
		Toast.makeText(
				mContext,
				"" + location.getLatitude() + "  &&&&&&&&&&&&  "
						+ location.getLongitude(), Toast.LENGTH_SHORT).show();

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}