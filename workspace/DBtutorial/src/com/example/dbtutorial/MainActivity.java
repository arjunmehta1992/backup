package com.example.dbtutorial;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "com.example.AddressBook.MESSAGE";

	private ListView obj;
	DBHelper mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mydb = new DBHelper(this);
		ArrayList array_list = (ArrayList) mydb.getAllCotacts();

		ArrayAdapter arrayAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, array_list);

		// adding it to the list view.
		obj = (ListView) findViewById(R.id.listView1);
		obj.setAdapter(arrayAdapter);

		obj.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int id_To_Search = arg2 + 1;
				Bundle dataBundle = new Bundle();
				dataBundle.putInt("id", id_To_Search);
				Intent intent = new Intent(getApplicationContext(),
						DisplayContact.class);
				intent.putExtras(dataBundle);
				startActivity(intent);
			}
		});

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
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.item1:
			Bundle dataBundle = new Bundle();
			dataBundle.putInt("id", 0);
			Intent intent = new Intent(MainActivity.this, DisplayContact.class);
			intent.putExtras(dataBundle);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	public boolean onKeyDown(int keycode, KeyEvent event) {
		if (keycode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);
		}
		return super.onKeyDown(keycode, event);
	}

}