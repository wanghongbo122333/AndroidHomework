package es.source.code.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import es.source.code.adapter.AlreadyOrderFoodAdapter;
import es.source.code.model.Food;
import es.source.code.model.OrderItem;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/11.
 */

/**
 * 已经点了并且已经下单的菜
 */
public class BillFragment extends Fragment {
    Context mContext;
    TextView textView;
    User user;
    private View view;

    public BillFragment() {

    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public BillFragment(User user) {
        this.user = user;
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
        List<OrderItem> list =initialBill();//初始化账单
        AlreadyOrderFoodAdapter adapter = new AlreadyOrderFoodAdapter(getActivity(), R.layout.already_order_conf_item, list);
        ListView listView = (ListView) view.findViewById(R.id.listview);

        LinearLayout payL = (LinearLayout) view.findViewById(R.id.pay_bottom);
        LinearLayout submitL = (LinearLayout) view.findViewById(R.id.submit_bottom);
        payL.setVisibility(View.VISIBLE);
        submitL.setVisibility(View.GONE);

        Button button = (Button) view.findViewById(R.id.pay_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null && user.getIsOldUser()) {
                    Toast.makeText(getContext(), "您好，老顾客，本次你可享受7折优惠", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "结账！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setAdapter(adapter);
//
//        FoodAdapter adapter = new FoodAdapter(getActivity(), R.layout.food_item, list);
//        ListView listView = (ListView) view.findViewById(R.id.listview);
//        listView.setAdapter(adapter);
//        textView = new TextView(mContext);
//        textView.setGravity(Gravity.CENTER);
//        textView.setText("");
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(25);
//        textView.setText(content);
    }

    public List<OrderItem> initialBill() {
        //使用一个Arrraylist 来存储已经下单的菜品
        List<OrderItem> list = new ArrayList<>();//初始化结账菜单
        //已经下单的菜品还额外设置了一个类，这里初始化得到实例
        OrderItem item1 = new OrderItem(new Food("凉皮", 25), 1, "备注");
        list.add(item1);
        OrderItem item2 = new OrderItem(new Food("凉皮", 25), 1, "备注");
        list.add(item2);
        OrderItem item3 = new OrderItem(new Food("凉皮", 25), 1, "备注");
        list.add(item3);
        return list;
    }
}

