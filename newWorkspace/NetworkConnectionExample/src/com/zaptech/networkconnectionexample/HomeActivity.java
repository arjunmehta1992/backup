package com.zaptech.networkconnectionexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {

	ProgressDialog mProgress;
	ImageView mImageview;
	Bitmap mBitmap;
	TextView mTextview;
	TextView txt_jsonData;

	String latitute;
	String longitude;
	String batteryDistance;
	String time;

	int warning;
	int active;
	String charge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mTextview = (TextView) findViewById(R.id.txtDisplay);
		txt_jsonData = (TextView) findViewById(R.id.txtDisplayJsondata);

		new GetImage().execute();
	}

	class GetImage extends AsyncTask<Void, Void, Void> {

		String str = "";

		@Override
		protected void onPreExecute() {

			mProgress = new ProgressDialog(HomeActivity.this);
			mProgress.setTitle("Image Loader");
			mProgress.setMessage("Loading...");
			mProgress.setCancelable(false);
			mProgress.show();

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			str = GET("https://dl.dropboxusercontent.com/u/7409975/findmybike.json");

			Log.i("Response", ">>>" + str);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (mProgress.isShowing()) {
				mProgress.dismiss();
			}
			/*
			 * if (mBitmap!=null) { mImageview.setImageBitmap(mBitmap); }
			 */

			mTextview.setText(str);

			String strReadJson = JsonParser(str);

			txt_jsonData.setText(strReadJson);

			super.onPostExecute(result);
		}
	}

	public String GET(String url) {
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	// convert inputstream to String
	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	public String JsonParser(String str) {
		String data = "";
		try {

			JSONObject obj = new JSONObject(str);
			time = obj.getString("datetime");

			JSONObject locationObj = new JSONObject(obj.getString("location"));
			latitute = locationObj.getString("latitude");
			longitude = locationObj.getString("longitude");

			JSONObject batteryObj = new JSONObject(obj.getString("battery"));
			charge = batteryObj.getString("charge");
			batteryDistance = batteryObj.getString("distance");
			JSONObject alarmObj = new JSONObject(obj.getString("alarm"));
			warning = alarmObj.getInt("warning");
			active = alarmObj.getInt("active");

			Log.i("Time", time);
			Log.i("latitute", latitute);
			Log.i("longitude", longitude);
			Log.i("charge", charge);
			Log.i("batteryDistance", batteryDistance);
			Log.i("warning", ">>>:" + warning);
			Log.i("active", ">>>:" + active);

			data = "Time=" + time + "Latitude=" + latitute + "Longitude="
					+ longitude + "Charge=" + charge + "Distance="
					+ batteryDistance + "Warning=" + warning + "Active="
					+ active;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return data;
	}
}
