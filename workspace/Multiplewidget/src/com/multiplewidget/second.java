package com.multiplewidget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class second extends Activity{

	RadioGroup rg1;
	RadioButton r1,r2;
	
	private ProgressDialog progress;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		
		rg1=(RadioGroup)findViewById(R.id.radioGroup1);
		r1=(RadioButton)findViewById(R.id.radio0);
		r2=(RadioButton)findViewById(R.id.radio1);
		
		rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				if(checkedId==R.id.radio0)
				{
					Toast.makeText(getApplicationContext(),"You are selected MALE",Toast.LENGTH_SHORT).show();
					
				}
				if(checkedId==R.id.radio1)
				{
					Toast.makeText(getApplicationContext(),"You are selected FEMALE",Toast.LENGTH_SHORT).show();
					
				}
	
				
				
				
			}
		});
		
		
		
		progress = new ProgressDialog(this);
		
		
		
		
			
	}
	
	
	
	
	
	
	public void open(View view){
	      progress.setMessage("Downloading Music :) ");
	      progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	      progress.setIndeterminate(true);
	      progress.show();

	   final int totalProgressTime = 10;

	   final Thread t = new Thread(){

	   @Override
	   public void run(){
	 
	      int jumpTime = 0;
	      while(jumpTime < totalProgressTime){
	         try {
	            sleep(200);
	            jumpTime += 5;
	            progress.setProgress(jumpTime);
	         } catch (InterruptedException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	         }

	      }

	   }
	   };
	   t.start();

	   }
	  
}