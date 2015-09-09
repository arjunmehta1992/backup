package com.example.studentmappingdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	DBHelper mydb;
	Button add, update, display, search, delete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mydb = new DBHelper(HomeActivity.this);
		mydb.openDB();
		add = (Button) findViewById(R.id.btnHomeAdd);
		update = (Button) findViewById(R.id.btnHomeUpdate);
		display = (Button) findViewById(R.id.btnHomeDisplay);
		search = (Button) findViewById(R.id.btnHomeSearch);
		delete = (Button) findViewById(R.id.btnHomeDelete);

		add.setOnClickListener(this);
		update.setOnClickListener(this);
		display.setOnClickListener(this);
		search.setOnClickListener(this);
		delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnHomeAdd:
			Intent add = new Intent(getApplicationContext(), AddActivity.class);
			startActivity(add);
			finish();

			break;
		case R.id.btnHomeUpdate:
			Intent dis1 = new Intent(HomeActivity.this, UpdateActivity.class);
			startActivity(dis1);

			break;

		case R.id.btnHomeDisplay:
			Intent dis = new Intent(HomeActivity.this, DisplayData.class);
			startActivity(dis);
			finish();
			
			break;

		case R.id.btnHomeSearch:
			Intent search = new Intent(HomeActivity.this, SearchActivity.class);
			startActivity(search);
			//finish();

			break;

		case R.id.btnHomeDelete:
			Intent delete=new Intent(HomeActivity.this, DeleteActivity.class);
			startActivity(delete);
			finish();
			break;

		default:
			break;
		}
	}

}
