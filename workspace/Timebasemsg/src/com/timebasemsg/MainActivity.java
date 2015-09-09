package com.timebasemsg;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Calendar c;
	int seconds,minutes,hour;
	TextView msgText,t2;
	private TimePicker timePicker1;
	private Calendar calendar;
	private String format = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
		msgText=(TextView)findViewById(R.id.messageText);
		t2=(TextView)findViewById(R.id.textView1);
		
		timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
	      
		
		
		c = Calendar.getInstance(); 
		seconds = c.get(Calendar.SECOND);
		minutes=c.get(Calendar.MINUTE);
		hour=c.get(Calendar.HOUR_OF_DAY);
		showTime(hour, minutes);
		
		//t2.setText("Display messages based on time:"+hour+":"+minutes+":"+seconds);
		
		
		
		if(hour < 12)
		{
			Toast.makeText(getApplicationContext(),"Goodmorning",Toast.LENGTH_SHORT).show();
			msgText.setText("Good morning");
		}
		else if(hour >= 12 && hour <= 16 )
		{
			Toast.makeText(getApplicationContext(),"Good Afternoon",Toast.LENGTH_SHORT).show();
			msgText.setText("Good afternoon");
		}
		else if(hour >=16 && hour <= 20)
		{
			Toast.makeText(getApplicationContext(),"Good Afternoon",Toast.LENGTH_SHORT).show();
			msgText.setText("Good Evening");
		}
		else if(hour >= 20)
		{
			Toast.makeText(getApplicationContext(),"Good Afternoon",Toast.LENGTH_SHORT).show();
			msgText.setText("Good night");
		}
		
		
		
	
		
		
		
		
	
	
	}
	
	
	 public void setTime(View view) {
	      int hour = timePicker1.getCurrentHour();
	      int min = timePicker1.getCurrentMinute();
	      showTime(hour, min);
	   }
	
	 
	 
	 
	 
	 public void showTime(int hour, int min) {
	      if (hour == 0) {
	         hour += 12;
	         format = "AM";
	      } else if (hour == 12) {
	         format = "PM";
	      } else if (hour > 12) {
	         hour -= 12;
	         format = "PM";
	      } else {
	         format = "AM";
	      }
	      t2.setText(new StringBuilder().append(hour).append(" : ").append(min)
	      .append(" ").append(format));
	   }

	 
	 
	
	

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