package com.ratingbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	
	
	Float rat;
	RatingBar ratingbar1;  
    Button button;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		// addListenerOnButtonClick();  
		 ratingbar1=(RatingBar)findViewById(R.id.ratingBar1);
		 
		ratingbar1.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				rat=rating;
				Toast.makeText(getApplicationContext(),"Rating="+rat,Toast.LENGTH_SHORT ).show();
				
				
				
				
			}
		});
		
		
		
		
		
		
	}
	
	
	
	/*public void addListenerOnButtonClick(){  
          
        button=(Button)findViewById(R.id.button1);  
        //Performing action on Button Click  
        button.setOnClickListener(new OnClickListener(){  
  
            @Override  
            public void onClick(View arg0) {  
                //Getting the rating and displaying it on the toast  
                String rating=String.valueOf(ratingbar1.getRating());  
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();  
            }  
              
        });  
    }  */
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}