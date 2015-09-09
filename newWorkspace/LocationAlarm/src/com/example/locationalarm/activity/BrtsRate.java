package com.example.locationalarm.activity;

import java.util.regex.Pattern;

import com.example.locationalarm.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class BrtsRate extends Activity {
	Spinner spinSource, spinDestination;
	String[] mTestArray;
	ArrayAdapter<String> dataAdapter;
	String[] SAddress;
	String[] DAddress;
	TextView tvprice;
	int pSource = 0, pDestination = 0;
	String price = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brts_rate);

		spinSource = (Spinner) findViewById(R.id.spinSource);
		spinDestination = (Spinner) findViewById(R.id.spinDestination);
		tvprice = (TextView) findViewById(R.id.tvprice);

		Log.i("Data", getResources().getString(R.string.str));

		mTestArray = getResources().getStringArray(R.array.placeArray);
		dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, mTestArray);
		spinSource.setAdapter(dataAdapter);
		spinDestination.setAdapter(dataAdapter);

		spinSource.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				pSource = arg2;
				GetAddress();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		spinDestination.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				pDestination = arg2;
				GetAddress();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		GetAddress();
	}

	private void GetAddress() {
		// TODO Auto-generated method stub

		SAddress = getResources().getString(R.string.str).split("@");
		Log.i("Source", "" + SAddress[pSource]);

		String source = "";
		source = SAddress[pSource];
		if (source.contains("{")) {
			source = source.replaceAll(Pattern.quote("{"), "");
		}

		if (source.contains("}")) {
			source = source.replaceAll(Pattern.quote("}"), "");
		}

		if (source.contains(";")) {
			source = source.replaceAll(Pattern.quote(";"), "");
		}

		source = source.trim();
		Log.i("source", "<><>" + source);

		DAddress = source.split(",");

		price = "" + DAddress[pDestination];
		tvprice.setText(price + " Rs/-");
	}

}
