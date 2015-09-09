package com.example.widgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.activity_home);

		remoteViews.setOnClickPendingIntent(R.id.linMain,
				buildButtonPendingIntent(context));

		pushWidgetUpdate(context, remoteViews);

	}

	// @Override
	// public void onEnabled(Context context) {
	//
	// AppWidgetManager mgr = AppWidgetManager.getInstance(context);
	// // retrieve a ref to the manager so we can pass a view update
	//
	// Intent i = new Intent();
	// i.setClassName("com.zaptech.wallpapermanager",
	// "com.zaptech.wallpapermanager.Splash");
	// PendingIntent myPI = PendingIntent.getService(context, 0, i, 0);
	// // intent to start service
	//
	// RemoteViews views = new RemoteViews(context.getPackageName(),
	// R.layout.activity_home);
	//
	// views.setOnClickPendingIntent(R.id.widget_button, myPI);
	//
	// ComponentName comp = new ComponentName(context.getPackageName(), null);
	//
	// mgr.updateAppWidget(comp, views);
	//
	// super.onEnabled(context);
	// }

	public static PendingIntent buildButtonPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction("pl.looksok.intent.action.CHANGE_PICTURE");
		return PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
	}

	static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {

		ComponentName myWidget = new ComponentName(context,
				MyWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(myWidget, remoteViews);
	}

}