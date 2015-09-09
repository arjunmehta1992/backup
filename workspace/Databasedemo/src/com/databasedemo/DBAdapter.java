package com.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		DatabaseHelper(Context context) {
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
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			Log.w(TAG, oldVersion + "to" + newVersion
					+ ",Which will destroye all old data");

			onCreate(db);
		}

	}

	public DBAdapter open() throws SQLException {

		db = DBHelper.getWritableDatabase();
		return this;

	}

	public void close() {
		DBHelper.close();

	}

	public long insertContact(String name, String email) {

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
		values.put(KEY_EMAIL, email);
		return db.insert(DATABASE_TABLE, null, values);

	}

	public boolean deleteContact(long rowID) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowID, null) > 0;

	}

	public Cursor getAllContacts() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_EMAIL }, null, null, null, null, null);

	}

	
	public Cursor getContact(long rowId) throws SQLException {
	    Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
	        KEY_ROWID, KEY_NAME, KEY_EMAIL }, KEY_ROWID + "=" + rowId,
	        null, null, null, null, null);
	    if (mCursor != null) {
	      mCursor.moveToFirst();
	    }
	    return mCursor;
	  }
	
	public boolean updateContact(long rowId, String name, String email) {
		ContentValues updateValues=new ContentValues();
		updateValues.put(KEY_NAME,name);
		updateValues.put(KEY_NAME,email);
		return db.update(DATABASE_TABLE,updateValues,KEY_ROWID+"="+rowId, null)>0;
		
		
		
	}
	

}
