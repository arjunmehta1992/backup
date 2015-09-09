package com.zaptech.livestreamingdemo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class HomeActivity extends Activity {

	private VideoView mVideoview;

	/*
	 * private String mHttpLiveUrl =
	 * "http://hlsstr02.svc.iptv.rt.ru/hls/CH_TNT/bw480000/variant.m3u8";
	 */

	private String mHttpLiveUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

	// private String mHttpLiveUrl =
	// "http://217.117.234.59:8000/ef6400031146/playlist.m3u8";

	// private String mHttpLiveUrl =
	// "http://playertest.longtailvideo.com/adaptive/oceans_aes/oceans_aes.m3u8";
	private String urlStream;
	private VideoView myVideoView;
	private URL url;
	long progress;
	int stopPosition = 0;

	final static int REQUEST_VIDEO_CAPTURED = 1;
	Uri uriVideo = null;

	Button btn_play, btn_pause;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		btn_play = (Button) findViewById(R.id.btn_play);
		btn_pause = (Button) findViewById(R.id.btn_);

		MediaController mediacontroller = new MediaController(HomeActivity.this);
		mediacontroller.setAnchorView(mVideoview);

		if (Uri.parse(mHttpLiveUrl) == null) {
			Toast.makeText(HomeActivity.this,
					"Please select another stream !!! ...Stream is empty",
					Toast.LENGTH_SHORT).show();
		}

		mVideoview.setVideoURI(Uri.parse(mHttpLiveUrl));
		mVideoview.setMediaController(new MediaController(HomeActivity.this));
		mVideoview.requestFocus();
		mVideoview.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub

				/* new DownloadTask().execute(); */
			}
		});

		btn_play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Toast.makeText(HomeActivity.this,
						"Resumed---- " + stopPosition, Toast.LENGTH_SHORT)
						.show();
				if (stopPosition == 0) {
					mVideoview.start();
				} else {
					mVideoview.seekTo(stopPosition);
					mVideoview.start();
				}

				// mVideoview.resume(); // Or use resume() if it doesn't work.
				// I'm not sure
				Log.d("Debugggggg>>>>>>>>>>", "onResume called");
				// mVideoview.resume();
			}
		});

		btn_pause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				stopPosition = mVideoview.getCurrentPosition() / 1000;
				// String tmp = "" + stopPosition;
				// Toast.makeText(HomeActivity.this, "Paused befor "+tmp,
				// Toast.LENGTH_SHORT).show();

				// stopPosition = Integer.parseInt(""+tmp.indexOf(0));

				Toast.makeText(HomeActivity.this, "Paused " + stopPosition,
						Toast.LENGTH_SHORT).show();

				mVideoview.pause();
				Log.d(">>>>>>>>>>>>>>>>>>>>", "onPause called");
				// mVideoview.start();

			}
		});

		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				DownloadFile(mHttpLiveUrl, "video.mp4");

			}
		});

		/* DownloadFromUrl(mHttpLiveUrl, "stream_video.m3u8"); */

		mVideoview.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {

				/* Alert_No_Video_Play("Something wrong with this channel"); */
				Toast.makeText(HomeActivity.this,
						"Something wrong with this channel", Toast.LENGTH_LONG)
						.show();

				return true;
			}
		});

		/*
		 * mVideoview.setMediaController(new MediaController(this));
		 * mVideoview.setVideoURI(Uri.parse(
		 * "http://pilatus.d1.comp.nus.edu.sg/~a0095695/video_repo/playlist.m3u8"
		 * )); mVideoview.requestFocus(); mVideoview.setOnPreparedListener(new
		 * OnPreparedListener() {
		 * 
		 * @Override public void onPrepared(MediaPlayer mp) { // TODO
		 * Auto-generated method stub
		 * 
		 * 
		 * mp.start(); } });
		 */
		/*
		 * MediaController mc = new MediaController(this);
		 * mVideoview.setMediaController(mc); urlStream =
		 * "http://jorgesys.net/i/irina_delivery@117489/master.m3u8";
		 * runOnUiThread(new Runnable() {
		 * 
		 * @Override public void run() {
		 * mVideoview.setVideoURI(Uri.parse(urlStream)); } });
		 */
	}

	public void init() {
		mVideoview = (VideoView) findViewById(R.id.video_view);

	}

	@Override
	public void onPause() {

		super.onPause();

		stopPosition = mVideoview.getCurrentPosition();
		Toast.makeText(HomeActivity.this, "Paused " + stopPosition,
				Toast.LENGTH_SHORT).show();
		// stopPosition is an
		// int
		mVideoview.pause();
		Log.d(">>>>>>>>>>>>>>>>>>>>", "onPause called");
	}

	@Override
	public void onResume() {
		super.onResume();
		Toast.makeText(HomeActivity.this, "Resumed---- " + stopPosition,
				Toast.LENGTH_SHORT).show();

		mVideoview.seekTo(stopPosition);
		Log.d("Debugggggg>>>>>>>>>>", "onResume called");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		stopPosition = mVideoview.getCurrentPosition();
		mVideoview.pause();
		outState.putInt("position", stopPosition);
	}

	public void DownloadFromUrl(String DownloadUrl, String fileName) {
		try {
			File root = android.os.Environment.getExternalStorageDirectory();
			File dir = new File(root.getAbsolutePath() + "/xmls");
			if (dir.exists() == false) {
				dir.mkdirs();
			}

			URL url = new URL(DownloadUrl); // you can write here any link
			File file = new File(dir, fileName);

			long startTime = System.currentTimeMillis();
			Log.d("DownloadManager", "download url:" + url);

			/* Open a connection to that URL. */
			URLConnection ucon = url.openConnection();

			/*
			 * Define InputStreams to read from the URLConnection.
			 */
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);

			/*
			 * Read bytes to the Buffer until there is nothing more to read(-1).
			 */
			ByteArrayBuffer baf = new ByteArrayBuffer(5000);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}

			/* Convert the Bytes read to a String. */
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baf.toByteArray());
			fos.flush();
			fos.close();
			Log.d("DownloadManager",
					"download ready in"
							+ ((System.currentTimeMillis() - startTime) / 1000)
							+ " sec");
		} catch (IOException e) {
			Log.d("DownloadManager", "Error: " + e);
		}
	}

	public void DownloadFile(String fileURL, String fileName) {
		try {
			String RootDir = Environment.getExternalStorageDirectory()
					+ File.separator + "Video";
			File RootFile = new File(RootDir);
			RootFile.mkdir();
			// File root = Environment.getExternalStorageDirectory();
			URL u = new URL(fileURL);
			HttpURLConnection c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setDoOutput(true);
			c.connect();

			FileOutputStream f = new FileOutputStream(new File(RootFile,
					fileName));
			InputStream in = c.getInputStream();
			byte[] buffer = new byte[1024];
			int len1 = 0;

			System.err.println("Start.........");

			while ((len1 = in.read(buffer)) > 0) {
				System.err.println("Write file .....");
				f.write(buffer, 0, len1);
			}
			System.err.println("End...");
			f.close();

		} catch (Exception e) {

			Log.d("Error....", e.toString());
		}

	}

	class DownloadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// DownloadFromUrl(mHttpLiveUrl, "video.mp4");
			System.err.println("File download Start.......");
			/* DownloadFile(mHttpLiveUrl, "video.mp4"); */
			return null;
		}

	}
}
