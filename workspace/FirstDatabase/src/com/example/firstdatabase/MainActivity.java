package com.example.firstdatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button add, update, delete, display,search,display_with_list,delete_all;
	DBhelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db=new DBhelper(getApplicationContext());
		
		add = (Button) findViewById(R.id.Add_Record);
		update = (Button) findViewById(R.id.Update_Record);
		delete = (Button) findViewById(R.id.Delete_Record);
		display = (Button) findViewById(R.id.Display_Record);
		search = (Button) findViewById(R.id.Search_Record);
		display_with_list = (Button) findViewById(R.id.display_custom_list);
		delete_all = (Button) findViewById(R.id.delete_all_record);
		
		
		delete_all.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					
				db.deleteAllContacts();
				Toast.makeText(getApplicationContext(),"All record deleted...",Toast.LENGTH_SHORT).show();
			}
		});
		
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(MainActivity.this, Add_Record.class);
				startActivity(i);

			}
		});

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						Delete_Record.class);
				startActivity(i);

			}
		});
		
		
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						Search_Record.class);
				startActivity(i);

			}
		});

		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						UpdateRecord.class);
				startActivity(i);

			}
		});
		
		
		display.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						Disply_Record.class);
				startActivity(i);

			}
		});

		
		display_with_list.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						DisplyWithList.class);
				startActivity(i);

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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
