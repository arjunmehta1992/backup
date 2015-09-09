package com.layoutnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends Activity{

	private static int SPLASH_TIME_OUT = 3000;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		
		
		
		 new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				
				Intent i = new Intent(splash.this, MainActivity.class);
                startActivity(i);
				
                finish();
				
			}
		
			
			
			
		
		 }, SPLASH_TIME_OUT);
		
		
		
		
	}

}
