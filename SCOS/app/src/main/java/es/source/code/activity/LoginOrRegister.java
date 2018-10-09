package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ProgressBar;

/**
 * Created by Wanghongbo on 2018/10/7.
 */

public class LoginOrRegister extends AppCompatActivity {
    private Button logBtn, backBtn;
    private EditText nameInput, pwdInput;
    private ProgressBar progressBar;
    private static final String TAG = "LoginOrRegister";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);
        logBtn = (Button) findViewById(R.id.logBtn);
        backBtn = (Button) findViewById(R.id.backBtn);
        nameInput = (EditText) findViewById(R.id.nameInput);
        pwdInput = (EditText) findViewById(R.id.pwdInput);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);//隐藏progressbar
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "onClick: login");
                myThread myThread = new myThread();
                myThread.start();
            }
        });
        //点击back按钮返回
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: back");
                Log.d(TAG, "onClick: login");
                Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                intent.putExtra("info", "Return");
                startActivity(intent);
            }
        });
    }


    public class myThread extends Thread {
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressBar.setProgress(msg.what);
            }
        };

        public void run() {
            super.run();
            int index = 0;
            while (index < 200) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(index);
                index++;
                if (index == 200) {
                    Log.d(TAG, "run:  login finish ");
                    Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                    intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                    intent.putExtra("info", "LoginSuccess");
                    startActivity(intent);
                }
            }
        }
    }
}


