package com.zaptech.json.DBmodel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zaptech.json.HomeItemModel;
import com.zaptech.json.MenuItem_Model;
import com.zaptech.json.NewsItem_model.DescriptionHTML_Model;
import com.zaptech.json.NewsItem_model.Description_Model;
import com.zaptech.json.NewsItem_model.Headline_Model;
import com.zaptech.json.NewsItem_model.Items_Model;
import com.zaptech.json.NewsItem_model.NewsItem_Model;

public class DBModel extends SQLiteOpenHelper {

	private static SQLiteDatabase myDataBase;
	private static Context myContext;
	public int count = 0;
	public static String DATABASE_NAME = "";
	public static String DATABASE_PATH = "";

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

	public static final String TABLE_HOMEITEMS = "homeitems";
	public static final String TABLE_HOMEITEMS_IMAGE = "homeitems_image";
	public static final String TABLE_MENUITEMS = "menu_items";
	public static final String TABLE_NEWSITEM = "newsitem";
	public static final String TABLE_NEWSIMAGE = "newsimage";
	public static final String TABLE_NEWSITEM_ITEM = "items";
	public static final String TABLE_HEADLINE = "headline";
	public static final String TABLE_DESCRIPTIONHTML = "descriptionhtml";
	public static final String TABLE_DESCRIPTION = "description";

	public static final String HOMEITEMS_ID = "id";
	public static final String HOMEITEMS_INCLUDE_IMAGE_IN_LAYOUT = "includeimageinlayout";
	public static final String HOMEITEMS_INCLUDE_TITLE_IN_LAYOUT = "includetitleinlayout";
	public static final String HOMEITEMS_INCLUDE_TEXT_IN_LAYOUT = "includetextinlayout";
	public static final String HOMEITEMS_IMAGE_POSITION = "imageposition";
	public static final String HOMEITEMS_TITLE_POSITION = "titleposition";
	public static final String HOMEITEMS_TEXT_POSITION = "textposition";
	public static final String HOMEITEMS_TITLE = "title";
	public static final String HOMEITEMS_TEXT = "text";
	public static final String HOMEITEMS_TEXTHTML = "textHTML";
	public static final String HOMEITEMS_TAB_POSITION = "tabposition";
	public static final String HOMEITEMS_TAB_TEXT = "tabtext";
	public static final String HOMEITEMS_TAB_ICON = "tabicon";
	public static final String HOMEITEMS_DATECHANGED = "datechanged";
	public static final String HOMEITEMS_ISDIRTY = "isdirty";
	public static final String HOMEITEMS_TEMP_UNIQUEUID = "tempuniqueuid";
	public static final String HOMEITEMS_TYPE = "type";
	public static final String HOMEITEMS_USE_TAB_ICON = "usetabicon";
	public static final String HOMEITEMS_SORT_POSITION = "sortposition";
	public static final String HOMEITEMS_ARCHIVED = "archived";
	public static final String HOMEITEMS_LIST_ICON = "listicon";

	public static final String HOMEITEMS_IMAGE_ID = "imageid";
	public static final String HOMEITEMS_IMAGE_WIDTH = "width";
	public static final String HOMEITEMS_IMAGE_HEIGHT = "height";
	public static final String HOMEITEMS_IMAGE_ORIGINAL_NAME = "originalname";
	public static final String HOMEITEMS_IMAGE_LOCATION_LOCAL = "locationlocal";
	public static final String HOMEITEMS_IMAGE_TYPE = "imagetype";
	public static final String HOMEITEMS_IMAGE_BASEURL = "baseurl";
	public static final String HOMEITEMS_IMAGE_MIMETYPE = "mimetype";
	public static final String HOMEITEMS_IMAGE_BASE64VERSION = "base64version";
	public static final String HOMEITEMS_IMAGE_ISDIRTY = "isdirty";
	public static final String HOMEITEMS_IMAGE_ARCHIVED = "archived";
	public static final String HOMEITEMS_IMAGE_NAME = "name";
	public static final String HOMEITEMS_IMAGE_FOREIGNid = "id";

	public static final String MENUITEMS_ID = "id";
	public static final String MENUITEMS_ITEM_NAME = "item_name";

