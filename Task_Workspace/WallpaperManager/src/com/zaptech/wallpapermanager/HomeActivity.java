package com.zaptech.wallpapermanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class HomeActivity extends Activity {

	GridView grid;

	CustomGrid adapter;
	public static String wallpaperName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Intent intent = getIntent();
		

		wallpaperName = intent.getStringExtra("GridItem");
		
		
		adapter = new CustomGrid(HomeActivity.this, wallpaperName);
		grid = (GridView) findViewById(R.id.grid);
		grid.setAdapter(adapter);

		Toast.makeText(HomeActivity.this,
				"Your selected categorty is " + wallpaperName,
				Toast.LENGTH_SHORT).show();

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(HomeActivity.this,
						SelectedImage.class);
				intent.putExtra("id", position);
				intent.putExtra("Category", wallpaperName);
				startActivity(intent);

			}
		});
	}

}