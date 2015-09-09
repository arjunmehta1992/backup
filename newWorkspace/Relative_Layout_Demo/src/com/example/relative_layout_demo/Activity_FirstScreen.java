package com.example.relative_layout_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemSelectedListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_FirstScreen extends Activity implements OnClickListener,
		android.widget.AdapterView.OnItemSelectedListener,OnCheckedChangeListener{

	Button btn_nexScreen;
	Spinner spnner_city;
	RadioGroup rdioGrp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_screen);
		init();

	}

	void init() {
		btn_nexScreen = (Button) findViewById(R.id.btn_FirstScreen_NextScreen);
		btn_nexScreen.setOnClickListener(this);
		spnner_city = (Spinner) findViewById(R.id.spinner_FirstScreen_city);
		spnner_city.setOnItemSelectedListener(this);
		rdioGrp=(RadioGroup)findViewById(R.id.myRadioGroup);
		rdioGrp.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_FirstScreen_NextScreen:
			Intent intent_firstScreen = new Intent(Activity_FirstScreen.this,
					Activity_SecondScreen.class);
			startActivity(intent_firstScreen);

			intent_firstScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

			break;

		default:
			break;
		}

	}

	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String text = spnner_city.getSelectedItem().toString();
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
		.show();

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if(checkedId==R.id.radioBtn_Cricket)
		{
			Toast.makeText(getApplicationContext(), "Your hobby is Cricket", Toast.LENGTH_SHORT)
			.show();
		}
		else if(checkedId==R.id.radioBtn_Reading)
		{
			Toast.makeText(getApplicationContext(),"Your hobby is Reading", Toast.LENGTH_SHORT)
			.show();
		}
		else if(checkedId==R.id.radioBtn_PlayingGames)
		{
			Toast.makeText(getApplicationContext(),"Your hobby is playing games", Toast.LENGTH_SHORT)
			.show();
		}
	}
}