package com.example.studentmappingdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCollege extends Activity implements OnClickListener {

	EditText name, address, number, latittude, longitude;
	Button btn_add, btn_clear, btn_home;
	CollegeData objCollegeData;
	DBHelper obj_helper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_college);
		init();
		
		
		
		
	
		
		
		btn_add.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_home.setOnClickListener(this);

	}

	public void init() {
		name = (EditText) findViewById(R.id.edClgName);
		address = (EditText) findViewById(R.id.edClgAddress);
		number = (EditText) findViewById(R.id.edClgContact);
		latittude = (EditText) findViewById(R.id.edClgLatitude);
		longitude = (EditText) findViewById(R.id.edClgLongitude);

		btn_add = (Button) findViewById(R.id.btnClgAdd);
		btn_clear = (Button) findViewById(R.id.btnClgClear);
		btn_home = (Button) findViewById(R.id.btnClgHome);

		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.btnClgAdd:
			objCollegeData=new CollegeData();
			obj_helper=new DBHelper(AddCollege.this);
			
			String getName=name.getText().toString();
			String getAddress=address.getText().toString();
			String getNumber=number.getText().toString();
			String getLat=latittude.getText().toString();
			String getLong=longitude.getText().toString();
			
			Toast.makeText(AddCollege.this,""+getName+""+getAddress+""+getNumber+""+getLat+""+getLong,Toast.LENGTH_SHORT).show();
			objCollegeData.setCollege_name(getName);
			objCollegeData.setCollege_address(getAddress);
			objCollegeData.setCollege_contact(getNumber);
			objCollegeData.setCollege_lat(getLat);
			objCollegeData.setCollege_long(getLong);
			
			
			obj_helper.addCollegeData(objCollegeData);
			Toast.makeText(AddCollege.this,"College is registerd",Toast.LENGTH_SHORT).show();
			name.setText("");
			address.setText("");
			number.setText("");
			latittude.setText("");
			longitude.setText("");
			
			break;
		
		
		
		
		case R.id.btnClgClear:
				
			name.setText("");
			address.setText("");
			number.setText("");
			latittude.setText("");
			longitude.setText("");
			break;

		case R.id.btnClgHome:

				Intent intentHome=new Intent(AddCollege.this,HomeActivity.class);
				startActivity(intentHome);
				finish();
			break;

		default:
			break;
		}

	}

}
