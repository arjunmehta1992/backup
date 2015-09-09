package com.example.musicshare;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Home extends Activity implements OnClickListener {

	Button btn_loadfile;
	Button On, Off, Visible, list;
	BluetoothAdapter bluetoothAdapter;
	ListView lv;
	Set<BluetoothDevice> pairedDevices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		btn_loadfile.setOnClickListener(this);
		On.setOnClickListener(this);
		Off.setOnClickListener(this);
		Visible.setOnClickListener(this);
		list.setOnClickListener(this);

	}

	public void init() {
		btn_loadfile = (Button) findViewById(R.id.Load_btn);
		On = (Button) findViewById(R.id.button1);
		Off = (Button) findViewById(R.id.button2);
		Visible = (Button) findViewById(R.id.button3);
		list = (Button) findViewById(R.id.button4);
		lv = (ListView) findViewById(R.id.listView1);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.Load_btn:
			Intent intent = new Intent(Home.this, LoadFiles.class);
			startActivity(intent);

			break;

		case R.id.button1:

			if (!bluetoothAdapter.isEnabled()) {
				Intent turnOn = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(turnOn, 0);
				Toast.makeText(getApplicationContext(), "Turned on",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), "Already on",
						Toast.LENGTH_LONG).show();
			}
			break;

		case R.id.button2:
			bluetoothAdapter.disable();
			Toast.makeText(getApplicationContext(), "Turned off",
					Toast.LENGTH_LONG).show();
			break;

		case R.id.button3:
			Intent getVisible = new Intent(BluetoothAdapter.
				      ACTION_REQUEST_DISCOVERABLE);
				      startActivityForResult(getVisible, 0);
			break;

		case R.id.button4:

			pairedDevices = bluetoothAdapter.getBondedDevices();

			ArrayList list = new ArrayList();
			for (BluetoothDevice bt : pairedDevices)
				list.add(bt.getName());

			Toast.makeText(getApplicationContext(), "Showing Paired Devices",
					Toast.LENGTH_SHORT).show();
			ArrayAdapter adapter = new ArrayAdapter(Home.this,
					android.R.layout.simple_list_item_1, list);
			lv.setAdapter(adapter);

			break;

		default:
			break;
		}

	}

}