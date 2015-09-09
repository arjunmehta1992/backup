package com.example.locationalarm.activity;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.locationalarm.R;
import com.example.locationalarm.database.DatabaseHelper;
import com.example.locationalarm.domain.FamousPlace;
import com.example.locationalarm.domain.Hotel;
import com.example.locationalarm.domain.PetrolPump;
import com.example.locationalarm.domain.ShoppingMall;
import com.example.locationalarm.domain.Theater;

public class PlaceDetail extends ListActivity {
	private DatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		helper = new DatabaseHelper(getApplicationContext());
		try {
			helper.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final ListView listview = (ListView) findViewById(R.id.listView1);
		String[] values = new String[] { "Hotels", "Theater", "FamousPlace",
				"Shopping Mall", "Petrol Pump" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Log.e("PLACE DETAIL POSITION", "" + position);

		helper = new DatabaseHelper(getApplicationContext());
		if (position == 0) {

			position += 1;
			ArrayList<Hotel> arrayList = helper.getPlacedetail(position);

			Log.e("arraylist", "" + arrayList);

			Intent intent = new Intent(PlaceDetail.this, HotelList.class);
			intent.putExtra("mylist", arrayList);

			startActivity(intent);

		}

		else if (position == 1) {

			position = 1;
			ArrayList<Theater> arrayList = helper.getTheaterdetail(position);

			Log.e("arraylist", "" + arrayList);

			Intent intent = new Intent(PlaceDetail.this, TheaterList.class);
			intent.putExtra("mylist", arrayList);

			startActivity(intent);

		} else if (position == 2) {

			position = 1;
			ArrayList<FamousPlace> arrayList = helper
					.getFamousPlacedetail(position);

			Log.e("arraylist", "" + arrayList);

			Intent intent = new Intent(PlaceDetail.this, FamousPlaceList.class);
			intent.putExtra("mylist", arrayList);

			startActivity(intent);

		} else if (position == 3) {

			position = 1;
			ArrayList<ShoppingMall> arrayList = helper
					.getShoppingMalldetail(position);

			Log.e("arraylist", "" + arrayList);

			Intent intent = new Intent(PlaceDetail.this, ShoppingMallList.class);
			intent.putExtra("mylist", arrayList);

			startActivity(intent);

		} else if (position == 4) {

			position = 1;
			ArrayList<PetrolPump> arrayList = helper
					.getPetrolPumpdetail(position);

			Log.e("arraylist", "" + arrayList);

			Intent intent = new Intent(PlaceDetail.this, PetrolPumpList.class);
			intent.putExtra("mylist", arrayList);

			startActivity(intent);

		}

	}
}
