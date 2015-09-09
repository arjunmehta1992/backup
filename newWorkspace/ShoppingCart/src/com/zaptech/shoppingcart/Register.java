package com.zaptech.shoppingcart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.zaptech.shoppingkart.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
	TextView bdate;
	EditText edname, edContact, edEmail, eduser, edpass,edCpass;
	
	Button save,cancle;
	DatePickerDialog birthDate;
	SimpleDateFormat dateFormatter;
	ShoppingDb db;
	CustomerModel cm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		bdate = (TextView) findViewById(R.id.customerBirthdate);
		bdate.setInputType(InputType.TYPE_NULL);
		bdate.setOnClickListener(this);
		db=new ShoppingDb(getApplicationContext());
		cm=new CustomerModel();
		edname = (EditText) findViewById(R.id.customerName);
		edContact = (EditText) findViewById(R.id.customerContact);
		edEmail = (EditText) findViewById(R.id.customerEmail);
		eduser = (EditText) findViewById(R.id.customerUserName);
		edpass = (EditText) findViewById(R.id.customerPassword);
		edCpass=(EditText)findViewById(R.id.customerConfirmPassword);
		save=(Button) findViewById(R.id.btnSave);
		cancle=(Button) findViewById(R.id.btnCancel);
		save.setOnClickListener(this);
		cancle.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.customerBirthdate:
			Calendar newCalendar = Calendar.getInstance();
			dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
			birthDate = new DatePickerDialog(this, new OnDateSetListener() {

				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					Calendar newDate = Calendar.getInstance();
					newDate.set(year, monthOfYear, dayOfMonth);
					bdate.setText(dateFormatter.format(newDate.getTime()));
				}

			}, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
					newCalendar.get(Calendar.DAY_OF_MONTH));

			birthDate.show();
			break;
		case R.id.btnSave:
			cm.setCu_Name(edname.getText().toString());
			cm.setCu_Date(bdate.getText().toString());
			cm.setCu_Contact(edContact.getText().toString());
			cm.setCu_Email(edEmail.getText().toString());
			cm.setCu_User(eduser.getText().toString());
			cm.setCu_Pass(edpass.getText().toString());
			if(edpass.getText().toString().equalsIgnoreCase(edCpass.getText().toString()))
			{
				db.insert(cm);
				Toast.makeText(getApplicationContext(), "Saved..", Toast.LENGTH_SHORT).show();
			}
			else
			{
				edCpass.setError("Password and Confirm password not match");
			
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
