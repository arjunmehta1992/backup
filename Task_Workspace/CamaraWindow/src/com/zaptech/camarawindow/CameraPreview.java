package com.zaptech.camarawindow;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements
SurfaceHolder.Callback{

	
	  SurfaceHolder mSurfaceHolder;
	     Camera mCamera;

	    // Constructor that obtains context and camera
	    @SuppressWarnings("deprecation")
	    public CameraPreview(Context context,  Camera mCamera) {
	        super(context);
	        this.mCamera = mCamera;
	        this.mSurfaceHolder = this.getHolder();
	        this.mSurfaceHolder.addCallback(this);
	        this.mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	    }

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {
	            mCamera.setPreviewDisplay(holder);
	            mCamera.startPreview();
	        } catch (IOException e) {
	            // left blank for now
	        }
			
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			mCamera.stopPreview();
	        mCamera.release();
			
		}

}