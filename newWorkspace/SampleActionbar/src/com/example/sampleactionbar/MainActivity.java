package com.example.sampleactionbar;

import android.app.ActionBar.OnNavigationListener;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		SearchView.OnQueryTextListener,OnNavigationListener {

	SearchView mSearchView;
	TextView mStatusView;
	ProgressBar refreshMenuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.app_name);
		actionBar.setIcon(R.drawable.ic_launcher);
		mStatusView = (TextView) findViewById(R.id.text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		SearchView search = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		/*TextView searchText = (TextView) search
				.findViewById(android.support.v7.appcompat.R.id.search_src_text);
		*/search.setSearchableInfo(searchManager
				.getSearchableInfo(new ComponentName(MainActivity.this,
						Search_Result_Activity.class)));
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_search:
			Toast.makeText(MainActivity.this, "Serach is clicked",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_location_found:
			Toast.makeText(MainActivity.this, "Location is clicked",
					Toast.LENGTH_SHORT).show();
			LocationFound();
			return true;
		case R.id.action_refresh:
			
			return true;
		case R.id.action_help:
			Toast.makeText(MainActivity.this, "help is clicked",
					Toast.LENGTH_SHORT).show();
			
			return true;
		case R.id.action_check_updates:
			Toast.makeText(MainActivity.this, "Updates call",
					Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void LocationFound() {
		Intent i = new Intent(MainActivity.this, Location_Found.class);
		startActivity(i);
	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

}

/*class SyncData extends AsyncTask<String, Void, String> {
    @Override
    protected void onPreExecute() {
     	ProgressBar refreshMenuItem = null;
        // set the progress bar view
        ((MenuItem) refreshMenuItem).setActionView(R.layout.action_progressbar);

        ((MenuItem) refreshMenuItem).expandActionView();
    }

    @Override
    protected String doInBackground(String... params) {
        // not making real request in this demo
        // for now we use a timer to wait for sometime
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        MenuItem refreshMenuItem = null;
		refreshMenuItem.collapseActionView();
        // remove the progress bar view
        refreshMenuItem.setActionView(null);
    }
};
*/