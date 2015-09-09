package com.example.loginsession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	EditText username, password;
	TextView Registration_user, Forgot_Password;
	Button login, clear;
	DBHelper helper;
	ImageView changePasswordLink, registerUserLink;

	LoginModel model_db;
	SharedPreferences pref;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String name = "nameKey";
	public static final String pass = "passwordKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();

		login.setOnClickListener(this);
		clear.setOnClickListener(this);
		Forgot_Password.setOnClickListener(this);
		changePasswordLink.setOnClickListener(this);
		registerUserLink.setOnClickListener(this);

	}

	public void init() {
		username = (EditText) findViewById(R.id.Username_Edittext);
		password = (EditText) findViewById(R.id.Password_Edittext);
		login = (Button) findViewById(R.id.Login_btn);
		clear = (Button) findViewById(R.id.Clear_btn);

		Forgot_Password = (TextView) findViewById(R.id.Fergot_password);
		changePasswordLink = (ImageView) findViewById(R.id.Login_changePasswordLink);
		registerUserLink = (ImageView) findViewById(R.id.Login_RegistrationLink);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Clear_btn:
			username.setText("");
			password.setText("");
			break;

		case R.id.Login_btn:

			checkValidation();

			break;

		case R.id.Fergot_password:
			Intent intent_forgotpass = new Intent(Login.this,
					ForgotPassword.class);
			startActivity(intent_forgotpass);
			break;

		case R.id.Login_changePasswordLink:
			Intent intent_changePassword = new Intent(Login.this,
					ChangePassword.class);
			startActivity(intent_changePassword);
			break;

		case R.id.Login_RegistrationLink:
			Intent intent_RegisterUSer = new Intent(Login.this,
					RegisterUser.class);
			startActivity(intent_RegisterUSer);

		default:
			break;
		}

	}

	void checkValidation() {
		if (!isValidEmail(username.getText().toString())) {
			username.setError("Username is not valied");
		}

		else if (password.getText().toString().equals("")) {
			password.setError("Password is not valied");
		} else {

			manageSession();
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
	protected void onResume() {
		pref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		if (pref.contains(name)) {
			if (pref.contains(pass)) {
				Intent i = new Intent(Login.this, Welcome.class);
				startActivity(i);
			}
		}
		super.onResume();
	}

	void manageSession() {

		helper = new DBHelper(Login.this);
		model_db = new LoginModel();
		int a = helper.checkLogin(username.getText().toString(), password
				.getText().toString());
		if (a > 0) {
			Editor editor = pref.edit();
			editor.putString(name, username.getText().toString());
			editor.putString(pass, password.getText().toString());
			editor.commit();
			Intent intent = new Intent(Login.this, Welcome.class);
			startActivity(intent);
		} else {
			Toast.makeText(Login.this, "Username and Password not found",
					Toast.LENGTH_SHORT).show();
		}

	}
}