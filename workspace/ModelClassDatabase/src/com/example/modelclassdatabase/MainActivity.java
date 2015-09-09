package com.example.modelclassdatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button add, update, delete, display, search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		init();

		add.setOnClickListener(this);
		update.setOnClickListener(this);
		delete.setOnClickListener(this);
		display.setOnClickListener(this);
		search.setOnClickListener(this);

	}

	public void init() {
		add = (Button) findViewById(R.id.Add_Employee);
		update = (Button) findViewById(R.id.Update_Employee);
		delete = (Button) findViewById(R.id.Delete_Employee);
		search = (Button) findViewById(R.id.Search_Employee);

		display = (Button) findViewById(R.id.Display_Employee);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.Add_Employee:
			Intent i = new Intent(MainActivity.this, Add_Employee.class);
			startActivity(i);

			break;

		case R.id.Update_Employee:

			Intent intent = new Intent(MainActivity.this, Update_Record.class);
			startActivity(intent);
			break;

		case R.id.Delete_Employee:
			Intent intent2 = new Intent(MainActivity.this, Delete_Record.class);
			startActivity(intent2);
			break;
			

		case R.id.Display_Employee:
			Intent display=new Intent(getApplicationContext(), Display_Record.class);
			startActivity(display);
			break;

		case R.id.Search_Employee:
			Intent intent3 = new Intent(MainActivity.this, Search_Record.class);
			startActivity(intent3);
			break;

		default:
			break;
		}

	}
}
