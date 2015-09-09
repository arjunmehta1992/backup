package com.example.studentmappingdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class DeleteActivity extends Activity implements OnClickListener {
	Button btn_deleteCollege, btn_deleteStudent, btn_delete_home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_delete);
		btn_deleteCollege = (Button) findViewById(R.id.btnDelCollege);
		btn_deleteStudent = (Button) findViewById(R.id.btnDelStudent);
		btn_delete_home = (Button) findViewById(R.id.btnDelHome);
		btn_deleteCollege.setOnClickListener(this);
		btn_deleteStudent.setOnClickListener(this);
		btn_delete_home.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnDelCollege:
			Intent deletecollege=new Intent(DeleteActivity.this, DeleteCollege.class);
			startActivity(deletecollege);
			finish();
			break;
		case R.id.btnDelStudent:

			Intent deletecollege1=new Intent(DeleteActivity.this, DeleteStudent.class);
			startActivity(deletecollege1);
			finish();
			break;
		case R.id.btnDelHome:
			Intent deletecollege2=new Intent(DeleteActivity.this, HomeActivity.class);
			startActivity(deletecollege2);
			finish();
			break;

		}

	}

}
