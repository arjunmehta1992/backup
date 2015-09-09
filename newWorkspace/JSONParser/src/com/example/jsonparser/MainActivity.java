package com.example.jsonparser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";

	private EditText location, country, temperature, humidity, pressure;
	private Button btn_weather;
	HandlerJSON obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		btn_weather.setOnClickListener(this);

	}

	public void init() {
		location = (EditText) findViewById(R.id.editText1);
		country = (EditText) findViewById(R.id.editText2);
		temperature = (EditText) findViewById(R.id.editText3);
		humidity = (EditText) findViewById(R.id.editText4);
		pressure = (EditText) findViewById(R.id.editText5);
		btn_weather = (Button) findViewById(R.id.button1);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:

			open(v);
			break;

		default:
			break;
		}

	}

	public void open(View view) {
		String url = location.getText().toString();
		String finalUrl = url1 + url;
		country.setText(finalUrl);
		obj = new HandlerJSON(finalUrl);
		obj.fetchJSON();

		while (obj.parsingComplete)
			;
		country.setText(obj.getCountry());
		temperature.setText(obj.getTempreture());
		humidity.setText(obj.getHumidity());
		pressure.setText(obj.getPressure());

	}
}
