package com.example.broadcastrecdemo;



import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public void onCreate() {
		Log.i("onCreate", ">>>>>>>>>>>>" + true);
		super.onCreate();
	}

	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		Log.i("onStartCommand", ">>>>zaptech>>>>>>>>" + true);

		new Thread(new Runnable() {
			int k = 1;

			@Override
			public void run() {

				try {
					
					
					
					for (int i = 1; i <= 10; i++) {
						Intent mIntent=new Intent();
						mIntent.setAction(Home.sAction);
						Thread.sleep(2000);
						mIntent.putExtra("COUNTER",String.valueOf(i));
						sendBroadcast(mIntent);
//						mIntent.putExtra("COUNTER", k++);	
					}
					
					
				} catch (InterruptedException e) {
					// TODO: handle exception
				}

			}
		}).start();
		return START_REDELIVER_INTENT;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}
}
