package com.zaptech.tabfragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AppOpsManager.OnOpChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class HomeActivity extends FragmentActivity implements
		ActionBar.TabListener {

	ActionBar.Tab firstTab, secondTab, thirdTab;
	DrawerLayout mydrawer;

	ArrayList<String> arraylist_Drawer;

	Fragment firstFragmentTab = new FirstActivity();
	Fragment secondFragmentTab = new SecondActivity();
	Fragment thirdFragmentTab = new ThirdActivity();

	public static GoogleAnalytics analytics;
	public static Tracker tracker;

	private EasyTracker easyTracker = null;

	TabsPagerAdapter obj_pagerAdapter;

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	ImageView imgBtnList;
	ListView drawer;
	// Tab titles
	private String[] tabs = { "Top Rated", "Games", "Movies" };

	private String[] navMenuTitles = { "ContactItems", "NewsItems",
			"GallaryItems", "YoutubeItems", "TwitterItems", "RssItems",
			"WebItems", "HomeItems", "FeedbackItems", "GeoLocationItems",
			"DataGraphics", "VideoItems", "inAppSettings" };

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		setContentView(R.layout.activity_home);

		final ActionBar actionBar = getActionBar();
		//
		// if (android.os.Build.VERSION.SDK_INT < 11) {
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// }
		actionBar.setHomeButtonEnabled(false);

		actionBar.setIcon(R.drawable.ic_list);

		actionBar.setDisplayOptions(actionBar.getDisplayOptions()
				| ActionBar.DISPLAY_SHOW_CUSTOM);
		ImageView imageView = new ImageView(actionBar.getThemedContext());
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		imageView.setImageResource(R.drawable.ic_list);
		ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.WRAP_CONTENT,
				ActionBar.LayoutParams.WRAP_CONTENT, Gravity.LEFT
						| Gravity.CENTER_VERTICAL);

		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//
		// firstTab = actionBar.newTab().setIcon(R.drawable.ic_launcher)
		// .setText("First Tab");
		// secondTab = actionBar.newTab().setIcon(R.drawable.ic_launcher)
		// .setText("Second Tab");
		// thirdTab = actionBar.newTab().setIcon(R.drawable.ic_launcher)
		// .setText("Third Tab");
		//
		// /*
		// * firstTab.setTabListener(new TabListener(firstFragmentTab));
		// * secondTab.setTabListener(new TabListener(secondFragmentTab));
		// * thirdTab.setTabListener(new TabListener(thirdFragmentTab));
		// */
		//
		// actionBar.addTab(firstTab);
		// actionBar.addTab(secondTab);
		// actionBar.addTab(thirdTab);
		//
		// viewPager = (ViewPager) findViewById(R.id.pager);
		// List<Fragment> fragments = getFragments();
		//
		// obj_pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(),
		// fragments);
		//
		// ViewPager pager = (ViewPager) findViewById(R.id.pager);
		//
		// pager.setAdapter(obj_pagerAdapter);

		analytics = GoogleAnalytics.getInstance(this);
		analytics.setLocalDispatchPeriod(1800);

		tracker = analytics.newTracker("UA-66147419-1"); // Replace with actual
															// tracker/property
															// Id

		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);

		tracker.setScreenName("main screen");

		tracker.send(new HitBuilders.EventBuilder().setCategory("UX")
				.setAction("click").setLabel("submit").build());

		viewPager = (ViewPager) findViewById(R.id.pager);
		// actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		// actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
				easyTracker = EasyTracker.getInstance();

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		imgBtnList = (ImageView) findViewById(R.id.imgBtn_list);
		arraylist_Drawer = new ArrayList<String>();
		drawer = (ListView) findViewById(R.id.left_drawer);
		mydrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

		ArrayAdapter<String> adpt = new ArrayAdapter<String>(HomeActivity.this,
				android.R.layout.simple_list_item_1, navMenuTitles);

		drawer.setAdapter(adpt);

		imgBtnList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mydrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

				if (mydrawer.isDrawerOpen(drawer)) {
					// Animation animation1 = AnimationUtils.loadAnimation(
					// getApplicationContext(), R.anim.clockwise);

					// imgBtnList.startAnimation(animation1);

					mydrawer.closeDrawer(drawer);
					// imgBtnList.setBackgroundResource(R.drawable.ic_list);

				} else {
					// Animation animation2 = AnimationUtils.loadAnimation(
					// getApplicationContext(), R.anim.clockwise);
					// imgBtnList.startAnimation(animation2);
					mydrawer.openDrawer(drawer);
					// imgBtnList.setBackgroundResource(R.drawable.img_back);
				}

			}
		});

	}

	private List<Fragment> getFragments() {

		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(firstFragmentTab);

		fList.add(secondFragmentTab);

		fList.add(thirdFragmentTab);

		return fList;

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	protected void onStart() {

		super.onStart();
		EasyTracker.getInstance().activityStart(this);

	}

	@Override
	protected void onStop() {

		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
}
