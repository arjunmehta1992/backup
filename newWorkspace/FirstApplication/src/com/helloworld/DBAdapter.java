package com.helloworld;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	public static final String KEY_ROWID = "_id";
	  public static final String KEY_NAME = "name";
	  public static final String KEY_EMAIL = "email";
	  private static final String TAG = "DBAdapter";
	  private static final String DATABASE_NAME = "MyDB";
	  private static final String DATABASE_TABLE = "contacts";
	  private static final int DATABASE_VERSION = 1;
	  
	  
	  private static final String DATABASE_CREATE = "create table contacts (_id integer primary key autoincrement, "
		      + "name text not null, email text not null);";

	  private final Context context;

	  private DatabaseHelper DBHelper;
	  private SQLiteDatabase db;
	  
	  
	  public DBAdapter(Context ctx) {
		    this.context = ctx;
		    DBHelper = new DatabaseHelper(context);
		  }
	  
	  
	  private static class DatabaseHelper extends SQLiteOpenHelper {
		    DatabaseHelper(Context context) 
		    {
		      super(context, DATABASE_NAME, null, DATABASE_VERSION);
		      	
		    
		    }

			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
			
				try {
					
					db.execSQL(DATABASE_CREATE);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {
				
				
				Log.w(TAG,oldVersion+"to"+newVersion+",Which will destroye all old data");
				
				onCreate(db);
			}
	  
	  }
	  
	  
	  
	  public  DBAdapter open() throws SQLException
	  {
		
		  
		  return this;
		  
	  }
	  
	  
}
