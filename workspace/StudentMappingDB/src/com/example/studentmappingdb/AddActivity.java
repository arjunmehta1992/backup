package com.example.studentmappingdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener {
	
	Button addClg,addStudent,home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		addClg=(Button)findViewById(R.id.btnAddCollege);
		addStudent=(Button)findViewById(R.id.btnAddStudent);
		home=(Button)findViewById(R.id.btnAddHome);
		
		addClg.setOnClickListener(this);
		addStudent.setOnClickListener(this);
		home.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		
		
		
		
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAddCollege:
			Intent clg=new Intent(AddActivity.this, AddCollege.class);
			startActivity(clg);
			finish();
			
			break;
		case R.id.btnAddStudent:
			Intent clg1=new Intent(AddActivity.this, AddStudent.class);
			startActivity(clg1);
			Toast.makeText(AddActivity.this, "Add Student Clicked...", Toast.LENGTH_SHORT).show();
			
			break;

		case R.id.btnAddHome:
			Intent home=new Intent(AddActivity.this, HomeActivity.class);
			startActivity(home);
			finish();
			break;

		default:
			break;
		}
	}
}