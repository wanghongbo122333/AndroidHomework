package es.source.code.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.adapter.FoodAdapter;
import es.source.code.model.Food;
import es.source.code.model.OrderItem;

import static android.content.ContentValues.TAG;


/**
 * Created by WangHongbo on 2018/10/11.
 */

public class CuisineFragment extends Fragment {
    private String mTitle;//标签名字
    private ArrayList<Food> foodList;//该标签下对应的菜
    private View view;
    private FoodAdapter adapter;
    private  Context mContext;
    private  List<OrderItem> userOrder;//存储用户点的菜


    public CuisineFragment() {//必须含有的空构造方法
    }
    /**
     * 构造方法中就含有菜单的标题和菜项
     * @param title
     * @param foodList
     */
    @SuppressLint({"NewApi", "ValidFragment"})
    public CuisineFragment(String title, ArrayList foodList) {
        this.mTitle = title;
        this.foodList = foodList;
        this.userOrder=new ArrayList<>();
    }

    public String getTitle() {
        return mTitle;
    }

    public List<OrderItem> getUserOrder() {
        return userOrder;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view!=null){

        }
        Log.d(TAG, this.getTitle()+"onCreateView:执行 " + this.foodList.get(0));
        view = inflater.inflate(R.layout.list_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, this.getTitle()+"onActivityCreated: 执行");
        super.onActivityCreated(savedInstanceState);
        List<Food> list = this.foodList;
        adapter = new FoodAdapter(getActivity(), R.layout.food_item, list);
        ListView listView =  view.findViewById(R.id.listview);
        LinearLayout linearLayout =  view.findViewById(R.id.submit_bottom);
        LinearLayout linearLayout_1 =  view.findViewById(R.id.pay_bottom);
        linearLayout.setVisibility(View.GONE);
        linearLayout_1.setVisibility(View.GONE);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, this.getTitle()+"onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, this.getTitle()+"onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, this.getTitle()+"onPause: ");
        //可以获取到adpter中的用户点的菜
        this.userOrder.addAll(adapter.getOrderList());
        this.userOrder=adapter.getOrderList();
        for (int i = 0;i<userOrder.size();i++){
            Log.d(TAG, "停止了打印菜单: "+userOrder.get(i).getFood().getName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, this.getTitle()+"onStop: ");



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, this.getTitle()+"onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, this.getTitle()+"onDestroy: ");

    }


}
