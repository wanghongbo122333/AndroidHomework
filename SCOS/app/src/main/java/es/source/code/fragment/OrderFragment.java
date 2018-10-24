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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.adapter.NoOrderFoodAdapter;
import es.source.code.model.MyApplication;
import es.source.code.model.OrderItem;
import es.source.code.model.User;

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
    private List<OrderItem> userOrder = new ArrayList<>();//用户点该类型的菜
    private User currentUser;

    @SuppressLint("ValidFragment")
    public OrderFragment(User currentUser) {
        this.currentUser = currentUser;
    }

    public OrderFragment() {//无参构造函数
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
        //适配器配置
        NoOrderFoodAdapter adapter = new NoOrderFoodAdapter(getActivity(), R.layout.no_order_conf_item, MyApplication.userOrder);
        ListView listView = view.findViewById(R.id.listview);
        LinearLayout payL = view.findViewById(R.id.pay_bottom);
        LinearLayout submitL = view.findViewById(R.id.submit_bottom);
        TextView amount = view.findViewById(R.id.total_1);
        amount.setText("共"+String.valueOf(MyApplication.userOrder.size())+"份");
        TextView totalPrice = view.findViewById(R.id.totalprice_1);
        totalPrice.setText("总价"+String.valueOf(MyApplication.getBill(MyApplication.userOrder))+"元");
        payL.setVisibility(View.GONE);
        submitL.setVisibility(View.VISIBLE);
        Button btn = view.findViewById(R.id.submit_order_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交订单，生成账单
                Toast.makeText(getContext(),"您已成功下单！",Toast.LENGTH_SHORT).show();
                MyApplication.billOrder.addAll(MyApplication.userOrder);
                MyApplication.userOrder.clear();//清空当前userorder
                MyApplication.printItems(MyApplication.billOrder);
            }
        });
        listView.setAdapter(adapter);
    }


}

