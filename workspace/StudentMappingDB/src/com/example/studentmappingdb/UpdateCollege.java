package com.example.studentmappingdb;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class UpdateCollege extends Activity implements OnClickListener {

	Button load, sClear, home, update;
	EditText clgName, clgAddress, clgContact, clgLongitude, clgLatitude;
	RelativeLayout display;
	DBHelper helper;
	CollegeData obj_clg;
	ArrayList<String> collegeList;
	ArrayList<CollegeData> college;
	AutoCompleteTextView searchName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_college);

		helper = new DBHelper(UpdateCollege.this);
		obj_clg = new CollegeData();
		collegeList = new ArrayList<String>();

		load = (Button) findViewById(R.id.btnUpdateClgLoad);
		sClear = (Button) findViewById(R.id.btnUpdateClgClear);
		home = (Button) findViewById(R.id.btnLoadClgHome);
		update = (Button) findViewById(R.id.btnUpdateClg);
		

		display = (RelativeLayout) findViewById(R.id.updateClgDisplay);

		load.setOnClickListener(this);
		sClear.setOnClickListener(this);
		home.setOnClickListener(this);
		update.setOnClickListener(this);
		
		searchName = (AutoCompleteTextView) findViewById(R.id.edUpdateClgName);

		clgName = (EditText) findViewById(R.id.updateClgNameResult);
		clgAddress = (EditText) findViewById(R.id.updateClgAddressResult);
		clgContact = (EditText) findViewById(R.id.updateClgContactResult);
		clgLongitude = (EditText) findViewById(R.id.updateClgLongitudeResult);
		clgLatitude = (EditText) findViewById(R.id.updateClgLatitudeResult);
		clgName.setTextSize(10);
		displayData();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnUpdateClgLoad:
			Toast.makeText(UpdateCollege.this, "Load is selected" + "...",
					Toast.LENGTH_SHORT).show();
			searchResult();
			break;

		case R.id.btnUpdateClgClear:
			searchName.setText("");
			display.setVisibility(display.INVISIBLE);
			break;

		case R.id.btnLoadClgHome:
			Intent home=new Intent(UpdateCollege.this, HomeActivity.class);
			startActivity(home);
			finish();
		break;

		case R.id.btnUpdateClg:
			String tempclgName=searchName.getText().toString();
			obj_clg.setCollege_name(clgName.getText().toString());
			obj_clg.setCollege_address(clgAddress.getText().toString());
			obj_clg.setCollege_contact(clgContact.getText().toString());
			obj_clg.setCollege_lat(clgLatitude.getText().toString());
			obj_clg.setCollege_long(clgLongitude.getText().toString());
			helper.updateCollege(obj_clg,tempclgName);
			Toast.makeText(getApplicationContext(), "Updated",
					Toast.LENGTH_SHORT).show();

			break;

			default:
			break;
		}

	}

	public void displayData() {
		collegeList = helper.getCollegeName();
		ArrayAdapter<String> adpt = new ArrayAdapter<String>(
				UpdateCollege.this, android.R.layout.simple_list_item_1,
				collegeList);
		searchName.setAdapter(adpt);
		searchName.setThreshold(1);
	}

	public void searchResult() {
		int n = -1;
		college = helper.getCollegeList();
		for (int i = 0; i < college.size(); i++) {
			if (searchName.getText().toString()
					.equalsIgnoreCase(college.get(i).getCollege_name())) {

				n = i;
				break;
			}
		}
		if (n > -1) {
			clgName.setText(college.get(n).getCollege_name());
			clgAddress.setText(college.get(n).getCollege_address());
			clgContact.setText(college.get(n).getCollege_contact());
			clgLatitude.setText(college.get(n).getCollege_lat());
			clgLongitude.setText(college.get(n).getCollege_long());
			display.setVisibility(display.VISIBLE);
		} else {
			Toast.makeText(getApplicationContext(), "No Record Found....",
					Toast.LENGTH_SHORT).show();
			display.setVisibility(display.INVISIBLE);
		}

	}

}