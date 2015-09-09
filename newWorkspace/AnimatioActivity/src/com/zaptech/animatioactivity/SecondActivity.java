package com.zaptech.animatioactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends ActionBarActivity {

	Button btn_SwitchToNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		
		btn_SwitchToNext = (Button) findViewById(R.id.btn_prevPage);
		btn_SwitchToNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent();
				intent.setClass(SecondActivity.this, HomeActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
				
				
			}
		});
	}

}
