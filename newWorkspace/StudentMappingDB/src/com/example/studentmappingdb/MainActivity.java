package com.example.studentmappingdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements
		android.view.View.OnClickListener {

	Button add, update, display, search, delete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
			Intent add=new Intent(getApplicationContext(), AddActivity.class);
			startActivity(add);
			
			break;
		case R.id.btnHomeUpdate:
			Intent update=new Intent(getApplicationContext(), UpdateActivity.class);
			startActivity(update);
			
			break;

		case R.id.btnHomeDisplay:
			Toast.makeText(getApplicationContext(), "Display Clicked...",
					Toast.LENGTH_SHORT).show();

			break;

		case R.id.btnHomeSearch:
			Intent search=new Intent(getApplicationContext(), SearchActivity.class);
			startActivity(search);
			

			break;

		case R.id.btnHomeDelete:
			Intent del=new Intent(getApplicationContext(), DeleteActivity.class);
			startActivity(del);

			break;

		default:
			break;
		}

	}

}
