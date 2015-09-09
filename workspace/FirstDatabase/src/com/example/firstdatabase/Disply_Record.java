package com.example.firstdatabase;

import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Disply_Record extends ActionBarActivity {

	private ListView obj;
	DBhelper mydb;
	Cursor c;
	ArrayList<String> array_list =new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disply__record);
		obj=(ListView) findViewById(R.id.listView1);
		mydb = new DBhelper(this);
		c=mydb.getAllCotacts();
		c.moveToFirst();
		if(c!=null)
		{
			do {
				
				String	getName=c.getString(c.getColumnIndex(DBhelper.CONTACTS_COLUMN_NAME));
				String	getNumber=c.getString(c.getColumnIndex(DBhelper.CONTACTS_COLUMN_PHONE));
				String getRecord=getName+" "+getNumber;	
				array_list.add(getRecord);
			} while (c.moveToNext());
		}
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, array_list);

		// adding it to the list view.
		obj.setAdapter(arrayAdapter);
		
		
		obj.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			
				
				Toast.makeText(getApplicationContext()," "+obj.getSelectedItemPosition(),Toast.LENGTH_SHORT);
				
				
				
			}
		});

	}

}
