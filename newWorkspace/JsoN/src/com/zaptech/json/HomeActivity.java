package com.zaptech.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zaptech.json.DBmodel.DBModel;

public class HomeActivity extends Activity {

	ProgressDialog mProgress;
	ListView lv_HomeItem;
	ListView lv_title;
	Context context;
	DBModel dbObj;
	ArrayList<HomeItemModel> arraylistData;
	DBModel onj;
	TextView mTextview;
	String appMetaDataStr, versionNumberStr, tABVersionNUmberStr,
			webServicesURLStr, appNameStr;

	HomeItemModel homeItemModel_obj;

	JSONArray homeitems = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

		context = HomeActivity.this;
		lv_title.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent go = new Intent(HomeActivity.this,
						HomeItems_Description.class);
				go.putExtra("TITLE", arraylistData.get(position)
						.getHomeItem_title());
				startActivity(go);
			}
		});
	}

	public void init() {
		arraylistData = new ArrayList<HomeItemModel>();
		String DB_NAME=getResources().getString(R.string.DB_NAME);
		String DB_PATH=getResources().getString(R.string.DB_PATH);
		onj = new DBModel(HomeActivity.this,DB_NAME,DB_PATH);
		arraylistData = onj.getHomeItemsData();
		homeItemModel_obj = new HomeItemModel();
		lv_title = (ListView) findViewById(R.id.lst_itemshome);

		lv_title.setAdapter(new CustomData());
		// new GetImage().execute();

	}

	public class CustomData extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arraylistData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arraylistData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.raw_layout_homeitems, null);

			TextView title = (TextView) convertView
					.findViewById(R.id.tv_HomeItemName);

			title.setText(arraylistData.get(position).getHomeItem_title());

			return convertView;
		}

	}
}