	public static final String NEWSITEM_VIDEOS = "videos";
	public static final String NEWSITEM_SORTTYPE = "sortType";
	public static final String NEWSITEM_SHAREPOINTURL = "sharePointUrl";
	public static final String NEWSITEM_DISPLAYASGANTT = "displayAsGantt";
	public static final String NEWSITEM_ID = "id";
	public static final String NEWSITEM_TABPOSITION = "tabPosition";
	public static final String NEWSITEM_TABTEXT = "tabText";
	public static final String NEWSITEM_TABICON = "tabIcon";
	public static final String NEWSITEM_DATECHANGED = "dateChanged";
	public static final String NEWSITEM_ISDIRTY = "isDirty";
	public static final String NEWSITEM_TEMPUNIQUEUID = "tempUniqueUID";
	public static final String NEWSITEM_TYPE = "type";
	public static final String NEWSITEM_USETABICON = "useTabIcon";
	public static final String NEWSITEM_SORTPOSITION = "sortPosition";
	public static final String NEWSITEM_ARCHIVED = "archived";
	public static final String NEWSITEM_LISTICON = "listIcon";

	public static final String ITEMS_ID = "id";
	public static final String ITEMS_URL = "url";
	public static final String ITEMS_DATEPUBLISHED = "datePublished";
	public static final String ITEMS_DATECHANGED = "dateChanged";
	public static final String ITEMS_ISDIRTY = "isDirty";
	public static final String ITEMS_EVENTFLAG = "eventFlag";
	public static final String ITEMS_EVENTDATE = "eventDate";
	public static final String ITEMS_PUBLISHTOFACEBOOK = "publishToFacebook";
	public static final String ITEMS_TEMPUNIQUEUID = "tempUniqueUID";
	public static final String ITEMS_EVENTDATEFINISH = "eventDateFinish";
	public static final String ITEMS_SORTPOSITION = "sortPosition";
	public static final String ITEMS_ARCHIVED = "archived";
	public static final String ITEMS_LISTICON = "listIcon";
	public static final String ITEMS_NEWSITEM_ID = "newsitem_id";

	public static final String NEWSIMAGE_WIDTH = "width";
	public static final String NEWSIMAGE_HEIGHT = "height";
	public static final String NEWSIMAGE_ORIGINALNAME = "originalName";
	public static final String NEWSIMAGE_LOCATIONLOCAL = "locationLocal";
	public static final String NEWSIMAGE_TYPE = "type";
	public static final String NEWSIMAGE_BASEURL = "baseURL";
	public static final String NEWSIMAGE_MIMETYPE = "mimeType";
	public static final String NEWSIMAGE_BASE64VERSION = "base64Version";
	public static final String NEWSIMAGE_ISDIRTY = "isDirty";
	public static final String NEWSIMAGE_ARCHIVED = "archived";
	public static final String NEWSIMAGE_ID = "id";
	public static final String NEWSIMAGE_NAME = "name";
	
	public static final String ID="item_id";

	public static final String HEADLINES_THESTRING = "theString";

	public static final String DESCRIPTION_THESTRING = "theString";

	public static final String DESCRIPTIONHTML_THESTRING = "theString";

	Items_Model itemModel_db = new Items_Model();

	/*
	 * public DBModel(Context context) { super(context, DATABASE_NAME, null, 4);
	 * 
	 * }
	 */
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

	public void insertHomeItemsRecord(HomeItemModel modelObj) {

		openDataBase();
		ContentValues values = new ContentValues();
		values.put(HOMEITEMS_ID, modelObj.getHomeItem_id());
		values.put(HOMEITEMS_INCLUDE_IMAGE_IN_LAYOUT,
				modelObj.getHomeItem_includeImageInLayout());
		values.put(HOMEITEMS_INCLUDE_TITLE_IN_LAYOUT,
				modelObj.getHomeItem_includeTitleInLayout());
		values.put(HOMEITEMS_INCLUDE_TEXT_IN_LAYOUT,
				modelObj.getHomeItem_includeTextInLayout());
		values.put(HOMEITEMS_IMAGE_POSITION,
				modelObj.getHomeItem_imagePosition());
		values.put(HOMEITEMS_TITLE_POSITION,
				modelObj.getHomeItem_titlePosition());
		values.put(HOMEITEMS_TEXT_POSITION, modelObj.getHomeItem_textPosition());
		values.put(HOMEITEMS_TITLE, modelObj.getHomeItem_title());
		values.put(HOMEITEMS_TEXT, modelObj.getHomeItem_text());
		values.put(HOMEITEMS_TEXTHTML, modelObj.getHomeItem_textHTML());
		values.put(HOMEITEMS_TAB_POSITION, modelObj.getHomeItem_tabPosition());
		values.put(HOMEITEMS_TAB_TEXT, modelObj.getHomeItem_tabText());
		values.put(HOMEITEMS_TAB_ICON, modelObj.getHomeItem_tabIcon());
		values.put(HOMEITEMS_DATECHANGED, modelObj.getHomeItem_dateChanged());
		values.put(HOMEITEMS_ISDIRTY, modelObj.getHomeItem_isDirty());
		values.put(HOMEITEMS_TEMP_UNIQUEUID,
				modelObj.getHomeItem_tempUniqueUID());
		values.put(HOMEITEMS_TYPE, modelObj.getHomeItem_Type());
		values.put(HOMEITEMS_USE_TAB_ICON, modelObj.getHomeItem_tabIcon());
		values.put(HOMEITEMS_SORT_POSITION, modelObj.getHomeItem_sortPosition());
		values.put(HOMEITEMS_ARCHIVED, modelObj.getHomeItem_archived());
		values.put(HOMEITEMS_LIST_ICON, modelObj.getHomeItem_listIcon());
		myDataBase.insert(TABLE_HOMEITEMS, null, values);
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
	}

