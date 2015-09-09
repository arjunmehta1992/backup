package com.example.studentmappingdb;

import java.util.ArrayList;

import com.example.studentmappingdb.DIsplayCollegeList.CustomAdapter;
import com.example.studentmappingdb.DIsplayCollegeList.DataLoad;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayStudent extends Activity implements android.view.View.OnClickListener{

	ListView displayList;
	ArrayList<StudentData> list_student;
	Context context;
	DBHelper helper;
	StudentData obj_std;
	ProgressDialog progressDialog;
	Button home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_student);

		
		progressDialog= new ProgressDialog(DisplayStudent.this);
		helper = new DBHelper(DisplayStudent.this);
		displayList = (ListView) findViewById(R.id.DisplayStudentList);
		list_student = new ArrayList<StudentData>();	
		home=(Button) findViewById(R.id.btnHome);

		context = DisplayStudent.this;
		obj_std = new StudentData();
		list_student = helper.getStudentList();
		new DataLoad().execute();
		
		displayList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				displayAlertCollegeDetail(list_student.get(position));
			}
		});
		home.setOnClickListener(this);

	}

		
	class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_student.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list_student.get(position);
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

			convertView = inflater.inflate(R.layout.row_display_student, null);

			TextView collegName = (TextView) convertView
					.findViewById(R.id.Student_Name);

			collegName.setText(list_student.get(position).getStd_name());

			return convertView;
		}

	}
	
	
	
	public void displayAlertCollegeDetail(StudentData collegeinfo) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("Student Informatoin");
		alert.setMessage("Student Id:-" + collegeinfo.getStd_id()
				+ "\nStudent Name:-" + collegeinfo.getStd_name()
				+ "\nStudent Address:-" + collegeinfo.getStd_address()
				+ "\n Student Contact:-" + collegeinfo.getStd_contact()
				+ "\nStudent Email:-" + collegeinfo.getStd_email()
				+ "\nStudent Gender:-" + collegeinfo.getStd_gender()
				+ "\nCollege Name:-" + helper.getCollegeName(collegeinfo.getCollege_id()));
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
			list_student=helper.getStudentList();
			
			
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnHome:
			Intent intent=new Intent(DisplayStudent.this,HomeActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
