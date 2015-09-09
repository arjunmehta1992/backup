package com.example.relative_layout_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_Home extends Activity implements OnClickListener {

	Button btn_FirstScreen, btn_SecondScreen, btn_ThirdScreen,
			btn_FourthScreen, btn_FifthScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		listener();

	}

	public void init() {

		btn_FifthScreen = (Button) findViewById(R.id.btn_Home_FifthScreen);
		btn_FourthScreen = (Button) findViewById(R.id.btn_Home_FourthScreen);
		btn_ThirdScreen = (Button) findViewById(R.id.btn_Home_ThirdScreen);
		btn_SecondScreen = (Button) findViewById(R.id.btn_Home_SecondScreen);
		btn_FirstScreen = (Button) findViewById(R.id.btn_Home_FirstScreen);
	}

	public void listener() {
		btn_FirstScreen.setOnClickListener(this);
		btn_SecondScreen.setOnClickListener(this);
		btn_ThirdScreen.setOnClickListener(this);
		btn_FourthScreen.setOnClickListener(this);
		btn_FifthScreen.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_Home_FirstScreen:
			Intent intent_firstScreen = new Intent(Activity_Home.this,
					Activity_FirstScreen.class);
			startActivity(intent_firstScreen);

			break;

		case R.id.btn_Home_SecondScreen:

			Intent intent_SecondScreen = new Intent(Activity_Home.this,
					Activity_SecondScreen.class);
			startActivity(intent_SecondScreen);
			break;

		case R.id.btn_Home_ThirdScreen:

			Intent intent_ThirdScreen = new Intent(Activity_Home.this,
					Activity_ThirdScreen.class);
			startActivity(intent_ThirdScreen);
			break;

		case R.id.btn_Home_FourthScreen:

			Intent intent_FourthScreen = new Intent(Activity_Home.this,
					Activity_FourthScreen.class);
			startActivity(intent_FourthScreen);
			break;

		case R.id.btn_Home_FifthScreen:

			Intent intent_FifthScreen = new Intent(Activity_Home.this,
					Activity_FifthScreen.class);
			startActivity(intent_FifthScreen);
			break;

		default:
			break;
		}

	}

}