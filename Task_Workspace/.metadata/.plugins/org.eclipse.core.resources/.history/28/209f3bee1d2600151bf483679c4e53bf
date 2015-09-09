package com.example.broadcastrecdemo;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	public static String sAction = "BROADCAST";
	IntentFilter filter;
	private TextView mTxtData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mTxtData = (TextView) findViewById(R.id.textView1);
		Intent myinIntent = new Intent(Home.this, MyService.class);
		startService(myinIntent);
		filter = new IntentFilter();
		filter.addAction(sAction);

	}

	@Override
	protected void onResume() {
		registerReceiver(mReceiver, filter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		unregisterReceiver(mReceiver);

		super.onPause();
	}

	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(Home.this,
					"Paused " + intent.getStringExtra("COUNTER"),
					Toast.LENGTH_SHORT).show();
			
			mTxtData.setText(mTxtData.getText()+intent.getStringExtra("COUNTER")+"\n");
			
			Intent stopIntent = new Intent(Home.this, MyService.class);
			stopService(stopIntent);

		}
	};
}
