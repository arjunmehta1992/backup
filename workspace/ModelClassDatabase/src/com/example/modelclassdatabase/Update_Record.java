package com.example.modelclassdatabase;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Update_Record extends Activity implements OnClickListener {

	EditText name, address, phone;
	DBHelper helpler_obj;
	Employee emp_obj;
	Button submit_btn, clear_btn;
	String getAddress, getPhone, getName;
	Spinner getSpinner;
	String getData;
	Cursor cursor;
	ArrayList<Employee> list = new ArrayList<Employee>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_update_record);
		helpler_obj = new DBHelper(getApplicationContext());

		init();
		loadSpinnerData();
		submit_btn.setOnClickListener(this);
		clear_btn.setOnClickListener(this);

		getSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				getData = getSpinner.getSelectedItem().toString();
				Toast.makeText(getApplicationContext(), " " + getData,
						Toast.LENGTH_SHORT);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void init() {
		name = (EditText) findViewById(R.id.update_record_name);
		address = (EditText) findViewById(R.id.update_address);
		getSpinner = (Spinner) findViewById(R.id.update_spinner_empname);
		phone = (EditText) findViewById(R.id.update_record_phone);
		submit_btn = (Button) findViewById(R.id.update_record_submit);
		clear_btn = (Button) findViewById(R.id.update_record_clear);

		name.setVisibility(View.INVISIBLE);
		address.setVisibility(View.INVISIBLE);
		phone.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.update_record_submit:

			cursor = helpler_obj.getAllEmployee();
			String getName = getSpinner.getSelectedItem().toString();

			cursor = helpler_obj.searchEmployee(getName);

			name.setVisibility(View.VISIBLE);
			address.setVisibility(View.VISIBLE);
			phone.setVisibility(View.VISIBLE);

			cursor.moveToFirst();
			if (cursor != null) {

				emp_obj = new Employee();
				String getname = cursor.getString(cursor
						.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_NAME));
				String getNumber = cursor.getString(cursor
						.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_PHONE));

				String getAddress = cursor.getString(cursor
						.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_ADDRESS));

				name.setText(getname);
				address.setText(getAddress);
				phone.setText(getNumber);
				clear_btn.setEnabled(true);
			}

			break;

		case R.id.update_record_clear:

			Employee emp = new Employee();
			String getTextName = name.getText().toString();
			String getTextAddress = address.getText().toString();
			String getTextPhone = phone.getText().toString();
			emp_obj.setName(getTextName);
			emp_obj.setAddress(getTextAddress);
			emp_obj.setPhone(getTextPhone);
			
			helpler_obj.updateEmployee(emp_obj);
			
			Toast.makeText(getApplicationContext(), "Data Uploded.....", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}

	private void loadSpinnerData() {

		DBHelper db = new DBHelper(this);
		List<String> labels = db.getAllLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, labels);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		getSpinner.setAdapter(dataAdapter);

		if (getSpinner.getAdapter().getCount() == 0) {
			submit_btn.setEnabled(false);
			Toast.makeText(getApplicationContext(),
					"Sorry, No records found..", Toast.LENGTH_SHORT).show();
		}
	}
}
