package com.example.relative_layout_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_ThirdScreen extends Activity implements OnClickListener {

	Button btn_nexScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third_screen);
		Toast.makeText(Activity_ThirdScreen.this, "Welcome to second screen",
				Toast.LENGTH_SHORT).show();
		init();
	}

	void init() {
		btn_nexScreen = (Button) findViewById(R.id.btn_ThirdScreen_next);
		btn_nexScreen.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_ThirdScreen_next:
			Intent intent_firstScreen = new Intent(Activity_ThirdScreen.this,
					Activity_FourthScreen.class);
			startActivity(intent_firstScreen);

			intent_firstScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

			break;

		default:
			break;
		}

	}
}
