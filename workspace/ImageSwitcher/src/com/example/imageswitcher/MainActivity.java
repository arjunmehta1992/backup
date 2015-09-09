package com.example.imageswitcher;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Int2;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	 private ImageButton img;
	 private ImageSwitcher imageSwitcher;
	 
	 
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		
		img = (ImageButton)findViewById(R.id.imageButton1);
	    imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher1);
	     imageSwitcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView myView = new ImageView(getApplicationContext());
			      myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			      myView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.
			      FILL_PARENT,LayoutParams.FILL_PARENT));
			      return myView;
				
				
				
			}
		});  

	}
	
	public void next(View view){
	      Toast.makeText(getApplicationContext(), "Next Image", 
	      Toast.LENGTH_LONG).show();
	      Animation in = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_in_left);
	      Animation out = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_out_right);
	      imageSwitcher.setInAnimation(in);
	      imageSwitcher.setOutAnimation(out);
	      imageSwitcher.setImageResource(R.drawable.one);
	      
	      
	   }
	   public void previous(View view){
	      Toast.makeText(getApplicationContext(), "previous Image", 
	      Toast.LENGTH_LONG).show();
	      Animation in = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_out_right);
	      Animation out = AnimationUtils.loadAnimation(this,
	      android.R.anim.slide_in_left);
	      imageSwitcher.setInAnimation(out);
	      imageSwitcher.setOutAnimation(in);
	      imageSwitcher.setImageResource(R.drawable.five);
	     
	   }


}
