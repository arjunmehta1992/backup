package com.androidopentutorials.fastscrolldemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.androidopentutorials.fastscrolldemo.adapter.ListAdapter;

public class MainActivity extends ListActivity {

	ListView fruitView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fruitView = (ListView) findViewById(android.R.id.list);
		
		fruitView.setFastScrollEnabled(true);
		String[] fruits = getResources().getStringArray(R.array.fruits_array);

		List<String> fruitList = Arrays.asList(fruits);
		Collections.sort(fruitList);
		setListAdapter(new ListAdapter(this, fruitList));
		
		fruitView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				Toast.makeText(getApplicationContext(),
						"" + parent.getItemAtPosition(position),
						Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
