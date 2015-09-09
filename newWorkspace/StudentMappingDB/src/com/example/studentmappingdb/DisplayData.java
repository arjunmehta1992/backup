package com.example.studentmappingdb;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DisplayData extends ActionBarActivity implements OnClickListener {
	Button displayCollege, displayStudent, displayHome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_data);
		displayCollege = (Button) findViewById(R.id.btnDisplayCollege);
		displayStudent = (Button) findViewById(R.id.btnDisplayStudent);
		displayHome = (Button) findViewById(R.id.btnDisplayHome);

		displayCollege.setOnClickListener(this);
		displayStudent.setOnClickListener(this);
		displayHome.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnDisplayCollege:
			Intent displaycollege = new Intent(DisplayData.this,
					DIsplayCollegeList.class);
			startActivity(displaycollege);
			finish();

			break;
		case R.id.btnDisplayStudent:
			Intent home = new Intent(DisplayData.this, DisplayStudent.class);
			startActivity(home);
			finish();
			break;
		case R.id.btnDisplayHome:
			Intent home2 = new Intent(DisplayData.this, HomeActivity.class);
			startActivity(home2);
			finish();

			break;

		default:
			break;
		}
	}

}