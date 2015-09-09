package com.zaptech.camarawindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class HomeActivity extends ActionBarActivity {

	private Camera mCamera;
	private CameraPreview mCameraPreview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Context context = HomeActivity.this;
		mCamera = getCameraInstance();
		mCameraPreview = new CameraPreview(context, mCamera);

		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mCameraPreview);

		Button captureButton = (Button) findViewById(R.id.button_capture);
		
//		 Camera.Parameters parameters = mCamera.getParameters();
//		 parameters.set("orientation", "portrait");
//		 mCamera.setParameters(parameters);
	
		setCameraDisplayOrientation(HomeActivity.this,0, mCamera);
		
		 
		captureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCamera.takePicture(null, null, mPicture);
			}
		});

	}

	private Camera getCameraInstance() {
		Camera camera = null;
		try {
			camera = Camera.open();
		} catch (Exception e) {
			// cannot get camera or does not exist
		}
		return camera;
	}

	PictureCallback mPicture = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
			File pictureFile = getOutputMediaFile();
			if (pictureFile == null) {
				return;
			}
			try {
				FileOutputStream fos = new FileOutputStream(pictureFile);
				fos.write(data);
				fos.close();
			} catch (FileNotFoundException e) {

			} catch (IOException e) {
			}

		}

		private File getOutputMediaFile() {
			File mediaStorageDir = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
					"MyCameraApp");
			if (!mediaStorageDir.exists()) {
				if (!mediaStorageDir.mkdirs()) {
					Log.d("MyCameraApp", "failed to create directory");
					return null;
				}
			}
			// Create a media file name
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			File mediaFile;
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");

			return mediaFile;
		}
	};

	
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static void setCameraDisplayOrientation(Activity activity,
	         int cameraId, android.hardware.Camera camera) {
	   @SuppressWarnings("deprecation")
	android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
	   android.hardware.Camera.getCameraInfo(cameraId, info);
	   int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
	   int degrees = 0;
	   switch (rotation) {
	         case Surface.ROTATION_0: degrees = 0; break;
	         case Surface.ROTATION_90: degrees = 90; break;
	         case Surface.ROTATION_180: degrees = 180; break;
	         case Surface.ROTATION_270: degrees = 270; break;
	   }

	   int result;
	   if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
	       result = (info.orientation + degrees) % 360;
	       result = (360 - result) % 360;  // compensate the mirror
	   } else {  // back-facing
	       result = (info.orientation - degrees + 360) % 360;
	   }
	   camera.setDisplayOrientation(result);
	}
}
