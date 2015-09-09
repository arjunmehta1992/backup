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
import android.widget.TextView;
import android.widget.Toast;

public class SearchStudent extends Activity implements OnClickListener {

	Button btnSearch, btnClear, btnHome;
	AutoCompleteTextView edStdName;
	TextView stdName, stdAddress, stdContact, stdGender, stdEmail,stdClg;
	RelativeLayout display;

	DBHelper helper;
	ArrayList<String> studentList;
	ArrayList<StudentData> student;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_student);

		helper=new DBHelper(SearchStudent.this);
		studentList=new ArrayList<String>();
		
		btnSearch = (Button) findViewById(R.id.btnStdSearch);
		btnClear = (Button) findViewById(R.id.btnSearchStdClear);
		btnHome = (Button) findViewById(R.id.btnSearchStdHome);

		edStdName = (AutoCompleteTextView) findViewById(R.id.edSearchStdName);

		display = (RelativeLayout) findViewById(R.id.searchStdDisplay);

		stdName = (TextView) findViewById(R.id.searchStdNameResult);
		stdAddress = (TextView) findViewById(R.id.searchStdAddressResult);
		stdContact = (TextView) findViewById(R.id.searchStdContactResult);
		stdGender = (TextView) findViewById(R.id.searchStdGenderResult);
		stdEmail = (TextView) findViewById(R.id.searchStdEmailResult);
		stdClg=(TextView) findViewById(R.id.searchStdCollegeNameResult);
		
		btnSearch.setOnClickListener(this);
		btnClear.setOnClickListener(this);
		btnHome.setOnClickListener(this);
		
		displayData();
	}
	public void displayData()
	{
		studentList=helper.getStudentName();
		ArrayAdapter<String> adpt=new ArrayAdapter<String>(SearchStudent.this, android.R.layout.simple_list_item_1, studentList);
		edStdName.setAdapter(adpt);
		edStdName.setThreshold(1);
	}
	public void searchResult()
	{
		int n=-1;
		student=helper.getStudentList();
		for(int i=0;i<student.size();i++)
		{
			if(edStdName.getText().toString().equalsIgnoreCase(student.get(i).getStd_name()))
			{
				
				n=i;
				break;
			}
		}
		if(n>-1)
		{
			stdName.setText(student.get(n).getStd_name());
			stdAddress.setText(student.get(n).getStd_address());
			stdContact.setText(student.get(n).getStd_contact());
			stdEmail.setText(student.get(n).getStd_email());
			stdGender.setText(student.get(n).getStd_gender());
			stdClg.setText(""+helper.getCollegeName(student.get(n).getCollege_id()));
			display.setVisibility(display.VISIBLE);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "No Record Found....", Toast.LENGTH_SHORT).show();
			display.setVisibility(display.INVISIBLE);
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnStdSearch:
			searchResult();
			break;
		case R.id.btnSearchStdClear:
			edStdName.setText("");

			break;
		case R.id.btnSearchStdHome:
			Intent home = new Intent(getApplicationContext(),
					HomeActivity.class);
			startActivity(home);
			finish();
			break;
		default:
			break;
		}

	}

}