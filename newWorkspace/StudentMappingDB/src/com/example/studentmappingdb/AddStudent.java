package com.example.studentmappingdb;

import java.util.ArrayList;
import java.util.Hashtable;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddStudent extends ActionBarActivity implements OnClickListener,
		OnItemSelectedListener, android.widget.RadioGroup.OnCheckedChangeListener {

	EditText std_name, std_address, std_number, std_email;
	RadioGroup gender;
	Spinner clg_name;
	Button btn_add, btn_clear, btn_home;
	ArrayList<String> listOFCollegeName;
	DBHelper obj_helper;
	StudentData obj_stud_data;
	Context context;
	String gen;
	int clgId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student);
		context=AddStudent.this;
		init();
		listOFCollegeName=new ArrayList<String>();
		obj_helper=new DBHelper(AddStudent.this);
		obj_stud_data=new StudentData();
		listOFCollegeName=obj_helper.getCollegeName();
		//Toast.makeText(getApplicationContext(), "lISTOF COLLEG NAME ="+listOFCollegeName.get(0).college_name,Toast.LENGTH_SHORT).show();
		ArrayAdapter<String> adpt=new ArrayAdapter<String>(AddStudent.this, android.R.layout.simple_spinner_dropdown_item,listOFCollegeName);
		clg_name.setAdapter(adpt);
		clg_name.setSelection(0,true);
	
	}

	public void init() {
		std_name = (EditText) findViewById(R.id.edStdName);
		std_address = (EditText) findViewById(R.id.edStdAddress);
		std_number = (EditText) findViewById(R.id.edStdContact);
		std_email = (EditText) findViewById(R.id.edStdEmail);
		gender = (RadioGroup) findViewById(R.id.addRgGender);
		clg_name = (Spinner) findViewById(R.id.edStudClgName);
		btn_add = (Button) findViewById(R.id.btnStdAdd);
		btn_clear = (Button) findViewById(R.id.btnStdClear);
		btn_home = (Button) findViewById(R.id.btnHome);
		
		
		clg_name.setOnItemSelectedListener(this);
		btn_home.setOnClickListener(this);
		gender.setOnCheckedChangeListener(this);
		btn_add.setOnClickListener(this);
		
	}

	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		clgId=obj_helper.getClgId(clg_name.getSelectedItem().toString());
		
		

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnStdAdd:
			obj_stud_data.setStd_name(std_name.getText().toString());
			obj_stud_data.setStd_address(std_address.getText().toString());
			obj_stud_data.setStd_contact(std_number.getText().toString());
			obj_stud_data.setStd_email(std_email.getText().toString());
			obj_stud_data.setStd_gender(gen);
			obj_stud_data.setCollege_id(clgId);
			obj_helper.addStudentData(obj_stud_data);
			Toast.makeText(getApplicationContext(), "Record Inserted...", Toast.LENGTH_SHORT).show();
			std_name.setText(" ");
			std_address.setText(" ");
			std_number.setText(" ");
			std_email.setText(" ");
			break;

		case R.id.btnStdClear:

			std_name.setText(" ");
			std_address.setText(" ");
			std_number.setText(" ");
			std_email.setText(" ");
			
			
			
			break;

		case R.id.btnHome:

			Intent i1=new Intent(AddStudent.this,HomeActivity.class);
			startActivity(i1);
			finish();
			break;

		default:
			break;
		}

	}
/*
	class GetCollegeNameCustomAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listOFCollegeName.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listOFCollegeName.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.id.edStudClgName,null);
			
			
			
			
			
			return ;
		}
		
	}*/

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if(checkedId==R.id.addStdMale)
		{
			gen="Male";
		}
		
		if(checkedId==R.id.addStdFemale)
		{
			gen="Female";
		}

	
	}
	
}
