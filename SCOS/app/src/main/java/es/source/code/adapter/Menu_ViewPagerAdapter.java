package es.source.code.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.source.code.fragment.CuisineFragment;
import es.source.code.model.OrderItem;

/**
 * Created by WangHongbo on 2018/10/17.
 */

public class Menu_ViewPagerAdapter extends FragmentPagerAdapter {
    private List<CuisineFragment> fragmentList;//含有一个fragmentList
//    private  List<OrderItem> userOrder;//点的所有的菜，包括所有的类型的菜
    public Menu_ViewPagerAdapter(FragmentManager fm, List<CuisineFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
//        this.userOrder=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    public List<OrderItem> getUserOrder(){
        List<OrderItem> allItem =new ArrayList<>();
        for(int i=0;i<fragmentList.size();i++) {
            allItem.addAll(fragmentList.get(i).getUserOrder());
        }
        return  allItem;

    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //注释掉父类的自动destroyView方法，不再自动销毁布局
        //super.destroyItem(container, position, object);
    }
}
