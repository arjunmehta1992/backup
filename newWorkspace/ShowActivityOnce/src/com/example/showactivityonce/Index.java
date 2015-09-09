package com.example.showactivityonce;

import java.io.IOException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Index extends ActionBarActivity {

	private static final int INSTRUCTIONS_CODE = 0;

	Button btn_Start, btn_Stop;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);

		btn_Start = (Button) findViewById(R.id.btn_startService);
		btn_Stop = (Button) findViewById(R.id.btn_stopService);
		
		
		
		
		SharedPreferences settings = getSharedPreferences("prefs", 0);
		boolean firstRun = settings.getBoolean("firstRun", true);
		if (firstRun) {
			// here run your first-time instructions, for example :
			startActivityForResult(new Intent(Index.this, ActivityHome.class),
					INSTRUCTIONS_CODE);

		}

		btn_Start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startService(new Intent(getBaseContext(), MyService.class));
				
			}
		});

		btn_Stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				stopService(new Intent(getBaseContext(), MyService.class));
				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == INSTRUCTIONS_CODE) {
			SharedPreferences settings = getSharedPreferences("prefs", 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("firstRun", false);
			editor.commit();
		}

	}

}
