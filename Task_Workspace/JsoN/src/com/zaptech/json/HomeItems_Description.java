package com.zaptech.json;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaptech.json.DBmodel.DBModel;

public class HomeItems_Description extends Activity {

	TextView titleName, subDiscription, descripiton;
	DBModel obj;
	ArrayList<HomeItemModel> list;
	ArrayList<HomeItemModel> listImage;
	ImageView newsImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_items__description);

		init();
		Intent get = getIntent();
		String data = get.getStringExtra("TITLE");
		list = obj.getHomeItemsData();
		listImage = obj.getHomeItemsImageData();
		Bitmap bitmap = null;
		InputStream in = null;
		BufferedOutputStream out = null;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getHomeItem_title().equalsIgnoreCase(data)) {

				titleName.setText(list.get(i).getHomeItem_title());
				subDiscription.setText(list.get(i).getHomeItem_tabText());
				descripiton.setText(Html.fromHtml(
						list.get(i).getHomeItem_textHTML()).toString());
			}
		}

	}

	void init() {
		titleName = (TextView) findViewById(R.id.titleName);
		subDiscription = (TextView) findViewById(R.id.subDiscription);
		descripiton = (TextView) findViewById(R.id.description);
		String DB_NAME = getResources().getString(R.string.DB_NAME);
		String DB_PATH = getResources().getString(R.string.DB_PATH);
		obj = new DBModel(HomeItems_Description.this, DB_NAME, DB_PATH);
		list = new ArrayList<HomeItemModel>();
		listImage = new ArrayList<HomeItemModel>();
		newsImage = (ImageView) findViewById(R.id.newsImage);
	}

}
