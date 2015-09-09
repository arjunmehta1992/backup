package com.example.framelayoutdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityFour extends Activity {

	Button btn_next;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
		
		
		btn_next=(Button)findViewById(R.id.btn_FourthScreen_next);
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(ActivityFour.this,ActivityFifth.class);
				startActivity(i);
			}
		});
	}

	
}