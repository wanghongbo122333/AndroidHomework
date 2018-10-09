package es.source.code.activity;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class SCOSEntry extends AppCompatActivity implements GestureDetector.OnGestureListener {

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

       /* RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relative);
        relativeLayout.setOnTouchListener(this);
        relativeLayout.setLongClickable(true);
        */
        gestureDetector = new GestureDetector(SCOSEntry.this, this);
//        Button btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(SCOSEntry.this, "You click the button", Toast.LENGTH_LONG).show();
//                Log.d("ESCOSnrty", "onClick:btn ");
//                //隐式调用
//                Intent intent = new Intent("scos.intent.action.SCOSMAIN");
//                intent.addCategory("scos.intent.category.SCOSLAUNCHER");
//                intent.putExtra("info","FromEntry");
//                startActivity(intent);
//                Log.d("ESCOSnrty", "onClick: intent");
//            }
//
//        });
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
            intent.putExtra("info","FromEntry");
            SCOSEntry.this.startActivity(intent);
            return  true;
        }
        return false;
    }
}
