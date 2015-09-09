package com.zaptech.cardviewdemo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityDisplayDetails extends ActionBarActivity {

	private String mPostion;
	private TextView mDetails;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_details);
		init();
		intent = getIntent();
		mDetails.setText(intent.getStringExtra("CardDetails"));

	}

	void init() {
		mDetails = (TextView) findViewById(R.id.txtDetails);

	}

}