	public void insertHomeItems_Image_Record(HomeItemModel modelObj) {

		openDataBase();
		ContentValues values = new ContentValues();
		values.put(HOMEITEMS_IMAGE_ID, modelObj.getHomeItem_Image_id());
		values.put(HOMEITEMS_IMAGE_WIDTH, modelObj.getHomeItem_Image_width());
		values.put(HOMEITEMS_IMAGE_HEIGHT, modelObj.getHomeItem_Image_height());
		values.put(HOMEITEMS_IMAGE_ORIGINAL_NAME,
				modelObj.getHomeItem_Image_originaleName());
		values.put(HOMEITEMS_IMAGE_LOCATION_LOCAL,
				modelObj.getHomeItem_Image_locationLocal());
		values.put(HOMEITEMS_IMAGE_TYPE, modelObj.getHomeItem_Image_type());
		values.put(HOMEITEMS_IMAGE_BASEURL,
				modelObj.getHomeItem_Image_baseURL());
		values.put(HOMEITEMS_IMAGE_MIMETYPE,
				modelObj.getHomeItem_Image_MIMEType());
		values.put(HOMEITEMS_IMAGE_BASE64VERSION,
				modelObj.getHomeItem_Image_base64Version());
		values.put(HOMEITEMS_IMAGE_ISDIRTY,
				modelObj.getHomeItem_Image_isDirty());
		values.put(HOMEITEMS_IMAGE_ARCHIVED,
				modelObj.getHomeItem_Image_Archived());
		values.put(HOMEITEMS_IMAGE_NAME, modelObj.getHomeItem_Image_Name());
		values.put(HOMEITEMS_IMAGE_FOREIGNid, modelObj.getHomeItem_id());
		myDataBase.insert(TABLE_HOMEITEMS_IMAGE, null, values);
		myDataBase.close();
		SQLiteDatabase.releaseMemory();

	}

