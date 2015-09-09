package com.autocomplete;

import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity {

	AutoCompleteTextView txt_city;
	MultiAutoCompleteTextView multi_city;
	EditText ed1;
	String[] city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		ed1 = (EditText) findViewById(R.id.form_lastname);
		TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

		txt_city = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		multi_city = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);

		txt_city.setThreshold(1);

		city = getResources().getStringArray(R.array.city);
		ArrayAdapter adpt = new ArrayAdapter(getApplicationContext(),
				android.R.layout.simple_expandable_list_item_1, city);
		txt_city.setAdapter(adpt);
		multi_city.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		multi_city.setAdapter(adpt);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
