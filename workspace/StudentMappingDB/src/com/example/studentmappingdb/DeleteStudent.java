package com.example.studentmappingdb;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteStudent extends ActionBarActivity {

	EditText edCollegeName;
	Button btn_delete,btn_clear,btn_home;
	AutoCompleteTextView tv1;
	ArrayList<String> list_student,list_college;
	
	DBHelper helper;
	Spinner Collegename;
	CollegeData obj_clg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_student);
		
		helper=new DBHelper(DeleteStudent.this);
		obj_clg=new CollegeData();
		
		
		btn_delete=(Button) findViewById(R.id.btnStdDel);
		btn_clear=(Button)findViewById(R.id.btnStdClear);
		btn_home=(Button)findViewById(R.id.btnHomeDelete);
		Collegename=(Spinner)findViewById(R.id.delStdSpinner);
		tv1=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
		list_college=new ArrayList<String>();
		list_student=new ArrayList<String>();
		
		loadSpinnerData();
		bindData();
		
		
		
		
		btn_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String getCollegeName=Collegename.getSelectedItem().toString();
				String getStdname=tv1.getText().toString();
				helper.deleteStudent(getCollegeName, getStdname);
			}
		});
		
		
		
		
	}
	public void bindData()
	{
		list_student=helper.getStudentName();
		ArrayAdapter<String> adpt=new ArrayAdapter<String>(DeleteStudent.this,android.R.layout.simple_list_item_1,list_student);
		tv1.setAdapter(adpt);
		tv1.setThreshold(1);
	}
	
	private void loadSpinnerData() {

		DBHelper db = new DBHelper(getApplicationContext());
		List<String> labels = db.getAllLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, labels);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Collegename.setAdapter(dataAdapter);

		if (Collegename.getAdapter().getCount() == 0) {
			//btn_submit.setEnabled(false);
			Toast.makeText(getApplicationContext(),
					"Sorry, No records found..", Toast.LENGTH_SHORT).show();
		}
	}
	

	
}
