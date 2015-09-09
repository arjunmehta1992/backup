package com.zaptech.jsonconnectionparsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class HomeActivity extends Activity {

	ProgressDialog mProgress;
	TextView tv_getData;
	String str = "";

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
		tv_getData = (TextView) findViewById(R.id.txt_Data);

		new GetJsonData().execute();

	}

	class GetJsonData extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			mProgress = new ProgressDialog(HomeActivity.this);
			mProgress.setTitle("Data loading");
			mProgress.setMessage("loading...");
			mProgress.setCancelable(false);
			mProgress.show();

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			str = GET("http://80.93.28.24/json/autoexpress.json");

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (mProgress.isShowing()) {

				mProgress.dismiss();
			}

			//String d = JsonParser(str);
			tv_getData.setText(str);

			super.onPostExecute(result);
		}

	}

	public String JsonParser(String str) {

		String data = "";
		try {

			JSONObject obj = new JSONObject(str);
			time = obj.getString("datetime");

			JSONObject locaSubObj = new JSONObject(obj.getString("location"));
			latitute = locaSubObj.getString("latitude");
			longitude = locaSubObj.getString("longitude");

			JSONObject batterySubObj = new JSONObject(obj.getString("battery"));
			charge = batterySubObj.getString("charge");
			batteryDistance = batterySubObj.getString("distance");

			JSONObject alarm = new JSONObject(obj.getString("alarm"));
			warning = alarm.getInt("warning");
			active = alarm.getInt("active");

			/*
			 * JSONObject obj = new JSONObject(str); time =
			 * obj.getString("datetime");
			 * 
			 * JSONObject locationObj = new
			 * JSONObject(obj.getString("location")); latitute =
			 * locationObj.getString("latitude"); longitude =
			 * locationObj.getString("longitude");
			 * 
			 * JSONObject batteryObj = new JSONObject(obj.getString("battery"));
			 * charge = batteryObj.getString("charge"); batteryDistance =
			 * batteryObj.getString("distance"); JSONObject alarmObj = new
			 * JSONObject(obj.getString("alarm")); warning =
			 * alarmObj.getInt("warning"); active = alarmObj.getInt("active");
			 */

			Log.i("Time", time);
			Log.i("latitute", latitute);
			Log.i("longitude", longitude);
			Log.i("charge", charge);
			Log.i("batteryDistance", batteryDistance);
			Log.i("warning", ">>>:" + warning);
			Log.i("active", ">>>:" + active);

			data = "Time=" + time;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}

	public String setInputStream(InputStream inputStream) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null) {

			result += line;

		}

		return result;
	}

	public String GET(String url) {

		String result = "";
		InputStream inputStream = null;
		try {
			HttpClient client = new DefaultHttpClient();

			HttpResponse response = client.execute(new HttpGet(url));

			inputStream = response.getEntity().getContent();
			if (inputStream != null) {
				result = setInputStream(inputStream);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}
}
