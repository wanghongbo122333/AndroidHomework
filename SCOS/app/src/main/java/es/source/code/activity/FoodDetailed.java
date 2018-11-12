package es.source.code.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.source.code.fragment.DetailedinfoFragment;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodDetailed extends AppCompatActivity {

    ViewPager viewPager;
    List<DetailedinfoFragment> fragmentList = new ArrayList<DetailedinfoFragment>();
    FoodDetailed.ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detailed);
        viewPager = findViewById(R.id.food_detail);

        fragmentList.add(new DetailedinfoFragment());
        fragmentList.add(new DetailedinfoFragment());
        fragmentList.add(new DetailedinfoFragment());

        adapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    class ViewPageAdapter extends FragmentPagerAdapter {
        ViewPageAdapter(FragmentManager fm) {
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
    }
}
