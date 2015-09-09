package com.chrnometer;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public  class MainActivity extends Activity implements android.view.View.OnClickListener 
{

	
	Chronometer focus;
	Button start, stop, restart, set, clear;
 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
			start=(Button)findViewById(R.id.button1);
			stop=(Button)findViewById(R.id.button2);
			restart=(Button)findViewById(R.id.button3);
			set=(Button)findViewById(R.id.button4);
			clear=(Button)findViewById(R.id.button5);
			
			focus=(Chronometer)findViewById(R.id.chronometer1);
			
			start.setOnClickListener(this);
			stop.setOnClickListener(this);
			restart.setOnClickListener(this);
			set.setOnClickListener(this);
			clear.setOnClickListener(this);
			
			
			
			
		
		
	
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) 
	{
		switch (v.getId()) {
		case R.id.button1:
			
			start();
			break;
			
			
		case R.id.button2:
			
			stop();
			break;

		case R.id.button3:
	
			restart();
			break;

		case R.id.button4:
	
			set();
			break;

		case R.id.button5:
	
			clear();
			break;

		default:
			break;
		}
		
	}

	public void start()
	{
		focus.start();
	}

	public void stop()
	{
		focus.stop();
	}
	
	public void restart()
	{
		focus.setBase(SystemClock.elapsedRealtime());
	}
	public void set()
	{
		focus.setFormat("Formated Time - %s");
	}
	public void clear()
	{
		focus.setFormat(null);
	}
	

	


	
}