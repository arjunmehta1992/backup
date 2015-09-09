package com.zaptech.wallpapermanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_Category extends Activity {

	private GridView grid_category;
	private TextView txt_Category;
	private ImageView img_category;
	

	
	String[] Category = { "Animal", "Kids", "Bike", "Cars", "Sport",
			"Computer", "Insects", "Flower", "Nature", "Cartoon" };

	Context mContext;
	int[] imgcategory = { R.drawable.cat_animal, R.drawable.cat_kids,
			R.drawable.cat_bike, R.drawable.cat_car, R.drawable.cat_sports,
			R.drawable.mac, R.drawable.cat_insects, R.drawable.cat_flower,
			R.drawable.cat_nature, R.drawable.cat_cartoon };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		init();

		grid_category.setAdapter(new CustomGridCategory(mContext));

		grid_category.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(Activity_Category.this,
						HomeActivity.class);

				intent.putExtra("GridItem", CustomGridCategory.web[position]);

				startActivity(intent);

			}
		});

	}

	public void init() {
		grid_category = (GridView) findViewById(R.id.grid_category);
		mContext = Activity_Category.this;

	}

	// public class CategoryGrid extends BaseAdapter {
	//
	// @Override
	// public int getCount() {
	// // TODO Auto-generated method stub
	// return Category.length;
	// }
	//
	// @Override
	// public Object getItem(int position) {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// // TODO Auto-generated method stub
	// return position;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// // TODO Auto-generated method stub
	//
	// LayoutInflater inflater = (LayoutInflater) mContext
	// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	//
	// // if(convertView==null)
	// {
	// convertView = inflater
	// .inflate(R.layout.activity_category, null);
	// TextView textView = (TextView) convertView
	// .findViewById(R.id.txt_category);
	// ImageView imageView = (ImageView) convertView
	// .findViewById(R.id.img_category);
	// // textView.setText("");
	// imageView.setImageResource(imgcategory[position]);
	//
	// }
	//
	// return convertView;
	// }
	//
	// }

}
