package com.example.rotateimage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity {

	ImageView image;
	Button btn_rotate;
	int count = 1, countLeft = 1;
	Button btn_SaveToSdcard;
	Bitmap bMapRotate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btn_rotate = (Button) findViewById(R.id.btn_rotate);
		image = (ImageView) findViewById(R.id.img);

		btn_SaveToSdcard = (Button) findViewById(R.id.saveTOSD_card);
		btn_SaveToSdcard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean response = storeImage(bMapRotate, "img2.jpg");

				if (response == true) {
					Toast.makeText(HomeActivity.this, "Saved ",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(HomeActivity.this, "not Saved ",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btn_rotate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				RotateImage();

			}
		});

	}

	public void RotateImage() {

		Bitmap bMap;
		Matrix matrix;
		float[] val = { (float) 45.0, (float) 90.0, (float) 135.0,
				(float) 180.0 };

		// Decode Image using Bitmap factory.
		bMap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);

		// Create object of new Matrix.
		matrix = new Matrix();

		// set image rotation value to 45 degrees in matrix.

		// matrix.postRotate( 90f, image.getDrawable().getBounds().width()/2,
		// image.getDrawable().getBounds().height()/2);

		switch (count) {
		case 1:
			matrix.postRotate(90);
			count++;
			break;
		case 2:
			matrix.postRotate(180);
			count++;
			break;
		case 3:
			matrix.postRotate(270);
			count++;
			break;
		case 4:
			matrix.postRotate(360);
			count = 1;
			break;

		default:
			break;
		}

		bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(),
				bMap.getHeight(), matrix, true);

		image.setImageBitmap(bMapRotate);
	}

	private boolean storeImage(Bitmap imageData, String filename) {

		String path = Environment.getExternalStorageDirectory().getPath()
				+ "/myAppDir/myImages/" + filename;
		File f = new File(path);
		if (f.exists()) {

		} else {
			String iconsStoragePath = Environment.getExternalStorageDirectory()
					+ "/myAppDir/myImages/";
			File sdIconStorageDir = new File(iconsStoragePath);

			sdIconStorageDir.mkdirs();

			try {
				String filePath = sdIconStorageDir.toString() + filename;
				FileOutputStream fileOutputStream = new FileOutputStream(
						filePath);

				BufferedOutputStream bos = new BufferedOutputStream(
						fileOutputStream);

				imageData.compress(CompressFormat.PNG, 100, bos);

				bos.flush();
				bos.close();

			} catch (FileNotFoundException e) {
				Log.w("TAG", "Error saving image file: " + e.getMessage());
				return false;
			} catch (IOException e) {
				Log.w("TAG", "Error saving image file: " + e.getMessage());
				return false;
			}

		}

		return true;
	}

}
