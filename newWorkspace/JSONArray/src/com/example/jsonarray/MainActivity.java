package com.example.jsonarray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv1;
	String []name;
	String []surname;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.textView1);

		String jsonArray = "{\"student\":[{\"Roll no\":\"1\",\"name\":\"Arjun\",\"surname\":\"Mehta\"},{\"Roll no\":\"2\",\"name\":\"Rutul\",\"surname\":\"Patel\"}]}";
		JSONObject objJson;
		try {

			objJson = new JSONObject(jsonArray);
			JSONArray nameArray = objJson.optJSONArray("student");
			name=new String[nameArray.length()];
			surname=new String[nameArray.length()];
			for (int i = 0; i < nameArray.length(); i++) {
				JSONObject jsonObject = nameArray.getJSONObject(i);

				name[i] = jsonObject.optString("name").toString();
				surname[i]=jsonObject.optString("surname").toString();
				tv1.setText(tv1.getText()+"\n"+name[i]+"  "+surname[i]);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
