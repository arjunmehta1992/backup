package com.databasedemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			String destPath = "/data/data/" + getPackageName()
					+ "/databases/MyDB";
			File f = new File(destPath);
			if (!f.exists()) 
			{

				CopyDB(getBaseContext().getAssets().open("mydb"),
						new FileOutputStream(destPath));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		DBAdapter db=new DBAdapter(this);
		db.open();
		
		long id=db.insertContact("Arjun","Arjunmehta1992@gmail.com");
		db.close();
		
		Cursor c=db.getAllContacts();
		if(c.moveToFirst())
		{
			do{
				
				DisplayContact(c);
			}while(	c.moveToNext());
		}
		
		db.close();
		
		
		
		db.open();
	    c = db.getContact(2);
	    if (c.moveToFirst())
	      DisplayContact(c);
	    else
	      Toast.makeText(this, "No contact found", Toast.LENGTH_SHORT).show();
	    db.close();

	    
	    
	    
	    
		
		
	}

	 public void CopyDB(InputStream inputStream, OutputStream outputStream)
		      throws IOException {
		    byte[] buffer = new byte[1024];
		    int length;
		    while ((length = inputStream.read(buffer)) > 0) {
		      outputStream.write(buffer, 0, length);
		    }
		    inputStream.close();
		    outputStream.close();
		  }
	
	public void DisplayContact(Cursor c) {
	    Toast.makeText(
	        this,
	        "id: " + c.getString(0) + "\n" + "Name: " + c.getString(1)
	            + "\n" + "Email:  " + c.getString(2), Toast.LENGTH_LONG)
	        .show();
	  }
}
