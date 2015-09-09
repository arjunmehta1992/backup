package com.example.relative_layout_demo;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_FifthScreen extends Activity {

	Integer[] imageIDs = { R.drawable.five, R.drawable.two, R.drawable.six,

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth_screen);
		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(getBaseContext(),
						"pic" + (position + 1) + " selected",
						Toast.LENGTH_SHORT).show();
				// display the images selected
				ImageView imageView = (ImageView) findViewById(R.id.image1);
				imageView.setImageResource(imageIDs[position]);
			}
		});

	}

	public class ImageAdapter extends BaseAdapter {
		private Context context;
		private int itemBackground;

		public ImageAdapter(Context c) {
			context = c;
			// sets a grey background; wraps around the images

		}

		// returns the number of images
		public int getCount() {
			return imageIDs.length;
		}

		// returns the ID of an item
		public Object getItem(int position) {
			return position;
		}

		// returns the ID of an item
		public long getItemId(int position) {
			return position;
		}

		// returns an ImageView view
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(imageIDs[position]);
			imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
			imageView.setBackgroundResource(itemBackground);
			return imageView;
		}
	}
}
