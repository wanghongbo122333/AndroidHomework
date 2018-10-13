package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.fragment.CuisineFragment;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodView extends AppCompatActivity {
    ViewPager viewPager;
    List<CuisineFragment> fragmentList = new ArrayList<>();
    ViewPageAdapter adapter;
    TabLayout tabLayout;
    private User currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        //获取当前的用户信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            this.currentUser = (User) bundle.getSerializable("user");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //一般来说能进入该页面，说明已经登录或者注册了，已经有user信息
        if (currentUser != null)
            Toast.makeText(this, "欢迎光临！亲爱的" + currentUser.getUserName(), Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "未注册", Toast.LENGTH_SHORT).show();
        }

        fragmentList.add(new CuisineFragment("冷菜"));
        fragmentList.add(new CuisineFragment("热菜"));
        fragmentList.add(new CuisineFragment("海鲜"));
        fragmentList.add(new CuisineFragment("酒水"));

        adapter = new ViewPageAdapter(getSupportFragmentManager());
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