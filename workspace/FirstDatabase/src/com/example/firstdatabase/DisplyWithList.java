package com.example.firstdatabase;

import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplyWithList extends ActionBarActivity {

	ListView listview_data;
	DBhelper mydb;
	Cursor c;
	ArrayList<ListData> list;
	CustomAdapter custom_adpt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disply_with_list);

		listview_data = (ListView) findViewById(R.id.List_Data);
		mydb = new DBhelper(this);
		// c=mydb.getAllCotacts();

		custom_adpt = new CustomAdapter(this);
		// showDetails();
		list = mydb.getAllData();

		listview_data.setAdapter(custom_adpt);

		listview_data.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String getName = list.get(position).getName().toString();

				Toast.makeText(getApplicationContext(),
						"You are selected " + getName, Toast.LENGTH_SHORT)
						.show();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.disply_with_list, menu);
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

	public class CustomAdapter extends BaseAdapter {
		LayoutInflater inflater;
		Context context;

		public CustomAdapter(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}

		@Override
		public int getCount() {
			return list.size();
			// TODO Auto-generated method stub

		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_listview, null);
			
			String getName = list.get(position).getName().toString();
			
			String getPhone = list.get(position).getPhone();

			
			
			TextView name = (TextView) convertView
					.findViewById(R.id.title);
			TextView number = (TextView)convertView.findViewById(R.id.genre);
			name.setText(String.valueOf(getName));
			number.setText(getPhone);

			return convertView;

		}

	}

	public void showDetails() {

		String selectQuery = "SELECT  * FROM " + DBhelper.CONTACTS_TABLE_NAME;

		Cursor c = mydb.getAllCotacts();

		if (c.moveToFirst()) {
			do {
				ListData td = new ListData();
				td.setName(c.getString(c
						.getColumnIndex(DBhelper.CONTACTS_COLUMN_NAME)));
				td.setPhone(c.getString(c
						.getColumnIndex(DBhelper.CONTACTS_COLUMN_PHONE)));
				list.add(td);
			} while (c.moveToNext());

		}
	}

}
