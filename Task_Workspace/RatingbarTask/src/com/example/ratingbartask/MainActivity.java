package com.example.ratingbartask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnFirst, btnSecond, btnThird;
	RatingBar ratingFirst, ratingSecond, ratingThird;
	TextView txtFirstResult, txtSecondResult, txtThirdResult;

	String first = "", second = "", third = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

		ratingFirst
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					@Override
					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						// TODO Auto-generated method stub

						first = String.valueOf(rating);

					}
				});

		btnFirst.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (first == "") {

					Toast.makeText(MainActivity.this, "Set rating first", 2000)
							.show();
				} else {
					txtFirstResult.setText(first);
					ratingSecond.setVisibility(View.VISIBLE);
				}

			}
		});

		ratingSecond
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					@Override
					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						// TODO Auto-generated method stub

						second = String.valueOf(rating);

					}
				});

		btnSecond.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (second == "") {

					Toast.makeText(MainActivity.this, "Set rating first", 2000)
							.show();
				} else {
					txtSecondResult.setText(second);
					ratingThird.setVisibility(View.VISIBLE);
				}

			}
		});
		ratingThird
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					@Override
					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						// TODO Auto-generated method stub

						third = String.valueOf(rating);

					}
				});

		btnThird.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (third == "") {

					Toast.makeText(MainActivity.this, "Set rating first", 2000)
							.show();
				} else {
					txtThirdResult.setText(third);

				}

			}
		});
	}

	void init() {
		btnFirst = (Button) findViewById(R.id.btn_One);
		btnSecond = (Button) findViewById(R.id.btn_Two);
		btnThird = (Button) findViewById(R.id.btn_Three);
		ratingFirst = (RatingBar) findViewById(R.id.firstRating);
		ratingSecond = (RatingBar) findViewById(R.id.secondRating);
		ratingThird = (RatingBar) findViewById(R.id.thirdRating);
		txtFirstResult = (TextView) findViewById(R.id.txtResultone);
		txtSecondResult = (TextView) findViewById(R.id.txtResulttwo);
		txtThirdResult = (TextView) findViewById(R.id.txtResultthree);

	}

}
