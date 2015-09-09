package com.audiocontrol;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	
	 private Button Vibrate , Ring , Silent , Mode;
	   private TextView Status;
	   private AudioManager myAudioManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		 Vibrate = (Button)findViewById(R.id.button2);
	      Ring = (Button)findViewById(R.id.button4);
	      Silent = (Button)findViewById(R.id.button3);
	      Mode = (Button)findViewById(R.id.button1);
	      Status = (TextView)findViewById(R.id.textView2);
	      myAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
	      
	
	}
	
	
	public void vibrate(View view){
	       myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	   }
	   public void ring(View view){
	      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	   }
	   public void silent(View view){
	      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	   }
	   public void mode(View view){
	     int mod = myAudioManager.getRingerMode();
	     if(mod == AudioManager.RINGER_MODE_NORMAL){
	         Status.setText("Current Status: Ring");
	   }
	   else if(mod == AudioManager.RINGER_MODE_SILENT){
	       Status.setText("Current Status: Silent");
	   }
	   else if(mod == AudioManager.RINGER_MODE_VIBRATE){
	      Status.setText("Current Status: Vibrate");
	   }
	  
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
}
