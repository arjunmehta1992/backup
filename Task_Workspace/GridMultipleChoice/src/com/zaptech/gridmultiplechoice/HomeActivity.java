package com.zaptech.gridmultiplechoice;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends ActionBarActivity {

	GridView mGrid;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);
		/* loadApps(); */
		mGrid = (GridView) findViewById(R.id.myGrid);
		// mGrid.setAdapter(new AppsAdapter());
		mGrid.setAdapter(new ImageAdapter(this));
		mGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
		mGrid.setMultiChoiceModeListener(new MultiChoiceModeListener());

	}

	private List<ResolveInfo> mApps;

	/*
	 * private void loadApps() { Intent mainIntent = new
	 * Intent(Intent.ACTION_MAIN, null);
	 * mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	 * 
	 * mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
	 * 
	 * }
	 */
	public class AppsAdapter extends BaseAdapter {
		public AppsAdapter() {
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			CheckableLayout l;
			ImageView i;

			if (convertView == null) {
				i = new ImageView(HomeActivity.this);
				i.setScaleType(ImageView.ScaleType.FIT_CENTER);
				i.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
				l = new CheckableLayout(HomeActivity.this);
				l.setLayoutParams(new GridView.LayoutParams(
						GridView.LayoutParams.WRAP_CONTENT,
						GridView.LayoutParams.WRAP_CONTENT));
				l.addView(i);
			} else {
				l = (CheckableLayout) convertView;
				i = (ImageView) l.getChildAt(0);
			}

			ResolveInfo info = mApps.get(position);
			i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));

			return l;
		}

		public final int getCount() {
			return mApps.size();
		}

		public final Object getItem(int position) {
			return mApps.get(position);
		}

		public final long getItemId(int position) {
			return position;
		}
	}

	public class CheckableLayout extends FrameLayout implements Checkable {
		private boolean mChecked;

		public CheckableLayout(Context context) {
			super(context);
		}

		public void setChecked(boolean checked) {
			mChecked = checked;
			setBackgroundDrawable(checked ? getResources().getDrawable(
					R.drawable.two) : null);

			setForeground(getBackground());
		}

		public boolean isChecked() {
			return mChecked;
		}

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void toggle() {
			setChecked(!mChecked);
		}

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public class MultiChoiceModeListener implements
			GridView.MultiChoiceModeListener {
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.setTitle("Select Items");
			mode.setSubtitle("One item selected");
			return true;
		}

		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return true;
		}

		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			return true;
		}

		public void onDestroyActionMode(ActionMode mode) {
		}

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {
			int selectCount = mGrid.getCheckedItemCount();
			Toast.makeText(HomeActivity.this, " " + selectCount,
					Toast.LENGTH_SHORT).show();
			switch (selectCount) {
			case 1:
				mode.setSubtitle("One item selected");
				break;
			default:

				mode.setSubtitle("" + selectCount + " items selected");
				break;
			}
		}

	}

}
