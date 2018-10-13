package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.model.User;

/**
 * Created by Wangongbo oHn 2018/10/7.
 */

public class MainScreen extends AppCompatActivity {
    private static final String TAG = "MainScreen";
//    private static final String TAG = "MainScreen";
//    private ImageButton logbtn,ordbtn,helpbtn,lookbtn;
//    private TextView logtv,looktv,ordtv,helptv;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.mainscreen);
//        Intent intent =getIntent();
//        String data = intent.getStringExtra("info");
//        Log.d(TAG, "onCreate: get info:"+data);
//
//        if("FromEntry".equals(data)){//FromEntry表示从欢迎界面过来，隐藏点菜和查看订单
//            setContentView(R.layout.mainscreen);
//            initial();
//            ordtv.setVisibility(View.GONE);
//            ordbtn.setVisibility(View.GONE);
//            looktv.setVisibility(View.GONE);
//            lookbtn.setVisibility(View.GONE);
//        }
//        else if ("LoginSuccess".equals(data)){//LoginSuccess表示登录成功跳转过来，展示所有按钮
//            setContentView(R.layout.mainscreen);
//            initial();
//        }
//        else if ("Return".equals(data)){//Return表示从登录界面的返回按钮跳转来，隐藏部分导航栏按钮
//            setContentView(R.layout.mainscreen);
//            initial();
//            ordtv.setVisibility(View.GONE);
//            ordbtn.setVisibility(View.GONE);
//            looktv.setVisibility(View.GONE);
//            lookbtn.setVisibility(View.GONE);
//        }
//        else{
//            Log.d(TAG, "onCreate: fail");
//        }
//    }
//    private void initial(){
//        helpbtn = (ImageButton) findViewById(R.id.helpbtn);
//        ordbtn = (ImageButton) findViewById(R.id.ordbtn);
//        logbtn = (ImageButton) findViewById(R.id.logbtn);
//        lookbtn = (ImageButton) findViewById(R.id.lookbtn);
//
//        helptv = (TextView)findViewById(R.id.helptv);
//        looktv = (TextView)findViewById(R.id.looktv);
//        ordtv = (TextView)findViewById(R.id.ordtv);
//        logtv = (TextView)findViewById(R.id.logtv);
//        logbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainScreen.this, LoginOrRegister.class);
//                startActivity(intent);
//            }
//        });
//    }

    private SimpleAdapter adapter;
    private GridView gridView;
    private List<Map<String, Object>> data_list;

    User user = null;
    private LinearLayout tab_order, tab_check, tab_register, tab_help;

    private int[] icon = {R.drawable.login, R.drawable.help, R.drawable.order, R.drawable.lookorder};
    private String[] iconName = {"注册/登录", "系统帮助", "点菜", "查看订单"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        setContentView(R.layout.mainscreen);
        //Toast.makeText(getApplicationContext(), "" + info, Toast.LENGTH_SHORT).show();
        gridView = (GridView) findViewById(R.id.gridview);

        String[] from = {"image", "name"};
        int[] to = {R.id.image, R.id.gridname};
        data_list = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(this, data_list, R.layout.main_item, from, to);
        //配置适配器
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 2: {//点菜
                        Log.d(TAG, "onItemClick:case 2 点菜");
                        Intent intent = new Intent(MainScreen.this, FoodView.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user",user);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                    case 3: {//查看已经点的菜
                        Log.i(TAG, "onItemClick: case 3 查看点菜");
                        Intent intent = new Intent(MainScreen.this, FoodOrderView.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user",user);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                    case 0: {//登录注册
                        Log.i(TAG, "onItemClick: case 0 登录注册");
                        Intent intent = new Intent(MainScreen.this, LoginOrRegister.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {//帮助
                        Log.i(TAG, "onItemClick: case 1  帮助");
//                        Intent intent = new Intent(MainScreen.this, FoodDetailed.class);
//                        startActivity(intent);
                        break;
                    }
                }
            }
        });

        if ("FromEntry".equals(info)) {
            showItems(2);
        }
        if ("LoginSuccess".equals(info)) {
            showItems(4);
            //user = (User) bundle.getSerializable("user");
        }
        if ("RegisterSuccess".equals(info)) {
            showItems(4);

            Toast.makeText(getApplicationContext(), "欢迎您成为SCOS新用户", Toast.LENGTH_SHORT).show();
            //  user = (User) bundle.getSerializable("user");
            //Toast.makeText(getApplicationContext(),user.getUserName(),Toast.LENGTH_SHORT).show();
        }
        if ("Return".equals(info)) {
            showItems(2);
        }
    }

    public void showItems(int i) {//要展示的导航栏的数量
        for (int j = 0; j < i; j++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[j]);
            map.put("name", iconName[j]);
            this.data_list.add(map);
        }
    }
}
