package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.fragment.CuisineFragment;
import es.source.code.model.Food;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodView extends AppCompatActivity {
    private static final String TAG = "FoodView";
    List<CuisineFragment> fragmentList = new ArrayList<>();
    TabLayout tabLayout;
    private User currentUser = null;
//    private android.support.v4.app.FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
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
            Toast.makeText(this, "未注册", Toast.LENGTH_SHORT).show();
        }

        //初始化菜单
        initialMenu();
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

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
                return true;
        }
        return true;
    }

    /**
     * 初始化菜单
     */
    public void initialMenu() {

        ArrayList<Food> coodDishes = new ArrayList<>();
        ArrayList<Food> hotDishes = new ArrayList<>();
        ArrayList<Food> seaFood = new ArrayList<>();
        ArrayList<Food> drinks = new ArrayList<>();

        coodDishes.add(new Food("凉皮1", 25));
        coodDishes.add(new Food("凉皮2", 25));
        coodDishes.add(new Food("凉皮4", 25));
        coodDishes.add(new Food("凉皮5", 25));

        hotDishes.add(new Food("大盘鸡1", 48));
        hotDishes.add(new Food("大盘鸡2", 48));
        hotDishes.add(new Food("大盘鸡3", 48));
        hotDishes.add(new Food("大盘鸡4", 48));


        seaFood.add(new Food("大闸蟹1", 256));
        seaFood.add(new Food("大闸蟹2", 256));
        seaFood.add(new Food("大闸蟹3", 256));
        seaFood.add(new Food("大闸蟹4", 256));

        drinks.add(new Food("茅台1", 2562));
        drinks.add(new Food("茅台1", 2562));
        drinks.add(new Food("茅台1", 2562));


        fragmentList.add(new CuisineFragment("冷菜", coodDishes));
        fragmentList.add(new CuisineFragment("热菜", hotDishes));
        fragmentList.add(new CuisineFragment("海鲜", seaFood));
        fragmentList.add(new CuisineFragment("酒水", drinks));
    }

    class ViewPageAdapter extends FragmentPagerAdapter {
        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentList.get(position).getTitle();
        }
    }
}