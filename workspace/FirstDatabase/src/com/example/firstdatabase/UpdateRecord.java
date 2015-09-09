package com.example.firstdatabase;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateRecord extends Activity {

	DBhelper mydb;
	EditText name, number;
	Button btn_submit, btn_loadData;
	Spinner sp1;
	Cursor c;
	ArrayList<String> list = new ArrayList<String>();
	ListView l1;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_record);

		sp1 = (Spinner) findViewById(R.id.updt_spinner);

		number = (EditText) findViewById(R.id.updt_record_number);
		btn_submit = (Button) findViewById(R.id.updt_submit);
		btn_loadData = (Button) findViewById(R.id.Load_record);

		mydb = new DBhelper(this);
		loadSpinnerData();
		number.setVisibility(View.INVISIBLE);
		btn_submit.setEnabled(false);
		btn_loadData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				c = mydb.getAllCotacts();
				String getName = sp1.getSelectedItem().toString();

				c = mydb.searchContacts(getName);

				number.setVisibility(View.VISIBLE);
				c.moveToFirst();
				if (c != null) {

					String getname = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_NAME));
					String getNumber = c.getString(c
							.getColumnIndex(DBhelper.CONTACTS_COLUMN_PHONE));

					number.setText(getNumber);
					btn_submit.setEnabled(true);
				}

			}
		});

		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String getName = sp1.getSelectedItem().toString();
				String getNumber = number.getText().toString();
				mydb.updateContact(getName, getNumber);

				number.setText(" ");
				
				
				Toast.makeText(getApplicationContext(),
						"Record is updated....", Toast.LENGTH_SHORT).show();
				number.setVisibility(View.INVISIBLE);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_record, menu);
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

	private void loadSpinnerData() {

		DBhelper db = new DBhelper(getApplicationContext());
		List<String> labels = db.getAllLabels();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, labels);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp1.setAdapter(dataAdapter);

		if (sp1.getAdapter().getCount() == 0) {
			btn_submit.setEnabled(false);
			Toast.makeText(getApplicationContext(),
					"Sorry, No records found..", Toast.LENGTH_SHORT).show();
		}
	}
}
