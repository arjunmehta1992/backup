package com.example.autocompletelist;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class HomeActivity extends ActionBarActivity {

	private ListView lv;

	ArrayAdapter<String> adapter;

	EditText inputSearch;

	ArrayList<HashMap<String, String>> productList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		String products[] = { "Dell Inspiron", "HTC One X", "HTC Wildfire S",
				"HTC Sense", "HTC Sensation XE", "iPhone 4S",
				"Samsung Galaxy Note 800", "Samsung Galaxy S3", "MacBook Air",
				"Mac Mini", "MacBook Pro" };

		lv = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		adapter = new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.product_name, products);
		lv.setAdapter(adapter);

		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				HomeActivity.this.adapter.getFilter().filter(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

	}

}
