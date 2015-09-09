package com.example.imageretrivegallary;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListImage extends ActionBarActivity {

	DbHelper helper;
	ImageModel model;
	ListView listImage;
	ArrayList<ImageModel> listOfImage;
	Context context;
	CustomAdapter obj;
	byte []img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_image);
		init();

	}

	public void init() {
		listImage = (ListView) findViewById(R.id.listImage);
		context = ListImage.this;
		helper = new DbHelper(ListImage.this);
		listOfImage = new ArrayList<ImageModel>();
		listOfImage = helper.getAllIMage();
		listImage=(ListView)findViewById(R.id.listImage);
		obj = new CustomAdapter(ListImage.this, listOfImage);
		listImage.setAdapter(obj);
		
	}

	class CustomAdapter extends BaseAdapter {

		ListImage listImage2;
		ArrayList<ImageModel> listOfImage2;

		public CustomAdapter(ListImage listImage,
				ArrayList<ImageModel> listOfImage) {

			this.listOfImage2 = listOfImage;
			this.listImage2 = listImage;

		}

		@Override
		public int getCount() {

			return listOfImage.size();
		}

		@Override
		public Object getItem(int position) {

			return listOfImage.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) listImage2
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list, null);

			ImageView image = (ImageView) convertView.findViewById(R.id.img);
			TextView imgName = (TextView) convertView
					.findViewById(R.id.imageName);
			
			imgName.setText(listOfImage.get(position).getName());
			
			img=listOfImage.get(position).getImage();
			Bitmap b1=BitmapFactory.decodeByteArray(img, 0, img.length);
            
			image.setImageBitmap(b1);
			

			return convertView;
		}

	}
}