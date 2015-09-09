package com.emailservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	EditText editTextTo,editTextSubject,editTextMessage;  
    Button send,cancel;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
		editTextTo=(EditText)findViewById(R.id.mail_to);  
        editTextSubject=(EditText)findViewById(R.id.mail_subject);  
        editTextMessage=(EditText)findViewById(R.id.mail_message);
        
        send=(Button)findViewById(R.id.mail_send);
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				 String to=editTextTo.getText().toString();  
                 String subject=editTextSubject.getText().toString();  
                 String message=editTextMessage.getText().toString();  
			
                 
                 Intent i=new Intent(Intent.ACTION_SEND);
                 i.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});  
                 i.putExtra(Intent.EXTRA_SUBJECT, subject);  
                 i.putExtra(Intent.EXTRA_TEXT, message);  
      
                 //need this to prompts email client only  
                 i.setType("message/rfc822");  
                 
      
                 startActivity(Intent.createChooser(i, "Choose an Email client :"));  
			}
		});
        
		/*cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				editTextTo.setText("");
				editTextSubject.setText("");
				editTextMessage.setText("");
				
				
			}
		});
	*/
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
