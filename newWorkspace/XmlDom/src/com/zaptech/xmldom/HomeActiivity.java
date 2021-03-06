package com.zaptech.xmldom;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActiivity extends ActionBarActivity implements OnClickListener {

	ListView lv_ListOfPizza;

	
	public static final String Url = "http://api.androidhive.info/pizza/?format=xml";
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
		lv_ListOfPizza = (ListView) findViewById(R.id.lv_ListOfPizza);
		context = HomeActiivity.this;

	}

	@Override
	public void onClick(View v) {

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
					.findViewById(R.id.tv_name);
			TextView tv_pizzacost = (TextView) convertView
					.findViewById(R.id.tv_cost);
			TextView tv_pizzadesc = (TextView) convertView
					.findViewById(R.id.tv_description);

			tv_pizzaName.setText(list_Pizza.get(position).get("name"));
			tv_pizzacost.setText("Rs."+list_Pizza.get(position).get("cost"));
			tv_pizzadesc.setText(list_Pizza.get(position).get("description"));

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
			Document doc= parser.getDomElement(xml);

			NodeList nl = doc.getElementsByTagName("item");

			for (int i = 0; i < nl.getLength(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				Element e = (Element) nl.item(i);
				map.put("id", parser.getValue(e, "id"));
				map.put("name", parser.getValue(e, "name"));
				map.put("cost", parser.getValue(e, "cost"));
				map.put("description", parser.getValue(e, "description"));
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
