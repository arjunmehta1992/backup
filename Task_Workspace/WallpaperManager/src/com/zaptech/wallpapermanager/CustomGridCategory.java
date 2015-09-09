package com.zaptech.wallpapermanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridCategory extends BaseAdapter {

	private Context mContext;

	public static String[] web = { "Animal", "Kids", "Bike", "Cars", "Sport",
			"Computer", "Insects", "Flower", "Nature", "Cartoon" };
	public static int[] imageId = { R.drawable.cat_animal, R.drawable.cat_kids,
			R.drawable.cat_bike, R.drawable.cat_car, R.drawable.sports,
			R.drawable.mac, R.drawable.cat_insects, R.drawable.cat_flowerr,
			R.drawable.cat_nature, R.drawable.cat_cartoon };

	public CustomGridCategory(Context context) {

		mContext = context;

	}

	@Override
	public int getCount() {

		return web.length;
	}

	@Override
	public Object getItem(int position) {

		return 0;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		convertView = inflater.inflate(R.layout.raw_category, null);
		TextView textView = (TextView) convertView
				.findViewById(R.id.txt_category);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.img_category);
		textView.setText(web[position]);
		imageView.setImageResource(imageId[position]);

		return convertView;

	}

}
