package com.example.imageretrivegallary;

import java.io.ByteArrayOutputStream;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PathImage extends ActionBarActivity implements OnClickListener {

	Button btn_load, insertImage, showData_btn;
	private static int RESULT_LOAD = 1;
	String img_Decodable_Str;
	ImageView image;
	byte[] img = null;
	DbHelper helper;
	EditText imgName;
	TextView showPath, usingPath;
	ImageModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_image);

		init();

		btn_load.setOnClickListener(this);
		insertImage.setOnClickListener(this);
		showData_btn.setOnClickListener(this);

	}

	private void init() {

		btn_load = (Button) findViewById(R.id.select_img_btn_path);
		insertImage = (Button) findViewById(R.id.insertBtn_path);
		image = (ImageView) findViewById(R.id.setImage_path);
		imgName = (EditText) findViewById(R.id.imageName_path);
		showData_btn = (Button) findViewById(R.id.showListbtn_path);
		showPath = (TextView) findViewById(R.id.pathText_path);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.select_img_btn_path:

			Intent galleryIntent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			startActivityForResult(galleryIntent, RESULT_LOAD);
			break;

		case R.id.insertBtn_path:

			helper = new DbHelper(PathImage.this);
			/*Bitmap b = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			img = bos.toByteArray();*/

			model = new ImageModel();
			model.setName_path(imgName.getText().toString());
			model.setImage_path(img_Decodable_Str);
			
			
			
			helper.insertImagePath(model);

			Toast.makeText(PathImage.this, "Image uploaded successfully",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.showListbtn_path:

			Intent intent = new Intent(PathImage.this, ListImagePath.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		try {

			if (requestCode == RESULT_LOAD && resultCode == RESULT_OK
					&& null != data) {

				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);

				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				img_Decodable_Str = cursor.getString(columnIndex);

				Toast.makeText(PathImage.this, " " + img_Decodable_Str,
						Toast.LENGTH_SHORT).show();
				showPath.setText(img_Decodable_Str);
				cursor.close();
				ImageView imgView = (ImageView) findViewById(R.id.setImage_path);

				imgView.setImageBitmap(BitmapFactory
						.decodeFile(img_Decodable_Str));

			} else {
				Toast.makeText(this, "Hey pick your image first",
						Toast.LENGTH_LONG).show();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
