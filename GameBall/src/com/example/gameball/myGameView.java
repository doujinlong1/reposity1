package com.example.gameball;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

public class myGameView extends View implements SensorEventListener{
	
	final float GRAVITY=40;
	float ball_X,ball_Y,xSpeed,ySpeed,xAclrt,yAclrt,height,width;
	SensorManager sm;
	public myGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	private void init(Context context) {
		sm = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_FASTEST);
		ball_X=ball_Y=20;
		xSpeed=ySpeed=xAclrt=yAclrt=height=width=0;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (height==0&&width==0) {
			height=canvas.getHeight();
			width=canvas.getWidth();
		}
		Paint p = new Paint();
		canvas.drawColor(Color.BLACK);
		p.setColor(Color.RED);
		canvas.drawCircle(ball_X, ball_Y, 20, p);
		canvas.drawText(ball_X+","+ball_Y, 300, 300, p);
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[]values = event.values;
		xAclrt =values[1];
		float xxAcl=(float) Math.sin(xAclrt);
		yAclrt =values[2];
		float yyAcl = (float)Math.sin(yAclrt);
		if (xAclrt>0) {
			ball_Y-=10+10*xxAcl;
		}else {
			ball_Y+=10+10*xxAcl;
		}
		if (yAclrt>0) {
			ball_X-=10+yyAcl*10;
		}else {
			ball_X+=10+10*yyAcl;
		}
		if (ball_X>=width) {
			ball_X=width;
		}else if (ball_X<=0) {
			ball_X=0;
		}
		if (ball_Y>=height) {
			ball_Y=height;
		}else if (ball_Y<=0) {
			ball_Y=0;
		}
		postInvalidate();
	}
	

}
