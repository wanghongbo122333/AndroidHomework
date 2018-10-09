package es.source.code.activity;


import android.content.Intent;
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

public class SCOSEntry extends AppCompatActivity implements View.OnTouchListener {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

       /* RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relative);
        relativeLayout.setOnTouchListener(this);
        relativeLayout.setLongClickable(true);
        */
        gestureDetector = new GestureDetector(SCOSEntry.this, onGestureListener);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SCOSEntry.this, "You click the button", Toast.LENGTH_LONG).show();
                Log.d("ESCOSnrty", "onClick:btn ");
                //隐式调用
                Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                intent.putExtra("info","FromEntry");
                startActivity(intent);
                Log.d("ESCOSnrty", "onClick: intent");
            }

        });
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            if (x < 0) {
                Intent intent = new Intent();
                intent.setClass(SCOSEntry.this, MainScreen.class);
                Bundle bundle = new Bundle();
                bundle.putString("info", "FromEntry");
                intent.putExtras(bundle);
                startActivity(intent);
            }
            return true;
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


}
