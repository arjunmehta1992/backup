package com.example.textchangedemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class ACtivityHome extends Activity {

	EditText edt_textSave;
	TextView txt_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();
		edt_textSave.addTextChangedListener(watch);
	}

	public void init() {
		edt_textSave = (EditText) findViewById(R.id.edt_textSave);
		txt_text = (TextView) findViewById(R.id.textView1);
	}

	TextWatcher watch = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

			txt_text.setText(s);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

}
