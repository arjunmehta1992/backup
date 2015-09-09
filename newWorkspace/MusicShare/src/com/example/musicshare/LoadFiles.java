package com.example.musicshare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LoadFiles extends Activity implements OnClickListener {

	protected static final int PICKFILE_RESULT_CODE = 1;
	ListView fileList;
	ArrayList<String> list;
	File file;

	List<String> myList;

	ArrayList<String> msqlist;
	File listFile[];
	ArrayList<String> absolutepath;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_files);
		context = LoadFiles.this;
		init();
		getFiles();

		fileList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 String selectedFromList =(String) (fileList.getItemAtPosition(position));
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.parse("file:///mnt/sdcard/"+selectedFromList), "*/*");
				startActivity(intent);
			}
		});
	
				
		
	}

	void init() {
		fileList = (ListView) findViewById(R.id.ListOfFiles);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		default:
			break;
		}
	}

	public void getFiles() {
		list = new ArrayList<String>();

		String root_sd = Environment.getExternalStorageDirectory().toString();
		myList = new ArrayList<String>();
		String msqPattern = ".mp3";

		file = new File(root_sd);
		File list[] = Environment.getExternalStorageDirectory().listFiles();

		for (int i = 0; i < list.length; i++) {
			myList.add(list[i].getName());
		}

		Toast.makeText(LoadFiles.this, "" + myList.size(), Toast.LENGTH_SHORT)
				.show();
		
		  ArrayAdapter<String> adpt = new ArrayAdapter<String>(LoadFiles.this,
		  android.R.layout.simple_list_item_1, myList);
		  fileList.setAdapter(adpt);
		 
	}

	

	public void walkdir(File dir) {
		String msqPattern = ".mp3";// Can include more strings for more
									// extensions and check it.

		File[] listFile = dir.listFiles();
		absolutepath = new ArrayList<String>();
		msqlist = new ArrayList<String>();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {

				if (listFile[i].isDirectory()) {
					walkdir(listFile[i]);
				} else {
					if (listFile[i].getName().endsWith(".mp3")) {
						msqlist.add(listFile[i].getName());
						absolutepath.add(listFile[i].getAbsolutePath());

					}
				}
			}
		}

	}
}
