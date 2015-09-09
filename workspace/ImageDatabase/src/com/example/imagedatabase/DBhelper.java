package com.example.imagedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper{

	
	public static final String DATABASE_NAME = "images.db";
	public static final String CONTACTS_TABLE_NAME = "stddata";
	public static final String CONTACTS_COLUMN_ID = "id";
	public static final String CONTACTS_COLUMN_IMAGE = "photo";
	public static final String CONTACTS_COLUMN_NAME = "name";
	public DBhelper(Context context) {
		super(context, DATABASE_NAME,null, 1);
		
		
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table if not exists stddata"+
		"(id integer primary key,name text,photo blob not null)");
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
