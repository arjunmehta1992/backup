package com.zaptech.jsonhive;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ActivityHome extends ListActivity {

	private ProgressDialog pDialog;

	private static String url = "http://api.androidhive.info/contacts/";

	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_PHONE_HOME = "home";
	private static final String TAG_PHONE_OFFICE = "office";

	JSONArray contacts = null;

	ArrayList<HashMap<String, String>> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		contactList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String name = ((TextView) view.findViewById(R.id.name))
						.getText().toString();
				String cost = ((TextView) view.findViewById(R.id.email))
						.getText().toString();
				String description = ((TextView) view.findViewById(R.id.mobile))
						.getText().toString();

				// Starting single contact activity
				/*
				 * Intent in = new Intent(getApplicationContext(),
				 * SingleContactActivity.class); in.putExtra(TAG_NAME, name);
				 * in.putExtra(TAG_EMAIL, cost); in.putExtra(TAG_PHONE_MOBILE,
				 * description); startActivity(in);
				 */
			}
		});

		new GetContacts().execute();
	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			pDialog = new ProgressDialog(ActivityHome.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			ServiceHandler sh = new ServiceHandler();

			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {

				try {

					JSONObject jsonObj = new JSONObject(jsonStr);

					contacts = jsonObj.getJSONArray(TAG_CONTACTS);

					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);

						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String email = c.getString(TAG_EMAIL);
						String address = c.getString(TAG_ADDRESS);
						String gender = c.getString(TAG_GENDER);

						JSONObject phone = c.getJSONObject(TAG_PHONE);
						String mobile = phone.getString(TAG_PHONE_MOBILE);
						String home = phone.getString(TAG_PHONE_HOME);
						String office = phone.getString(TAG_PHONE_OFFICE);

						HashMap<String, String> contact = new HashMap<String, String>();

						contact.put(TAG_ID, id);
						contact.put(TAG_NAME, name);
						contact.put(TAG_EMAIL, email);
						contact.put(TAG_PHONE_MOBILE, mobile);

						contactList.add(contact);
					}

				} catch (Exception e) {

				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (pDialog.isShowing())

				pDialog.dismiss();

			ListAdapter adapter = new SimpleAdapter(ActivityHome.this,
					contactList, R.layout.list_item, new String[] { TAG_NAME,
							TAG_EMAIL, TAG_PHONE_MOBILE }, new int[] {
							R.id.name, R.id.email, R.id.mobile });

			setListAdapter(adapter);

		}

	}
}
