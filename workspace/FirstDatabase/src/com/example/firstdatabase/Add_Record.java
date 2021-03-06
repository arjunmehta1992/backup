package com.example.firstdatabase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Record extends Activity {

	DBhelper mydb;

	EditText name, number;
	Button btn_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_record);

		name = (EditText) findViewById(R.id.add_record_name);
		number = (EditText) findViewById(R.id.add_record_number);
		btn_submit = (Button) findViewById(R.id.add_submit);

		mydb = new DBhelper(this);

		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String getName = name.getText().toString();
				String getPhone = number.getText().toString();

				if (getName.length() <= 0) {
					name.setError("Name is empty....");
				} else if (getPhone.length() <= 0 || getPhone.length() >= 10) {
					number.setError("Phone number is empty....");
				} else {
					mydb.insertContact(getName, getPhone);
					Toast.makeText(Add_Record.this, "Record inserted...",
							Toast.LENGTH_SHORT).show();

					name.setText(" ");
					number.setText(" ");
				}
			}
		});

	}
}
