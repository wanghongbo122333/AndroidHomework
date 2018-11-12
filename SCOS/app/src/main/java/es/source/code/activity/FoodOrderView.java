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

import java.util.List;

import es.source.code.fragment.BillFragment;
import es.source.code.fragment.OrderFragment;
import es.source.code.model.OrderItem;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodOrderView extends AppCompatActivity {
    private static final String TAG = "FoodOrderView";
    private User user = null;
    String action;
    private List<OrderItem> userOrder;//用户点的菜，需要传给OrderFragment
//    private List<OrderItem> billOrder;//已经出账的菜，传给BillFragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_view);
        //绑定视图组件
        ViewPager viewpager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        //获取数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        action = bundle.getString("action");
        //这里如果没有定义动作，默认设置成为action_already，去往已下单的菜
        if (action == null) action = "action_already";
        user = (User) bundle.getSerializable("user");//获取用户信息
        if (user != null)
            Toast.makeText(this, user.getUserName() + "欢迎光临", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "未登录，请登录！", Toast.LENGTH_SHORT).show();
        }
        //配置适配器
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), user);
        viewpager.setAdapter(adapter);

        if (action.equals("action_already")) {
            viewpager.setCurrentItem(0);
        }
        if (action.equals("action_check")) {
            viewpager.setCurrentItem(1);
        }
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//固定
    }

    class ViewPageAdapter extends FragmentPagerAdapter {

        private String[] mTitles = new String[]{"未下单菜", "已下单菜"};
        private User user;

        private ViewPageAdapter(FragmentManager fm, User user) {
            super(fm);
            this.user = user;
        }


        @Override
        public Fragment getItem(int position) {
            return position == 0 ? new OrderFragment(user) : new BillFragment(user);
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