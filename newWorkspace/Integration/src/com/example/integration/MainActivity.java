package com.example.integration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.fbintegration.R;

public class MainActivity extends Activity  {

	ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView) findViewById(R.id.imageView1);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				open(v);
			}
		});
		
	
	}
	public void open(View view){
	      Intent shareIntent = new Intent();
	      shareIntent.setAction(Intent.ACTION_SEND);
	      shareIntent.setType("text/plain");
	      shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, from arjun");
	      startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));

	   }
	
}
