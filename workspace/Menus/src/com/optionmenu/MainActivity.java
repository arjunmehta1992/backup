package com.optionmenu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	String[] ToolsList={"Index","First","Second","Third"};
	ListView l1;
	Menu menu;
	View v;
	RelativeLayout l2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		l1=(ListView)findViewById(R.id.List);
		l2=(RelativeLayout)findViewById(R.id.layout);
		
		LayoutInflater 	Li=getLayoutInflater();
		v=Li.inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.layout_toast));
		
		
		
		
		registerForContextMenu(l2);
		
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
		if (id == R.id.Settings) {

			Toast toast=new Toast(MainActivity.this);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setView(v);
			toast.show();
		}
		else if(id == R.id.home) {

			Toast toast=new Toast(MainActivity.this);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setView(v);
			toast.show();
		}
		else if(id == R.id.First) {

			Toast toast=new Toast(MainActivity.this);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setView(v);
			toast.show();
		}

		else if(id == R.id.Second) {

			Toast toast=new Toast(MainActivity.this);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setView(v);
			toast.show();
		}
		else if(id == R.id.Third) {

			Toast toast=new Toast(MainActivity.this);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setView(v);
			toast.show();
		}
		else if(id == R.id.Four) {

			Toast toast=new Toast(MainActivity.this);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setView(v);
			toast.show();
		}


		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("City");
		
		menu.add(0, v.getId(), 0,"Ahmedabad");
		menu.add(0, v.getId(), 0,"Baroda");
		menu.add(0, v.getId(), 0,"Rajkot");
		menu.add(0, v.getId(), 0,"Bhavnagar");
		
		
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if(item.getTitle()=="Ahmedabad")
		{
			Toast.makeText(MainActivity.this, "Ahmedabad", Toast.LENGTH_SHORT)
			.show();
			
		}
		
		if(item.getTitle()=="Baroda")
		{
			Toast.makeText(MainActivity.this, "Baroda", Toast.LENGTH_SHORT)
			.show();
			
		}
		
		if(item.getTitle()=="Rajkot")
		{
			Toast.makeText(MainActivity.this, "Rajkot", Toast.LENGTH_SHORT)
			.show();
			
		}
		if(item.getTitle()=="Bhavnagar")
		{
			Toast.makeText(MainActivity.this, "Bhavnagar", Toast.LENGTH_SHORT)
			.show();
			
		}
		
		
		return super.onContextItemSelected(item);
	}
	
	
}
