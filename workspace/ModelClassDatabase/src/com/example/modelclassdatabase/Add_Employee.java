package com.example.modelclassdatabase;

import android.app.Activity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Add_Employee extends Activity implements OnClickListener {

	EditText name, address, phone;
	DBHelper helpler_obj;
	Employee emp_obj;
	Button submit_btn, clear_btn;
	String getName, getAddress, getPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_employee);

		init();
		helpler_obj = new DBHelper(this);
		emp_obj = new Employee();
		// helpler_obj.open();

		submit_btn.setOnClickListener(this);
		clear_btn.setOnClickListener(this);
	}

	public void init() {
		name = (EditText) findViewById(R.id.add_record_name);
		address = (EditText) findViewById(R.id.add_record_address);
		phone = (EditText) findViewById(R.id.add_record_phone);
		submit_btn = (Button) findViewById(R.id.add_record_submit);
		clear_btn = (Button) findViewById(R.id.add_record_clear);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.add_record_submit:

			getName = name.getText().toString();
			getAddress = address.getText().toString();
			getPhone = phone.getText().toString();

			emp_obj.setName(getName);
			emp_obj.setAddress(getAddress);
			emp_obj.setPhone(getPhone);
			emp_obj.setName(getName);
			helpler_obj = new DBHelper(this);
			helpler_obj.insertEmployee(emp_obj);

			break;

		case R.id.add_record_clear:

			break;

		default:
			break;
		}

	}

}
