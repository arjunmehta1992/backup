package com.zaptech.cardviewdemo;

import java.io.File;
import java.util.ArrayList;

import com.zaptech.adapter.MyRecyclerViewAdapter;
import com.zaptech.model.DataObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActivityHome extends Activity {

	private RecyclerView.Adapter mAdapter;
	private RecyclerView mRecyclerView;
	private RecyclerView.LayoutManager mLayoutManager;
	private static String LOG_TAG = "CardViewActivity";
	private ArrayList<DataObject> results;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	void init() {
		mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);
		mAdapter = new MyRecyclerViewAdapter(getDataSet());
		mRecyclerView.setAdapter(mAdapter);
	}

	private ArrayList<DataObject> getDataSet() {
		results = new ArrayList<DataObject>();
		for (int index = 0; index < 20; index++) {
			DataObject obj = new DataObject("Some Primary Text " + index,
					"Secondary " + index);
			results.add(index, obj);
		}
		return results;
	}

	@Override
	protected void onResume() {

		super.onResume();

		((MyRecyclerViewAdapter) mAdapter)
				.setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {

					@Override
					public void onItemClick(int position, View v) {
						// TODO Auto-generated method stub

						Log.i(LOG_TAG, " Clicked on Item " + position);

					}
				});
	}

}
