package com.zaptech.mycall;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Contact extends Activity implements OnClickListener {
	Button call, save, list;
	EditText no, name;
	ContactHelper ch;
	ContactModel cm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		call = (Button) findViewById(R.id.buttonCall);
		save = (Button) findViewById(R.id.buttonSave);
		list = (Button) findViewById(R.id.buttonList);
		no = (EditText) findViewById(R.id.phonenumber);
		name = (EditText) findViewById(R.id.ContactName);
	
		cm=new ContactModel();
		ch=new ContactHelper(Contact.this);
		save.setOnClickListener(this);
		call.setOnClickListener(this);
		list.setOnClickListener(this);
			
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonSave:
			cm.setContactName(name.getText().toString());
			cm.setContactNumber(no.getText().toString());
			ch.insertData(cm);
			Toast.makeText(Contact.this, "Record Saved...", Toast.LENGTH_SHORT).show();
			name.setText("");
			no.setText("");
			break;
		case R.id.buttonCall:
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:" + no.getText().toString()));
			startActivity(callIntent);
			finish();
			break;
		case R.id.buttonList:
			Intent disp=new Intent(Contact.this, Display.class);
			startActivity(disp);
			finish();
			break;
		default:
			break;
		}
	}
	
	
}
