package com.example.locationalarm.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.locationalarm.R;
import com.example.locationalarm.database.DatabaseHelper;
import com.example.locationalarm.domain.BusRoute;
import com.google.android.gms.maps.model.LatLng;

public class StationList extends Activity {
	private DatabaseHelper helper;
	private ListView listView = null;
	private Button button;
	ArrayList<BusRoute> myList;
	ArrayList<BusRoute> arrayList;
	ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout2);

		list = new ArrayList<String>();

		myList = (ArrayList<BusRoute>) getIntent().getSerializableExtra(
				"mylist");
		listView = (ListView) findViewById(R.id.listView2);
		for (int i = 0; i < myList.size(); i++) {
			String add = myList.get(i).getSTATION().toString();
			// Log.i("",""+add);
			list.add(add);

		}

		button = (Button) findViewById(R.id.button1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				LatLng latLng = new LatLng(myList.get(position).getLATITUDE(),
						myList.get(position).getLONGITUDE());
				Intent intent = new Intent(StationList.this, LocationMap.class);
				intent.putExtra("mylist", myList);
				intent.putExtra("position", position);

				startActivity(intent);

			}
		});

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(StationList.this, LocationMap.class);
				intent.putExtra("mylist", myList);
				intent.putExtra("position", -1);

				startActivity(intent);

			}
		});

	}

}
