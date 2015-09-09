package com.example.vpn;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	private TextView mServerAddress;
	private TextView mServerPort;
	private TextView mSharedSecret;
	private Button btnConnect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mServerAddress = (TextView) findViewById(R.id.address);
		mServerPort = (TextView) findViewById(R.id.port);
		mSharedSecret = (TextView) findViewById(R.id.secret);
		btnConnect = (Button) findViewById(R.id.connect);
		btnConnect.setOnClickListener(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			String prefix = getPackageName();
			Intent intent = new Intent(this, ToyVpnService.class)
					.putExtra(prefix + ".ADDRESS",
							mServerAddress.getText().toString())
					.putExtra(prefix + ".PORT",
							mServerPort.getText().toString())
					.putExtra(prefix + ".SECRET",
							mSharedSecret.getText().toString());
			startService(intent);
		}

	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = VpnService.prepare(this);
		if (intent != null) {
			startActivityForResult(intent, 0);
		} else {
			onActivityResult(0, RESULT_OK, null);
		}
	}

}
