package com.example.progressbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity {

	 Button btnStartProgress;  
	    ProgressDialog progressBar;  
	    private int progressBarStatus = 0;  
	    private Handler progressBarHandler = new Handler();  
	        private long fileSize = 0;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		 addListenerOnButtonClick();  
    
	}

	private void addListenerOnButtonClick() {
		  btnStartProgress = (Button) findViewById(R.id.button1);  
		  
		  btnStartProgress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
				
				 progressBar = new ProgressDialog(v.getContext());  
		            progressBar.setCancelable(true);  
		            progressBar.setMessage("File downloading ...");  
		            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		            progressBar.setProgress(0);  
		            progressBar.setMax(100);  
		            progressBar.show();  
		            //reset progress bar and filesize status  
		            progressBarStatus = 0;  
		            fileSize = 0;  
		   
		            new Thread(new Runnable() {  
		              public void run() {  
		                while (progressBarStatus < 100) {  
		                  // performing operation  
		                  progressBarStatus = doOperation();  
		                  try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}  
		                  // Updating the progress bar  
		                  progressBarHandler.post(new Runnable() {  
		                    public void run() {  
		                      progressBar.setProgress(progressBarStatus);  
		                    }  
		                  });  
		                }  
		                // performing operation if file is downloaded,  
		                if (progressBarStatus >= 100) {  
		                    // sleeping for 1 second after operation completed  
		                    try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}  
		                    // close the progress bar dialog  
		                    progressBar.dismiss();  
		                }  
		              }  
		             }).start();  
		            }//end of onClick method  
		          });  
		         }  
		    
		    public int doOperation() {  
		        
		        while (fileSize <= 10000) {  
		            fileSize++;  
		            if (fileSize == 1000) {  
		                return 10;  
		            } else if (fileSize == 2000) {  
		                return 20;  
		            } else if (fileSize == 3000) {  
		                return 30;  
		            } else if (fileSize == 4000) {  
		            return 40;//you can add more else if  
		            } else{  
		                return 100;  
		            }  
		        }//end of while  
		        return 100;  
		    }//end of doOperation  
		  
		    @Override  
		    public boolean onCreateOptionsMenu(Menu menu) {  
		        // Inflate the menu; this adds items to the action bar if it is present.  
		        getMenuInflater().inflate(R.menu.main, menu);  
		        return true;  
		    }  
}