package com.example.alarmmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ClockHome extends Activity implements OnClickListener {

	AlarmManager alarmManager;
	private PendingIntent pendingIntent;
	private TimePicker alarmTimePicker;

	private TextView alarmTextView;

	private static ClockHome inst;

	public static ClockHome instance() {
		return inst;
	}

	public long getMinutes;
	public long getHour;

	public void onStart() {
		super.onStart();
		inst = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock_home);

		init();

		getMinutes = timePicker.getCurrentMinute();

		Toast.makeText(ClockHome.this,
				" " + getMinutes + System.currentTimeMillis(),
				Toast.LENGTH_LONG).show();
		setAlarm.setOnClickListener(this);
		timePicker.setOnClickListener(this);

		RegisterAlarmBroadcast();

		boolean getNetworkStatus = isNetworkConnected();
		if (getNetworkStatus == false) {
			Toast.makeText(ClockHome.this, "Network is not available",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(ClockHome.this, "Network is available",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void init() {
		alarmTimePicker = (TimePicker) findViewById(R.id.timePicker1);
		alarmTextView = (TextView) findViewById(R.id.textView1);

		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.setAlarm:

			/*
			 * timeText.setText(timePicker.getCurrentHour().toString() + ":" +
			 * timePicker.getCurrentMinute().toString()); long totalSeconds =
			 * timePicker.getCurrentMinute() * 1000 * 60 - getMinutes * 1000 *
			 * 60;
			 * 
			 * long TotalHours = timePicker.getCurrentHour() * 3600 * 1000 -
			 * getHour * 3600 * 1000;
			 * 
			 * Toast.makeText(ClockHome.this, " " + TotalHours +
			 * System.currentTimeMillis(), Toast.LENGTH_LONG).show();
			 * alarmManager.set(AlarmManager.RTC_WAKEUP,
			 * System.currentTimeMillis() + totalSeconds, pendingIntent);
			 */

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
			calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
			Intent myIntent = new Intent(ClockHome.this, AlarmReceiver.class);
			pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0,
					myIntent, 0);
			alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),
					pendingIntent);
			break;

		default:
			break;
		}

	}

	/*void RegisterAlarmBroadcast() {
		mReceiver = new BroadcastReceiver() {
			private static final String TAG = "Alarm Example Receiver";

			@Override
			public void onReceive(Context context, Intent intent) {
				Log.i(TAG,
						"BroadcastReceiver::OnReceive() >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				Toast.makeText(context,
						"Congrats!. Your Alarm time has been reached",
						Toast.LENGTH_LONG).show();
				displayAlert();
				// TODO Auto-generated method stub

			}
		};*/

		/*registerReceiver(mReceiver, new IntentFilter(
				"com.techblogon.alarmexample"));
		pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(
				"com.techblogon.alarmexample"), 0);
		alarmManager = (AlarmManager) (this
				.getSystemService(Context.ALARM_SERVICE));

	}*/


	private void displayAlert() {

		final MediaPlayer mp = MediaPlayer.create(this, R.raw.song);
		mp.start();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Tap on yes to stop")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (mp.isPlaying() == true) {
									mp.pause();
									dialog.cancel();
									alarmManager.cancel(pendingIntent);
								}
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.setTitle("Wake-up plz");
		alert.setIcon(R.drawable.ic_launcher);
		alert.show();
	}

	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}

	public void setAlarmText(String string) {

		alarmTextView.setText(string);

	}

	
	
	
	
}
