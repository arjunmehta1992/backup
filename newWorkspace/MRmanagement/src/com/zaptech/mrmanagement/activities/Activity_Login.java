package com.zaptech.mrmanagement.activities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.mrmanagement.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_Login extends Activity implements OnClickListener {

	EditText edtText_username, edtText_password;
	Button btn_login, btn_clear;
	TextView txtView_forget_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		init();
		onClickEvents();

	}

	public void init() {
		edtText_username = (EditText) findViewById(R.id.login_Username_Edittext);
		edtText_password = (EditText) findViewById(R.id.login_Password_Edittext);
		txtView_forget_password = (TextView) findViewById(R.id.login_Fergot_password);
		btn_login = (Button) findViewById(R.id.login_Login_btn);
		btn_clear = (Button) findViewById(R.id.login_Clear_btn);

	}

	void onClickEvents() {
		btn_login.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.login_Login_btn:

			checkValidation();

			break;

		case R.id.login_Clear_btn:
			edtText_username.setText("");
			edtText_password.setText("");

			break;

		default:
			break;
		}

	}

	void checkValidation() {
		if (!isValidEmail(edtText_username.getText().toString())) {
			edtText_username.setError("Username is not valied");
		}

		else if (edtText_password.getText().toString().equals("")) {
			edtText_password.setError("Password is not valied");
		}
	}

	private boolean isValidEmail(String email) {
		
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();

	}

}