package com.zaptech.wallpapermanager;

import java.io.IOException;

import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SelectedImage extends ActionBarActivity {

	ImageView imgWallpapaerImage;

	public final static int PAGES = 10;
	public final static int LOOPS = 10;
	public final static int FIRST_PAGE = 9;
	public final static float BIG_SCALE = 1.0f;
	public final static float SMALL_SCALE = 0.8f;
	public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

	public MyPagerAdapter adapter;
	public ViewPager pager;

	// CustomGrid gridObject;
	int position;
	MyFragment objFragment;
	public static String wallpaperCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected_image);
		init();
		Intent intent = getIntent();
		position = intent.getExtras().getInt("id");
		wallpaperCategory = intent.getStringExtra("Category");

		Toast.makeText(SelectedImage.this, "Car" + wallpaperCategory,
				Toast.LENGTH_SHORT).show();

		if (wallpaperCategory.equalsIgnoreCase("Cars")) {

			imgWallpapaerImage
					.setImageResource(ArrayWallpaper.ImageCarsArray[position]);
		} else if (wallpaperCategory.equalsIgnoreCase("Animal")) {

			imgWallpapaerImage
					.setImageResource(ArrayWallpaper.ImageAnimalArray[position]);
		}

		pager = (ViewPager) findViewById(R.id.myviewpager);

		adapter = new MyPagerAdapter(this, this.getSupportFragmentManager(),
				wallpaperCategory);

		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(adapter);

		pager.setCurrentItem(position);

		pager.setOffscreenPageLimit(3);

		pager.setPageMargin(Integer.parseInt(getString(R.string.pagermargin)));

	}

	void init() {
		imgWallpapaerImage = (ImageView) findViewById(R.id.wallpaper_image);
		// gridObject = new CustomGrid(SelectedImage.this, wallpaperCategory);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selected_image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		if (id == R.id.item1) {

			try {
				objFragment = new MyFragment();

				WallpaperManager myWallpaperManager = WallpaperManager
						.getInstance(getApplicationContext());

				System.err.println("Image .............position===="
						+ pager.getCurrentItem());

				if (wallpaperCategory.equalsIgnoreCase("Cars")) {

					myWallpaperManager
							.setResource(ArrayWallpaper.ImageCarsArray[pager
									.getCurrentItem()]);
				} else if (wallpaperCategory.equalsIgnoreCase("Animal")) {

					myWallpaperManager
							.setResource(ArrayWallpaper.ImageAnimalArray[pager
									.getCurrentItem()]);
				}

				Toast.makeText(SelectedImage.this,
						"Wallpaper set" + pager.getCurrentItem(),
						Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				Toast.makeText(SelectedImage.this, "Error setting wallpaper",
						Toast.LENGTH_SHORT).show();
			}

			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}