package com.zaptech.sockpicture;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity_Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		ListView listView1 = (ListView) findViewById(R.id.lvStockNumber);

		String[] items = { "Milk", "Butter", "Yogurt", "Toothpaste",
				"Ice Cream" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				Activity_Home.this, android.R.layout.simple_list_item_1, items);

		listView1.setAdapter(adapter);

	}

}
