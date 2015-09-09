package com.example.xmlparsing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class HomeActivity extends Activity implements OnClickListener{

	
	ListView lv_listOfPizza;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		
		
	}

	private void init() {
		lv_listOfPizza=(ListView)findViewById(R.id.lv_pizzaList);
		
		
		

	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
		
		
	}

	
}
