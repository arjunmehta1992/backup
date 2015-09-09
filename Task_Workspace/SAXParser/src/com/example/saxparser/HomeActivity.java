	package com.example.saxparser;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.saxparser.adapter.PostBaseAdapter;
import com.example.saxparser.model.PostValue;
import com.example.saxparser.util.XMLHelper;

public class HomeActivity extends ActionBarActivity {

	ListView lvPcsPost;
	ArrayList<PostValue> postValueArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		lvPcsPost = (ListView) findViewById(R.id.lv_listDetails);
		lvPcsPost.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (postValueArrayList != null && postValueArrayList.size() > 0) {

					Toast.makeText(HomeActivity.this,
							"" + postValueArrayList.get(position).getTitle(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		new PostAsync().execute();

	}

	class PostAsync extends AsyncTask<Void, Void, Void> {

		ProgressDialog pd;
		XMLHelper helper;

		@Override
		protected void onPreExecute() {

			pd = ProgressDialog.show(HomeActivity.this, "Loading",
					"Please wait...", true, false);
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			helper = new XMLHelper();
			helper.get();
			postValueArrayList = helper.getPostsList();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			pd.dismiss();
			PostBaseAdapter adapter = new PostBaseAdapter(HomeActivity.this,
					postValueArrayList);
			lvPcsPost.setAdapter(adapter);

		}
	}

}
