package com.example.benoitarnaud.ecamroomfinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.graphics.Bitmap.Config.ARGB_8888;

public class DrawStuff extends View {
    Bitmap map;
    public DrawStuff(Context context) {
        super(context);
        map = BitmapFactory.decodeResource(getResources(),R.drawable.e1);
        map = map.copy(ARGB_8888 ,true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLACK);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(10);
        //MainActivity.salles.get(MainActivity.salleDepart.getSelectedItem().toString()).displayMap(canvas, myPaint,getResources());
        MainActivity.salles.get(MainActivity.salleDepart.getSelectedItem().toString()).findPath(MainActivity.salles.get(MainActivity.salleArrival.getSelectedItem().toString()), canvas,myPaint, getResources() );
        //MainActivity.salles.get("112").displayStart(canvas, myPaint, getResources());
        //MainActivity.salles.get("112").goToPoint(canvas, myPaint);
        //MainActivity.salles.get("112").pointToPoint(MainActivity.salles.get("107"), canvas, myPaint);
        //MainActivity.salles.get("107").goToPoint(canvas, myPaint);
        //MainActivity.salles.get("107").displayArrival(canvas,myPaint, getResources());
        //canvas.drawRect(new Rect(0,0,100,100), myPaint);
        //canvas.drawBitmap(map, null, new Rect(canvas.getWidth()*15/100, 0, canvas.getWidth()*70/100, canvas.getHeight()), null);
        //canvas.drawCircle(canvas.getWidth()*60/100, canvas.getHeight()*51/100,40, myPaint);
        //canvas.drawLine(canvas.getWidth()*25/100,canvas.getHeight()*81/100,canvas.getWidth()*39/100, canvas.getHeight()*18/100,myPaint);
    }


}
