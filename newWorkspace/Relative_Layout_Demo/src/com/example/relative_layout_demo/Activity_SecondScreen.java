package com.example.relative_layout_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Activity_SecondScreen extends Activity implements OnClickListener {

	Button btn_nexScreen, btn_getRating;
	RatingBar rtngBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_screen);
		init();

		Toast.makeText(Activity_SecondScreen.this, "Welcome to second screen",
				Toast.LENGTH_SHORT).show();
	}

	void init() {
		btn_nexScreen = (Button) findViewById(R.id.btn_SecondScreen_NextScreen);
		rtngBar = (RatingBar) findViewById(R.id.rtngBar_SecondScreen);
		btn_getRating = (Button) findViewById(R.id.btn_GiveRating);
		btn_getRating.setOnClickListener(this);
		btn_nexScreen.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_SecondScreen_NextScreen:
			Intent intent_firstScreen = new Intent(Activity_SecondScreen.this,
					Activity_ThirdScreen.class);
			startActivity(intent_firstScreen);

			intent_firstScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

			break;

		case R.id.btn_GiveRating:

			String rating = String.valueOf(rtngBar.getRating());
			Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG)
					.show();

			break;

		default:
			break;
		}

	}
}
