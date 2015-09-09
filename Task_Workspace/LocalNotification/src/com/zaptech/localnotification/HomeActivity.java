package com.zaptech.localnotification;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity implements OnClickListener {

	EditText edt_Time, edt_Date;
	AlarmManager alarmManager;
	Button btn_NotifyMe;
	ImageView img_Date, img_Time;
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	public int year, month, day, hour, minute;
	private int mYear, mMonth, mDay, mHour, mMinute;
	private NotificationManager alarmNotificationManager;
	private PendingIntent pendingIntent;
	private TimePicker mTimePicker;
	public long getMinutes;
	public long getHour;
	BroadcastReceiver mReceiver;
	private static HomeActivity inst;

	public static HomeActivity instance() {
		return inst;
	}

	public HomeActivity() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		clickListener();

		mTimePicker = new TimePicker(HomeActivity.this);
		getMinutes = mTimePicker.getCurrentMinute();
		Calendar c = Calendar.getInstance();
		int seconds = c.get(Calendar.SECOND);
		int minutes = c.get(Calendar.MINUTE);
		int hour = c.get(Calendar.HOUR);

		Toast.makeText(HomeActivity.this,
				"" + hour + ":" + minutes + ":" + seconds, Toast.LENGTH_SHORT)
				.show();
		RegisterAlarmBroadcast();

	}

	public void clickListener() {
		img_Date.setOnClickListener(this);
		img_Time.setOnClickListener(this);
		btn_NotifyMe.setOnClickListener(this);
	}

	public void init() {

		img_Date = (ImageView) findViewById(R.id.img_Date);
		img_Time = (ImageView) findViewById(R.id.img_TimePicker);
		edt_Time = (EditText) findViewById(R.id.edt_Time);
		edt_Date = (EditText) findViewById(R.id.edt_Date);
		btn_NotifyMe = (Button) findViewById(R.id.btn_NotifyMe);
		/*alarmManager = (AlarmManager) (this
				.getSystemService(Context.ALARM_SERVICE));*/

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.img_Date:
			showDialog(DATE_DIALOG_ID);
			break;

		case R.id.img_TimePicker:

			showDialog(TIME_DIALOG_ID);
			break;

		case R.id.btn_NotifyMe:

			// sendNotification("Hii");

			System.err.println("Hour minute" + hour + minute);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			calendar.set(Calendar.MINUTE, minute);
			System.err.println("cal" + calendar.getTimeInMillis());
			Intent myIntent = new Intent(HomeActivity.this, AlarmReceiver.class);
			pendingIntent = PendingIntent.getBroadcast(HomeActivity.this, 0,
					myIntent, 0);

			alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),
					pendingIntent);
			break;

		default:
			break;
		}

	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		// the callback received when the user "sets" the Date in the
		// DatePickerDialog

		@Override
		public void onDateSet(DatePicker view, int yearSelected,
				int monthOfYear, int dayOfMonth) {

			year = yearSelected;
			month = monthOfYear;
			day = dayOfMonth;
			// Set the Selected Date in Select date Button
			edt_Date.setText("Date selected : " + day + "-" + month + "-"
					+ year);

		}
	};

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		// the callback received when the user "sets" the TimePickerDialog in
		// the dialog

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int min) {

			hour = hourOfDay;
			minute = min;
			// Set the Selected Date in Select date Button
			edt_Time.setText("Time selected :" + hour + "-" + minute);

		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// create a new DatePickerDialog with values you want to show
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
			// create a new TimePickerDialog with values you want to show
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					false);

		}
		return null;
	}

	// private void sendNotification(String string) {
	// Log.d("AlarmService", "Preparing to send notification...: ");
	// alarmNotificationManager = (NotificationManager) this
	// .getSystemService(Context.NOTIFICATION_SERVICE);
	//
	// PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
	// new Intent(this, HomeActivity.class), 0);
	//
	// NotificationCompat.Builder alamNotificationBuilder = new
	// NotificationCompat.Builder(
	// this)
	// .setContentTitle(getResources().getString(R.string.app_name))
	// .setSmallIcon(R.drawable.ic_launcher)
	// .setStyle(new NotificationCompat.BigTextStyle().bigText("hii"))
	// .setContentText("Wake Up Please");
	//
	// alamNotificationBuilder.setContentIntent(contentIntent);
	// alarmNotificationManager.notify(1, alamNotificationBuilder.build());
	// Log.d("AlarmService", "Notification sent.");
	//
	// }
	public void setAlarmText(String string) {

		edt_Date.setText(string);

	}
	
	void RegisterAlarmBroadcast() {
		mReceiver = new BroadcastReceiver() {
			private static final String TAG = "Alarm Example Receiver";

			@Override
			public void onReceive(Context context, Intent intent) {
				Log.i(TAG,
						"BroadcastReceiver::OnReceive() >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

			Toast.makeText(context,
						"Congrats!. Your Alarm time has been reached",
						Toast.LENGTH_LONG).show();
				intent = new Intent(context, HomeActivity.class);
				startActivity(intent);

				// TODO Auto-generated method stub

			}
		};

		registerReceiver(mReceiver, new IntentFilter(
				"com.techblogon.alarmexample"));
		pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(
				"com.techblogon.alarmexample"), 0);
		alarmManager = (AlarmManager) (this
				.getSystemService(Context.ALARM_SERVICE));

	}

}