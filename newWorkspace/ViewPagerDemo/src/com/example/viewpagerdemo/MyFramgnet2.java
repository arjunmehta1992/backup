package com.example.viewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFramgnet2 extends Fragment {

	TextView txtView_msg;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater
				.inflate(R.layout.activity_fragment2, container, false);

		txtView_msg = (TextView) v.findViewById(R.id.txtView_msg);

		txtView_msg.setText("GOod morning");

		return v;

	}

}
