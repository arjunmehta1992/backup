package com.example.loginsession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity implements OnClickListener {

	Button changePassword, Clear;
	EditText username, newPassword, confirmPassword;
	LoginModel modelDB;
	DBHelper helper; 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_change_password);
		init();
		changePassword.setOnClickListener(this);
		Clear.setOnClickListener(this);
		
		
	}

	public void init() {

		changePassword = (Button) findViewById(R.id.ChangePassword_btn);
		Clear = (Button) findViewById(R.id.ChangePassword_btnClear);
		username = (EditText) findViewById(R.id.changePasswordUsername);
		newPassword = (EditText) findViewById(R.id.ChangePasswordNewpassword);
		confirmPassword = (EditText) findViewById(R.id.ChangePasswordConfirmPassword);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.ChangePassword_btn:

			velidationAndChangepassword();
			break;

		case R.id.ChangePassword_btnClear:

			break;

		default:

			break;
		}

	}

	void velidationAndChangepassword() {
		if (!isValidEmail(username.getText().toString())) {
			username.setError("Username is not valied");
		} else if (newPassword.getText().equals(" ")) {
			newPassword.setError("Field is empty");

		} else if (confirmPassword.getText().equals(" ")) {
			confirmPassword.setError("Field is empty");
		} else if(!confirmPassword.getText().toString().equals( newPassword.getText().toString())){
			confirmPassword.setError("Password not match");
		}
		else
		{
			modelDB = new LoginModel();
			helper=new DBHelper(ChangePassword.this);
			modelDB.setPassword(confirmPassword.getText().toString());
			modelDB.setUsername(username.getText().toString());
			int a=helper.changePassword(modelDB);
			
			if(a==1)
			{
				Toast.makeText(ChangePassword.this,"Email_ID is not correct",Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(ChangePassword.this,"Your password has been changed.",Toast.LENGTH_SHORT).show();
			}
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