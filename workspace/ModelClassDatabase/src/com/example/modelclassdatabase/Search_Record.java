package com.example.modelclassdatabase;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search_Record extends Activity {

	DBHelper mydb;
	EditText name;
	Button btn_search;
	ArrayList<Employee> list = new ArrayList<Employee>();
	String getName, getNumber, getAddress;

	Cursor cursor;
	Employee emp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_record);

		name = (EditText) findViewById(R.id.search_record_name);
		btn_search = (Button) findViewById(R.id.search_record_submit);
		emp = new Employee();

		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String getName = name.getText().toString();

				emp.setName(getName);

				mydb = new DBHelper(getApplicationContext());

				cursor = mydb.searchEmployee(emp);
				// c.moveToFirst();
				Toast.makeText(getApplicationContext(), "" + cursor.getCount(),
						Toast.LENGTH_SHORT).show();

				cursor.moveToFirst();
				if (cursor != null) {

					getName = cursor.getString(cursor
							.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_NAME));
					getNumber = cursor.getString(cursor
							.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_PHONE));

					getAddress = cursor.getString(cursor
							.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_ADDRESS));

					Toast.makeText(getApplicationContext(), "" + getAddress,
							Toast.LENGTH_SHORT).show();

				}

				AlertDialog.Builder alert = new AlertDialog.Builder(
						Search_Record.this);

				alert.setTitle("Search ");

				alert.setMessage("Emplolyee ID:->" + getName
						+ "\nEmployee Name:->" + getNumber
						+ "\nEmployee Salary:->" + getAddress);
				alert.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(),
										"Record Searched...",
										Toast.LENGTH_SHORT).show();
							}
						});
				alert.show();

			}

		});

	}
}
