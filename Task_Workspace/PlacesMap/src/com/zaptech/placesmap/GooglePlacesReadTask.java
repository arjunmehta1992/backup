package com.zaptech.placesmap;

import java.io.IOException;

import com.google.android.gms.maps.GoogleMap;

import android.content.Context;
import android.os.AsyncTask;

public class GooglePlacesReadTask extends AsyncTask<Object, Integer, String> {

	String googlePlacesData = null;
	GoogleMap googleMap;
	String name;

	@Override
	protected String doInBackground(Object... params) {

		googleMap = (GoogleMap) params[0];
		name = (String) params[2];

		String googlePlacesUrl = (String) params[1];

		System.err.println("Url=====5656456456==" + googlePlacesUrl);
		Http http = new Http();
		try {
			googlePlacesData = http.read(googlePlacesUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return googlePlacesData;
	}

	@Override
	protected void onPostExecute(String result) {

		PlacesDisplayTask placesDisplayTask = new PlacesDisplayTask();
		Object[] toPass = new Object[3];
		toPass[0] = googleMap;
		toPass[1] = result;
		toPass[2] = name;

		placesDisplayTask.execute(toPass);
		super.onPostExecute(result);
	}
}
