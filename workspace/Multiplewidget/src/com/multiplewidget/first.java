package com.multiplewidget;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class first extends Activity {

	
	NotificationManager nm;
	EditText title,subject,body;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		title=(EditText) findViewById(R.id.editText1);
		subject=(EditText)findViewById(R.id.editText2);
		body=(EditText)findViewById(R.id.editText3);
		
		
		
		
		
		
	}
	
	
	public void notify(View v)
	{
		String title_noti=title.getText().toString();
		String subject_noti=title.getText().toString();
		String body_noti=title.getText().toString();
		nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		@SuppressWarnings("deprecation")
		Notification notify=new Notification(android.R.drawable.
			      stat_notify_more,title_noti,System.currentTimeMillis());
		PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0,new Intent(), 0);
		notify.setLatestEventInfo(getApplicationContext(), title_noti, body_noti, pending);
		nm.notify(0, notify);
		
		
		
		
		
	}
}
