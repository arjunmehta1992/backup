package com.example.loginsession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends Activity implements OnClickListener {

	EditText username, password;
	Button register, clear;
	DBHelper helper;
	LoginModel model_db;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_user);
		init();
		register.setOnClickListener(this);
		clear.setOnClickListener(this);

	}

	public void init() {
		username = (EditText) findViewById(R.id.Username_Edittext_register);
		password = (EditText) findViewById(R.id.Password_Edittext_register);
		register = (Button) findViewById(R.id.Register);
		clear = (Button) findViewById(R.id.Clear_btn_register);

	}

	void checkValidationAndRegistration() {
		if (!isValidEmail(username.getText().toString())) {
			username.setError("Username is not valied");
		}

		else if (password.getText().toString().equals("")) {
			password.setError("Password is not valied");
		} else {

			helper = new DBHelper(RegisterUser.this);
			model_db = new LoginModel();
			model_db.setUsername(username.getText().toString());
			model_db.setPassword(password.getText().toString());
			helper.registerUser(model_db);

			Intent intent = new Intent(RegisterUser.this, Login.class);
			startActivity(intent);
			Toast.makeText(RegisterUser.this, "Registration successfull",
					Toast.LENGTH_SHORT).show();
		}
	}

	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.Register:

			checkValidationAndRegistration();

			break;

		case R.id.Clear_btn_register:
			username.setText("");
			password.setText("");
			break;

		default:
			break;
		}

	}
}
