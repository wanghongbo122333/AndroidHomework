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
import android.widget.Toast;

public class SCOSEntry extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SCOSEntry.this,"You click the button", Toast.LENGTH_LONG);
                Log.d("ESCOSnrty", "onClick:btn ");
                Intent intent = new Intent(SCOSEntry.this,MainScreen.class);
                startActivity(intent);
                Log.d("ESCOSnrty","onClick: intent");
            }

        });
    }




}
