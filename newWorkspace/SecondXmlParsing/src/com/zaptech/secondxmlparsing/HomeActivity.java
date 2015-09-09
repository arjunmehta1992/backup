package com.zaptech.secondxmlparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {

	ListView lv_ListOfPizza;

	public static final String Url = "http://www.androidbegin.com/tutorial/XMLParseTutorial.xml";
	Context context;

	ArrayList<HashMap<String, String>> list_Pizza = new ArrayList<HashMap<String, String>>();

	ProgressDialog mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
		new RetrieveFeedTask().execute();
		
		
		
		
	}

	public void init() {
		lv_ListOfPizza = (ListView) findViewById(R.id.lv_ListData);
		context = HomeActivity.this;

	}

	public class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_Pizza.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list_Pizza.get(position);
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
			TextView tv_pizzaName = (TextView) convertView
					.findViewById(R.id.tv_Title);
			TextView tv_pizzacost = (TextView) convertView
					.findViewById(R.id.tv_Description);
			TextView tv_pizzadesc = (TextView) convertView
					.findViewById(R.id.tv_Link);

			TextView tv_date = (TextView) convertView
					.findViewById(R.id.tv_Date);

			tv_pizzaName.setText(list_Pizza.get(position).get("title"));

			tv_pizzacost.setText(list_Pizza.get(position).get("description"));
			
			tv_pizzadesc.setText(list_Pizza.get(position).get("link"));

			tv_date.setText(list_Pizza.get(position).get("date"));

			return convertView;
		}

	}

	class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

			mProgress = new ProgressDialog(context);
			mProgress.setTitle("Loading");
			mProgress.setMessage("Please wait...");
			mProgress.setCancelable(false);
			mProgress.show();

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			XMLParser parser = new XMLParser();
			String xml = parser.getXmlFromUrl(Url);
			Document doc = parser.getDomElement(xml);

			NodeList nl = doc.getElementsByTagName("item");

			for (int i = 0; i < nl.getLength(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				Element e = (Element) nl.item(i);
				map.put("title", parser.getValue(e, "title"));
				map.put("description", parser.getValue(e, "description"));
				map.put("link", parser.getValue(e, "link"));
				map.put("date", parser.getValue(e, "date"));
				list_Pizza.add(map);

				
				
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}

			lv_ListOfPizza.setAdapter(new CustomAdapter());

			
			super.onPostExecute(result);
		}

	}

}
