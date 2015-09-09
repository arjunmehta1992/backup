package com.example.contentproviderdemo;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class StudentsProvider extends ContentProvider {

	public static final String PROVIDER_NAME = "com.example.contentproviderdemo.provider.College";
	public static final String URL = "content://" + PROVIDER_NAME + "/students";
	public static final Uri CONTENT_URI = Uri.parse(URL);
	static final String _ID = "_id";
	static final String NAME = "name";
	static final String GRADE = "grade";
	private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

	static final int STUDENTS = 1;
	static final int STUDENT_ID = 2;

	 static final UriMatcher uriMatcher;
	 static{
	      uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	      uriMatcher.addURI(PROVIDER_NAME, "students", STUDENTS);
	      uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
	   }
	 
	 
	 private SQLiteDatabase db;
	   static final String DATABASE_NAME = "College";
	   static final String STUDENTS_TABLE_NAME = "students";
	   static final int DATABASE_VERSION = 1;
	   static final String CREATE_DB_TABLE = 
	      " CREATE TABLE " + STUDENTS_TABLE_NAME +
	      " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	      " name TEXT NOT NULL, " +
	      " grade TEXT NOT NULL);";
	   
	   private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			 db.execSQL(CREATE_DB_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			 db.execSQL("DROP TABLE IF EXISTS " +  STUDENTS_TABLE_NAME);
	          onCreate(db);
		}
	   }
	   
	
	@Override
	public boolean onCreate() {
		
		
		Context context = getContext();
	      DatabaseHelper dbHelper = new DatabaseHelper(context);
	      
	      
	      
	      db = dbHelper.getWritableDatabase();
	      return (db == null)? false:true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		long rowID = db.insert(	STUDENTS_TABLE_NAME, "", values);
		 if (rowID > 0)
	      {
	         Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
	         getContext().getContentResolver().notifyChange(_uri, null);
	         return _uri;
	      }
		 throw new SQLException("Failed to add a record into " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