	public ArrayList<HomeItemModel> getHomeItemsData() {
		openDataBase();

		ArrayList<HomeItemModel> list = new ArrayList<HomeItemModel>();
		Cursor cursor = myDataBase.rawQuery("select * from " + TABLE_HOMEITEMS,
				null);
		if (cursor.getCount() > 0) {
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						HomeItemModel homeItemModel_obj = new HomeItemModel();

						homeItemModel_obj
								.setHomeItem_includeImageInLayout(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_INCLUDE_IMAGE_IN_LAYOUT)));
						homeItemModel_obj
								.setHomeItem_includeTitleInLayout(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_INCLUDE_TITLE_IN_LAYOUT)));
						homeItemModel_obj
								.setHomeItem_imagePosition(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_POSITION)));
						homeItemModel_obj
								.setHomeItem_titlePosition(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_TITLE_POSITION)));
						homeItemModel_obj
								.setHomeItem_textPosition(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_TEXT_POSITION)));
						homeItemModel_obj.setHomeItem_title(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_TITLE)));
						homeItemModel_obj.setHomeItem_text(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_TEXT)));
						homeItemModel_obj.setHomeItem_textHTML(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_TEXTHTML)));
						homeItemModel_obj
								.setHomeItem_id(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_ID)));
						homeItemModel_obj
								.setHomeItem_tabPosition(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_TAB_POSITION)));
						homeItemModel_obj.setHomeItem_tabText(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_TAB_TEXT)));
						homeItemModel_obj.setHomeItem_tabIcon(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_TAB_ICON)));
						homeItemModel_obj
								.setHomeItem_dateChanged(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_DATECHANGED)));
						homeItemModel_obj.setHomeItem_isDirty(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_ISDIRTY)));
						homeItemModel_obj
								.setHomeItem_tempUniqueUID(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_TEMP_UNIQUEUID)));
						homeItemModel_obj.setHomeItem_Type(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_TYPE)));
						homeItemModel_obj
								.setHomeItem_useTabIcon(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_USE_TAB_ICON)));
						homeItemModel_obj
								.setHomeItem_sortPosition(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_SORT_POSITION)));
						homeItemModel_obj.setHomeItem_archived(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_ARCHIVED)));
						homeItemModel_obj.setHomeItem_listIcon(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_LIST_ICON)));

						list.add(homeItemModel_obj);
					} while (cursor.moveToNext());
				}
			}
		}
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
		return list;

	}

	public void insertMenuItems(MenuItem_Model model_obj) {

		openDataBase();
		ContentValues values = new ContentValues();
		values.put(MENUITEMS_ID, model_obj.getId());
		values.put(MENUITEMS_ITEM_NAME, model_obj.getItem_name());
		System.err.println(">>>>>>>>>>" + model_obj.getItem_name());

		myDataBase.insert(TABLE_MENUITEMS, null, values);

		myDataBase.close();
		SQLiteDatabase.releaseMemory();

	}

	public ArrayList<MenuItem_Model> getMenuItems() {
		openDataBase();
		ArrayList<MenuItem_Model> list = new ArrayList<MenuItem_Model>();

		Cursor cursor = myDataBase.rawQuery("select * from " + TABLE_MENUITEMS,
				null);

		if (cursor.getCount() > 0) {
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {

						MenuItem_Model model = new MenuItem_Model();
						model.setId(cursor.getString(cursor
								.getColumnIndex("id")));
						model.setItem_name(cursor.getString(cursor
								.getColumnIndex("item_name")));
						list.add(model);

						Log.i(">>>>>>>>>>>>>>>>> ", "Datattttttttttt>>>>>>>>"
								+ model.getItem_name());

					} while (cursor.moveToNext());
				}
			}
		}
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
		return list;
	}

	public ArrayList<HomeItemModel> getHomeItemsImageData() {
		openDataBase();

		ArrayList<HomeItemModel> list = new ArrayList<HomeItemModel>();
		Cursor cursor = myDataBase.rawQuery("select * from "
				+ TABLE_HOMEITEMS_IMAGE, null);
		if (cursor.getCount() > 0) {
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						HomeItemModel homeItemModel_obj = new HomeItemModel();

						homeItemModel_obj
								.setHomeItem_Image_width(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_WIDTH)));
						homeItemModel_obj
								.setHomeItem_Image_height(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_HEIGHT)));
						homeItemModel_obj
								.setHomeItem_Image_originaleName(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_ORIGINAL_NAME)));
						homeItemModel_obj
								.setHomeItem_Image_locationLocal(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_LOCATION_LOCAL)));
						homeItemModel_obj.setHomeItem_Image_type(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_TYPE)));
						homeItemModel_obj
								.setHomeItem_Image_baseURL(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_BASEURL)));
						homeItemModel_obj
								.setHomeItem_Image_MIMEType(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_MIMETYPE)));
						homeItemModel_obj
								.setHomeItem_Image_base64Version(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_BASE64VERSION)));
						homeItemModel_obj
								.setHomeItem_Image_isDirty(cursor.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_ISDIRTY)));
						homeItemModel_obj.setHomeItem_Image_id(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_ID)));
						homeItemModel_obj.setHomeItem_Image_Name(cursor
								.getString(cursor
										.getColumnIndex(HOMEITEMS_IMAGE_NAME)));
						list.add(homeItemModel_obj);
					} while (cursor.moveToNext());
				}
			}
		}
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
		return list;

	}

	public void insertNewsItems(NewsItem_Model newsitem_model_obj) {
		openDataBase();

		ArrayList<Items_Model> list_item = new ArrayList<Items_Model>();
		ContentValues values = new ContentValues();

		values.put(NEWSITEM_VIDEOS, newsitem_model_obj.getVideos());
		values.put(NEWSITEM_SORTTYPE, newsitem_model_obj.getSortType());
		values.put(NEWSITEM_SHAREPOINTURL,
				newsitem_model_obj.getSharePointURL());
		values.put(NEWSITEM_DISPLAYASGANTT,
				newsitem_model_obj.getDisplayAsGantt());
		values.put(NEWSITEM_ID, newsitem_model_obj.getId());
		values.put(NEWSITEM_TABPOSITION, newsitem_model_obj.getTabPosition());
		values.put(NEWSITEM_TABTEXT, newsitem_model_obj.getTabText());
		values.put(NEWSITEM_TABICON, newsitem_model_obj.getTabIcon());
		values.put(NEWSITEM_DATECHANGED, newsitem_model_obj.getDateChanged());
		values.put(NEWSITEM_ISDIRTY, newsitem_model_obj.getIsDirty());
		values.put(NEWSITEM_TEMPUNIQUEUID,
				newsitem_model_obj.getTempUniqueUID());
		values.put(NEWSITEM_TYPE, newsitem_model_obj.getType());
		values.put(NEWSITEM_USETABICON, newsitem_model_obj.getUseTabIcon());
		values.put(NEWSITEM_SORTPOSITION, newsitem_model_obj.getSortPosition());
		values.put(NEWSITEM_ARCHIVED, newsitem_model_obj.getArchived());
		values.put(NEWSITEM_LISTICON, newsitem_model_obj.getListIcon());

		ContentValues values_items = new ContentValues();

		values_items.put(ITEMS_ID, newsitem_model_obj.getObj_item().getId());
		values_items.put(ITEMS_URL, newsitem_model_obj.getObj_item().getUrl());
		values_items.put(ITEMS_DATEPUBLISHED, newsitem_model_obj.getObj_item()
				.getDatePublished());
		values_items.put(ITEMS_DATECHANGED, newsitem_model_obj.getObj_item()
				.getDateChanged());
		values_items.put(ITEMS_ISDIRTY, newsitem_model_obj.getObj_item()
				.getIsDirty());
		values_items.put(ITEMS_EVENTFLAG, newsitem_model_obj.getObj_item()
				.getEventFlag());
		values_items.put(ITEMS_EVENTDATE, newsitem_model_obj.getObj_item()
				.getEventDate());
		values_items.put(ITEMS_PUBLISHTOFACEBOOK, newsitem_model_obj
				.getObj_item().getPublishToFacebook());
		values_items.put(ITEMS_TEMPUNIQUEUID, newsitem_model_obj.getObj_item()
				.getTempUniqueUID());
		values_items.put(ITEMS_EVENTDATEFINISH, newsitem_model_obj
				.getObj_item().getEventDateFinish());
		values_items.put(ITEMS_SORTPOSITION, newsitem_model_obj.getObj_item()
				.getSortPosition());
		values_items.put(ITEMS_ARCHIVED, newsitem_model_obj.getObj_item()
				.getArchived());
		values_items.put(ITEMS_LISTICON, newsitem_model_obj.getObj_item()
				.getListIcon());
		values_items.put(ITEMS_NEWSITEM_ID, newsitem_model_obj.getId());

		ContentValues values_newsimage=new ContentValues();
		
		values_newsimage.put(NEWSIMAGE_WIDTH,newsitem_model_obj.getObj_item().getObj_newsImage().getWidth());
		values_newsimage.put(NEWSIMAGE_HEIGHT,newsitem_model_obj.getObj_item().getObj_newsImage().getHeight());
		values_newsimage.put(NEWSIMAGE_ORIGINALNAME,newsitem_model_obj.getObj_item().getObj_newsImage().getOriginalName());
		values_newsimage.put(NEWSIMAGE_LOCATIONLOCAL,newsitem_model_obj.getObj_item().getObj_newsImage().getLocationLocal());
		values_newsimage.put(NEWSIMAGE_TYPE,newsitem_model_obj.getObj_item().getObj_newsImage().getType());
		values_newsimage.put(NEWSIMAGE_BASEURL,newsitem_model_obj.getObj_item().getObj_newsImage().getBaseURL());
		values_newsimage.put(NEWSIMAGE_MIMETYPE,newsitem_model_obj.getObj_item().getObj_newsImage().getMimeType());
		values_newsimage.put(NEWSIMAGE_BASE64VERSION,newsitem_model_obj.getObj_item().getObj_newsImage().getBase64Version());
		values_newsimage.put(NEWSIMAGE_ISDIRTY,newsitem_model_obj.getObj_item().getObj_newsImage().getIsDirty());
		values_newsimage.put(NEWSIMAGE_ARCHIVED,newsitem_model_obj.getObj_item().getObj_newsImage().getArchived());
		values_newsimage.put(NEWSIMAGE_ID,newsitem_model_obj.getObj_item().getObj_newsImage().getId());
		values_newsimage.put(NEWSIMAGE_NAME,newsitem_model_obj.getObj_item().getObj_newsImage().getName());
		values_newsimage.put(ID,newsitem_model_obj.getObj_item().getId());
		
				
		ContentValues values_description=new ContentValues();
		values_description.put(DESCRIPTION_THESTRING, newsitem_model_obj.getObj_item().getObj_description().getTheString());
		values_description.put(ID,newsitem_model_obj.getObj_item().getId());
		
		ContentValues values_descriptionhtml=new ContentValues();
		values_descriptionhtml.put(DESCRIPTIONHTML_THESTRING, newsitem_model_obj.getObj_item().getObj_descriptionHTML().getTheString());
		values_descriptionhtml.put(ID,newsitem_model_obj.getObj_item().getId());
		
		ContentValues values_headline=new ContentValues();
		values_headline.put(DESCRIPTION_THESTRING, newsitem_model_obj.getObj_item().getObj_headline().getTheString());
		values_headline.put(ID,newsitem_model_obj.getObj_item().getId());
		
		
		myDataBase.insert(TABLE_NEWSITEM, null, values);
		myDataBase.insert(TABLE_NEWSITEM_ITEM, null, values_items);
		myDataBase.insert(TABLE_NEWSIMAGE, null, values_newsimage);
		myDataBase.insert(TABLE_DESCRIPTION, null, values_description);
		myDataBase.insert(TABLE_DESCRIPTIONHTML, null, values_descriptionhtml);
		myDataBase.insert(TABLE_HEADLINE, null, values_headline);
		
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
	}

	
	public ArrayList<NewsItem_Model> getNewsItemCategory() {
		openDataBase();
		ArrayList<NewsItem_Model> list = new ArrayList<NewsItem_Model>();

		Cursor cursor = myDataBase.rawQuery("select * from " + TABLE_NEWSITEM,
				null);

		if (cursor.getCount() > 0) {
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {

						NewsItem_Model model = new NewsItem_Model();
						
						model.setVideos(cursor.getString(cursor.getColumnIndex(NEWSITEM_VIDEOS)));
						model.setSortType(cursor.getString(cursor.getColumnIndex(NEWSITEM_SORTTYPE)));
						model.setSharePointURL(cursor.getString(cursor.getColumnIndex(NEWSITEM_SHAREPOINTURL)));
						model.setDisplayAsGantt(cursor.getString(cursor.getColumnIndex(NEWSITEM_DISPLAYASGANTT)));
						model.setId(cursor.getString(cursor.getColumnIndex(NEWSITEM_ID)));
						model.setTabPosition(cursor.getString(cursor.getColumnIndex(NEWSITEM_TABPOSITION)));
						model.setTabText(cursor.getString(cursor.getColumnIndex(NEWSITEM_TABTEXT)));
						model.setTabIcon(cursor.getString(cursor.getColumnIndex(NEWSITEM_TABICON)));
						model.setDateChanged(cursor.getString(cursor.getColumnIndex(NEWSITEM_DATECHANGED)));
						model.setIsDirty(cursor.getString(cursor.getColumnIndex(NEWSITEM_ISDIRTY)));
						model.setTempUniqueUID(cursor.getString(cursor.getColumnIndex(NEWSITEM_TEMPUNIQUEUID)));
						model.setType(cursor.getString(cursor.getColumnIndex(NEWSITEM_TYPE)));
						model.setUseTabIcon(cursor.getString(cursor.getColumnIndex(NEWSITEM_USETABICON)));
						model.setSortPosition(cursor.getString(cursor.getColumnIndex(NEWSITEM_SORTPOSITION)));
						model.setArchived(cursor.getString(cursor.getColumnIndex(NEWSITEM_ARCHIVED)));
						model.setListIcon(cursor.getString(cursor.getColumnIndex(NEWSITEM_LISTICON)));
						
						
						
						
						
						
						list.add(model);

						
					} while (cursor.moveToNext());
				}
			}
		}
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
		return list;
	}
	
	public ArrayList<Items_Model> getNewsItem_Item(String id) {
		openDataBase();
		ArrayList<Items_Model> list = new ArrayList<Items_Model>();

		Cursor cursor = myDataBase.rawQuery("select * from " + TABLE_NEWSITEM_ITEM + " where "+ITEMS_NEWSITEM_ID+ " = '"+ id +"'",
				null);

		if (cursor.getCount() > 0) {
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {

						Items_Model model = new Items_Model();
						model.setId(cursor.getString(cursor.getColumnIndex(ITEMS_ID)));
						model.setUrl(cursor.getString(cursor.getColumnIndex(ITEMS_URL)));
						model.setDatePublished(cursor.getString(cursor.getColumnIndex(ITEMS_DATEPUBLISHED)));
						model.setDateChanged(cursor.getString(cursor.getColumnIndex(ITEMS_DATECHANGED)));
						model.setIsDirty(cursor.getString(cursor.getColumnIndex(ITEMS_ISDIRTY)));
						model.setEventFlag(cursor.getString(cursor.getColumnIndex(ITEMS_EVENTFLAG)));
						model.setEventDate(cursor.getString(cursor.getColumnIndex(ITEMS_EVENTDATE)));
						model.setPublishToFacebook(cursor.getString(cursor.getColumnIndex(ITEMS_PUBLISHTOFACEBOOK)));
						model.setTempUniqueUID(cursor.getString(cursor.getColumnIndex(ITEMS_TEMPUNIQUEUID)));
						model.setEventDateFinish(cursor.getString(cursor.getColumnIndex(ITEMS_EVENTDATEFINISH)));
						model.setSortPosition(cursor.getString(cursor.getColumnIndex(ITEMS_SORTPOSITION)));
						model.setArchived(cursor.getString(cursor.getColumnIndex(ITEMS_ARCHIVED)));
						model.setListIcon(cursor.getString(cursor.getColumnIndex(ITEMS_LISTICON)));
					
						

						Cursor cursor1=myDataBase.rawQuery("select * from "+ TABLE_HEADLINE +" where "+ ID +" = " +cursor.getString(cursor.getColumnIndex(ITEMS_ID)) , null);
						if(cursor1!=null)
						{
							if(cursor1.moveToFirst())
							{
								do {
									Headline_Model model_headline=new Headline_Model();
									model_headline.setTheString(cursor1.getString(cursor1.getColumnIndex(HEADLINES_THESTRING)));
								
									/*Cursor cursor2=myDataBase.rawQuery("select * from "+ TABLE_DESCRIPTION + " where " + ID + " = " + cursor1.getString(cursor1.getColumnIndex(ID)), null);
									
									if(cursor2!=null)
									{
										if(cursor2.moveToFirst())
										{
											do {
												Description_Model model_description=new Description_Model();
												model_description.setTheString(cursor2.getString(cursor2.getColumnIndex(DESCRIPTION_THESTRING)));
												model.setObj_description(model_description);
											} while (cursor2.moveToNext());
											
											
										}
									}*/
									
									model.setObj_headline(model_headline);
								} while (cursor1.moveToNext());
							}
						}
						
						list.add(model);
					} while (cursor.moveToNext());
				}
			}
		}
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
		return list;
	}

	
	public Items_Model getNewsItemDescription(String item_id,String Headline) {
		openDataBase();
		Items_Model model_item=new Items_Model();
		Cursor cursor=myDataBase.rawQuery("select "+ DESCRIPTIONHTML_THESTRING + " from " + TABLE_DESCRIPTIONHTML + " where "+ ID +" = '"+ item_id +"'" , null);
			
		
		
		if(cursor!=null)
		{
			
			if (cursor.moveToFirst()) {
				do {
					
					
				DescriptionHTML_Model modelDesc=new DescriptionHTML_Model();
					String descriptionHTML=cursor.getString(cursor.getColumnIndex(DESCRIPTIONHTML_THESTRING));
					System.err.println("DESCRIPTION="+descriptionHTML);
					modelDesc.setTheString(descriptionHTML);
					model_item.setObj_descriptionHTML(modelDesc);
				} while (cursor.moveToNext());	
			}
			
		}
		myDataBase.close();
		SQLiteDatabase.releaseMemory();
		return model_item;
		
	}
	
	
	
}