package com.example.studentmappingdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SearchActivity extends Activity {

	Button btnClg, btnStd, home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		btnClg = (Button) findViewById(R.id.btnSearchCollege);
		btnStd = (Button) findViewById(R.id.btnSearchStudent);
		home = (Button) findViewById(R.id.btnSearchHome);

		btnClg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(SearchActivity.this, SearchCollege.class);
				startActivity(intent);
				finish();
			}
		});
		btnStd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchActivity.this, SearchStudent.class);
				startActivity(intent);
				finish();
			}
		});
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchActivity.this,HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	//@Override
	/*public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnSearchCollege:
			Intent intent = new Intent(SearchActivity.this, SearchCollege.class);
			startActivity(intent);

			finish();
		case R.id.btnSearchStudent:
			Intent std = new Intent(SearchActivity.this, SearchStudent.class);
			startActivity(std);
			finish();
		case R.id.btnSearchHome:
			Intent home = new Intent(SearchActivity.this, HomeActivity.class);
			startActivity(home);
			finish();
			break;

		default:
			break;
		}

	}*/

}