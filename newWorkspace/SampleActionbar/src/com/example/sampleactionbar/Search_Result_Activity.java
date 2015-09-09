package com.example.sampleactionbar;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Search_Result_Activity extends ActionBarActivity {

	private TextView txtQuery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		 
	        
		
	        txtQuery = (TextView) findViewById(R.id.txtQuery);
	 
	        handleIntent(getIntent());

	}

	 @Override
	    protected void onNewIntent(Intent intent) {
	        setIntent(intent);
	        handleIntent(intent);
	    }
	 
	
	 private void handleIntent(Intent intent) {
	        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	            String query = intent.getStringExtra(SearchManager.QUERY);
	            txtQuery.setText("Search Query: " + query);
	 
	        }
	 
	    }
}
