package es.source.code.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.adapter.NoOrderFoodAdapter;
import es.source.code.model.Food;
import es.source.code.model.OrderItem;

import static android.content.ContentValues.TAG;

/**
 * Created by WangHongbo on 2018/10/11.
 */

/**
 * 已经点了，但是没有下单的菜
 */
public class OrderFragment extends Fragment {
    Context mContext;
    private View view;

    public OrderFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化订单信息
        List<OrderItem> list = initialOrder();
        //适配器配置
        NoOrderFoodAdapter adapter = new NoOrderFoodAdapter(getActivity(), R.layout.no_order_conf_item, list);
        ListView listView = view.findViewById(R.id.listview);
        LinearLayout payL = view.findViewById(R.id.pay_bottom);
        LinearLayout submitL = view.findViewById(R.id.submit_bottom);
        payL.setVisibility(View.GONE);
        submitL.setVisibility(View.VISIBLE);
        Button btn = view.findViewById(R.id.submit_order_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交订单，生成账单
                Log.d(TAG, "onClick: 提交订单");
            }
        });
        listView.setAdapter(adapter);
    }

    /*
    初始化订单信息，已经点了但是没出账的菜
     */
    public List<OrderItem> initialOrder() {

        List<OrderItem> list = new ArrayList<>();
        OrderItem tex1 = new OrderItem(new Food("白斩鸡", 153), 1, "备注");
        list.add(tex1);
        OrderItem tex2 = new OrderItem(new Food("红斩鸡", 153), 1, "备注");
        list.add(tex2);
        OrderItem tex3 = new OrderItem(new Food("黄斩鸡", 153), 1, "备注");
        list.add(tex3);
        OrderItem tex4 = new OrderItem(new Food("蓝斩鸡", 153), 1, "备注");
        list.add(tex4);
        return list;
    }

}

