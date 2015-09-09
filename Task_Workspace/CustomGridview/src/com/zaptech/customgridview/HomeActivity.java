package com.zaptech.customgridview;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

public class HomeActivity extends ActionBarActivity {

	GridView gv;
	Context context;
	ArrayList prgmName;

	public static int[] prgmImages = { R.drawable.index, R.drawable.london,
			R.drawable.one, R.drawable.two, R.drawable.tiger, R.drawable.six,
			R.drawable.visit_doctors, R.drawable.win };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		gv = (GridView) findViewById(R.id.gridView1);
		gv.setAdapter(new CustomAdapter(HomeActivity.this, prgmImages));
	}

}
