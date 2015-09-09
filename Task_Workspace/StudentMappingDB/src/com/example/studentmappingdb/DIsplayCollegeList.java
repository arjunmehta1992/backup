package com.example.studentmappingdb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DIsplayCollegeList extends Activity {

	ListView displayList;
	ArrayList<CollegeData> list_college;
	Context context;
	DBHelper helper;
	CollegeData obj_collegData;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_college);

		progressDialog= new ProgressDialog(DIsplayCollegeList.this);
		helper = new DBHelper(DIsplayCollegeList.this);
		displayList = (ListView) findViewById(R.id.DisplayCollegeList);
		list_college = new ArrayList<CollegeData>();	

		context = DIsplayCollegeList.this;
		obj_collegData = new CollegeData();
		list_college = helper.getCollegeList();
		new DataLoad().execute();
		
		displayList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				displayAlertCollegeDetail(list_college.get(position));
			}
		});

	}

	class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_college.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list_college.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.row_display_college, null);

			TextView collegName = (TextView) convertView
					.findViewById(R.id.College_Name);

			collegName.setText(list_college.get(position).getCollege_name());

			return convertView;
		}

	}

	public void displayAlertCollegeDetail(CollegeData collegeinfo) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("College Informatoin");
		alert.setMessage("College Id:-" + collegeinfo.getCollege_id()
				+ "\nCollege Name:-" + collegeinfo.getCollege_name()
				+ "\nCollege Address:-" + collegeinfo.getCollege_address()
				+ "\n College Contact:-" + collegeinfo.getCollege_contact()
				+ "\n College Latitude:-" + collegeinfo.getCollege_lat()
				+ "\nCollege Longitude:-" + collegeinfo.getCollege_long()
				+ "\nTotal No. Of Student:-" + helper.getTotalStudent(collegeinfo.getCollege_id()));
	alert.setPositiveButton("Ok", new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			dialog.cancel();
		}
	});
	alert.show();
	}
	
	
	
	
	class DataLoad extends AsyncTask<Void,Void,Void>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressDialog.setTitle("Data is loading......");
			progressDialog.setCancelable(false);
			progressDialog.show();
			
			super.onPreExecute();
		}	
		
		
		@Override
		protected Void doInBackground(Void... params) {
			list_college=helper.getCollegeList();
			
			
			return null;
		}
		
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(progressDialog.isShowing())
			{
				progressDialog.dismiss();
			}
			displayList.setAdapter(new CustomAdapter());
		}
		
	}
}
