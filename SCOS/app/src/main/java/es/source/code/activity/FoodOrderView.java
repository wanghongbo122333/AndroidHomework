package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.fragment.OptionFragment;
import es.source.code.fragment.OptionNotFragment;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodOrderView extends AppCompatActivity {
    ViewPager viewpage;
    List fragmentList = new ArrayList<>();
    List list = new ArrayList();
    ViewPageAdapter adapter;
    TabLayout tabLayout;

    User user = null;
    String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_view);
        viewpage = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        action = bundle.getString("action");
        if(action == null){
            action = "action_already";
        }

        user = (User) bundle.getSerializable("user");
        if(user != null)
            Toast.makeText(this, ""+user.getUserName(), Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "未注册，请注册！", Toast.LENGTH_SHORT).show();
        }
//        adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter = new ViewPageAdapter(getSupportFragmentManager(), user);

        viewpage.setAdapter(adapter);
        if(action.equals("action_already")){
            viewpage.setCurrentItem(0);
        }if(action.equals("action_check")){
            viewpage.setCurrentItem(1);
        }
        tabLayout.setupWithViewPager(viewpage);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//固定
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//滑动
    }

    class ViewPageAdapter extends FragmentPagerAdapter {

        private String[] mTitles = new String[]{"未下单菜", "已下单菜"};
        private User user;
        public ViewPageAdapter(FragmentManager fm, User user) {
            super(fm);
            this.user = user;
        }
        @Override
        public Fragment getItem(int position) {
//            return (Fragment)fragmentList.get(position);
            if (position == 0) {
                return new OptionNotFragment();
//                return new OptionNotFragment();
            } else if (position == 1) {
                return new OptionFragment(user);
//                return new OptionFragment();
            }
            return new OptionFragment();
        }
        @Override
        public int getCount() {
            return mTitles.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}