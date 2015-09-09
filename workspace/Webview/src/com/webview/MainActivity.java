package com.webview;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	WebView v1;
	EditText url_editText;
	Button go;
	String http="http://";
	String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		go.setOnClickListener(this);
		
		
	}

	public void init() {
		v1 = (WebView) findViewById(R.id.Webview);

		url_editText = (EditText) findViewById(R.id.ed_url);
		go = (Button) findViewById(R.id.Go);

	}

	@Override
	public void onClick(View v) {

		
		switch (v.getId()) {
		case R.id.Go:
			urlGo();
			break;

		default:
			break;
		}
		
	}
	
	
	public void urlGo()
	{
		
		url=url_editText.getText().toString();
		v1.loadUrl(http+url);
	}
	
	

}
