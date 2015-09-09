package com.example.integrationdemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private static String APP_ID = "1467602490220879"; // Replace your App ID
	private ImageButton btn_LoginWithFacebook;
	private Button btn_getProfileDetails;
	private Button btn_postToWall;
	private Button btn_logoutFromFacebook;
	private Button btn_loadProfilePic;
	private ImageView img_profilePic;
	public String mTokenInfo;
	private Facebook facebook;
	private AsyncFacebookRunner mAsyncRunner;
	private String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	public String access_token;
	private LinearLayout lin_details, lin_login;
	String email;
	String id;
	Bitmap bitmap_pic;
	ProgressDialog mProgress;
	SharedPreferences pref;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		clickListener();

		facebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(facebook);

		mTokenInfo = facebook.getAccessToken();

		Toast.makeText(MainActivity.this, "" + getUserDetails(),
				Toast.LENGTH_SHORT).show();

		pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		pref.edit();

	}

	public void init() {
		btn_LoginWithFacebook = (ImageButton) findViewById(R.id.btn_LoginWithFacebook);
		btn_getProfileDetails = (Button) findViewById(R.id.btn_GetProfileDetail);
		btn_postToWall = (Button) findViewById(R.id.btn_PostToWall);
		btn_logoutFromFacebook = (Button) findViewById(R.id.btn_Logout);
		img_profilePic = (ImageView) findViewById(R.id.img_profilePic);
		btn_loadProfilePic = (Button) findViewById(R.id.btn_loadProfilePic);
		lin_details = (LinearLayout) findViewById(R.id.lin_Details);
		lin_login = (LinearLayout) findViewById(R.id.lin_LoginButton);

	}

	public void clickListener() {
		btn_LoginWithFacebook.setOnClickListener(this);
		btn_getProfileDetails.setOnClickListener(this);
		btn_postToWall.setOnClickListener(this);
		btn_logoutFromFacebook.setOnClickListener(this);
		btn_loadProfilePic.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_LoginWithFacebook:

			loginToFacebook();

			getUserDetails();
			break;

		case R.id.btn_PostToWall:
			postToWall();
			break;

		case R.id.btn_GetProfileDetail:
			getProfileDetails();
			break;

		case R.id.btn_Logout:
			logoutFromFacebook();
			break;

		case R.id.btn_loadProfilePic:

			new picture_get().execute();

			break;

		default:
			break;
		}

	}

	Bitmap getFacebookProfilePicture(String userID) {
		Bitmap bitmap = null;
		try {
			URL imageURL = new URL("https://graph.facebook.com/" + userID
					+ "/picture");

			bitmap = BitmapFactory.decodeStream(imageURL.openConnection()
					.getInputStream());
		} catch (IOException e) {

			e.printStackTrace();
		}

		return bitmap;
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {

		super.onActivityResult(arg0, arg1, arg2);
		facebook.authorizeCallback(arg0, arg1, arg2);
	}

	private void logoutFromFacebook() {

		mAsyncRunner.logout(this, new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {

				if (Boolean.parseBoolean(response) == true) {

					runOnUiThread(new Runnable() {
						public void run() {

							Toast.makeText(MainActivity.this, "Logged out",
									Toast.LENGTH_SHORT).show();
						}
					});

				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});

		Editor e = mPrefs.edit();
		e.remove("access_token");
		e.remove("access_expires");

		e.commit();
		clearCookies();
		lin_login.setVisibility(View.VISIBLE);
		lin_details.setVisibility(View.GONE);
	}

	public void loginToFacebook() {
		mPrefs = getPreferences(MODE_PRIVATE);
		access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);

		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			facebook.authorize(MainActivity.this, new String[] { "email",
					"public_profile" }, new DialogListener() {

				public void onFacebookError(FacebookError e) {

				}

				@Override
				public void onError(DialogError e) {

				}

				@Override
				public void onComplete(Bundle values) {
					SharedPreferences.Editor editor = mPrefs.edit();
					editor.putString("access_token", facebook.getAccessToken());
					editor.putLong("access_expires",
							facebook.getAccessExpires());
					editor.commit();

					/*
					 * if (getUserDetails() != null) {
					 * lin_login.setVisibility(View.GONE);
					 * lin_details.setVisibility(View.VISIBLE); }
					 */
					Toast.makeText(MainActivity.this, "Completed",
							Toast.LENGTH_SHORT).show();

				}

				@Override
				public void onCancel() {

				}
			});
		}

		lin_login.setVisibility(View.GONE);
		lin_details.setVisibility(View.VISIBLE);

	}

	public void getProfileDetails() {

		mAsyncRunner.request("me", new RequestListener() {

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {

			}

			@Override
			public void onIOException(IOException e, Object state) {

			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {

			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {

			}

			@Override
			public void onComplete(String response, Object state) {

				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);

					// getting name of the user
					final String name = profile.getString("name");

					// getting email of the user
					email = profile.getString("email");

					id = profile.getString("id");

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(
									getApplicationContext(),
									"Name: " + name + "\nEmail: " + email
											+ "\n User id:" + id,
									Toast.LENGTH_LONG).show();
						}

					});

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void postToWall() {
		facebook.dialog(MainActivity.this, "feed", new DialogListener() {

			@Override
			public void onFacebookError(FacebookError e) {

			}

			@Override
			public void onError(DialogError e) {

			}

			@Override
			public void onComplete(Bundle values) {

			}

			@Override
			public void onCancel() {

			}
		});
	}

	class picture_get extends AsyncTask<String, Bitmap, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mProgress = new ProgressDialog(MainActivity.this);
			mProgress.setTitle("Loadling");
			mProgress.setMessage("Please wait...");
			mProgress.setCancelable(false);
			mProgress.show();

		}

		@Override
		protected Bitmap doInBackground(String... params) {

			URL img_value = null;

			try {
				img_value = new URL("https://graph.facebook.com/" + id
						+ "/picture?type=large");
				bitmap_pic = BitmapFactory.decodeStream(img_value
						.openConnection().getInputStream());

				Log.i("bitmap_pic_get", "ok");
			} catch (IOException e) {

				e.printStackTrace();
			}

			// bitmap_pic = Bitmap.createScaledBitmap(bitmap_pic, 50, 50,
			// false);
			return bitmap_pic;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap_pic) {
			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			if (bitmap_pic != null) {
				img_profilePic.setImageBitmap(bitmap_pic);
			}

			Toast.makeText(MainActivity.this, " Get Data:  " + id,
					Toast.LENGTH_SHORT).show();
			Toast.makeText(MainActivity.this, "Bitmap" + bitmap_pic,
					Toast.LENGTH_SHORT).show();

		}
	}

	public String getUserDetails() {

		mAsyncRunner.request("me", new RequestListener() {

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {

			}

			@Override
			public void onIOException(IOException e, Object state) {

			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {

			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {

			}

			@Override
			public void onComplete(String response, Object state) {

				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);

					// getting name of the user
					final String name = profile.getString("name");

					// getting email of the user
					email = profile.getString("email");

					id = profile.getString("id");

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// Toast.makeText(
							// getApplicationContext(),
							// "Name: " + name + "\nEmail: " + email
							// + "\n User id:" + id,
							// Toast.LENGTH_LONG).show();
						}

					});

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		return id;

	}

	public void clearCookies() {
		CookieSyncManager.createInstance(this);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		// cookieManager.setAcceptCookie(false);
	}
}