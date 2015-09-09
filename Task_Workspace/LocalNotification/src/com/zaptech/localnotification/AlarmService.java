package com.zaptech.localnotification;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmService extends IntentService {

	private NotificationManager alarmNotificationManager;

	public AlarmService() {
		super("AlarmService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		sendNotification("Wake Up! Wake Up!");
	}

	private void sendNotification(String string) {
		Log.d("AlarmService", "Preparing to send notification...: ");
		alarmNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, HomeActivity.class), 0);

		NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
				this)
				.setContentTitle(getResources().getString(R.string.app_name))
				.setSmallIcon(R.drawable.ic_launcher)
				.setStyle(new NotificationCompat.BigTextStyle().bigText("hii"))
				.setContentText("Wake Up Please");

		alamNotificationBuilder.setContentIntent(contentIntent);
		alarmNotificationManager.notify(1, alamNotificationBuilder.build());
		Log.d("AlarmService", "Notification sent.");

	}

}