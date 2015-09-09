package com.example.jsonpost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {

	TextView tvIsConnected;
	EditText etName, etCountry, etTwitter;
	Button btnPost;
	ProgressDialog mProgressDialog;
	Person personObj;
	String str;
	InputStream inputstream;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

	}

	public void init() {
		tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
		etName = (EditText) findViewById(R.id.etName);
		etCountry = (EditText) findViewById(R.id.etCountry);
		etTwitter = (EditText) findViewById(R.id.etTwitter);
		btnPost = (Button) findViewById(R.id.btnPost);

		if (isConnected()) {
			tvIsConnected.setBackgroundColor(0xFF00CC00);
			tvIsConnected.setText("You are conncted");
		} else {
			tvIsConnected.setText("You are NOT conncted");
		}

		mProgressDialog = new ProgressDialog(HomeActivity.this);
		btnPost.setOnClickListener(this);
	}

	public boolean isConnected() {

		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;

	}

	private boolean validate() {
		if (etName.getText().toString().trim().equals(""))
			return false;
		else if (etCountry.getText().toString().trim().equals(""))
			return false;
		else if (etTwitter.getText().toString().trim().equals(""))
			return false;
		else
			return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnPost:
			if (!validate())
				Toast.makeText(getBaseContext(), "Enter some data!",
						Toast.LENGTH_LONG).show();
			// call AsynTask to perform network operation on separate thread
			new HttpAsyncTask().execute();
			break;

		default:
			break;
		}
	}

	class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			mProgressDialog.setTitle("Data is upload");
			mProgressDialog.setMessage("loading...");

			mProgressDialog.setCancelable(false);
			mProgressDialog.show();

			super.onPreExecute();
		}

		protected Void doInBackground(Void... params) {
			personObj = new Person();
			personObj.setName(etName.getText().toString());
			personObj.setTwitter(etTwitter.getText().toString());
			personObj.setCountry(etCountry.getText().toString());

			POST(personObj, "http://hmkcode.appspot.com/jsonservlet");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG)
					.show();

			super.onPostExecute(result);
		}

	}

	public static String POST(Person person, String url) {
		String result = "";
		InputStream inputstream = null;

		try {

			HttpClient hhtpClient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(url);

			String json = "";
			JSONObject mainObj = new JSONObject();
			mainObj.accumulate("name", person.getName().toString());
			mainObj.accumulate("twitter", person.getTwitter().toString());
			mainObj.accumulate("country", person.getCountry().toString());

			json = mainObj.toString();

			StringEntity se = new StringEntity(json);
			httpPost.setEntity(se);

			HttpResponse httpResponse = hhtpClient.execute(httpPost);

			inputstream = httpResponse.getEntity().getContent();

			if (inputstream != null)
				result = convertInputStreamToString(inputstream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

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

}
