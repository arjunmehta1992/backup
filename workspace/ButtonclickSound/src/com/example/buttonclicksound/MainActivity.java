package com.example.buttonclicksound;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	
	Button btn_sound;
	MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		
		btn_sound.setOnClickListener(this);
		
		
	}
	
	public void init()
	{
		btn_sound=(Button)findViewById(R.id.button1);
		mp=MediaPlayer.create(MainActivity.this, R.raw.click);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.button1:
			soundforbutton();
			
			break;

		default:
			break;
		}
		
		
	}
	
	
	public void soundforbutton()
	{
		
		mp.start();
		
	}
	

}
