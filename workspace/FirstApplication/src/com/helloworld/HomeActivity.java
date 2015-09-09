package com.helloworld;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class HomeActivity extends Activity implements ViewFactory{
	
	TextView t1;
	@SuppressWarnings("deprecation")
	
	
	
	ImageSwitcher imgSwitcher;
 

	
	int imgs[] = 
		{ 
			R.drawable.two,
			R.drawable.six,
			R.drawable.five
			
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		imgSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher1);
		
		imgSwitcher.setFactory(this);
		imgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		
		imgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		
		
		
		
		
		t1=(TextView)findViewById(R.id.StringText);
		
		Intent intent= getIntent(); // gets the previously created intent
		String value = intent.getStringExtra("Text");
		
		t1.setText(value);
		
		
gallery.setAdapter(new ImageAdapter(this));
		
		gallery.setOnItemClickListener(new OnItemClickListener() {
 
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				imgSwitcher.setImageResource(imgs[arg2]);
			}
		});
		
		
	}


	public class ImageAdapter extends BaseAdapter {
		 
		private Context ctx;
 
		public ImageAdapter(Context c) {
			ctx = c; 
		}
 
		public int getCount() {
 
			return imgs.length;
		}
 
		public Object getItem(int arg0) {
 
			return arg0;
		}
 
		public long getItemId(int arg0) {
 
			return arg0;
		}
 
		public View getView(int arg0, View arg1, ViewGroup arg2) {
 
			ImageView iView = new ImageView(ctx);
			iView.setImageResource(imgs[arg0]);
			iView.setScaleType(ImageView.ScaleType.FIT_XY);
			iView.setLayoutParams(new Gallery.LayoutParams(200, 150));
			return iView;
		}
	}



	@Override
	public View makeView() {
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iView.setLayoutParams(new ImageSwitcher.LayoutParams
			(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		iView.setBackgroundColor(0xFF000000);
		return iView;
	}
	
	
	

}
