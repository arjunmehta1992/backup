package com.example.routedisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	Button getRoute, clean_text,Polylinemap;
	EditText source,dest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		Polylinemap.setOnClickListener(this);
		getRoute.setOnClickListener(this);
		clean_text.setOnClickListener(this);
	}

	public void init() {
		getRoute = (Button) findViewById(R.id.displayMap_btn);
		Polylinemap = (Button) findViewById(R.id.Indore_map_button);
		clean_text = (Button) findViewById(R.id.clearText_btn);
		source=(EditText)findViewById(R.id.Source_Edittext);
		dest=(EditText)findViewById(R.id.Destination_Edittext);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.displayMap_btn:
			Intent i=new Intent(MainActivity.this,ShowRoute.class);
			startActivity(i);
			
			break;

		case R.id.clearText_btn:
			
			source.setText("");
			dest.setText("");
			
		case R.id.Indore_map_button:
			
			Intent intent=new Intent(MainActivity.this,PolylineMap.class);
			startActivity(intent);
			break;

			
		default:
			break;
		}

	}

}
