package com.multiplewidget;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);
		
		
		
		
		
		 
		TabHost tabHost = getTabHost(); 
 
		// Android tab
		Intent intentAndroid = new Intent().setClass(this, first.class);
		TabSpec tabSpecAndroid = tabHost
		  .newTabSpec("Android")
		  .setIndicator("First Activity",null )
		  .setContent(intentAndroid);
 
		// Apple tab
		Intent intentApple = new Intent().setClass(this, second.class);
		TabSpec tabSpecApple = tabHost
		  .newTabSpec("Apple")
		  .setIndicator("Second Activity", null)
		  .setContent(intentApple);
 
		
		Intent intentThird=new Intent().setClass(this, third.class);
		TabSpec tabspec=tabHost.newTabSpec("Third").setIndicator("Third Activity",null).setContent(intentThird);
		
		
		 
		 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabspec);
		
		tabHost.setCurrentTab(2);
		
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
