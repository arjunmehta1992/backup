package com.example.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;

public class LoadHtml extends ActionBarActivity {

	private WebView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_html);
		view = (WebView) findViewById(R.id.web);
		WebSettings webSettings = view.getSettings();
		view.getSettings().setPluginState(PluginState.ON);

		view.clearCache(true);
		view.clearHistory();
		view.getSettings().setJavaScriptEnabled(true);
		view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);
		view.getSettings().setJavaScriptEnabled(true);
		view.getSettings().setDomStorageEnabled(true);
		view.loadUrl("file:///android_asset/sample.html");
		// view.loadUrl("http://stackoverflow.com/questions/13888481/android-webview-javascript-enabled");

	}

}
