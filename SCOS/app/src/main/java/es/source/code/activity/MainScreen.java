package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.model.User;

/**
 * Created by WangHongbo oHn 2018/10/7.
 */

public class MainScreen extends AppCompatActivity {
    private static final String TAG = "MainScreen";
    private List<Map<String, Object>> data_list;
    private User currentUser = null;
    private int[] icon = {R.drawable.login, R.drawable.help, R.drawable.order, R.drawable.lookorder};
    private String[] iconName = {"注册/登录", "系统帮助", "点菜", "查看订单"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //请求窗口特征
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");//获取info信息
        //获取当前的用户信息
        this.currentUser = (User) intent.getSerializableExtra("user");
        GridView gridView = findViewById(R.id.gridview);
        String[] from = {"image", "name"};
        int[] to = {R.id.image, R.id.gridname};
        data_list = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, data_list, R.layout.main_item, from, to);
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
                        bundle.putSerializable("user", currentUser);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                    case 3: {//查看已经点的菜
                        Log.d(TAG, "onItemClick: case 3 查看点菜");
                        Intent intent = new Intent(MainScreen.this, FoodOrderView.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", currentUser);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    }
                    case 0: {//登录注册
                        Log.d(TAG, "onItemClick: case 0 登录注册");
                        Intent intent = new Intent(MainScreen.this, LoginOrRegister.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {//帮助
                        Log.d(TAG, "onItemClick: case 1  帮助");
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
