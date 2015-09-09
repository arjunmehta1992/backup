package com.example.modelclassdatabase;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.JetPlayer.OnJetEventListener;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "employes.db";
	public static final String EMPLOYEE_TABLE_NAME = "employeemgmt";
	public static final String EMPLOYEE_COLUMN_ID = "id";
	public static final String EMPLOYEE_COLUMN_NAME = "name";
	public static final String EMPLOYEE_COLUMN_ADDRESS = "address";
	public static final String EMPLOYEE_COLUMN_PHONE = "phone";

	Employee obj_emp;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists " + EMPLOYEE_TABLE_NAME + "("
				+ EMPLOYEE_COLUMN_ID + " integer primary key, "
				+ EMPLOYEE_COLUMN_NAME + " text, " + EMPLOYEE_COLUMN_ADDRESS
				+ " text, " + EMPLOYEE_COLUMN_PHONE + " text)");

		/*
		 * db.execSQL("create table if not exists employee " +
		 * "(id integer primary key, name text,address text,phone text)");
		 */
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void open() {
		SQLiteDatabase db = getWritableDatabase();

	}

	public void insertEmployee(Employee emp) {
		SQLiteDatabase db = getWritableDatabase();
		obj_emp = new Employee();
		ContentValues values = new ContentValues();
		values.put(EMPLOYEE_COLUMN_NAME, emp.getName());
		values.put(EMPLOYEE_COLUMN_ADDRESS, emp.getAddress());
		values.put(EMPLOYEE_COLUMN_PHONE, emp.getPhone());
		db.insert(EMPLOYEE_TABLE_NAME, null, values);

	}

	public Cursor getAllNames() {
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery("select name from " + EMPLOYEE_TABLE_NAME,
				null);

		return cursor;
	}
	
	public Cursor getAllEmployee() {
		ArrayList array_list = new ArrayList();
		// hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from "+EMPLOYEE_TABLE_NAME, null);
		return res;

	}

	public List<String> getAllLabels() {
		List<String> list = new ArrayList<String>();

		String selectQuery = "SELECT  * FROM " + EMPLOYEE_TABLE_NAME;

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
	
	public Cursor searchEmployee(String name) {
		SQLiteDatabase db = this.getWritableDatabase();

		/*
		 * ContentValues values = new ContentValues(); values.put("name", name);
		 */

		Cursor cursor = db.rawQuery("Select * from "+EMPLOYEE_TABLE_NAME+" where "+EMPLOYEE_COLUMN_NAME+"='"
				+ name + "'", null);

		return cursor;

	}
	
	
	public void updateEmployee(Employee emp)
	{
		SQLiteDatabase db=getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put(EMPLOYEE_COLUMN_ADDRESS,emp.getAddress());
		values.put(EMPLOYEE_COLUMN_PHONE,emp.getPhone());
		db.update(EMPLOYEE_TABLE_NAME,values,EMPLOYEE_COLUMN_NAME+"=?",new String[] {String.valueOf(emp.getName())});
		
		
	}
	
	public void deleteEmployee(Employee emp)
	{
		SQLiteDatabase db=getWritableDatabase();
		db.delete(EMPLOYEE_TABLE_NAME, EMPLOYEE_COLUMN_NAME+"=?",new String[]{String.valueOf(emp.getName())});
		
	}
	public Cursor searchEmployee(Employee name) {
		SQLiteDatabase db = this.getWritableDatabase();

		Employee emp=new Employee();
		/*
		 * ContentValues values = new ContentValues(); values.put("name", name);
		 */

		Cursor cursor = db.rawQuery("Select * from employeemgmt where name='"
				+ name.getName() + "'", null);

		return cursor;

	}
}
