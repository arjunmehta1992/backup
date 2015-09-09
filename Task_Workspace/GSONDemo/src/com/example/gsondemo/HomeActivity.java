package com.example.gsondemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.JetPlayer.OnJetEventListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {

	ListView lv_list;
	String str;
	Context context;
	Model_MainResource obj_RootCountry;
	ArrayList<RootCountry> list_mainNode;
	ArrayList<CountryList> list_child;
	TextView tv_success;
	List<Model_countryList> list_cntyList = new ArrayList<Model_countryList>();
	Model_MainResource obj=new Model_MainResource();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		new GetData().execute();

	}

	private void init() {
		context = HomeActivity.this;
		obj_RootCountry = new Model_MainResource();
		lv_list = (ListView) findViewById(R.id.lv_List);
		list_mainNode = new ArrayList<RootCountry>();
		list_child = new ArrayList<CountryList>();
		tv_success = (TextView) findViewById(R.id.txt_success);

	}

	public class GetData extends AsyncTask<Void, Void, Void> {
		ProgressDialog mprogress;

		@Override
		protected void onPreExecute() {
			mprogress = new ProgressDialog(HomeActivity.this);
			mprogress.setTitle("Loadling");
			mprogress.setMessage("Please wait...");
			mprogress.setCancelable(false);
			mprogress.show();

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			str = GET("http://demo.mysamplecode.com/Servlets_JSP/CountryJSONData");

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (mprogress.isShowing()) {
				mprogress.dismiss();

			}
			Gson gson = new Gson();

			obj=gson.fromJson(str, Model_MainResource.class);

			tv_success.setText(obj.getSuccess());
			
			list_cntyList = obj.getCountryList();
			
			System.err.println("Size>>>>>>>>>>>>>>>>>>>>>>>>"+obj.getCountryList().size());
			
			lv_list.setAdapter(new CustomAdapter());

			super.onPostExecute(result);
		}

	}

	class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_cntyList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list_cntyList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.raw_layout, null);
			TextView tv_name = (TextView) convertView
					.findViewById(R.id.txt_name);
			TextView tv_code = (TextView) convertView
					.findViewById(R.id.txt_code);
			TextView tv_continent = (TextView) convertView
					.findViewById(R.id.txt_continent);

			tv_code.setText(obj.getCountryList().get(position)
					.getCode());

			tv_name.setText(obj.getCountryList().get(position)
					.getName());
			tv_continent.setText(obj.getCountryList().get(position)
					.getContinent());

			return convertView;
		}

	}

	public String GET(String url) {
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	// convert inputstream to String
	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

}
