package com.example.locationalarm.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.locationalarm.R;
import com.example.locationalarm.database.DatabaseHelper;
import com.example.locationalarm.domain.BusRoute;

public class PlaceActivity extends ListActivity {
	private DatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		final ListView listview = (ListView) findViewById(R.id.listView1);
		String[] values = new String[] { "Hotels", "Shopping malls",
				"Petrol pumps", };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

	}

}
