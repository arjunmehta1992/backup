package com.zaptech.mycall;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "ContactDetail";
	public static final String COLUMN_CONTACT_NAME = "C_Name";
	public static final String COLUMN_CONTACT_NUMBER = "C_number";

	public ContactHelper(Context context) {
		super(context, TABLE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists " + TABLE_NAME + "("
				+ COLUMN_CONTACT_NAME + " text, " + COLUMN_CONTACT_NUMBER
				+ " number)");
		Log.i("Table Status===============", "Table Created........");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void insertData(ContactModel cm) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = getWritableDatabase();
		ContentValues v = new ContentValues();
		v.put(COLUMN_CONTACT_NAME, cm.getContactName());
		v.put(COLUMN_CONTACT_NUMBER, cm.getContactNumber());
		db.insert(TABLE_NAME, null, v);
		db.close();
	}

	public ArrayList<ContactModel> getData() {
		SQLiteDatabase sqldb = this.getWritableDatabase();
		ArrayList<ContactModel> list=new ArrayList<ContactModel>();
		Cursor cursor = sqldb.rawQuery("select * from " + TABLE_NAME, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
							ContactModel model=new ContactModel();
							
							model.setContactName(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NAME)));
							model.setContactNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NUMBER)));
							list.add(model);
							
					
				} while (cursor.moveToNext());
			}
		}
		sqldb.close();
		cursor.close();
		return list;

	}

	public int deleteContact(ContactModel temp) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = getWritableDatabase();
		int no=db.delete(TABLE_NAME, COLUMN_CONTACT_NAME+"= '"+temp.getContactName()+"'", null);
		db.close();
		return no;
	}

	public void updateData(ContactModel temp, String tempname) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(COLUMN_CONTACT_NAME, temp.getContactName());
		values.put(COLUMN_CONTACT_NUMBER, temp.getContactNumber());
		int no=db.update(TABLE_NAME, values, COLUMN_CONTACT_NAME+"='"+ tempname+"'", null);
		
	}
}
