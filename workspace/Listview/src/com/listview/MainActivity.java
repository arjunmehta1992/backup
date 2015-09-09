package com.listview;

import java.util.ArrayList;
import java.util.Arrays;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	ListView mainListView;
	 private ArrayAdapter<String> listAdapter ;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mainListView = (ListView) findViewById( R.id.mainListView ); 
		 String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",  
                 "Jupiter", "Saturn", "Uranus", "Neptune"};    
		 ArrayList<String> planetList = new ArrayList<String>();
		 planetList.addAll( Arrays.asList(planets) ); 
		 listAdapter = new ArrayAdapter<String>(this, R.layout.rowlayout, planetList);  
		
		 listAdapter.add( "Ceres" );  
		    listAdapter.add( "Pluto" );  
		    listAdapter.add( "Haumea" );  
		    listAdapter.add( "Makemake" );  
		    listAdapter.add( "Eris" ); 
		    
		    
		    
		    
		    mainListView.setAdapter( listAdapter ); 
		
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