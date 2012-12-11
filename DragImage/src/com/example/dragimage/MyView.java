package com.example.dragimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	private int x = 100;
	private int y = 100;
	private Bitmap bmp = null;
	private boolean selected = false;

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ic_launcher);
	}

	/**
	 * @param args
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(bmp, x, y, null);
	}
	
	int deltax=0;
	int deltay=0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int eventaction = event.getAction();
		int touchx = (int) event.getX();
		int touchy = (int) event.getY();

		switch (eventaction) {
		case MotionEvent.ACTION_DOWN:
			if (touchx > x & touchx < x + bmp.getWidth() & touchy > y
					& touchy < y + bmp.getHeight()) {
				selected = true;
			deltax=touchx-x;
			deltay=touchy-y;
			
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			x = touchx-deltax;
			y = touchy-deltay;
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			selected = false;
			break;
		default:
			break;
		}
		return true;
	}

}
