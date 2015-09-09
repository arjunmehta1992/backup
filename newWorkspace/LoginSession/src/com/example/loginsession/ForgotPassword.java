package com.example.loginsession;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ForgotPassword extends Activity implements OnClickListener{

	Button sendEmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		init();
		
		sendEmail.setOnClickListener(this);
		
	}
	public void init()
	{
		sendEmail=(Button)findViewById(R.id.button1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			
			/*try {
				
				GmailSender sender = new GmailSender("username@gmail.com", "password");
                sender.sendMail("This is Subject",   
                        "This is Body",   
                        "user@gmail.com",   
                        "user@yahoo.com");   
				
			} catch (Exception e) {
				Log.e("SendMail", e.getMessage(), e);
			}*/
			break;

		default:
			break;
		}
	}

	
	
}
