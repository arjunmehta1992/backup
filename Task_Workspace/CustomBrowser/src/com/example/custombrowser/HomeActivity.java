package com.example.custombrowser;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends ActionBarActivity {

	private EditText edtSearchBar;
	private WebView webView;
	private Button btnSeatch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

		btnSeatch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webView.loadUrl("https://" + edtSearchBar.getText().toString());

				// webView.loadUrl("https://www.google.co.in/?gfe_rd=cr&ei=qk3gVectqcbwB5-Vp4AM&gws_rd=ssl");
			}
		});

	}

	void init() {
		edtSearchBar = (EditText) findViewById(R.id.edt_SearchBar);
		webView = (WebView) findViewById(R.id.webView);
		btnSeatch = (Button) findViewById(R.id.btn_Search);
	}

}
