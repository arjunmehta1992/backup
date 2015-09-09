package com.example.stackoverflow;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	ListView lst;
	ArrayList<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lst = (ListView) findViewById(R.id.lst);
		list.add("Arjun");
		list.add("Arjun");
		list.add("Arjun");
		list.add("Arjun");
		list.add("Arjun");
		list.add("Arjun");
		list.add("Arjun");
		list.add("Arjun");

		lst.setAdapter(new CustomAdapter());

	}

	public class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
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
			// TODO Auto-generated method stub
			LayoutInflater inflater = (LayoutInflater) MainActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.raw, null);
			TextView tv = (TextView) convertView.findViewById(R.id.subtitle);

			tv.setText(list.get(position).toString());

			return convertView;
		}

	}

}
