package com.example.imageretrivegallary;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "image_collection";
	public static final String TABLENAME = "image";
	public static final String IMAGE_COLUMN_NAME = "image_name";
	public static final String IMAGE_COLUMN_Source = "image_source";

	public static final String PATH_TABLENAME = "imagepath";
	public static final String PATH_COLUMN_NAME = "imagename";
	public static final String PATH_COLUMN_SOURCE = "imagesource";

	public static final String TABLE_CREATE = "create table if not exists "
			+ PATH_TABLENAME + " ( id integer primary key, " + PATH_COLUMN_NAME
			+ " text, " + PATH_COLUMN_SOURCE + " text)";
	ImageModel model;

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);

		Log.i("Created-------->>>>>", "table -------->>>>");

		db.execSQL("create table if not exists " + TABLENAME
				+ " ( id integer primary key, " + IMAGE_COLUMN_NAME + " text, "
				+ IMAGE_COLUMN_Source + " blob)");

		Log.i("Created-------->>>>>", "table -------->>>>");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	void insertImage(ImageModel model) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(IMAGE_COLUMN_NAME, model.getName());
		values.put(IMAGE_COLUMN_Source, model.getImage());
		db.insert(TABLENAME, null, values);

	}

	public ArrayList<ImageModel> getAllIMage() {
		ArrayList<ImageModel> list = new ArrayList<ImageModel>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLENAME, null);
		if (cursor.moveToFirst()) {

			do {

				ImageModel model = new ImageModel();
				model.setName(cursor.getString(cursor
						.getColumnIndex(IMAGE_COLUMN_NAME)));
				model.setImage(cursor.getBlob(cursor
						.getColumnIndex(IMAGE_COLUMN_Source)));

				list.add(model);

			} while (cursor.moveToNext());

		}

		return list;

	}

	/*
	 * void open() { SQLiteDatabase db=this.getWritableDatabase(); onCreate(db);
	 * }
	 */
	void insertImagePath(ImageModel model) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PATH_COLUMN_NAME, model.getName_path());
		values.put(PATH_COLUMN_SOURCE, model.getImage_path());
		db.insert(PATH_TABLENAME, null, values);

	}

	public ArrayList<ImageModel> getAllIMagePath() {
		ArrayList<ImageModel> list = new ArrayList<ImageModel>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + PATH_TABLENAME, null);
		if (cursor.moveToFirst()) {

			do {

				ImageModel model = new ImageModel();
				model.setName_path(cursor.getString(cursor
						.getColumnIndex(PATH_COLUMN_NAME)));
				model.setImage_path(cursor.getString(cursor
						.getColumnIndex(PATH_COLUMN_SOURCE)));

				list.add(model);

			} while (cursor.moveToNext());

		}

		return list;

	}
}
