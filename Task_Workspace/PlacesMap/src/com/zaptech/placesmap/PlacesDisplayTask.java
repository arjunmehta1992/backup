package com.zaptech.placesmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class PlacesDisplayTask extends
		AsyncTask<Object, Integer, List<HashMap<String, String>>> {

	JSONObject googlePlacesJson;
	GoogleMap googleMap;

	String placeCategory;
	View CustomMarker;
	String str;
	Context obj;
	LatLng latLng;
	HomeActivity objContext = new HomeActivity();
	String iconUrl;
	private StringBuilder urlString;
	List<List<HashMap<String, String>>> routes;
	ArrayList<LatLng> points;
	List<HashMap<String, String>> path;
	Polyline polyline;
	RelativeLayout relative_mapview;
	RelativeLayout viewContainer;

	Context con;

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

		List<HashMap<String, String>> googlePlacesList = null;
		Places placeJsonParser = new Places();

		try {
			googleMap = (GoogleMap) inputObj[0];
			placeCategory = (String) inputObj[2];

			googlePlacesJson = new JSONObject((String) inputObj[1]);
			googlePlacesList = placeJsonParser.parse(googlePlacesJson);
		} catch (Exception e) {
			Log.d("Exception", e.toString());
		}
		return googlePlacesList;
	}

	@Override
	protected void onPostExecute(List<HashMap<String, String>> list) {
		googleMap.clear();

		JSONObject jsonObj = new JSONObject();

		// Creating JSON Parser instance
		JSONArray jr;
		try {
			jr = jsonObj.getJSONArray("results");
			for (int h = 0; h < jr.length(); h++) {
				JSONObject js = jr.getJSONObject(h);
				String icon_array = js.getString("icon");
				Log.i("icon ===========URL", "++++++++++" + icon_array);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}

		// System.err.println("Size of list===============" + list.size());
		if (list != null) {
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					MarkerOptions markerOptions = new MarkerOptions();
					HashMap<String, String> googlePlace = list.get(i);
					double lat = Double.parseDouble(googlePlace.get("lat"));
					double lng = Double.parseDouble(googlePlace.get("lng"));
					String placeName = googlePlace.get("place_name");
					String vicinity = googlePlace.get("vicinity");
					iconUrl = googlePlace.get("icon");
					Log.d("Url=======", "++++++++++" + iconUrl);
					latLng = new LatLng(lat, lng);
					markerOptions.position(latLng);

					markerOptions.title(placeName + ":" + vicinity);

					Log.d("Place name", ">>>>>>>>>" + placeCategory);
					if (placeCategory.equalsIgnoreCase("airport"))

					{

						googleMap
								.addMarker(new MarkerOptions()
										.position(latLng)
										.title(placeName + ":" + vicinity)
										.icon(BitmapDescriptorFactory
												.fromResource(R.drawable.icon_airport)));

					}

					else if (placeCategory.equalsIgnoreCase("atm"))

					{

						googleMap
								.addMarker(new MarkerOptions()
										.position(latLng)
										.title(placeName + ":" + vicinity)
										.icon(BitmapDescriptorFactory
												.fromResource(R.drawable.icon_map_atm)));

					} else if (placeCategory.equalsIgnoreCase("bank"))

					{

						googleMap.addMarker(new MarkerOptions()
								.position(latLng)
								.title(placeName + ":" + vicinity)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.icon_bank)));

					}

					else if (placeCategory.equalsIgnoreCase("bus_station"))

					{

						googleMap
								.addMarker(new MarkerOptions()
										.position(latLng)
										.title(placeName + ":" + vicinity)
										.icon(BitmapDescriptorFactory
												.fromResource(R.drawable.icon_busstop)));

					} else if (placeCategory.equalsIgnoreCase("church"))

					{

						googleMap.addMarker(new MarkerOptions()
								.position(latLng)
								.title(placeName + ":" + vicinity)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.icon_church)));

					} else if (placeCategory.equalsIgnoreCase("doctor"))

					{

						googleMap
								.addMarker(new MarkerOptions()
										.position(latLng)
										.title(placeName + ":" + vicinity)
										.icon(BitmapDescriptorFactory
												.fromResource(R.drawable.icon_doctor_icon)));

					} else if (placeCategory.equalsIgnoreCase("hospital"))

					{

						googleMap.addMarker(new MarkerOptions()
								.position(latLng)
								.title(placeName + ":" + vicinity)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.ic_hospital)));

					} else if (placeCategory.equalsIgnoreCase("movie_theater"))

					{

						googleMap.addMarker(new MarkerOptions()
								.position(latLng)
								.title(placeName + ":" + vicinity)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.icon_movies)));

					} else if (placeCategory.equalsIgnoreCase("hindu_temple"))

					{

						googleMap
								.addMarker(new MarkerOptions()
										.position(latLng)
										.title(placeName + ":" + vicinity)
										.icon(BitmapDescriptorFactory
												.fromResource(R.drawable.icon_hindu_temple)));

					} else if (placeCategory.equalsIgnoreCase("restaurant"))

					{

						googleMap
								.addMarker(new MarkerOptions()
										.position(latLng)
										.title(placeName + ":" + vicinity)
										.icon(BitmapDescriptorFactory
												.fromResource(R.drawable.icon_restaurants)));

					} else if (placeCategory.equalsIgnoreCase("liquor_store"))

					{

						googleMap.addMarker(new MarkerOptions()
								.position(latLng)
								.title(placeName + ":" + vicinity)
								.icon(BitmapDescriptorFactory
										.fromResource(R.drawable.icon_liquor)));

					}

					else {
						// googleMap.addMarker(new MarkerOptions()
						// .position(latLng)
						// .title(placeName + ":" + vicinity)
						// .icon(BitmapDescriptorFactory
						// .fromResource(R.drawable.ic_hospital)));
					}

				}
			}
		}

		googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker arg0) {

				if (polyline != null) {
					polyline.remove();
				}

				// drawCustomMarker(arg0.getPosition().latitude,
				// arg0.getPosition().longitude, arg0);

				int zoom = (int) googleMap.getCameraPosition().zoom;

				Log.d("Lat Long>>>>>>>>>", "++++++++cur lat"
						+ HomeActivity.currentLattitude + "cur long"
						+ HomeActivity.currentLongitude);

				LatLng lat = arg0.getPosition();

				Log.i("hello", "++++++++++++++++++++++++++++++++" + lat);

				urlString = new StringBuilder();

				urlString
						.append("http://maps.googleapis.com/maps/api/directions/json");
				urlString.append("?origin=");// from
				urlString.append(HomeActivity.currentLattitude);
				urlString.append(",");
				urlString.append(HomeActivity.currentLongitude);
				urlString.append("&destination=");// to
				urlString.append(lat.latitude);
				urlString.append(",");
				urlString.append(lat.longitude);
				Log.i("", "URL=====" + urlString);

				DownloadTask downloadTask = new DownloadTask();

				downloadTask.execute(urlString.toString());

				return false;

			}

		});

	}

	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	private class DownloadTask extends AsyncTask<String, Void, String> {

		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {

			// For storing data from web service
			String data = "";

			try {
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			ParserTask parserTask = new ParserTask();

			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
		}
	}

	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}

		return poly;
	}

	public List<List<HashMap<String, String>>> parse(JSONObject jObject) {

		routes = new ArrayList<List<HashMap<String, String>>>();
		JSONArray jRoutes = null;
		JSONArray jLegs = null;
		JSONArray jSteps = null;

		try {

			jRoutes = jObject.getJSONArray("routes");

			/** Traversing all routes */
			for (int i = 0; i < jRoutes.length(); i++) {
				jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
				List path = new ArrayList<HashMap<String, String>>();

				/** Traversing all legs */
				for (int j = 0; j < jLegs.length(); j++) {
					jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

					/** Traversing all steps */
					for (int k = 0; k < jSteps.length(); k++) {
						String polyline = "";
						polyline = (String) ((JSONObject) ((JSONObject) jSteps
								.get(k)).get("polyline")).get("points");
						List<LatLng> list = decodePoly(polyline);

						/** Traversing all points */
						for (int l = 0; l < list.size(); l++) {
							HashMap<String, String> hm = new HashMap<String, String>();
							hm.put("lat",
									Double.toString(((LatLng) list.get(l)).latitude));
							hm.put("lng",
									Double.toString(((LatLng) list.get(l)).longitude));
							path.add(hm);
						}
					}
					routes.add(path);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}

		return routes;
	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		// Parsing the data in non-ui thread
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);

				// Starts parsing data
				routes = parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			// ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			MarkerOptions markerOptions = new MarkerOptions();

			for (int i = 0; i < result.size(); i++) {
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();

				path = result.get(i);

				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);

					points.add(position);
				}

				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(2);
				lineOptions.color(Color.BLUE);
			}

			// Drawing polyline in the Google Map for the i-th route
			// googleMap.addPolyline(lineOptions);
			polyline = googleMap.addPolyline(lineOptions);

			points.clear();
			path.clear();
			routes.clear();
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

	public class InfoWindow implements InfoWindowAdapter {

		LayoutInflater inflater;

		public InfoWindow(LayoutInflater inflater) {

			this.inflater = inflater;
		}

		@Override
		public View getInfoContents(Marker arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public View getInfoWindow(Marker arg0) {

			View v = inflater.inflate(R.layout.raw_infowindow, null);

			TextView textName = (TextView) v
					.findViewById(R.id.txt_PlaceDetails);
			textName.setText(arg0.getTitle());

			return (v);
		}

	}

}
