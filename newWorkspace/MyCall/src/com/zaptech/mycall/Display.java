package com.zaptech.mycall;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends Activity {
	ListView displayList;
	Context context;
	ArrayList<ContactModel> listOfContacts;
	ContactModel obj_Contact;
	ContactHelper helper;
	customAdapter adapter;
	ImageView go;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		context = Display.this;
		displayList = (ListView) findViewById(R.id.list);
		go = (ImageView) findViewById(R.id.img_home);
		helper = new ContactHelper(context);

		listOfContacts = new ArrayList<ContactModel>();
		listOfContacts = helper.getData();
		obj_Contact = new ContactModel();
		if (listOfContacts.size() == 0) {
			Toast.makeText(context, "Records Not Available....",
					Toast.LENGTH_SHORT).show();
			Intent home = new Intent(context, Contact.class);
			startActivity(home);
			finish();
		} else {
			adapter = new customAdapter();
			displayList.setAdapter(adapter);
		}
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent home = new Intent(context, Contact.class);
				startActivity(home);
				finish();
			}
		});

	}

	class customAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listOfContacts.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listOfContacts.get(position);
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
			convertView = inflater.inflate(R.layout.display_custom, null);
			final TextView name = (TextView) convertView
					.findViewById(R.id.c_name);
			final TextView number = (TextView) convertView
					.findViewById(R.id.c_number);
			ImageView img = (ImageView) convertView.findViewById(R.id.img_call);
			ImageView delete = (ImageView) convertView
					.findViewById(R.id.img_delete);
			ImageView update = (ImageView) convertView
					.findViewById(R.id.img_update);
			name.setText(listOfContacts.get(position).getContactName());
			number.setText(listOfContacts.get(position).getContactNumber());
			update.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					final Dialog d = new Dialog(Display.this);

					d.setContentView(R.layout.update);
					d.setTitle("Update");
					final EditText e_name = (EditText) d
							.findViewById(R.id.ed_name);
					final EditText e_number = (EditText) d
							.findViewById(R.id.ed_number);
					Button btn_update = (Button) d
							.findViewById(R.id.btn_update);
					Button btn_close = (Button) d.findViewById(R.id.btn_close);
					e_name.setText(name.getText());
					e_number.setText(number.getText());
					btn_close.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							d.cancel();
						}
					});
					btn_update.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ContactModel temp = new ContactModel();
							temp.setContactName(e_name.getText().toString());
							temp.setContactNumber(e_number.getText().toString());
							helper.updateData(temp,name.getText().toString());
							Toast.makeText(context,
									"Record Updated....",
									Toast.LENGTH_SHORT).show();
							Intent reload = new Intent(context,
									Display.class);
							startActivity(reload);
							finish();
						}
					});
					d.show();

				}
			});

			img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent callIntent = new Intent(Intent.ACTION_CALL);

					callIntent.setData(Uri.parse("tel:"
							+ number.getText().toString()));
					startActivity(callIntent);

				}
			});
			delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final ContactModel temp = new ContactModel();
					temp.setContactName(name.getText().toString());
					AlertDialog.Builder alert = new AlertDialog.Builder(context);

					alert.setTitle("Close ");
					alert.setMessage("Do You Want To Delete This Record?");
					alert.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									int count = helper.deleteContact(temp);
									if (count <= 0) {
										Toast.makeText(context,
												"Record Not Found....",
												Toast.LENGTH_SHORT).show();

									} else {
										Toast.makeText(context,
												"Record Deleted....",
												Toast.LENGTH_SHORT).show();
										Intent reload = new Intent(context,
												Display.class);
										startActivity(reload);
										finish();
									}
								}
							});
					alert.setNegativeButton("No",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							});
					alert.show();

				}
			});
			return convertView;
		};

	}

}
