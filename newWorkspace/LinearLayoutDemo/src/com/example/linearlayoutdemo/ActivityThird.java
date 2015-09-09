package com.example.linearlayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityThird extends ActionBarActivity {

	Button btn_Next;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_third);
		
		btn_Next=(Button)findViewById(R.id.btn_login);
		btn_Next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(ActivityThird.this,ActivityFour.class);
				startActivity(i);
				
			}
		});
		
	}


}
