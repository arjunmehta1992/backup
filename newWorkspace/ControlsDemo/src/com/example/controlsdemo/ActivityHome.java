package com.example.controlsdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ActivityHome extends Activity implements OnTouchListener {

	ImageView tampil;
	Canvas canvas;
	Paint paint;
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	private static final String TAG = "Touch";
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
	float downx = 0, downy = 0, upx = 0, upy = 0;
	// Remember some things for zooming
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;
	float x1, y1, x2, y2;
	Bitmap bmp;
	int i = 1;
	RelativeLayout rel_touchBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_home);

		rel_touchBox = (RelativeLayout) findViewById(R.id.rel_drawLine);
			
	}

	/*
	 * @SuppressLint("WrongCall")
	 * 
	 * @Override public boolean onTouchEvent(MotionEvent event) {
	 * 
	 * switch (event.getAction() & MotionEvent.ACTION_MASK) { case
	 * MotionEvent.ACTION_DOWN: savedMatrix.set(matrix); start.set(event.getX(),
	 * event.getY()); Log.d(TAG, "mode=DRAG"); mode = DRAG; Log.i(TAG,
	 * "("+String
	 * .valueOf((int)event.getX())+","+String.valueOf((int)event.getY())+")");
	 * if (i==1){ x1 = event.getX(); y1 = event.getY(); i = 2; Log.i(TAG,
	 * "coordinate x1 : "+String.valueOf(x1)+" y1 : "+String.valueOf(y1)); }
	 * else if (i==2){ x2 = event.getX(); y2 = event.getY(); i = 3; Log.i(TAG,
	 * "coordinate x2 : "+String.valueOf(x2)+" y2 : "+String.valueOf(y2));
	 * onDraw(); } break; } return true;
	 * 
	 * 
	 * 
	 * }
	 */

	public void onDraw() {
		bmp = Bitmap.createBitmap(tampil.getWidth(), tampil.getHeight(),
				Config.ARGB_8888);
		Canvas c = new Canvas(bmp);
		tampil.draw(c);

		Paint pnt = new Paint();
		pnt.setColor(Color.RED);
		c.drawLine(x1, y1, x2, y2, pnt);
		tampil.setImageBitmap(bmp);
		i = 1;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			upx = event.getX();
			upy = event.getY();
			canvas.drawLine(downx, downy, upx, upy, paint);
			// imageView.invalidate();
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		default:
			break;
		}
		return true;

	}

}
