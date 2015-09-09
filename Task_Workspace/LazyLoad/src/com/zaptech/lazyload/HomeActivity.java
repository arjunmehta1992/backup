package com.zaptech.lazyload;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity {

	private String[] mStrings = {
			"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image1.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image2.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image3.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image5.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image6.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image7.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image8.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image9.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image10.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image1.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image2.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image3.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image5.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image6.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image7.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image8.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image9.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image10.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image1.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image2.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image3.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image5.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image6.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image7.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image8.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image9.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image10.png"

	};
	  private SwipeRefreshLayout mSwipeRefreshLayout = null;

	ListView list;
	LazyImageLoadAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		list = (ListView) findViewById(R.id.list);

		adapter = new LazyImageLoadAdapter(HomeActivity.this, mStrings);
		
		list.setAdapter(adapter);
		mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(listener);
		
		
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				
				
				mSwipeRefreshLayout.setRefreshing(true);
				// TODO Auto-generated method stub
				adapter.imageLoader.clearCache();
				adapter.notifyDataSetChanged();
				
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});

	}

	public void onItemClick(int mPosition) {
		String tempValues = mStrings[mPosition];

		Toast.makeText(HomeActivity.this, "Image URL : " + tempValues,
				Toast.LENGTH_LONG).show();
	}

	public OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			/*adapter.imageLoader.clearCache();
			adapter.notifyDataSetChanged();*/
		}
	};
}