package com.example.test;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private ProgressBar progress;
	private Handler progressBarHandler = new Handler();
	private int progressStatus = 0;
	private TextView textView;
	private Handler handler = new Handler();

	private TextView text;
	private Button btn_frm;
	int i;
	int notificationCount;

	private CalendarView mCalenderView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progress = (ProgressBar) findViewById(R.id.progressBar);
		textView = (TextView) findViewById(R.id.txt_percentage);
		mCalenderView = (CalendarView) findViewById(R.id.calendar);
		btn_frm = (Button) findViewById(R.id.btn_notifyMe);
		text = (TextView) findViewById(R.id.txt_Text);

		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shruti.ttf");

		text.setTypeface(tf);
		text.setText("સતત વોટ્સએપ પર લાગેલા રહેતા મિત્રોનું મગજ પણ સતત એ બાજુ...");

		btn_frm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendNotification("Hii");
				Intent intent = new Intent(MainActivity.this, LoadHtml.class);
				startActivity(intent);

			}
		});

		new Thread(new Runnable() {
			public void run() {
				while (progressStatus < 100) {
					progressStatus += 1;
					handler.post(new Runnable() {
						public void run() {
							progress.setProgress(progressStatus);
							textView.setText(progressStatus + "%");
						}
					});
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		mCalenderView.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				// TODO Auto-generated method stub

				Toast.makeText(
						getBaseContext(),
						"Selected Date is\n\n" + dayOfMonth + " / " + month
								+ " / " + year, Toast.LENGTH_LONG).show();

			}
		});

	}

	private void sendNotification(String string) {
		Log.d("AlarmService", "Preparing to send notification...: ");
		NotificationManager alarmNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

		notificationCount++;

		NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
				this)
				.setContentTitle("Test")
				.setSmallIcon(R.drawable.ic_launcher)
				.setStyle(
						new NotificationCompat.BigTextStyle()
								.bigText("hi You have " + notificationCount
										+ " notifications"))
				.setContentText(string);

		alamNotificationBuilder.setContentIntent(contentIntent);
		alarmNotificationManager.notify(1, alamNotificationBuilder.build());

	}

}
