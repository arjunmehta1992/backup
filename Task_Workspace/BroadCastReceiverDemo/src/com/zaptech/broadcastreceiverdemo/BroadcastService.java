package com.zaptech.broadcastreceiverdemo;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BroadcastService extends Service {
	private String mLog;
	private ArrayList<String> mList;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mLog=this.getClass().getSimpleName();
		Log.i(mLog, "You Are In onCreate.....");
		mList=new ArrayList<String>();
		mList.add("Android");
		mList.add("IOS");
		mList.add("PHP");
		mList.add(".Net");
		mList.add("JAVA");
		mList.add("C");
		mList.add("C++");
		mList.add("VB");
				
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(mLog, "You Are In onStartCommand....");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				for (int i = 0; i < mList.size(); i++) {
					Intent mIntent=new Intent();
					mIntent.setAction(HomeActivity.mStringData);
					mIntent.putExtra("Data", mList.get(i));
					sendBroadcast(mIntent);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						
					}
				}
				
			}	
		}).start();
		return START_REDELIVER_INTENT;
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(mLog, "You Are In onBind....");
		return null;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(mLog, "You Are In onDestroy......");
	}
	

}
