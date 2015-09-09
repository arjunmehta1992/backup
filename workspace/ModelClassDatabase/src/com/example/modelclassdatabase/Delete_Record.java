package com.example.modelclassdatabase;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Delete_Record extends Activity {

	DBHelper mydb;
	EditText name, number;
	Button btn_submit, btn_loadData;
	Spinner sp1;
	Cursor c;
	Employee emp;
	ListView l1;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_delete_record);
		
		sp1=(Spinner)findViewById(R.id.delete_spinner_empname);
		btn_submit=(Button)findViewById(R.id.delete_record_submit);
		
		loadSpinnerData();
		btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				
				if(cursor!=null)
				{
					String getName=sp1.getSelectedItem().toString();
				mydb=new DBHelper(getApplicationContext());
				emp=new Employee();
				
				emp.setName(getName);
				mydb.deleteEmployee(emp);
				
				loadSpinnerData();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "No record found...",Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void loadSpinnerData() {

		DBHelper db = new DBHelper(this);
		List<String> labels = db.getAllLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, labels);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp1.setAdapter(dataAdapter);

		if (sp1.getAdapter().getCount() == 0) {
			sp1.setEnabled(false);
			Toast.makeText(getApplicationContext(),
					"Sorry, No records found..", Toast.LENGTH_SHORT).show();
		}
	}
}
