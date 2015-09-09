package com.zaptech.animatioactivity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends ActionBarActivity implements OnClickListener {

	Button btn_SwitchToNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btn_SwitchToNext = (Button) findViewById(R.id.btn_nextPage);
		btn_SwitchToNext.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_nextPage:

			Intent i = new Intent();
			i.setClass(HomeActivity.this, SecondActivity.class);
			startActivity(i);
			overridePendingTransition(R.anim.right_in, R.anim.left_out);
			break;

		default:
			break;
		}

	}

}