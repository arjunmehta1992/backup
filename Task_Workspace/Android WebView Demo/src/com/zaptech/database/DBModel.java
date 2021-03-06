package com.zaptech.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zaptech.model.Bookmark;

public class DBModel extends SQLiteOpenHelper {

	private static SQLiteDatabase myDataBase;
	private static Context myContext;
	public int count = 0;
	public static String DATABASE_NAME = "";
	public static String DATABASE_PATH = "";

	public static final String BOOKMARK_SITENAME = "sitename";
	public static final String BOOKMARK_SITEURL = "siteurl";
	public static final String TABLE_BOOKMARK = "bookmark";

	public DBModel(Context context, String name, String path) {

		super(context, name, null, 1);
		DATABASE_NAME = name;
		DATABASE_PATH = path;
		DBModel.myContext = context;
		try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static final String DATABASE_NAME = "newsdb";

	public void createDataBase() throws IOException {

		// ---Check whether database is already created or not---
		boolean dbExist = checkDataBase();

		if (!dbExist) {
			this.getReadableDatabase();
			try {
				// ---If not created then copy the database---
				copyDataBase();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean checkDataBase() {
		try {
			String myPath = DATABASE_PATH + DATABASE_NAME;
			File f = new File(myPath);
			if (f.exists())
				return true;
			else
				return false;
		} catch (SQLiteException e) {
			e.printStackTrace();
			return false;
		}
	}

	// --- Copy the database to the output stream---
	private void copyDataBase() throws IOException {

		InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

		String outFileName = DATABASE_PATH + DATABASE_NAME;

		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public static void openDataBase() throws SQLException {

		// --- Open the database---
		String myPath = DATABASE_PATH + DATABASE_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void insertBookmarklist(Bookmark objbookmark) {
		openDataBase();

		ContentValues values = new ContentValues();

		values.put(BOOKMARK_SITENAME, objbookmark.getSitename());
		values.put(BOOKMARK_SITEURL, objbookmark.getSiteurl());
		myDataBase.insert(TABLE_BOOKMARK, null, values);
		myDataBase.close();
		SQLiteDatabase.releaseMemory();

	}

	public ArrayList<Bookmark> getHomeItemsData() {
		openDataBase();
		ArrayList<Bookmark> list = new ArrayList<Bookmark>();
		Cursor cursor = myDataBase.rawQuery("select * from " + TABLE_BOOKMARK,
				null);
		if (cursor.getCount() > 0) {
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						Bookmark objBookmark = new Bookmark();

						objBookmark.setSitename(cursor.getString(cursor
								.getColumnIndex(BOOKMARK_SITENAME)));
						objBookmark.setSiteurl(cursor.getString(cursor
								.getColumnIndex(BOOKMARK_SITEURL)));

						list.add(objBookmark);

					} while (cursor.moveToNext());
				}
			}
		}
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
		return list;
	}

	public void deleteAllBookmarks() {
		openDataBase();
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_BOOKMARK, null, null);

		myDataBase.close();
		SQLiteDatabase.releaseMemory();
	}

}
