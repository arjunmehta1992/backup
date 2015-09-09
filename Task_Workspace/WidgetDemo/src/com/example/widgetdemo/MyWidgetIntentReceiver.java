package com.example.widgetdemo;

import com.example.widgetdemo.R.color;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.widget.RemoteViews;

public class MyWidgetIntentReceiver extends BroadcastReceiver {

	private static int clickCount = 0;

	String packageName = "com.zaptech.wallpapermanager";
	String className = "com.zaptech.wallpapermanager.Splash";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals("pl.looksok.intent.action.CHANGE_PICTURE")) {

			System.err.println("-----------------");

			intent = new Intent(Intent.ACTION_MAIN);
			intent.setComponent(new ComponentName(packageName, className));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			// intent = new Intent(context, MainScreen.class);
			// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// context.startActivity(intent);
			// updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(Context context) {

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.activity_home);
		remoteViews.setImageViewResource(R.id.widget_image, getImageToSet());

		// REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
		remoteViews.setOnClickPendingIntent(R.id.linMain,
				MyWidgetProvider.buildButtonPendingIntent(context));

		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(),
				remoteViews);
	}

	private int getImageToSet() {
		clickCount++;
		return clickCount % 2 == 0 ? R.drawable.animal_one
				: R.drawable.animal_two;
	}

}
