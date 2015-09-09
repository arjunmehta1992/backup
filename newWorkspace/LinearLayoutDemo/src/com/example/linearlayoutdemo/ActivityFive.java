package com.example.linearlayoutdemo;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ActivityFive extends ActionBarActivity implements OnClickListener{

	TimePicker timePicker;
	Button setAlarm;
	TextView timeText;
	AlarmManager alarmManager;
	PendingIntent pendingIntent;
	BroadcastReceiver mReceiver;

	public long getMinutes;
	public long getHour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_five);
		init();
		setAlarm.setOnClickListener(this);
		timePicker.setOnClickListener(this);
	}

	public void init() {
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		setAlarm = (Button) findViewById(R.id.setAlarm);
		timeText = (TextView) findViewById(R.id.textView1);

	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.setAlarm:

			timeText.setText(timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute());

			break;

		default:
			break;
		}

	}
}