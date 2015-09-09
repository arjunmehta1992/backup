package com.example.locationalarm.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.locationalarm.R;

public class AlarmActivity extends Activity {

	private MediaPlayer mMediaPlayer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		// PowerManager is responsible for showing alarm even if device is
		// locked
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		boolean screenOn = pm.isScreenOn();// check id screen is on
		if (!screenOn) {// if not turn it on or wkaeup the screen
			final PowerManager.WakeLock wl = pm.newWakeLock(
					PowerManager.SCREEN_DIM_WAKE_LOCK, "Screen On");
			wl.acquire();
			Toast.makeText(getBaseContext(), "This is WAKEUP SCREEN",
					Toast.LENGTH_SHORT).show();
			Thread timer = new Thread() {
				public void run() {
					Window window = getWindow();
					window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
							| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
							| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
							| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				}
			};
			timer.start();
		}
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
		// calling media player for playing sound when alarm is appear
		playSound(this, getAlarmUri());
	}

	private Uri getAlarmUri() {
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		if (alert == null) {
			alert = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			if (alert == null) {
				alert = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			}
		}
		return alert;
	}

	private void playSound(Context context, Uri alert) {
		mMediaPlayer = new MediaPlayer();
		String url = "http://media.djmazadownload.com/music/Singles/Sawan%20Aaya%20Hai%20(Creature%203D)%20-190Kbps%20[DJMaza.Info].mp3";

		try {
			mMediaPlayer.setDataSource(url);
			final AudioManager audioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mMediaPlayer.setLooping(true);
				mMediaPlayer.prepare();
				mMediaPlayer.start();
				mMediaPlayer
						.setOnCompletionListener(new OnCompletionListener() {
							@Override
							public void onCompletion(MediaPlayer arg0) {
								finish();
							}
						});
			}
		} catch (IOException e) {
			System.out.println("OOPS");
		}
	}

}
