package com.example.testapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.Button;
import android.widget.Toast;

public class StockDetail extends ActionBarActivity {

	Button btnNext, btnPrev;
	int strPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_detail);
		Intent intent = getIntent();
		strPosition = intent.getIntExtra("Position", 0);
		// int pos = Integer.parseInt(strPosition);

		btnNext = (Button) findViewById(R.id.btnNext);
		btnPrev = (Button) findViewById(R.id.btnPrevious);
		if (strPosition <= 0) {
			btnPrev.setVisibility(View.GONE);
		}
	}

}
