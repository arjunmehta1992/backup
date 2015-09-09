package com.example.allcontrolnew;

import java.io.BufferedInputStream;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class QuickContactBadgeActivity extends Activity {
	QuickContactBadge email_badge;
	QuickContactBadge phone_badge;
	QuickContactBadge uri_badge;
	Button button;
	private static final int CONTACT_PICKER_RESULT = 0;
	private String contactID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button) findViewById(R.id.pick_contact);

		email_badge = (QuickContactBadge) findViewById(R.id.email_badge);
		email_badge.assignContactFromEmail("emailcontact@gmail.com", true);
		email_badge.setMode(ContactsContract.QuickContact.MODE_SMALL);

		phone_badge = (QuickContactBadge) findViewById(R.id.contact_badge);
		phone_badge.assignContactFromPhone("+84988123234", true);
		phone_badge.setMode(ContactsContract.QuickContact.MODE_MEDIUM);

		uri_badge = (QuickContactBadge) findViewById(R.id.pick_badge);
		uri_badge.assignContactFromPhone("01654377399", true);
		uri_badge.setMode(ContactsContract.QuickContact.MODE_LARGE);
	}

	public void onPickContact(View view) {
		Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
				Contacts.CONTENT_URI);
		startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
	}

	@SuppressLint("UseValueOf")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case CONTACT_PICKER_RESULT:
				Uri contactUri = data.getData();

				Cursor cursorID = getContentResolver().query(contactUri,
						new String[] { ContactsContract.Contacts._ID }, null,
						null, null);
				if (cursorID.moveToFirst()) {
					contactID = cursorID.getString(cursorID
							.getColumnIndex(ContactsContract.Contacts._ID));

				}

				InputStream input = ContactsContract.Contacts
						.openContactPhotoInputStream(getContentResolver(),
								ContentUris.withAppendedId(
										ContactsContract.Contacts.CONTENT_URI,
										new Long(contactID)));

				BufferedInputStream buf = new BufferedInputStream(input);
				Bitmap my_btmp = BitmapFactory.decodeStream(buf);

				if (my_btmp != null)
					uri_badge.setImageBitmap(my_btmp);
				else
					uri_badge.setImageResource(R.drawable.contact_logo);
				uri_badge.assignContactUri(contactUri);
				uri_badge.setMode(ContactsContract.QuickContact.MODE_LARGE);
			}
		}
	}
}
