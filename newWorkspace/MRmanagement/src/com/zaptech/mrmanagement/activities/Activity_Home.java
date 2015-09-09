package com.zaptech.mrmanagement.activities;

import java.util.ArrayList;

import com.example.mrmanagement.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Home extends Activity implements OnClickListener,
		OnItemClickListener {

	ListView drawer;
	DrawerLayout mydrawer;
	String[] colorlist;
	View v;
	Context context;
	Button btn;
	ImageView imgBtnList;
	ArrayList<String> arraylist_Drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

		drawer.setAdapter(new CustomAdapter(Activity_Home.this, new String[] {
				"Home", "Login", "Visits", "Settings" }, new int[] {
				R.drawable.ic_home, R.drawable.ic_login,
				R.drawable.visit_doctors, R.drawable.settings_img_two }));

	}

	void init() {

		drawer = (ListView) findViewById(R.id.left_drawer);
		mydrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		imgBtnList = (ImageView) findViewById(R.id.imgBtn_list);
		arraylist_Drawer = new ArrayList<String>();
		imgBtnList.setOnClickListener(this);
		drawer.setOnItemClickListener(this);
	}

	public class CustomAdapter extends BaseAdapter {

		Context context;
		String[] data;
		int[] img_list;
		LayoutInflater inflater = null;

		public CustomAdapter(Context context, String[] data, int[] img_list) {
			this.context = context;
			this.data = data;
			this.img_list = img_list;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data[position];
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View vi = convertView;

			vi = inflater.inflate(R.layout.activity_homelist_layout, null);

			ImageView img_home = (ImageView) vi.findViewById(R.id.img_Home);

			img_home.setBackgroundResource(img_list[position]);

			TextView txtView_home = (TextView) vi
					.findViewById(R.id.txtView_Home);

			txtView_home.setText(data[position]);

			return vi;
		}

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.imgBtn_list:

			mydrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

			if (mydrawer.isDrawerOpen(Gravity.LEFT)) {
				Animation animation1 = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.clockwise);

				imgBtnList.startAnimation(animation1);

				mydrawer.closeDrawer(Gravity.LEFT);
				imgBtnList.setBackgroundResource(R.drawable.ic_list);

			} else {
				Animation animation2 = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.clockwise);
				imgBtnList.startAnimation(animation2);
				mydrawer.openDrawer(Gravity.LEFT);

				imgBtnList.setBackgroundResource(R.drawable.img_back);
			}

			break;

		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		String str = drawer.getItemAtPosition(position).toString();
		Toast.makeText(Activity_Home.this, " " + str, Toast.LENGTH_SHORT)
				.show();

		if (str.equalsIgnoreCase("Home")) {
			Intent intent_home = new Intent(Activity_Home.this,
					Activity_Home.class);
			startActivity(intent_home);
		} else if (str.equalsIgnoreCase("Login")) {
			Intent intent_login = new Intent(Activity_Home.this,
					Activity_Login.class);
			startActivity(intent_login);
		}

	}

}
