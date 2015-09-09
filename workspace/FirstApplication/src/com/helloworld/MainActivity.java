package com.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	String getString;
	Spinner sp1;
	String txt;
	TextView t1,t4;
	EditText ed1;
	Button b1;
	SeekBar seekbar1;
	//int prog=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		ed1=(EditText)findViewById(R.id.editText1);
		sp1=(Spinner)findViewById(R.id.spinner1);
		t1=(TextView)findViewById(R.id.textView3);
		b1=(Button)findViewById(R.id.button1);
		seekbar1=(SeekBar)findViewById(R.id.seekBar1);
		t4=(TextView)findViewById(R.id.textView5);
		
		
		
		
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getString=ed1.getText().toString();
				Intent i = new Intent(MainActivity.this, HomeActivity.class);
                i.putExtra("Text", getString);
                startActivity(i);	
			}
		});
		
	
		
		seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			
			
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				t4.setTextSize(progress);
		        Toast.makeText(getApplicationContext(), String.valueOf(progress),Toast.LENGTH_SHORT).show();
				
				
				
			}
		});
		
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				txt=sp1.getSelectedItem().toString();
				
				Toast.makeText(getApplicationContext(),"You are selected:"+txt,Toast.LENGTH_SHORT).show();
				
				t1.setText(txt);
				
				
				
				
			}
			
			
			
		
			
			
			
			
			
			
			
			

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			
			
		});
		
		
		
		
		
		
		
	
		
		
	
		
		
	
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
		if(id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
