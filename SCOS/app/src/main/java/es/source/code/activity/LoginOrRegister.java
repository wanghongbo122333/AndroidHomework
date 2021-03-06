package es.source.code.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.source.code.model.HttpUtilsHttpClient;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/7.
 */

public class LoginOrRegister extends AppCompatActivity {
    private EditText nameInput, pwdInput;
    private final String Reg = "[A-Za-z0-9]+";//只包含数字和字母
    private static final String TAG = "LoginOrRegister";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);
        Button logBtn = findViewById(R.id.logBtn);
        Button backBtn = findViewById(R.id.backBtn);
        Button registerBtn = findViewById(R.id.registerBtn);
        nameInput = findViewById(R.id.nameInput);
        pwdInput = findViewById(R.id.pwdInput);

        //读取SharedPreferences数据信息
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        //读取username，如果没有就用默认值代替
        final String userName = sharedPreferences.getString("userName", "");
        Log.d(TAG, "onCreate: 获取sharedPreferences" + userName);
        if (userName.equals("")) {
            logBtn.setVisibility(View.GONE);
        } else {
            registerBtn.setVisibility(View.GONE);
            nameInput.setText(userName);
        }
        //点击登录按钮
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String pwd = pwdInput.getText().toString();
                Pattern pattern = Pattern.compile(Reg);
                Matcher matcher_name = pattern.matcher(name);
                Matcher matcher_pws = pattern.matcher(pwd);
                if (!matcher_name.matches()) nameInput.setError("输入内容不符合规则");
                if (!matcher_pws.matches()) pwdInput.setError("输入内容不符合规则");
                if (matcher_name.matches() && matcher_pws.matches()) {
                    final ProgressDialog pd = new ProgressDialog(LoginOrRegister.this);
                    pd.setTitle("努力加载中~~~");//设置一个标题
                    pd.setMessage("请稍后……");//设置消息
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setCancelable(false);
                    pd.show();//展示对话框
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.cancel();//取消对话框
                            //新建一个user对象并传递
                            String name = nameInput.getText().toString();
                            String pwd = pwdInput.getText().toString();
                            User loginUser = new User(name, pwd, true);
                            Log.d(TAG, "onClick: login");
                            //服务器验证
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("HttpServer", "threadStart");
                                    String url = HttpUtilsHttpClient.BASE_URL + "/LoginValidator";
                                    Map<String, String> params = new HashMap<String, String>();
                                    String name = "whb";
                                    String password = "123456";
                                    params.put("name", name);
                                    params.put("password", password);
                                    //请求，返回json
                                    String result = HttpUtilsHttpClient.postRequest(url, params);
                                    Message msg = new Message();
                                    msg.what = 0x11;
                                    Bundle data = new Bundle();
                                    data.putString("result", result);
                                    msg.setData(data);
                                    handler.sendMessage(msg);
                                }

                                @SuppressLint("HandlerLeak")
                                Handler handler = new Handler() {
                                    @Override
                                    public void handleMessage(Message msg) {
                                        if (msg.what == 0x11) {
                                            Log.d("httpServer", "GetResponse");
                                            Bundle data = msg.getData();
                                            String key = data.getString("result");//得到json返回的json数据
                                            Log.d("httpServer", "getKey");
                                            try {
                                                JSONObject json = new JSONObject(key);
                                                Log.d("httpServer", "newJson");
                                                int result = (Integer) json.get("result");
                                                Log.d("httpServer", "getResult");
                                                if (1 == result) {
                                                    Log.d("login", "loginSuccess");
                                                    Toast.makeText(LoginOrRegister.this, "登录成功", Toast.LENGTH_LONG).show();
                                                } else if (0 == result) {
                                                    Log.d("login", "loginFailed");
                                                    Toast.makeText(LoginOrRegister.this, "登录失败", Toast.LENGTH_LONG).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                };
                            }).start();
                            //下面这里是隐式使用intent
                            Intent intent = new Intent("scos.intent.action.SCOSMAIN");//set Action
                            intent.addCategory("scos.intent.category.SCOSLAUNCHER");//add category
                            intent.putExtra("info", "LoginSuccess");//putExtra使用键值对传数据
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("user", loginUser);//将该注册的用户对象存入bundle中
                            intent.putExtras(bundle);//把bundle放进intent中
                            startActivity(intent);

                            //写入SharedPreferences数据
                            SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                            editor.putString("userName", name);
                            editor.putInt("loginState", 1);
                            editor.apply();
                        }
                    }, 2000);


                    Log.d(TAG, "run:  登录成功 ");
                }
            }
        });
        //点击注册按钮
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String pwd = pwdInput.getText().toString();
                Pattern pattern = Pattern.compile(Reg);
                Matcher matcher_name = pattern.matcher(name);
                Matcher matcher_pws = pattern.matcher(pwd);
                if (!matcher_name.matches()) nameInput.setError("输入内容不符合规则");
                if (!matcher_pws.matches()) pwdInput.setError("输入内容不符合规则");
                if (matcher_name.matches() && matcher_pws.matches()) {
                    final ProgressDialog pd = new ProgressDialog(LoginOrRegister.this);
                    pd.setTitle("努力加载中~~~");//设置一个标题
                    pd.setMessage("请稍后……");//设置消息
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setCancelable(false);
                    pd.show();//展示对话框
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.cancel();
                            String name = nameInput.getText().toString();
                            String pwd = pwdInput.getText().toString();
                            User registerUser = new User(name, pwd, false);
                            Log.d(TAG, "onClick: register");
                            //下面这里是隐式使用intent
                            Intent intent = new Intent("scos.intent.action.SCOSMAIN");//set Action
                            intent.addCategory("scos.intent.category.SCOSLAUNCHER");//add category
                            intent.putExtra("info", "RegisterSuccess");//putExtra使用键值对传数据
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("user", registerUser);//将该注册的用户对象存入bundle中
                            intent.putExtras(bundle);//把bundle放进intent中
                            startActivity(intent);
                            //写入SharedPreferences数据
                            SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                            editor.putString("userName", name);
                            editor.putInt("loginState", 1);
                            editor.apply();
                            Log.d(TAG, "run:  注册成功 ");
                        }
                    }, 2000);
                }
            }
        });
        //点击back按钮返回
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: back");
                if (userName.equals("")) {
                    //写入SharedPreferences数据
                    SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                    editor.putInt("loginState", 0);
                    editor.apply();
                }
                Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                intent.putExtra("info", "Return");
                startActivity(intent);
            }
        });
    }
}


