package com.example.xmlparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ACtivityHome extends ListActivity {

	static final String URL = "http://api.androidhive.info/pizza/?format=xml";

	static final String KEY_ITEM = "item";
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	static final String KEY_COST = "cost";
	static final String KEY_DESC = "description";
	ArrayList<HashMap<String, String>> menuItems;

	XMLParser parser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		menuItems = new ArrayList<HashMap<String, String>>();

		parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		org.w3c.dom.Document doc = parser.getDomElement(xml);

		NodeList nl = doc.getElementsByTagName(KEY_ITEM);

		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
			map.put(KEY_COST, "Rs." + parser.getValue(e, KEY_COST));
			map.put(KEY_DESC, parser.getValue(e, KEY_DESC));

			// adding HashList to ArrayList
			menuItems.add(map);

			ListAdapter adapter = new SimpleAdapter(this, menuItems,
					R.layout.raw_list, new String[] { KEY_NAME, KEY_DESC,
							KEY_COST }, new int[] { R.id.txt_PizzaTitle,
							R.id.txt_PizzaName, R.id.txt_PizzaCost });

			setListAdapter(adapter);

			ListView lv = getListView();

			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub

					/*String name = ((TextView) view.findViewById(R.id.txt_PizzaTitle))
							.getText().toString();
					String cost = ((TextView) view.findViewById(R.id.txt_PizzaCost))
							.getText().toString();
					String description = ((TextView) view
							.findViewById(R.id.txt_PizzaName)).getText()
							.toString();

					// Starting new intent
					Intent in = new Intent(getApplicationContext(),
							.class);
					in.putExtra(KEY_NAME, name);
					in.putExtra(KEY_COST, cost);
					in.putExtra(KEY_DESC, description);
					startActivity(in);*/
				}
			});
		}

	}

}
