package com.example.studentmappingdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UpdateActivity extends Activity implements OnClickListener {

	Button upClg, upStd, upHome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		upClg = (Button) findViewById(R.id.btnUpdateCollege);
		upStd = (Button) findViewById(R.id.btnUpdateStudent);
		upHome = (Button) findViewById(R.id.btnUpdateHome);

		upClg.setOnClickListener(this);
		upStd.setOnClickListener(this);
		upHome.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.btnUpdateCollege:
					Intent clg=new Intent(getApplicationContext(), UpdateCollege.class);
					startActivity(clg);
			break;
		case R.id.btnUpdateStudent:
			Intent std=new Intent(getApplicationContext(), UpdateStudent.class);
			startActivity(std);
			
			break;
		case R.id.btnUpdateHome:
					Intent home=new Intent(getApplicationContext(), HomeActivity.class);
					startActivity(home);
			break;

		default:
			break;
		}

	}

}