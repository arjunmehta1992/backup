package com.zaptech.json;

import java.util.ArrayList;

import com.zaptech.json.DBmodel.DBModel;
import com.zaptech.json.NewsItem_model.Headline_Model;
import com.zaptech.json.NewsItem_model.Items_Model;
import com.zaptech.json.NewsItem_model.NewsItem_Model;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsItemDescription extends ActionBarActivity {

	TextView titleName, subDiscription, descripiton;
	DBModel obj;
	Items_Model objItem;
	ImageView newsImage;
	DBModel model_helper;
	TextView tv_Description,subDiscription_newsitem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_item_description);
		
		init();
		Intent intentData = getIntent();
		String headline = intentData.getStringExtra("TITLE");
		String id=intentData.getStringExtra("ITEM_ID");
		
		tv_Description = (TextView) findViewById(R.id.subDiscription_newsitem);
		subDiscription_newsitem=(TextView)findViewById(R.id.description_newsitem);
		
		tv_Description.setText(headline);
		
		objItem=model_helper.getNewsItemDescription(id, headline);
		
		String html=objItem.getObj_descriptionHTML().getTheString();
		subDiscription_newsitem.setText(Html.fromHtml(html));
		
		
	}

	void init() {

		model_helper = new DBModel(NewsItemDescription.this, getResources()
				.getString(R.string.DB_NAME), getResources().getString(
				R.string.DB_PATH));

	}

}
