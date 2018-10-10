package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Wanghongbo on 2018/10/7.
 */

public class LoginOrRegister extends AppCompatActivity {
    private Button logBtn, backBtn;
    private EditText nameInput, pwdInput;
    private ProgressBar progressBar;
    private final String Reg = "[A-Za-z0-9]+";//只包含数字和字母
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
            public void onClick(View view) {//点击登录按钮
                String name = nameInput.getText().toString();
                String pwd = pwdInput.getText().toString();
                Pattern pattern = Pattern.compile(Reg);
                Matcher matcher_name = pattern.matcher(name);
                Matcher matcher_pws = pattern.matcher(pwd);
                if (!matcher_name.matches())nameInput.setError("输入内容不符合规则");
                if (!matcher_pws.matches())pwdInput.setError("输入内容不符合规则");
                if (matcher_name.matches()&&matcher_pws.matches()){
                    progressBar.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onClick: login");
                    myThread myThread = new myThread();
                    myThread.start();
                }
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
              public void run() {
            super.run();
                try {
                    Thread.sleep(2000);
                    Log.d(TAG, "run:  login finish ");
                    Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                    intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                    intent.putExtra("info","LoginSuccess");
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}


