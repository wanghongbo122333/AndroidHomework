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
    ViewPager viewpage;
    List<CuisineFragment> fragmentList = new ArrayList<CuisineFragment>();
    ViewPageAdapter adapter;
    TabLayout tabLayout;

    User user = null;

    //action bar
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
            case R.id.action_already://
                bundle = new Bundle();
                bundle.putString("action", "action_already");
                bundle.putSerializable("user", user);

                intent = new Intent(FoodView.this, FoodOrderView.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.action_check:

                bundle = new Bundle();
                bundle.putString("action", "action_check");
                bundle.putSerializable("user", user);

                intent = new Intent(FoodView.this, FoodOrderView.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.action_help:
                Toast.makeText(this, "action_help", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
        viewpage = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.user = (User)bundle.getSerializable("user");
        if (user != null)
            Toast.makeText(this, "" + user.getUserName(), Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "未注册", Toast.LENGTH_SHORT).show();
        }

        fragmentList.add(new CuisineFragment("冷菜"));
        fragmentList.add(new CuisineFragment("热菜"));
        fragmentList.add(new CuisineFragment("海鲜"));
        fragmentList.add(new CuisineFragment("酒水"));

        adapter = new ViewPageAdapter(getSupportFragmentManager());
        viewpage.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpage);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
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