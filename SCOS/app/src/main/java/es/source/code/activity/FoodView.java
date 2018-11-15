package es.source.code.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.adapter.Menu_ViewPagerAdapter;
import es.source.code.fragment.CuisineFragment;
import es.source.code.model.Food;
import es.source.code.model.User;
import es.source.code.service.ServerObserverService;
import es.source.code.service.UpdateService;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodView extends AppCompatActivity {
    private static final String TAG = "FoodView";
    List<CuisineFragment> fragmentList = new ArrayList<>();
    ArrayList<Food> coldFood = new ArrayList<>();
    ArrayList<Food> hotDishes = new ArrayList<>();
    ArrayList<Food> seaFood = new ArrayList<>();
    ArrayList<Food> drinks = new ArrayList<>();
    TabLayout tabLayout;
    private User currentUser = null;

    Messenger mServerMessenger;
    ServiceConnection sc = new MyServiceConnection();
    Messenger mClientMessenger = new Messenger(new ClientHandler());//mClientMessenger指向ClientHandler

    private class ClientHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                Toast.makeText(FoodView.this, "" + msg.what, Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 10) {
                Bundle bundle = msg.getData();
                final int inventory = bundle.getInt("inventory");
                final String foodName = bundle.getString("foodname");
                final int price = bundle.getInt("price");
                final String type = bundle.getString("type");
                Toast.makeText(FoodView.this, "新增" + foodName + " " + price + "元", Toast.LENGTH_SHORT).show();

                //展示在菜单中
                addFood(new Food(foodName, price, inventory), type);
                //通知栏显示
                Intent intent2 = new Intent(FoodView.this, UpdateService.class);
                intent2.putExtra("info", bundle);
                startService(intent2);
            }
        }
    }

    private class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServerMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);

        //绑定服务
        Intent serviceIntent = new Intent();
        serviceIntent.setClass(FoodView.this, ServerObserverService.class);
        bindService(serviceIntent, sc, BIND_AUTO_CREATE);

        ViewPager viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        //获取当前的用户信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            this.currentUser = (User) bundle.getSerializable("user");
        } else {
            Log.d(TAG, "onCreate:没有获取到bundle ");
        }


        //一般来说能进入该页面，说明已经登录或者注册了，已经有user信息
        if (currentUser != null)
            Toast.makeText(this, "欢迎光临！亲爱的" + currentUser.getUserName(), Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
        }

        //初始化菜单
        initialMenu();
        //设置fragment
        Menu_ViewPagerAdapter menu_viewPagerAdapter = new Menu_ViewPagerAdapter(getSupportFragmentManager(), this.fragmentList);

        viewPager.setAdapter(menu_viewPagerAdapter);
        //设置tablayout
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_menu, menu);
        return true;
    }

    @Override
    //Actionbar
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle;
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_already://查看已经点的菜
                bundle = new Bundle();
                bundle.putString("action", "action_already");
                bundle.putSerializable("user", currentUser);

                intent = new Intent(FoodView.this, FoodOrderView.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.action_check://已下单菜
                bundle = new Bundle();
                bundle.putString("action", "action_check");
                bundle.putSerializable("user", currentUser);
                intent = new Intent(FoodView.this, FoodOrderView.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.action_help://呼叫服务
                Toast.makeText(this, "action_help", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(FoodView.this, SCOSHelper.class);
                FoodView.this.startActivity(intent1);
                return true;
            case R.id.action_update://更新数据

                //启动Service服务
                String title = item.getTitle().toString();//获取当前的标题
                Message message = Message.obtain();
                if (title.equals("启动实时更新")) {
                    item.setTitle("停止实时更新");
                    message.what = 1;

                }
                if (title.equals("停止实时更新")) {
                    item.setTitle("启动实时更新");
                    message.what = 0;
                }
                message.replyTo = mClientMessenger;
                try {
                    mServerMessenger.send(message);//发送消息
                    Log.d(TAG, "onOptionsItemSelected: 客户端向服务器发送了消息为what=" + message.what);

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return true;
    }

    /**
     * 初始化菜单
     */
    public void initialMenu() {

        coldFood.add(new Food("凉皮1", 25, 13));
        coldFood.add(new Food("凉皮2", 25, 13));
        coldFood.add(new Food("凉皮4", 25, 13));
        coldFood.add(new Food("凉皮5", 25, 13));

        hotDishes.add(new Food("大盘鸡1", 48, 13));
        hotDishes.add(new Food("大盘鸡2", 48, 13));
        hotDishes.add(new Food("大盘鸡3", 48, 13));
        hotDishes.add(new Food("大盘鸡4", 48, 13));


        seaFood.add(new Food("大闸蟹1", 256, 13));
        seaFood.add(new Food("大闸蟹2", 256, 13));
        seaFood.add(new Food("大闸蟹3", 256, 13));
        seaFood.add(new Food("大闸蟹4", 256, 13));

        drinks.add(new Food("茅台1", 2562, 13));
        drinks.add(new Food("茅台1", 2562, 13));
        drinks.add(new Food("茅台1", 2562, 13));


        fragmentList.add(new CuisineFragment("冷菜", coldFood));
        fragmentList.add(new CuisineFragment("热菜", hotDishes));
        fragmentList.add(new CuisineFragment("海鲜", seaFood));
        fragmentList.add(new CuisineFragment("酒水", drinks));
    }

    public void addFood(Food f, String type) {
        switch (type) {
            case "冷菜":
                coldFood.add(f);
                break;
            case "热菜":
                hotDishes.add(f);
                break;
            case "海鲜":
                seaFood.add(f);
                break;
            case "酒水":
                drinks.add(f);
                break;
        }

    }
}