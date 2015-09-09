package com.layoutnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.widget.TextView;
import android.widget.Toast;

public class data extends Activity{
	
	
	TextView tv_name,tv_address,tv_gender,tv_password,tv_confirmpasseord,tv_lastname,tv_city,tv_email;
	String name,address,gender,password,confirm,lastname,city,email;
	
	protected void onCreate(Bundle savedinstancestate) {
		super.onCreate(savedinstancestate);
		setContentView(R.layout.data);
		
		
		tv_name=(TextView)findViewById(R.id.textView1);
		tv_lastname=(TextView)findViewById(R.id.textView2);
		tv_gender=(TextView)findViewById(R.id.textView3);
		tv_password=(TextView)findViewById(R.id.textView4);
		tv_confirmpasseord=(TextView)findViewById(R.id.textView5);
		tv_address=(TextView)findViewById(R.id.textView6);
		tv_email=(TextView)findViewById(R.id.textView7);
		
		tv_city=(TextView)findViewById(R.id.textView8);

		
		
		Intent i=getIntent();
		name=i.getStringExtra("name");
		
		
		address=i.getStringExtra("address");
		gender=i.getStringExtra("radioGroup");
		password=i.getStringExtra("password");
		confirm=i.getStringExtra("confirmpass");
		email=i.getStringExtra("email");
		lastname=i.getStringExtra("lastname");
		city=i.getStringExtra("city");
		
		Toast.makeText(getApplicationContext(), ""+gender, Toast.LENGTH_SHORT).show();
		
		tv_name.setText("First name:"+name);
		tv_address.setText("Address:"+address);
		tv_gender.setText("Gender:"+gender);
		tv_password.setText("Password:"+password);
		tv_confirmpasseord.setText("Confirm password:"+confirm);
		tv_lastname.setText("Last name:"+lastname);
		tv_city.setText("City:"+city);
		tv_email.setText("E-mail ID:"+email);
		
		
		
		
		
		
		
		
	}
	

}
