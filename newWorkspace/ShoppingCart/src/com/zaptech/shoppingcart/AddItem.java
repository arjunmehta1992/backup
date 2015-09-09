package com.zaptech.shoppingcart;

import java.util.ArrayList;

import com.zaptech.shoppingkart.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddItem extends Activity implements OnClickListener {
	ImageButton openaddcategory, addItemImageOpen, addItemImageCamera;
	EditText itemName, itemPurchasePrice, itemSellsPrice, itemQuantity;
	Button save, clear;
	Spinner categoryList;
	ArrayList<String> listOfCategory;
	ShoppingDb db;
	ItemModel objItem;
	ArrayAdapter<String> adpt;
	String img_Decodable_Str;
	ImageView itemImage;
	private static int RESULT_LOAD = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_item);

		init();
		addListener();
		categoryBind();

		/*
		 * categoryList.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * } });
		 */
	}

	void init() {
		addItemImageOpen = (ImageButton) findViewById(R.id.addItemImageOpen);
		

		save = (Button) findViewById(R.id.btnItemSave);
		clear = (Button) findViewById(R.id.btnItemClear);
		itemName = (EditText) findViewById(R.id.itemName);
		itemPurchasePrice = (EditText) findViewById(R.id.itemPurchasePrice);
		itemSellsPrice = (EditText) findViewById(R.id.itemSellPrice);
		itemQuantity = (EditText) findViewById(R.id.itemQuantity);
		openaddcategory = (ImageButton) findViewById(R.id.addItemCategory);
		categoryList = (Spinner) findViewById(R.id.itemCategory);
		listOfCategory = new ArrayList<String>();
		db = new ShoppingDb(AddItem.this);
		objItem = new ItemModel();
	}

	void addListener() {
		openaddcategory.setOnClickListener(this);
		save.setOnClickListener(this);
		clear.setOnClickListener(this);
		addItemImageOpen.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addItemCategory:
			final Dialog dialog = new Dialog(AddItem.this);
			dialog.setContentView(R.layout.add_category);
			dialog.setTitle("Add Category");

			Button categorySave = (Button) dialog
					.findViewById(R.id.btnCategorySave);
			Button categoryCancel = (Button) dialog
					.findViewById(R.id.btnCategoryCancel);
			final EditText categoryName = (EditText) dialog
					.findViewById(R.id.addItemCategory);
			// if button is clicked, close the custom dialog
			categoryCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			categorySave.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					db.insertCategory(categoryName.getText().toString());
					Toast.makeText(AddItem.this, "Category inserted....",
							Toast.LENGTH_SHORT).show();
					categoryBind();
				}
			});

			dialog.show();
			break;
		case R.id.btnItemSave:
			objItem.setItemName(itemName.getText().toString());
			objItem.setItemImage(img_Decodable_Str);
			objItem.setItemCategory(categoryList.getSelectedItem().toString());
			objItem.setItemPurchasePrice(Integer.parseInt(itemPurchasePrice
					.getText().toString()));
			objItem.setItemSellsPrice(Integer.parseInt(itemSellsPrice.getText()
					.toString()));
			objItem.setItemQuantity(Integer.parseInt(itemQuantity.getText()
					.toString()));

			db.insertItem(objItem);
			db.open();

			Toast.makeText(AddItem.this, "Item inserted....",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnItemClear:
			break;
			
		case R.id.addItemImageOpen:
			
			Intent galleryIntent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			startActivityForResult(galleryIntent, RESULT_LOAD);
			break;
		default:
			break;
		}
	}

	void categoryBind() {
		listOfCategory = db.getAllCategory();

		Log.i("get arraylist here", "++++" + listOfCategory);
		adpt = new ArrayAdapter<String>(AddItem.this,
				android.R.layout.simple_spinner_item, listOfCategory);
		categoryList.setAdapter(adpt);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
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

				Toast.makeText(AddItem.this, " " + img_Decodable_Str,
						Toast.LENGTH_SHORT).show();
				
				cursor.close();
				ImageView imgView = (ImageView) findViewById(R.id.itemImage);

				imgView.setImageBitmap(BitmapFactory
						.decodeFile(img_Decodable_Str));

			} else {
				Toast.makeText(this, "Hey pick your image first",
						Toast.LENGTH_LONG).show();
			}

			
			
		} catch (Exception e) {
			
		}
	}
	
}
