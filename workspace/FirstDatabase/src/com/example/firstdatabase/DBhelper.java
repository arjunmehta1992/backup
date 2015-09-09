package com.example.firstdatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import android.R.bool;
import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "Directory.db";
	public static final String CONTACTS_TABLE_NAME = "contacts";
	public static final String CONTACTS_COLUMN_ID = "id";
	public static final String CONTACTS_COLUMN_PHONE = "phone";
	public static final String CONTACTS_COLUMN_NAME = "name";

	public DBhelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("create table if not exists contacts "
				+ "(id integer primary key, name text,phone text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS contacts");
		onCreate(db);

	}

	public boolean insertContact(String name, String phone) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("name", name);
		contentValues.put("phone", phone);
		db.insert("contacts", null, contentValues);
		return true;
	}

	public void deleteContact(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(CONTACTS_TABLE_NAME, "name=?", new String[] { name });

	}

	public void updateContact(String name, String phone) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("name", name);
		contentValues.put("phone", phone);
		db.update(CONTACTS_TABLE_NAME, contentValues, "name=?",
				new String[] { name });

	}

	public Cursor getAllCotacts() {
		ArrayList array_list = new ArrayList();
		// hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from contacts", null);
		return res;

	}

	public Cursor getText() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select name from contacts", null);

		return c;
	}

	public List<String> getAllLabels() {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT  * FROM " + CONTACTS_TABLE_NAME;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);// selectQuery,selectedArguments

		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(1));// adding 2nd column data
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return list;
	}

	public Cursor searchContacts(String name) {
		SQLiteDatabase db = this.getWritableDatabase();

		/*
		 * ContentValues values = new ContentValues(); values.put("name", name);
		 */

		Cursor cursor = db.rawQuery("Select * from contacts where name='"
				+ name + "'", null);

		return cursor;

	}

	// not worked....
	public ArrayList<ListData> getAllData() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ListData> list = new ArrayList<ListData>();
		String selectQuery = "SELECT  * FROM " + CONTACTS_TABLE_NAME;

		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				ListData td = new ListData();
				td.setName(c.getString(c.getColumnIndex(CONTACTS_COLUMN_NAME)));
				td.setPhone(c.getString(c.getColumnIndex(CONTACTS_COLUMN_PHONE)));
				list.add(td);
			} while (c.moveToNext());

		}
		return list;
	}

	// .....

	public void deleteAllContacts() {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(CONTACTS_TABLE_NAME, null, null);

	}

}
