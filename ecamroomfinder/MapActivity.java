package com.example.benoitarnaud.ecamroomfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MapActivity extends AppCompatActivity {
    DrawStuff drawing;
    public static boolean touched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        drawing = new DrawStuff(this);
        touched = false;
        setContentView(drawing);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            drawing.invalidate();
            touched = !touched;
        }
        return super.onTouchEvent(event);

    }
}
