package com.zaptech.shoppingcart;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ShoppingDb extends SQLiteOpenHelper {
	
	//Custome table 
	final public static String CUSTOMER_TABLE_NAME = "Customer_Master";
	final public static String COLUMN_CUSTOMER_NAME = "C_name";
	final public static String COLUMN_CUSTOMER_BIRTHDATE = "c_date";
	final public static String COLUMN_CUSTOMER_CONTACT = "c_contact";
	final public static String COLUMN_CUSTOMER_USERNAME = "C_username";
	final public static String COLUMN_CUSTOMER_PASSWORD = "C_password";
	final public static String COLUMN_CUSTOMER_EMAIL = "C_email";

	//Ctegory Table
	
	final public static String CATEGORY_TABLE_NAME = "Category_Master";
	final public static String COLUMN_CATEGORY_NAME = "Category_name";
	
	//Item Table
	final public static String ITEM_TABLE_NAME = "item_master";
	final public static String COLUMN_ITEM_ID = "Item_Id";
	final public static String COLUMN_ITEM_NAME = "Item_Name";
	final public static String COLUMN_ITEM_CATEGORY = "Item_Category";
	final public static String COLUMN_ITEM_IMAGE = "Item_Image";
	final public static String COLUMN_ITEM_PURCHASE_PRICE= "Item_Purchase_Price";
	final public static String COLUMN_ITEM_SELLS_PRICE = "Item_Sells_Price";
	final public static String COLUMN_ITEM_QUANTITY = "Item_Quantity";
	
	
	public ShoppingDb(Context contex) {
		super(contex, CUSTOMER_TABLE_NAME, null, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL("create table " + ITEM_TABLE_NAME + " ( " + COLUMN_ITEM_ID + " integer primary key, "
				+ COLUMN_ITEM_NAME + " text, " + COLUMN_ITEM_CATEGORY
				+ " text, " + COLUMN_ITEM_IMAGE + " text, "
				+ COLUMN_ITEM_PURCHASE_PRICE + " integer, " + COLUMN_ITEM_SELLS_PRICE
				+ " integer, " + COLUMN_ITEM_QUANTITY + " integer )");
		
		
		
		
		//Customer Table Created
		db.execSQL("create table IF NOT EXISTS " + CUSTOMER_TABLE_NAME + " ("
				+ COLUMN_CUSTOMER_NAME + " text, " + COLUMN_CUSTOMER_BIRTHDATE
				+ " text, " + COLUMN_CUSTOMER_CONTACT + " text, "
				+ COLUMN_CUSTOMER_EMAIL + " text, " + COLUMN_CUSTOMER_USERNAME
				+ " text, " + COLUMN_CUSTOMER_PASSWORD + " text)");
		Log.i("Datebase>>>>>>>>>>>>>>>>>>>>>>>>", "Created");
		//Category Table Created
		db.execSQL("create table IF NOT EXISTS " + CATEGORY_TABLE_NAME + " ("
				+ COLUMN_CATEGORY_NAME + " text) ");
		Log.i("Datebase>>>>>>>>>>>>>>>>>>>>>>>>", "Created");
		
		//Item Table Created
		
		Log.i("Datebase>>>>>>>>>>>>>>>>>>>>>>>>", "Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists "+CUSTOMER_TABLE_NAME);
		onCreate(db);
	}
	public void insert(CustomerModel cm)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues value=new ContentValues();
		value.put(COLUMN_CUSTOMER_NAME, cm.getCu_Name());
		value.put(COLUMN_CUSTOMER_BIRTHDATE, cm.getCu_Date());
		value.put(COLUMN_CUSTOMER_CONTACT, cm.getCu_Contact());
		value.put(COLUMN_CUSTOMER_EMAIL, cm.getCu_Email());
		value.put(COLUMN_CUSTOMER_USERNAME, cm.getCu_User());
		value.put(COLUMN_CUSTOMER_PASSWORD, cm.getCu_Pass());
		db.insert(CUSTOMER_TABLE_NAME, null, value);
		db.close();
	}

	public boolean customerLoginCheck(CustomerModel cm) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from " +CUSTOMER_TABLE_NAME,null);
		boolean flag=false;
		if(cursor!=null)
		{
			if(cursor.moveToFirst())
			{
				do {
					if(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_USERNAME)).equalsIgnoreCase(cm.getCu_User()) &&cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_PASSWORD)).equalsIgnoreCase(cm.getCu_Pass()))
					{
						flag=true;
					}
				} while (cursor.moveToNext());
			}
		}
		return flag;
	}
	
	//Functions for item Category
	public void insertCategory(String name)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues value=new ContentValues();
		value.put(COLUMN_CATEGORY_NAME, name);
		db.insert(CATEGORY_TABLE_NAME, null, value);
		db.close();
	}
	public ArrayList<String> getAllCategory()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ArrayList<String> list=new ArrayList<String>();
		Cursor cursor=db.rawQuery("select * from " +CATEGORY_TABLE_NAME,null);
		
		if(cursor!=null)
		{
			if(cursor.moveToFirst())
			{
				do {
					list.add(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
				} while (cursor.moveToNext());
			}
		}
		Log.i("No Of Category>>>>>>>>>>>>>>>>>>>>>>>>", ""+list.size());
		db.close();
		return list;	
	}

	//Function For Item Master
	public void insertItem(ItemModel obj)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues value=new ContentValues();
		value.put(COLUMN_ITEM_NAME, obj.getItemName());
		value.put(COLUMN_ITEM_CATEGORY,obj.getItemCategory() );
		value.put(COLUMN_ITEM_IMAGE, obj.getItemImage());
		value.put(COLUMN_ITEM_PURCHASE_PRICE,obj.getItemPurchasePrice() );
		value.put(COLUMN_ITEM_SELLS_PRICE,obj.getItemSellsPrice() );
		value.put(COLUMN_ITEM_QUANTITY, obj.getItemQuantity());
		db.insert(ITEM_TABLE_NAME, null, value);
		db.close();
	}
	
	
	public void open()
	{
		SQLiteDatabase db=this.getWritableDatabase();
	}

}
