package com.zaptech.broadcastreceiverdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {

	public static final String mStringData="StringData";
	private TextView mTxtData;
	private IntentFilter mfilter;
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mTxtData=(TextView) findViewById(R.id.data);
		mfilter=new IntentFilter();
		mfilter.addAction(mStringData);
	
		
		Intent serviceIntent=new Intent(HomeActivity.this, BroadcastService.class);
		startService(serviceIntent);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		registerReceiver(mReceiver, mfilter);
	}
	private BroadcastReceiver mReceiver=new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			mTxtData.setText(mTxtData.getText()+"BroadCast From Service: \n");
				mTxtData.setText(mTxtData.getText()+intent.getStringExtra("Data")+"\n\n\n");
			
			
			Intent stopIntent = new Intent(HomeActivity.this,
					BroadcastService.class);
			stopService(stopIntent);

		}
	};
		
	@Override
	protected void onPause() {
		unregisterReceiver(mReceiver);
		super.onPause();
	}

}
