package com.example.locationalarm.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent myIntent = new Intent(context, AlarmActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Toast.makeText(context, "Alarm is set", Toast.LENGTH_SHORT).show();
		context.startActivity(myIntent);
	}

}