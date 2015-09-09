package com.zaptech.viewflipperdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class HomeActivity extends ActionBarActivity {

	
	 private ViewFlipper viewFlipper;
	 
	 private float lastX;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			lastX = event.getX();
			break;

		case MotionEvent.ACTION_UP:
			
			float currentX=event.getX();
			if(lastX<currentX)
			{
				if (viewFlipper.getDisplayedChild() == 0)
				{
					break;
				}
				
				 viewFlipper.setInAnimation(this, R.drawable.slide_in_from_left);
				 
				 viewFlipper.setOutAnimation(this, R.drawable.slide_out_to_right);
				 viewFlipper.showNext();
			}
			
			if(lastX>currentX)
			{
				if (viewFlipper.getDisplayedChild() == 1)
				{
					
					break;
					
				}
				viewFlipper.setInAnimation(this, R.drawable.slide_in_from_right);
				 
				 viewFlipper.setOutAnimation(this, R.drawable.slide_out_to_left);
				 viewFlipper.showPrevious();
			}
			
			break;
			
		default:
			break;
		}
		
		
		return false;
	}
	
}
