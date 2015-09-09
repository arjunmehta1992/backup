package com.example.customseekbar;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.app.AlarmManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.TimePicker.OnTimeChangedListener;

public class ActivityHome extends Activity {

	AlarmManager alarmMgr;
	Button btn_StartTime;
	TimePicker tmp_Time;
	TextView txt_getTime;
	Calendar calendar;
	Date inTime, outTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btn_StartTime = (Button) findViewById(R.id.btn_setTime);
		tmp_Time = (TimePicker) findViewById(R.id.tmPicker_TimeClock);
		txt_getTime = (TextView) findViewById(R.id.txt_selectedTime);

		tmp_Time.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub

				txt_getTime.setText(hourOfDay + ":" + minute);

				calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(calendar.MINUTE, minute);

			}
		});

		btn_StartTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/*
				 * long timeInMillis = System.currentTimeMillis(); Calendar cal1
				 * = Calendar.getInstance(); Calendar cal2 =
				 * Calendar.getInstance();
				 * 
				 * cal1.setTimeInMillis(timeInMillis); SimpleDateFormat
				 * dateFormat = new SimpleDateFormat("hh:mm:ss a");
				 *//*
					 * String dateforTimePicker = dateFormat.format(cal2);
					 * 
					 * String dateforrow = dateFormat.format(cal1.getTime());
					 * Toast.makeText(ActivityHome.this, dateforrow,
					 * Toast.LENGTH_SHORT).show();
					 */

				Calendar calDate = Calendar.getInstance();
				calDate.setTimeInMillis(0);
				int dayDateCal = calDate.get(Calendar.DAY_OF_MONTH);
				int monthDateCal = calDate.get(Calendar.MONTH);
				int yearDateCal = calDate.get(Calendar.YEAR);

				String day = String.valueOf(dayDateCal);
				String month = String.valueOf(monthDateCal);
				String year = String.valueOf(yearDateCal);

				Toast.makeText(ActivityHome.this, " " + day + month + year,
						Toast.LENGTH_SHORT).show();

				Calendar calDate2 = Calendar.getInstance();
				calDate2.setTimeInMillis(0);
				int dayDateCal2 = calDate.get(Calendar.DAY_OF_MONTH);
				int monthDateCal2 = calDate.get(Calendar.MONTH);
				int yearDateCal2 = calDate.get(Calendar.YEAR);

				String day2 = String.valueOf(dayDateCal2);
				String month2 = String.valueOf(monthDateCal2);
				String year2 = String.valueOf(yearDateCal2);

				Toast.makeText(ActivityHome.this, " " + day2 + month2 + year2,
						Toast.LENGTH_SHORT).show();
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date1 = (Date) sdf.parse(year +"-"+ month + "-"
							+ day);
					Date date2 = (Date) sdf.parse(year2 + "-" + month2 + "-"
							+ day2);

					Toast.makeText(ActivityHome.this, "" + date1 + date2,
							Toast.LENGTH_SHORT).show();

					if (sdf.format(date1).equals(sdf.format(date2))) {

						Toast.makeText(ActivityHome.this,
								"Current date is today", Toast.LENGTH_SHORT)
								.show();

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		});

	}
}
