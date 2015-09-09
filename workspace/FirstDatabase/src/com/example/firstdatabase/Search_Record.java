package com.example.firstdatabase;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Search_Record extends Activity {

	DBhelper mydb;
	EditText name;
	Button btn_serach;
	ArrayList<String> list = new ArrayList<String>();
	ListView l1;
	Cursor c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search__record);

		mydb = new DBhelper(getApplicationContext());
		l1 = (ListView) findViewById(R.id.search_list);
		name = (EditText) findViewById(R.id.Search_record_name);
		btn_serach = (Button) findViewById(R.id.search_submit);

		btn_serach.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String getName = name.getText().toString();

				if (name.getText().toString().length() <= 0
						|| !name.getText().toString().matches("[a-zA-Z ]+")) {

					Toast.makeText(getApplicationContext(),
							"First enter the name", Toast.LENGTH_SHORT).show();
					name.setError("Accepts Alphabets Only.");
				} 
				else 
				{
					c = mydb.searchContacts(getName);
					c.moveToFirst();
					if (c != null) 
					{

						String getname = c.getString(c
								.getColumnIndex(DBhelper.CONTACTS_COLUMN_NAME));
						String getNumber = c.getString(c
								.getColumnIndex(DBhelper.CONTACTS_COLUMN_PHONE));
						String getRecord = getName + " " + getNumber;
						list.add(getRecord);
						// } while (c.moveToNext());
					}
						
					
					Toast.makeText(getApplicationContext(),
								"No record found", Toast.LENGTH_SHORT).show();
						
					
					ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
							getApplicationContext(),
							android.R.layout.simple_list_item_single_choice,
							list);

					
					l1.setAdapter(arrayAdapter);

					l1.setBackgroundResource(R.drawable.rounded_corner);
				}
			}
		});

	}

	
	

}
