package com.zaptech.shoppingcart;

import com.zaptech.shoppingkart.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
	}
	public void login(View v)
	{
		switch (v.getId()) {
		case R.id.admintext:
			Intent adminlogin=new Intent(getApplicationContext(), Login.class);
			adminlogin.putExtra("Account", "Admin");
			startActivity(adminlogin);
			finish();
			break;
		case R.id.customertext:
			
			Intent customerlogin=new Intent(getApplicationContext(), Login.class);
			customerlogin.putExtra("Account", "Customer");
			startActivity(customerlogin);
			finish();
			break;
		default:
			break;
		}
	}
		
}
