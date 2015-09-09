package com.zaptech.xmlparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AndroidXMLParsingActivity extends ListActivity {

	// All static variables
	static final String URL = "http://skillmuni.cloudapp.net/smservice/Api/Search?category=0&organization=1";
	// static final String URL =
	// "http://api.androidhive.info/pizza/?format=xml";

	// XML node keys
	static final String KEY_SEARCH_RESPONSE = "SearchResponce"; // parent node
	static final String KEY_CONTENT_QUESTION = "CONTENT_QUESTION";
	static final String KEY_EXPIRYDATE = "EXPIRYDATE";
	static final String KEY_ID_CONTENT = "ID_CONTENT";
	static final String KEY_ID_CONTENT_LEVEL = "ID_CONTENT_LEVEL";
	String xml;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		try {
			xml = parser.doHTTPGetRequest(URL);
		} catch (Exception e1) {
			e1.printStackTrace();
		} // getting XML

		Log.i("xml>>>>>>>>>>>>>>>>>>>>>>>>>", "=======" + xml);
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_SEARCH_RESPONSE);
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_CONTENT_QUESTION, parser.getValue(e, KEY_CONTENT_QUESTION));
			map.put(KEY_EXPIRYDATE, parser.getValue(e, KEY_EXPIRYDATE));
			map.put(KEY_ID_CONTENT, parser.getValue(e, KEY_ID_CONTENT));
			map.put(KEY_ID_CONTENT_LEVEL, parser.getValue(e, KEY_ID_CONTENT_LEVEL));

			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems, R.layout.list_item,
				new String[] { KEY_CONTENT_QUESTION, KEY_EXPIRYDATE, KEY_ID_CONTENT },
				new int[] { R.id.name, R.id.desciption, R.id.cost });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String cost = ((TextView) view.findViewById(R.id.cost)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();

				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(KEY_CONTENT_QUESTION, name);
				in.putExtra(KEY_EXPIRYDATE, cost);
				in.putExtra(KEY_ID_CONTENT, description);
				startActivity(in);

			}
		});
	}
}