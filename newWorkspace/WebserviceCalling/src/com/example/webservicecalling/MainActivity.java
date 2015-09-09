package com.example.webservicecalling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button GetServerData;
	String ServerURL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		GetServerData.setOnClickListener(this);

	}

	private void init() {
		GetServerData = (Button) findViewById(R.id.GetServerData);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.GetServerData:

			ServerURL = "http://androidexample.com/media/webservice/JsonReturn.php";
			new LongOperation().execute(ServerURL);
			break;

		default:
			break;
		}
	}

	class LongOperation extends AsyncTask<String, Void, Void> 
	{

		@SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
		String content;
		ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
		String data = "";
		TextView uiUpdate = (TextView) findViewById(R.id.output);
		TextView jsonParsed = (TextView) findViewById(R.id.jsonParsed);
		TextView serverText = (TextView) findViewById(R.id.serverText);

		String Content;

		@Override
		protected void onPreExecute() {
			Dialog.setTitle("Please waiting");
			Dialog.show();

			try {

				data += "&" + URLEncoder.encode("data", "UTF-8") + "="
						+ serverText.getText().toString();

			} catch (Exception e) {
				
			}

		}

		@Override
		protected Void doInBackground(String... urls) {
			BufferedReader reader = null;

			try {
				URL url = new URL(urls[0]);
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(
						conn.getOutputStream());
				wr.write(data);
				wr.flush();

				reader = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;

				while ((line = reader.readLine()) != null) {
					
					sb.append(line + " ");
				}
				Content = sb.toString();
			} catch (Exception e) {
				
			}
			 finally
             {
                 try
                 {
       
                     reader.close();
                 }
     
                 catch(Exception ex) {}
             }
          

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Dialog.dismiss();
			String OutputData = "";
			JSONObject jsonResponse;
			try {
				jsonResponse = new JSONObject(Content);
				JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");
				for(int i=0; i < jsonMainNode.length(); i++)
                {
                    
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    String name       = jsonChildNode.optString("name").toString();
                    String number     = jsonChildNode.optString("number").toString();
                    String date_added = jsonChildNode.optString("date_added").toString();
                      
                    
                    OutputData += " Name: "+ name +"\n"+ "Number: "+ number +"\ndate_added"+date_added;
                    
                     
               }
					
			} catch (Exception e) {

			}
			jsonParsed.setText( OutputData );
		}
		
	}	
}
