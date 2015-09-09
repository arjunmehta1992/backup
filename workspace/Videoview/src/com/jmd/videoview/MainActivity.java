package com.jmd.videoview;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	VideoView player;
	DisplayMetrics dm;
	SurfaceView sur_view;
	MediaController media_cntrl;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		player=(VideoView)findViewById(R.id.video_player_view);
		media_cntrl=new MediaController(this);
		dm=new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int height = dm.heightPixels;
		int width = dm.widthPixels;
		player.setMinimumWidth(width);
		player.setMinimumHeight(height);
		player.setMediaController(media_cntrl);
		player.setVideoPath("");
		player.start();
		



		
		
		
		
		
		
		
	}

	
}
