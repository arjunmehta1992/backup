package com.zaptech.gridmultiplechoice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	 private Context mContext;
	
	 public Integer[] mThumbIds = {
	            R.drawable.five, R.drawable.six,
	            R.drawable.fore, R.drawable.index,
	            R.drawable.mac, R.drawable.tiger,
	           
	    };
	 
	 public ImageAdapter(Context c){
	        mContext = c;
	    }
	 
	
	@Override
	public int getCount() {
		 return mThumbIds.length;
		
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
	}

}
