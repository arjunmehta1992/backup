package com.example.linearlayoutdemo;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener {

	EditText edt_FirstNumber, edt_SecondNumber;
	Button btn_Add, btn_sub, btn_dev, btn_mul, btn_Next;
	float getFirstNo;
	float getSecondNo;
	TextView txtResult;
	float number3, number4, result1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

	}

	void init() {
		edt_FirstNumber = (EditText) findViewById(R.id.edt_FirstScreen_frstNo);
		edt_SecondNumber = (EditText) findViewById(R.id.edt_FirstScreen_secndNo);
		txtResult = (TextView) findViewById(R.id.txt_FirstScreen_res);
		btn_Add = (Button) findViewById(R.id.btn_FirstScreen_Add);
		btn_sub = (Button) findViewById(R.id.btn_FirstScreen_Minus);
		btn_dev = (Button) findViewById(R.id.btn_FirstScreen_Devid);
		btn_Next = (Button) findViewById(R.id.btn_FirstScreen_NextScreen);
		btn_mul = (Button) findViewById(R.id.btn_FirstScreen_Multiply);
		btn_Add.setOnClickListener(this);
		btn_sub.setOnClickListener(this);
		btn_mul.setOnClickListener(this);
		btn_dev.setOnClickListener(this);
		btn_Next.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btn_FirstScreen_Add:

			float number = Float.valueOf(edt_FirstNumber.getText().toString());
			float number2 = Float
					.valueOf(edt_SecondNumber.getText().toString());
			float result = number + number2;
			Toast.makeText(Activity_Home.this, "" + result, Toast.LENGTH_SHORT)

			.show();

			txtResult.setText(Float.toString(result));

			break;

		case R.id.btn_FirstScreen_Minus:
			float number3 = Float.valueOf(edt_FirstNumber.getText().toString());
			float number4 = Float
					.valueOf(edt_SecondNumber.getText().toString());
			float result1 = number3 - number4;
			Toast.makeText(Activity_Home.this, "" + result1, Toast.LENGTH_SHORT)

			.show();

			txtResult.setText(Float.toString(result1));

			break;

		case R.id.btn_FirstScreen_Devid:

			number3 = Float.valueOf(edt_FirstNumber.getText().toString());
			number4 = Float.valueOf(edt_SecondNumber.getText().toString());
			result1 = number3 / number4;
			Toast.makeText(Activity_Home.this, "" + result1, Toast.LENGTH_SHORT)

			.show();

			txtResult.setText(Float.toString(result1));

			break;

		case R.id.btn_FirstScreen_Multiply:

			number3 = Float.valueOf(edt_FirstNumber.getText().toString());
			number4 = Float.valueOf(edt_SecondNumber.getText().toString());
			result1 = number3 * number4;
			Toast.makeText(Activity_Home.this, "" + result1, Toast.LENGTH_SHORT)

			.show();

			txtResult.setText(Float.toString(result1));
			break;

		case R.id.btn_FirstScreen_NextScreen:

			Intent intent = new Intent(Activity_Home.this, SecondScreen.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
