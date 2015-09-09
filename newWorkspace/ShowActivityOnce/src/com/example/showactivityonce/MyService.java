package com.example.showactivityonce;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

	MediaPlayer player = new MediaPlayer();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

		try {
			player.setDataSource("/sdcard/Music/song.mp3");// Write your
															// location here
			player.prepare();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.start();

		return START_STICKY;

	}

	@Override
	public void onDestroy() {

		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
		player.stop();
		super.onDestroy();
	}

}