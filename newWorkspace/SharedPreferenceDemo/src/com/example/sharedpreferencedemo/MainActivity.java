package com.example.sharedpreferencedemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	EditText name, phone, email, street, place;
	public static final String MyPREFERENCES = "MyPref";
	public static final String Name = "nameKey";
	public static final String Phone = "phoneKey";
	public static final String Email = "emailKey";
	public static final String Street = "streetKey";
	public static final String Place = "placeKey";
	Button btn_Save;
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		activityOpen();
		btn_Save.setOnClickListener(this);
		

	}

	private void init() {

		name = (EditText) findViewById(R.id.editTextName);
		phone = (EditText) findViewById(R.id.editTextPhone);
		email = (EditText) findViewById(R.id.editTextStreet);
		street = (EditText) findViewById(R.id.editTextEmail);
		place = (EditText) findViewById(R.id.editTextCity);
		btn_Save = (Button) findViewById(R.id.button1);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:

			String n  = name.getText().toString();
		      String ph  = phone.getText().toString();
		      String e  = email.getText().toString();
		      String s  = street.getText().toString();
		      String p  = place.getText().toString();
		      Editor editor = sharedpreferences.edit();
		      editor.putString(Name, n);
		      editor.putString(Phone, ph);
		      editor.putString(Email, e);
		      editor.putString(Street, s);
		      editor.putString(Place, p);

		      editor.commit(); 
			break;

		default:
			break;
		}

	}

	void activityOpen() {
		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences != null) {
			if (sharedpreferences.contains(Name)) {
				name.setText(sharedpreferences.getString(Name, ""));

			}
			if (sharedpreferences.contains(Phone)) {
				phone.setText(sharedpreferences.getString(Phone, ""));

			}
			if (sharedpreferences.contains(Email)) {
				email.setText(sharedpreferences.getString(Email, ""));

			}
			if (sharedpreferences.contains(Street)) {
				street.setText(sharedpreferences.getString(Street, ""));

			}
			if (sharedpreferences.contains(Place)) {
				place.setText(sharedpreferences.getString(Place, ""));

			}
		}
	}

}
