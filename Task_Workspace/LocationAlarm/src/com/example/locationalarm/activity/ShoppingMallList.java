package com.example.locationalarm.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.locationalarm.R;
import com.example.locationalarm.database.DatabaseHelper;
import com.example.locationalarm.domain.ShoppingMall;

public class ShoppingMallList extends Activity {
	private DatabaseHelper helper;
	private ListView listView = null;
	private Button button;
	ArrayList<ShoppingMall> myList;
	ArrayList<ShoppingMall> arrayList;
	ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout2);

		list = new ArrayList<String>();

		myList = (ArrayList<ShoppingMall>) getIntent().getSerializableExtra(
				"mylist");
		listView = (ListView) findViewById(R.id.listView2);
		for (int i = 0; i < myList.size(); i++) {

			String add = myList.get(i).getNAME().toString();
			add = add.replaceAll("\"", "");
			Log.i("", "" + add);
			list.add(add);

		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				String name = myList.get(position).getNAME();
				String address = myList.get(position).getADDRESS();
				address = address.replaceAll("\"", "");

				double lat = myList.get(position).getLATITUDE();
				double lng = myList.get(position).getLONGITUDE();

				Log.e("LatLang", "" + lat + "" + lng);

				Intent intent = new Intent(ShoppingMallList.this,
						ShoppingMallDetail.class);
				intent.putExtra("name", name);
				intent.putExtra("address", address);
				intent.putExtra("latitude", lat);
				intent.putExtra("longitude", lng);

				startActivity(intent);

			}
		});

	}

}