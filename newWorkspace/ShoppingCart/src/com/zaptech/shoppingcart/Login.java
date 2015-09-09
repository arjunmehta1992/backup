package com.zaptech.shoppingcart;

import com.zaptech.shoppingkart.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{

	EditText edUserName,edPassword;
	Button login,cancle;
	TextView txtregister;
	CustomerModel cm;
	ShoppingDb db;
	String accountType="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		cm=new CustomerModel();
		db=new ShoppingDb(getApplicationContext());
		edUserName=(EditText) findViewById(R.id.userName);
		edPassword=(EditText) findViewById(R.id.userPassword);
		
		login=(Button) findViewById(R.id.btnLogin);
		cancle=(Button) findViewById(R.id.btnCancel);
		
		txtregister=(TextView) findViewById(R.id.loginregister);
				
		
		Intent getAccount=getIntent();
		if(getAccount.getStringExtra("Account").equalsIgnoreCase("Admin"))
		{
			accountType="Admin";
		}
		if(getAccount.getStringExtra("Account").equalsIgnoreCase("Customer"))
		{
			accountType="Customer";
			txtregister.setVisibility(txtregister.VISIBLE);
			txtregister.setOnClickListener(this);
		}
		login.setOnClickListener(this);
		cancle.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.loginregister:
			Intent register=new Intent(getApplicationContext(), Register.class);
			startActivity(register);
			finish();
			break;
		case R.id.btnLogin:
			if(accountType.equalsIgnoreCase("Customer"))
			{
				cm.setCu_User(edUserName.getText().toString());
				
				cm.setCu_Pass(edPassword.getText().toString());
				if(db.customerLoginCheck(cm))
				{
					Intent  login =new Intent(getApplicationContext(), Customer_Home.class);
					startActivity(login);
					finish();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Usrname or pasword is incorrect...", Toast.LENGTH_SHORT).show();
				}
			}
			if(accountType.equalsIgnoreCase("Admin"))
			{
				if(edUserName.getText().toString().equalsIgnoreCase("admin") && edPassword.getText().toString().equalsIgnoreCase("admin"))
				{
					Intent  login =new Intent(getApplicationContext(), Admin_Home.class);
					startActivity(login);
					finish();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Usrname or pasword is incorrect...", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.btnCancel:
			Intent home=new Intent(getApplicationContext(), Home.class);
			startActivity(home);
			finish();
			break;

		default:
			break;
		}
	}

	
}
