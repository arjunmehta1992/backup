package com.database;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	private Button btn_save;
	private EditText edit_first,edit_last;
	private DBHelper mHelper;
	private SQLiteDatabase dataBase;
	private String id,fname,lname;
	private boolean isUpdate;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 btn_save=(Button)findViewById(R.id.button1);
	        edit_first=(EditText)findViewById(R.id.editText1);
	        edit_last=(EditText)findViewById(R.id.editText2);
		
	        
	        
	        isUpdate=getIntent().getExtras().getBoolean("update");
	        if(isUpdate)
	        {
	            id=getIntent().getExtras().getString("ID");
	            fname=getIntent().getExtras().getString("Fname");
	            lname=getIntent().getExtras().getString("Lname");
	            edit_first.setText(fname);
	            edit_last.setText(lname);
	           
	        }
	        
	         btn_save.setOnClickListener((OnClickListener) this);
	        
	         mHelper=new DBHelper(this);
	        
	        
		
		
		
	}
	
	
	
	
	 public void onClick(View v) {
	        fname=edit_first.getText().toString().trim();
	        lname=edit_last.getText().toString().trim();
	        if(fname.length()>0 && lname.length()>0)
	        {
	            saveData();
	        }
	        else
	        {
	            AlertDialog.Builder alertBuilder=new AlertDialog.Builder(MainActivity.this);
	            alertBuilder.setTitle("Invalid Data");
	            alertBuilder.setMessage("Please, Enter valid data");
	            alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	               
	                public void onClick(DialogInterface dialog, int which) {
	                       dialog.cancel();
	                   
	                }
	            });
	            alertBuilder.create().show();
	        }
	       
	    }
	
	 
	 
	 
	 
	 
	 
	 private void saveData(){
	        dataBase=mHelper.getWritableDatabase();
	        ContentValues values=new ContentValues();
	       
	        values.put(DBHelper.KEY_FNAME,fname);
	        values.put(DBHelper.KEY_LNAME,lname );
	       
	        System.out.println("");
	        if(isUpdate)
	        {   
	            //update database with new data
	            dataBase.update(DBHelper.TABLE_NAME, values, DBHelper.KEY_ID+"="+id, null);
	        }
	        else
	        {
	            //insert data into database
	            dataBase.insert(DBHelper.TABLE_NAME, null, values);
	        }
	        //close database
	        dataBase.close();
	        finish();
	       
	       
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
