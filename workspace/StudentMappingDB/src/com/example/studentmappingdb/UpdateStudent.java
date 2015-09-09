package com.example.studentmappingdb;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateStudent extends Activity implements OnClickListener{

	Button load,sClear,home,update;
	EditText stdName,stdAddress,stdContact,stdEmail;
	Spinner stdClg;
	DBHelper helper;
	StudentData obj_std;
	CollegeData obj_clg;
	
	RadioButton stdMale,stdFemale;
	RadioGroup stdGender;
	ArrayList<String> studentList;
	ArrayList<StudentData> student;
	RelativeLayout display;
	AutoCompleteTextView searchStdName;
	ArrayList<String> list_college;
	String gen="";
	
	int n;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_student);
		
		helper=new DBHelper(UpdateStudent.this);
		obj_std=new StudentData();
		obj_clg=new CollegeData();
		
		studentList=new ArrayList<String>();
		list_college=new ArrayList<String>();
		
		
		load=(Button)findViewById(R.id.btnLoadStd);
		sClear=(Button)findViewById(R.id.btnUpdateStdClear);
		home=(Button)findViewById(R.id.btnUpdateStdHome);
		update=(Button)findViewById(R.id.btnUpdateStd);
		
		
		display=(RelativeLayout)findViewById(R.id.updateStdDisplay);
		
		load.setOnClickListener(this);
		sClear.setOnClickListener(this);
		home.setOnClickListener(this);
		update.setOnClickListener(this);
		stdMale=(RadioButton) findViewById(R.id.radioMale);
		stdFemale=(RadioButton) findViewById(R.id.radioFemale);
		
		searchStdName=(AutoCompleteTextView)findViewById(R.id.edUpdateStdName);
		
		stdName=(EditText)findViewById(R.id.UpdateStdNameResult);
		stdAddress=(EditText)findViewById(R.id.updateStdAddressResult);
		stdContact=(EditText)findViewById(R.id.updateStdContactResult);
		stdEmail=(EditText)findViewById(R.id.updateStdEmailResult);
		stdGender=(RadioGroup)findViewById(R.id.updateStdGenderResult);
		stdClg=(Spinner)findViewById(R.id.updateStdCollegeNameResult);
		displayData();
		stdGender.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.radioMale)
				{
					gen="Male";
				}
				if(checkedId == R.id.radioFemale)
				{
					gen="Female";
				}
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btnLoadStd:
			
				
				searchResult();
				
				Toast.makeText(getApplicationContext(), "Load is selected" +
						"...", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btnUpdateStdClear:
			searchStdName.setText("");
			display.setVisibility(display.INVISIBLE);
		break;

		case R.id.btnUpdateStdHome:
			Intent home=new Intent(UpdateStudent.this, HomeActivity.class);
			startActivity(home);
			finish();
		break;

		case R.id.btnUpdateStd:
			
			String getSearchname=searchStdName.getText().toString();
			obj_std.setStd_name(stdName.getText().toString());
			obj_std.setStd_address(stdAddress.getText().toString());
			obj_std.setStd_contact(stdContact.getText().toString());
			obj_std.setStd_email(stdEmail.getText().toString());
			obj_std.setStd_gender(gen);
			obj_clg.setCollege_name(stdClg.getSelectedItem().toString());
			helper.updateStudent(obj_std,getSearchname , obj_clg);
			Toast.makeText(getApplicationContext(), "Update is selected...", Toast.LENGTH_SHORT).show();
		break;

		
		default:
			break;
		}
		
		
	}
	
	
	
	
	
	
	public void displayData()
	{
		studentList=helper.getStudentName();
		ArrayAdapter<String> adpt=new ArrayAdapter<String>(UpdateStudent.this, android.R.layout.simple_list_item_1, studentList);
		searchStdName.setAdapter(adpt);
		searchStdName.setThreshold(1);
	}
	public void searchResult()
	{
		n=-1;
		student=helper.getStudentList();
		for(int i=0;i<student.size();i++)
		{
			if(searchStdName.getText().toString().equalsIgnoreCase(student.get(i).getStd_name()))
			{
				
				n=i;
				break;
			}
		}
		if(n>-1)
		{
			list_college=helper.getCollegeName();
			ArrayAdapter<String> adpt=new ArrayAdapter<String>(UpdateStudent.this,android.R.layout.simple_list_item_1,list_college);
			stdClg.setAdapter(adpt);
			
			stdName.setText(student.get(n).getStd_name());
			stdAddress.setText(student.get(n).getStd_address());
			stdContact.setText(student.get(n).getStd_contact());
			stdEmail.setText(student.get(n).getStd_email());
			stdClg.setSelection(adpt.getPosition(helper.getCollegeName(student.get(n).getCollege_id())));
			//stdGender.setText(student.get(n).getStd_gender());
			if(student.get(n).getStd_gender().equalsIgnoreCase("Male"))
			{
				stdMale.setChecked(true);
			}
			if(student.get(n).getStd_gender().equalsIgnoreCase("Female"))
			{
				stdFemale.setChecked(true);
			}
			display.setVisibility(display.VISIBLE);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "No Record Found....", Toast.LENGTH_SHORT).show();
			display.setVisibility(display.INVISIBLE);
		}
		
	}
	
	
	
	
	
}