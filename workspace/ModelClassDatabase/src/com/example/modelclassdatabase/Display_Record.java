package com.example.modelclassdatabase;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Display_Record extends ActionBarActivity {

	private ListView obj;
	DBHelper mydb;
	Cursor c;
	ArrayList<String> array_list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_record);

		obj = (ListView) findViewById(R.id.listView1);
		mydb = new DBHelper(this);
		
		
		c = mydb.getAllEmployee();
		c.moveToFirst();
		array_list.add("Name        Number        Address");
		if (c != null) {
			do {

				String getName = c.getString(c
						.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_NAME));
				String getNumber = c.getString(c
						.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_PHONE));
				String getAddress = c.getString(c
						.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_ADDRESS));

				String getRecord = getName +"        " + getNumber + "        " + getAddress;

				array_list.add(getRecord);

			} while (c.moveToNext());
		}

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array_list);

		// adding it to the list view.
		obj.setAdapter(arrayAdapter);
	}

	
	
}
