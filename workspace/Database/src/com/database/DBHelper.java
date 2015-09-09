package com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	 static String DATABASE_NAME="userdata";
	    public static final String TABLE_NAME="user";
	    public static final String KEY_FNAME="fname";
	    public static final String KEY_LNAME="lname";
	    public static final String KEY_ID="id";
	public DBHelper(Context context)
	{
		super(context,DATABASE_NAME,null,1);
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		
		 String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_FNAME+" TEXT, "+KEY_LNAME+" TEXT)";
	        db.execSQL(CREATE_TABLE);
		
		
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
		
		
	}
	
	
}
