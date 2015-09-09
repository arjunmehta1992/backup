package com.example.stockmarket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText textBox;
	Button button;
	TextView resultView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textBox = (EditText) findViewById(R.id.edt_company);
		button = (Button) findViewById(R.id.btn_getPrice);
		resultView = (TextView) findViewById(R.id.txt_result);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String url = "http://download.finance.yahoo.com/d/quotes.csv?f=sl1d1t1c1ohgv&e=.csv&s=";
				String finalUrl = url + textBox.getText().toString();

				getString(finalUrl);

			}
		});

	}

	public String getString(String s) {
		HttpURLConnection connection = null;
		try {

			connection = (HttpURLConnection) new URL(s).openConnection();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return getResponse(connection);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";

	}

	private String getResponse(HttpURLConnection connection) throws IOException {

		InputStreamReader reader = new InputStreamReader(
				connection.getInputStream());

		StringBuilder sb = new StringBuilder();
		char[] buf = new char[1024];
		int read;
		while ((read = reader.read(buf)) != -1) {
			sb.append(buf, 0, read);

		}
		reader.close();

		return sb.toString();
	}

}
