package com.example.framelayoutdemo;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityFirst extends ActionBarActivity {

	private ImageView image;
	private TextView text;
	private FrameLayout frame;
	private Button btn_nextScreen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_first);
	
		
		frame = (FrameLayout) findViewById(R.id.framelayout);
		text = (TextView) findViewById(R.id.frameText);
		image = (ImageView) findViewById(R.id.frameImage);
		btn_nextScreen=(Button)findViewById(R.id.btn_FirstScreen_nextScreen);
		
		// add a new element to the FrameLayout
		TextView newText = new TextView(this);
		
		newText.setTextColor(Color.CYAN);
		newText.setTextSize(20);
		frame.addView(newText);
		
		
		btn_nextScreen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(ActivityFirst.this,ActivitySecond.class);
				startActivity(i);
			}
		});
		
		
		
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// change the visibility mode of the TextView
				if(text.getVisibility() == View.GONE) {
					text.setVisibility(View.VISIBLE);
					frame.setBackgroundColor(Color.MAGENTA);
				} else {
					text.setVisibility(View.GONE);
				}
				
			}
		});
	}
	

	

	
}