package com.example.locationalarm.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.locationalarm.R;
import com.example.locationalarm.domain.BusRoute;
import com.example.locationalarm.domain.FamousPlace;
import com.example.locationalarm.domain.Hotel;
import com.example.locationalarm.domain.PetrolPump;
import com.example.locationalarm.domain.ShoppingMall;
import com.example.locationalarm.domain.Theater;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static SQLiteDatabase myDataBase;
	private static Context myContext;
	public int count = 0;

	public DatabaseHelper(Context context) {

		super(context, context.getResources().getString(R.string.DB_NAME),
				null, 1);
		this.myContext = context;
	}

	// ---Create the database---
	public void createDataBase() throws IOException {

		// ---Check whether database is already created or not---
		boolean dbExist = checkDataBase();

		if (!dbExist) {
			this.getReadableDatabase();
			try {
				// ---If not created then copy the database---
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database" + e.getMessage());
			}
		}

	}

	// --- Check whether database already created or not---
	private boolean checkDataBase() {
		try {
			String myPath = myContext.getString(R.string.DB_PATH)
					+ myContext.getString(R.string.DB_NAME);
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

	public ArrayList<BusRoute> getRoutedata() {
		final Cursor cursor;
		openDataBase();
		ArrayList<BusRoute> routelist = new ArrayList<BusRoute>();
		BusRoute route;
		cursor = myDataBase.rawQuery("select * from ROUTE where ROUTE='1'",
				null);
		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				route = new BusRoute();
				Log.e("getting ",
						"" + cursor.getString(0) + "::" + cursor.getString(1));

				route.setROUTE(cursor.getDouble(cursor.getColumnIndex("ROUTE")));
				route.setSTATION(cursor.getString(cursor
						.getColumnIndex("STATION")));
				route.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				route.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));

				routelist.add(route);
				cursor.moveToNext();
			}

		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return routelist;

	}

	public ArrayList<BusRoute> getRoutedata(int position) {
		final Cursor cursor;
		openDataBase();
		ArrayList<BusRoute> routelist = new ArrayList<BusRoute>();
		BusRoute route;
		cursor = myDataBase.rawQuery(
				"select LATITUDE,LONGITUDE from ROUTE where ROUTE='" + position
						+ "'", null);
		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				route = new BusRoute();
				Log.e("getting ",
						"" + cursor.getString(0) + "::" + cursor.getString(1));

				route.setROUTE(cursor.getDouble(cursor.getColumnIndex("ROUTE")));
				route.setSTATION(cursor.getString(cursor
						.getColumnIndex("STATION")));
				route.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				route.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));

				routelist.add(route);
				cursor.moveToNext();
			}

		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return routelist;

	}

	// --- Copy the database to the output stream---
	private void copyDataBase() throws IOException {

		InputStream myInput = myContext.getAssets().open(
				myContext.getString(R.string.DB_NAME));

		String outFileName = myContext.getString(R.string.DB_PATH)
				+ myContext.getString(R.string.DB_NAME);

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
		String myPath = myContext.getString(R.string.DB_PATH)
				+ myContext.getString(R.string.DB_NAME);
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

		Log.e("MY DATABASE", "" + myDataBase);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	public ArrayList<BusRoute> getQuestUserData() {
		final Cursor cursor;
		openDataBase();
		ArrayList<BusRoute> quest_Model = new ArrayList<BusRoute>();
		BusRoute qModel;
		cursor = myDataBase.rawQuery("select * from ROUTE", null);

		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				qModel = new BusRoute();

				qModel.setROUTE(cursor.getDouble(cursor.getColumnIndex("ROUTE")));
				qModel.setSTATION(cursor.getString(cursor
						.getColumnIndex("STATION")));
				qModel.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				qModel.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));

				quest_Model.add(qModel);
				cursor.moveToNext();
			}

		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return quest_Model;
	}

	public ArrayList<BusRoute> getStationNameData() {
		final Cursor cursor;
		openDataBase();
		ArrayList<BusRoute> quest_Model = new ArrayList<BusRoute>();
		BusRoute qModel;
		cursor = myDataBase.rawQuery(
				"select STATION from ROUTE where ROUTE='1'", null);
		Log.e("LENGTH>>>>>>>>>>>>>", "" + cursor.getCount());

		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				qModel = new BusRoute();
				Log.e("getting ",
						"" + cursor.getString(0) + "::" + cursor.getString(1));

				qModel.setSTATION(cursor.getString(cursor
						.getColumnIndex("STATION")));

				quest_Model.add(qModel);
				cursor.moveToNext();
			}

		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return quest_Model;
	}

	public ArrayList<BusRoute> getRouteLatlangData() {
		final Cursor cursor;
		openDataBase();
		ArrayList<BusRoute> routelist = new ArrayList<BusRoute>();
		BusRoute route;
		cursor = myDataBase.rawQuery(
				"select LATITUDE,LONGITUDE from ROUTE where ROUTE='1'", null);
		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				route = new BusRoute();

				route.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				route.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));

				routelist.add(route);
				cursor.moveToNext();
			}

		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return routelist;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public Cursor getRowStation(String STATION) {
		final Cursor cursor;
		openDataBase();

		cursor = myDataBase.rawQuery(
				"select LATITUDE,LONGITUDE from ROUTE where STATION='"
						+ STATION + "'", null);
		String hello = "select LATITUDE,LONGITUDE from ROUTE where STATION="
				+ STATION;
		Log.e("", "" + STATION);
		return cursor;
	}

	public ArrayList<BusRoute> getRowFromquestID(int position) {
		final Cursor cursor;
		openDataBase();

		ArrayList<BusRoute> arrayList = new ArrayList<BusRoute>();

		BusRoute route;
		cursor = myDataBase.rawQuery("select * from ROUTE where ROUTE='"
				+ position + "'", null);
		String hello = "select STATION from ROUTE where ROUTE=" + position;
		Log.e("", "" + cursor);
		Log.e("query", "" + hello);
		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {

				route = new BusRoute();
				route.setROUTE(cursor.getDouble(cursor.getColumnIndex("ROUTE")));
				route.setSTATION(cursor.getString(cursor
						.getColumnIndex("STATION")));

				route.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				route.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));

				Log.e("getting arraylist",
						"" + cursor.getString(cursor.getColumnIndex("STATION")));

				arrayList.add(route);
				cursor.moveToNext();

			}
		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return arrayList;
	}

	public ArrayList<Hotel> getPlacedetail(int position) {
		final Cursor cursor;
		openDataBase();

		ArrayList<Hotel> arrayList = new ArrayList<Hotel>();

		Hotel hotel;

		Log.e("", "" + position);
		cursor = myDataBase.rawQuery("select * from HOTEL where ID='"
				+ position + "'", null);

		Log.e("coloum count", "" + cursor.getColumnCount());
		Log.e("count", "" + cursor.getCount());

		/*
		 * String hello = "select NAME from HOTEL where POSITION='0'"; Log.e("",
		 * "" + cursor); Log.e("query", "" + hello);
		 */
		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {

				hotel = new Hotel();

				Log.e("getting arraylist",
						"" + cursor.getString(cursor.getColumnIndex("NAME")));
				hotel.setID(cursor.getString(cursor.getColumnIndex("ID")));
				hotel.setNAME(cursor.getString(cursor.getColumnIndex("NAME")));
				hotel.setADDRESS(cursor.getString(cursor
						.getColumnIndex("ADDRESS")));
				hotel.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				hotel.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));
				hotel.setPHONENUMBER(cursor.getString(cursor
						.getColumnIndex("PHONENUMBER")));

				Log.e("cursor",
						""
								+ cursor.getInt(cursor
										.getColumnIndex("PHONENUMBER")));

				arrayList.add(hotel);
				cursor.moveToNext();

			}
		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return arrayList;
	}

	public ArrayList<Theater> getTheaterdetail(int position) {
		final Cursor cursor;
		openDataBase();

		ArrayList<Theater> arrayList = new ArrayList<Theater>();

		Theater theater;

		Log.e("", "" + position);
		cursor = myDataBase.rawQuery("select * from THEATER where ID='"
				+ position + "'", null);

		Log.e("coloum count", "" + cursor.getColumnCount());
		Log.e("count", "" + cursor.getCount());

		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {

				theater = new Theater();

				Log.e("getting arraylist",
						"" + cursor.getString(cursor.getColumnIndex("NAME")));
				theater.setID(cursor.getString(cursor.getColumnIndex("ID")));
				theater.setNAME(cursor.getString(cursor.getColumnIndex("NAME")));
				theater.setADDRESS(cursor.getString(cursor
						.getColumnIndex("ADDRESS")));
				theater.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				theater.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));
				theater.setPHONENUMBER(cursor.getString(cursor
						.getColumnIndex("PHONENUMBER")));

				arrayList.add(theater);
				cursor.moveToNext();

			}
		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return arrayList;
	}

	public ArrayList<FamousPlace> getFamousPlacedetail(int position) {
		final Cursor cursor;
		openDataBase();

		ArrayList<FamousPlace> arrayList = new ArrayList<FamousPlace>();

		FamousPlace famousPlace;

		Log.e("", "" + position);
		cursor = myDataBase.rawQuery("select * from FAMOUSPLACE where ID='"
				+ position + "'", null);

		Log.e("coloum count", "" + cursor.getColumnCount());
		Log.e("count", "" + cursor.getCount());

		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {

				famousPlace = new FamousPlace();

				Log.e("getting arraylist",
						"" + cursor.getString(cursor.getColumnIndex("NAME")));
				famousPlace
						.setID(cursor.getString(cursor.getColumnIndex("ID")));
				famousPlace.setNAME(cursor.getString(cursor
						.getColumnIndex("NAME")));
				famousPlace.setADDRESS(cursor.getString(cursor
						.getColumnIndex("ADDRESS")));
				famousPlace.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				famousPlace.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));
				famousPlace.setDescription(cursor.getString(cursor
						.getColumnIndex("DESCRIPTION")));
				

				arrayList.add(famousPlace);
				cursor.moveToNext();

			}
		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return arrayList;
	}

	public ArrayList<ShoppingMall> getShoppingMalldetail(int position) {
		final Cursor cursor;
		openDataBase();

		ArrayList<ShoppingMall> arrayList = new ArrayList<ShoppingMall>();

		ShoppingMall shoppingMall;

		Log.e("", "" + position);
		cursor = myDataBase.rawQuery("select * from SHOPPINGMALL where ID='"
				+ position + "'", null);

		Log.e("coloum count", "" + cursor.getColumnCount());
		Log.e("count", "" + cursor.getCount());

		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {

				shoppingMall = new ShoppingMall();

				Log.e("getting arraylist",
						"" + cursor.getString(cursor.getColumnIndex("NAME")));
				shoppingMall
						.setID(cursor.getString(cursor.getColumnIndex("ID")));
				shoppingMall.setNAME(cursor.getString(cursor
						.getColumnIndex("NAME")));
				shoppingMall.setADDRESS(cursor.getString(cursor
						.getColumnIndex("ADDRESS")));
				shoppingMall.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				shoppingMall.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));

				arrayList.add(shoppingMall);
				cursor.moveToNext();

			}
		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return arrayList;
	}

	public ArrayList<PetrolPump> getPetrolPumpdetail(int position) {
		final Cursor cursor;
		openDataBase();

		ArrayList<PetrolPump> arrayList = new ArrayList<PetrolPump>();

		PetrolPump petrolPump;

		Log.e("", "" + position);
		cursor = myDataBase.rawQuery("select * from PETROLPUMP where ID='"
				+ position + "'", null);

		Log.e("coloum count", "" + cursor.getColumnCount());
		Log.e("count", "" + cursor.getCount());

		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {

				petrolPump = new PetrolPump();

				Log.e("getting arraylist",
						"" + cursor.getString(cursor.getColumnIndex("NAME")));
				petrolPump.setID(cursor.getString(cursor.getColumnIndex("ID")));
				petrolPump.setNAME(cursor.getString(cursor
						.getColumnIndex("NAME")));
				petrolPump.setADDRESS(cursor.getString(cursor
						.getColumnIndex("ADDRESS")));
				petrolPump.setLATITUDE(cursor.getDouble(cursor
						.getColumnIndex("LATITUDE")));
				petrolPump.setLONGITUDE(cursor.getDouble(cursor
						.getColumnIndex("LONGITUDE")));

				arrayList.add(petrolPump);
				cursor.moveToNext();

			}
		}
		myDataBase.close();
		cursor.close();
		SQLiteDatabase.releaseMemory();
		return arrayList;
	}

}