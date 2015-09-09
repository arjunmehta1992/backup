package com.example.testapp;

import java.io.InputStream;
import java.sql.Date;
import java.util.Timer;

import com.squareup.picasso.Picasso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Button btn_startMusic;

	String URL = "http://www.hdwallpapersllc.com/wp-content/uploads/2015/07/3D-Android-Wallpaper-HD-3.jpg";
	ImageView image;
	Button button;
	ProgressDialog mProgressDialog;
	ImageView imageView;
	private static final long SPLASH_DISPLAY_LENGTH = 5000;
	Timer timer;

	private ProgressDialog mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageFromPicasso);
		btn_startMusic = (Button) findViewById(R.id.btn_start);
		// image = (ImageView) findViewById(R.id.img);

		ListView listView1 = (ListView) findViewById(R.id.listview);

		final String[] items = { "1", "2", "3", "4", "5" };
		timer = new Timer();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);

		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				Toast.makeText(MainActivity.this,
						"selected===" + position + "Length==" + items.length,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, StockDetail.class);
				intent.putExtra("position", items.length);
				startActivity(intent);

			}
		});

		btn_startMusic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 0);

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {

						Toast.makeText(MainActivity.this, "captured===",
								Toast.LENGTH_SHORT).show();

					}
				}, SPLASH_DISPLAY_LENGTH);

			}
		});

		// Picasso.with(MainActivity.this)
		// .load("http://cdn.arstechnica.net/wp-content/uploads/2013/07/Screenshot_2013-07-31-12-00-50.png")
		// .into(imageView);

	}

	public class LoadImage extends AsyncTask<Void, Void, Bitmap> {

		@Override
		protected void onPreExecute() {

			mProgressDialog = new ProgressDialog(MainActivity.this);

			mProgressDialog.setTitle("Image is downloading");

			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);

			mProgressDialog.show();

			super.onPreExecute();
		}

		@Override
		protected Bitmap doInBackground(Void... params) {

			String imageURL = URL;

			Bitmap bitmap = null;
			try {
				// Download Image from URL
				InputStream input = new java.net.URL(imageURL).openStream();
				// Decode Bitmap
				bitmap = BitmapFactory.decodeStream(input);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return bitmap;
		}

		protected void onPostExecute(Bitmap result) {

			image.setImageBitmap(result);
			// Close progressdialog
			mProgressDialog.dismiss();

		}

	}

}