package es.source.code.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class SCOSEntry extends AppCompatActivity implements GestureDetector.OnGestureListener {

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        gestureDetector = new GestureDetector(SCOSEntry.this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d("Touch", "onDown:检测 ");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
        float x = e1.getX() - e2.getX();
        if (x > 50) {
            Log.d("Infosssss", "onFling: 滑动检测");
            Intent intent = new Intent("scos.intent.action.SCOSMAIN");
            intent.addCategory("scos.intent.category.SCOSLAUNCHER");
            intent.putExtra("info", "FromEntry");
            SCOSEntry.this.startActivity(intent);
            return true;
        }
        return false;
    }
}
