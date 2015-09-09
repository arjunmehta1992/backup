package com.zaptech.adapter;

import java.io.File;
import java.util.ArrayList;

import com.zaptech.cardviewdemo.ActivityDisplayDetails;
import com.zaptech.cardviewdemo.ActivityHome;
import com.zaptech.cardviewdemo.R;
import com.zaptech.model.DataObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends
		RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {

	private static String LOG_TAG = "MyRecyclerViewAdapter";
	private ArrayList<DataObject> mDataset;
	private static MyClickListener myClickListener;
	private ArrayList<String> f = new ArrayList<String>();// list of file paths
	private File[] listFile;

	public static class DataObjectHolder extends RecyclerView.ViewHolder
			implements View.OnClickListener {
		private TextView label;
		private TextView dateTime;
		private ImageView mImags;

		public DataObjectHolder(View itemView) {
			super(itemView);
			label = (TextView) itemView.findViewById(R.id.textView);
			dateTime = (TextView) itemView.findViewById(R.id.textView2);
			mImags = (ImageView) itemView.findViewById(R.id.imageView);
			Log.i(LOG_TAG, "Adding Listener");
			itemView.setOnClickListener(this);

		}

		@Override
		public void onClick(View v) {
			myClickListener.onItemClick(getAdapterPosition(), v);
			Toast.makeText(v.getContext(), "Hiiii" + getAdapterPosition(),
					Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(v.getContext(),
					ActivityDisplayDetails.class);

			intent.putExtra("CardDetails", String.valueOf(getAdapterPosition()));

			v.getContext().startActivity(intent);
			((Activity) v.getContext()).overridePendingTransition(
					R.anim.push_left_in, R.anim.push_left_out);
		}
	}

	public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
		mDataset = myDataset;
	}

	public void setOnItemClickListener(MyClickListener myClickListener) {
		this.myClickListener = myClickListener;

	}

	@Override
	public int getItemCount() {

		return mDataset.size();
	}

	@Override
	public void onBindViewHolder(DataObjectHolder arg0, int arg1) {

		arg0.label.setText(mDataset.get(arg1).getmTextONe());
		arg0.dateTime.setText(mDataset.get(arg1).getmTextTwo());

		getFromSdcard();
		Log.i("Size of array list=", "--->" + f.size());
		Bitmap myBitmap = BitmapFactory.decodeFile(f.get(arg1));

		arg0.mImags.setImageBitmap(myBitmap);

	}

	@Override
	public DataObjectHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(arg0.getContext()).inflate(
				R.layout.card_view_row, arg0, false);

		DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
		return dataObjectHolder;
	}

	public void addItem(DataObject dataObj, int index) {
		mDataset.add(index, dataObj);
		notifyItemInserted(index);
	}

	public void deleteItem(int index) {
		mDataset.remove(index);
		notifyItemRemoved(index);
	}

	public interface MyClickListener {
		public void onItemClick(int position, View v);

	}

	private void getFromSdcard() {
		File file = new File(
				android.os.Environment.getExternalStorageDirectory(), "Images");

		if (file.isDirectory()) {
			listFile = file.listFiles();

			for (int i = 0; i < listFile.length; i++) {

				f.add(listFile[i].getAbsolutePath());

			}
		}
	}

}
