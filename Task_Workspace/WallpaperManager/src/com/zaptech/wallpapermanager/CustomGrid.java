package com.zaptech.wallpapermanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGrid extends BaseAdapter {

	private Context mContext;
	private String mCategory;

	String[] web;

	public int[] imageId;

	public CustomGrid(Context context, String imgCategory) {
		mContext = context;
		mCategory = imgCategory;

		if (mCategory.equalsIgnoreCase("Cars")) {
			imageId = ArrayWallpaper.ImageCarsArray;
			web = ArrayWallpaper.TextImageCars;

		} else if (mCategory.equalsIgnoreCase("Animal")) {
			imageId = ArrayWallpaper.ImageAnimalArray;
			web = ArrayWallpaper.TextImageAnimal;

		}

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

		convertView = inflater.inflate(R.layout.grid_single, null);
		TextView textView = (TextView) convertView.findViewById(R.id.grid_text);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.grid_image);
		textView.setText(web[position]);
		imageView.setImageResource(imageId[position]);

		return convertView;

	}
}