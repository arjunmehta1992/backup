package com.example.loginsession;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Welcome extends Activity implements OnClickListener {

	TextView logout, welcome_note;
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		init();
		logout.setOnClickListener(this);
		sharedpreferences = getSharedPreferences(Login.MyPREFERENCES,
				Context.MODE_PRIVATE);

		welcome_note.setText("Welcome:"
				+ sharedpreferences.getString(Login.name, ""));

	}

	void init() {
		logout = (TextView) findViewById(R.id.logout);
		welcome_note = (TextView) findViewById(R.id.welcome_note);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.logout:

			Intent intent = new Intent(Welcome.this, Login.class);
			startActivity(intent);
			
			sharedpreferences = getSharedPreferences(Login.MyPREFERENCES,
					Context.MODE_PRIVATE);
			Editor editor = sharedpreferences.edit();
			editor.clear();
			editor.commit();

			
			break;
			
			
		

		default:
			break;
		}
	}

}
