package com.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHub extends SQLiteOpenHelper {


	 private static final String dbname = "demo.db";
	    private static final int version = 2;
	    public static String Ename="Ename";
	    public static String Eid="Eid";
	    public static String Eadd="Eadd";
	    public static String Emp="Emp";
	
	public DataBaseHub(Context context, String name, CursorFactory factory,
			int version) {
		super(context,dbname, null, version);
		
		
		
		

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		
		
		String employee1 = "create table "+Emp+"("+Eid+" integer primary key,"+Ename+" tex                            t,"+Eadd+" text)";
        db.execSQL(employee1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		
		
		 if (oldVersion < newVersion) {
	            String employee1 = "create table emp("+Eid+" integer,"+Ename+" text,"+Eadd+" t            ext)";
	            db.execSQL(employee1);
	        }
	}

}