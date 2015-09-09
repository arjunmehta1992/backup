package com.example.firstdatabase;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Delete_Record extends Activity {

	DBhelper mydb;
	EditText name, number;
	Button btn_submit;
	Spinner sp1;
	String getName;
	Cursor cursor;
	ArrayList<String> list = new ArrayList<String>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_record);

		btn_submit = (Button) findViewById(R.id.del_submit);

		mydb = new DBhelper(this);
		sp1 = (Spinner) findViewById(R.id.spinner1);

		loadSpinnerData();

		
		if(sp1.getAdapter().getCount() == 0)
		{	
		
			Toast.makeText(getApplicationContext(), "Sorry, No records found..",
					Toast.LENGTH_SHORT).show();
			btn_submit.setEnabled(false);

		}

		else
		{
		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

									
					String getName = sp1.getSelectedItem().toString();

				mydb.deleteContact(getName);
				Toast.makeText(getApplicationContext(), "Record is deleted",
						Toast.LENGTH_SHORT).show();
				loadSpinnerData();
				

			}
		});
		}
	}

	private void loadSpinnerData() {
		
		DBhelper db = new DBhelper(getApplicationContext());
		List<String> labels = db.getAllLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, labels);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp1.setAdapter(dataAdapter);
		
		if(sp1.getAdapter().getCount() == 0)
		{
			btn_submit.setEnabled(false);
			Toast.makeText(getApplicationContext(), "Sorry, No records found..",
					Toast.LENGTH_SHORT).show();
		}
	}
}