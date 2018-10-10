package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Wanghongbo on 2018/10/7.
 */

public class MainScreen extends AppCompatActivity {
    private static final String TAG = "MainScreen";
    private ImageButton logbtn,ordbtn,helpbtn,lookbtn;
    private TextView logtv,looktv,ordtv,helptv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.mainscreen);
        Intent intent =getIntent();
        String data = intent.getStringExtra("info");
        Log.d(TAG, "onCreate: get info:"+data);

        if("FromEntry".equals(data)){
            setContentView(R.layout.mainscreen);
            initial();
            ordtv.setVisibility(View.GONE);
            ordbtn.setVisibility(View.GONE);
            looktv.setVisibility(View.GONE);
            lookbtn.setVisibility(View.GONE);
        }
        else if ("LoginSuccess".equals(data)){
            setContentView(R.layout.mainscreen);
            initial();
            /*ordtv.setVisibility(View.VISIBLE);
            ordbtn.setVisibility(View.VISIBLE);
            looktv.setVisibility(View.VISIBLE);
            lookbtn.setVisibility(View.VISIBLE);*/
        }
        else if ("RegisterSuccess".equals(data)){
            setContentView(R.layout.mainscreen);
            initial();
        }
        else if ("Return".equals(data)){
            setContentView(R.layout.mainscreen);
            initial();
        }
        else{
            Log.d(TAG, "onCreate: fail");
        }
    }
    private void initial(){
        helpbtn = (ImageButton) findViewById(R.id.helpbtn);
        ordbtn = (ImageButton) findViewById(R.id.ordbtn);
        logbtn = (ImageButton) findViewById(R.id.logbtn);
        lookbtn = (ImageButton) findViewById(R.id.lookbtn);

        helptv = (TextView)findViewById(R.id.helptv);
        looktv = (TextView)findViewById(R.id.looktv);
        ordtv = (TextView)findViewById(R.id.ordtv);
        logtv = (TextView)findViewById(R.id.logtv);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, LoginOrRegister.class);
                startActivity(intent);
            }
        });
    }
    //can you see me ?
}
