package com.example.studentmappingdb;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class DeleteCollege extends ActionBarActivity {

	EditText edCollegeName;
	Button btn_delete,btn_clear,btn_home;
	AutoCompleteTextView tv1;
	ArrayList<String> list_college;
	DBHelper helper;
	CollegeData obj_clg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_college);
		helper=new DBHelper(DeleteCollege.this);
		obj_clg=new CollegeData();
		
		
		btn_delete=(Button) findViewById(R.id.btnClgDel);
		btn_clear=(Button)findViewById(R.id.btnClgClear);
		btn_home=(Button)findViewById(R.id.btnDelHome);
		tv1=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		list_college=new ArrayList<String>();
		
		bindData();
		
		btn_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String getName=tv1.getText().toString();
				obj_clg.setCollege_name(getName);
				helper.deleteCollege(obj_clg);
					
				bindData();
				tv1.setText("");
			}
		});
		
		
		
		
		
	}
	public void bindData()
	{
		list_college=helper.getCollegeName();
		ArrayAdapter<String> adpt=new ArrayAdapter<String>(DeleteCollege.this,android.R.layout.simple_list_item_1,list_college);
		tv1.setAdapter(adpt);
		tv1.setThreshold(1);
	}
	
}