package com.example.imageretrivegallary;

import java.io.File;
import java.util.ArrayList;

import com.example.imageretrivegallary.ListImage.CustomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListImagePath extends ActionBarActivity {

	DbHelper helper;
	ImageModel model;
	ListView listImage;
	ArrayList<ImageModel> listOfImage;
	Context context;
	CustomAdapter obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_image_path);
		init();

		
	}

	public void init() {
		listImage = (ListView) findViewById(R.id.ListImagePath);
		context = ListImagePath.this;
		helper = new DbHelper(ListImagePath.this);
		listOfImage = new ArrayList<ImageModel>();
		listOfImage = helper.getAllIMagePath();
		listImage=(ListView)findViewById(R.id.ListImagePath);
		obj = new CustomAdapter(ListImagePath.this, listOfImage);
		listImage.setAdapter(obj);
	}

	public class CustomAdapter extends BaseAdapter {

		ListImagePath listImage2;
		ArrayList<ImageModel> listOfImage2;
		public CustomAdapter(ListImagePath listImage,
				ArrayList<ImageModel> listOfImage) {

			this.listOfImage2 = listOfImage;
			this.listImage2 = listImage;

		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listOfImage.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listOfImage.get(position);
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
			convertView = inflater.inflate(R.layout.list_path, null);

			ImageView image = (ImageView) convertView
					.findViewById(R.id.img_path);
			TextView imgName = (TextView) convertView
					.findViewById(R.id.imageName_path);

			imgName.setText(listOfImage.get(position).getName_path());

			File imgFile = new File(listOfImage.get(position).getImage_path());

			if (imgFile.exists()) {

				Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
						.getAbsolutePath());

				image.setImageBitmap(myBitmap);

			}

			return convertView;
		}

	}

}
