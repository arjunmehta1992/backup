package com.example.servicedemo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button btn_getTime;
	TextView txt_selectedTime;
	TimePicker tmPick_timePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

	}

	public void init() {
		btn_getTime = (Button) findViewById(R.id.btn_setTimer);
		txt_selectedTime = (TextView) findViewById(R.id.txt_selectedTime);
		tmPick_timePicker = (TimePicker) findViewById(R.id.timePicker1);
		btn_getTime.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btn_setTimer:

			int hour = tmPick_timePicker.getCurrentHour();
			int minute = tmPick_timePicker.getCurrentMinute();

			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
			String strDate = sdf.format(c.getTime());
			String[] str = strDate.split(":", 1);

			txt_selectedTime
					.setText(hour + ":" + minute + Arrays.toString(str));

			Intent intent=new Intent(getBaseContext(),MyService.class);
			
				startService(intent);
				
			break;

		default:
			break;
		}
	}

	
}
