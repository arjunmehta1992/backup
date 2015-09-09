package com.example.locationalarm.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.locationalarm.R;
import com.example.locationalarm.database.DatabaseHelper;
import com.example.locationalarm.domain.BusRoute;

public class BrtsAllRoute extends ListActivity {
	private DatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		final ListView listview = (ListView) findViewById(R.id.listView1);
		String[] values = new String[] {
				"Line no. 1 RTO Circle - Maninagar",
				"Line no. 2 Anjali (Vasna) - Naroda",
				"Line no. 3 RTO Circle - Naroda",
				"Line no. 4 RTO Circle - Sarkari Litho Press (Delhi Darwaja)",
				"Line no. 5 Bopal - Iskcon - Maninagar",
				"Line no. 6 Soni Ni Chali - Odhav - S P Ring Road",
				"Line no. 7 Sarkari Litho Press (Delhi Darwaja) - Science City Approach",
				"Line no. 8 Chandkheda - Visat Junction - Maninagar",
				"Line no. 9 RTO Circle - Town Hall (Ellisbridge) - Memco - Naroda",
				"Line no. 10 Anjali (Vasna) - Kalupur Railway Station (Ahmedabad Central)",
				"Line no. 12 RTO Circle - Nehrunagar - Kalupur - RTO Circle",
				"Line no. 13 Anjali (Vasna) - Kalupur - Akhbarnagar - Anjali (Vasna)" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		position += 1;
		Log.e("", "" + position);

		helper = new DatabaseHelper(getApplicationContext());

		ArrayList<BusRoute> arrayList = helper.getRowFromquestID(position);

		Log.e("arraylist", "" + arrayList);

		Intent intent = new Intent(BrtsAllRoute.this, StationList.class);
		intent.putExtra("mylist", arrayList);

		startActivity(intent);

	}
}