package com.zaptech.json;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zaptech.json.DBmodel.DBModel;
import com.zaptech.json.NewsItem_model.NewsItem_Model;

public class NewsItemActivity extends Activity implements OnClickListener {

	ListView lv_categoryname;
	DBModel helper_obj;
	ArrayList<NewsItem_Model> list_newsItem;
	Context context;
	ArrayAdapter<String> adpt_listCategory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_item);
		inti();
		lv_categoryname.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Intent go = new Intent(NewsItemActivity.this,
						NewsItme_SubCategory.class);
				go.putExtra("TITLE", list_newsItem.get(position)
						.getId());
				
				startActivity(go);
				Toast.makeText(NewsItemActivity.this,""+list_newsItem.get(position)
						.getTabText(), Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	public void inti() {

		lv_categoryname = (ListView) findViewById(R.id.lv_newsCategory);
		helper_obj = new DBModel(NewsItemActivity.this, getResources()
				.getString(R.string.DB_NAME), getResources().getString(
				R.string.DB_PATH));
		list_newsItem = new ArrayList<NewsItem_Model>();
		list_newsItem = helper_obj.getNewsItemCategory();
		context=NewsItemActivity.this;
		lv_categoryname.setAdapter(new AdapterCategory());

	}

	@Override
	public void onClick(View v) {

	}
	
	public class AdapterCategory extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_newsItem.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list_newsItem.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.raw_newsitem_category, null);

			TextView category_name = (TextView) convertView
					.findViewById(R.id.tv_category_name);

			category_name.setText(list_newsItem.get(position).getTabText());
			
			return convertView;
		}
		
	}
	

}