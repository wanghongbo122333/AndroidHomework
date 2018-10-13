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
import es.source.code.adapter.AlreadyOrderConf;
import es.source.code.adapter.AlreadyOrderConfAdapter;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/11.
 */

/**
 * 已经点了并且已经下单的菜
 */
public class OptionFragment extends Fragment {
    Context mContext;
    TextView textView;
    User user;

    public OptionFragment() {

    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public OptionFragment(User user) {
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
        View view = inflater.inflate(R.layout.list_view, container, false);
        //使用一个Arrraylist 来存储已经下单的菜品
        List<AlreadyOrderConf> list = new ArrayList<>();
        //已经下单的菜品还额外设置了一个类，这里初始化得到实例
        AlreadyOrderConf tex1 = new AlreadyOrderConf("红烧鸡", "21元", "1份", "备注");
        list.add(tex1);
        AlreadyOrderConf tex2 = new AlreadyOrderConf("大闸蟹", "35元", "1份", "备注");
        list.add(tex2);

        AlreadyOrderConfAdapter adapter = new AlreadyOrderConfAdapter(getActivity(), R.layout.already_order_conf_item, list);
        ListView listView = (ListView) view.findViewById(R.id.listview);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bottom);
        LinearLayout linearLayout_1 = (LinearLayout) view.findViewById(R.id.bottom_1);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout_1.setVisibility(View.GONE);

        Button button = (Button) view.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null && user.getOldUser()) {
                    Toast.makeText(getContext(), "您好，老顾客，本次你可享受7折优惠", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setAdapter(adapter);
//
//        ConfAdapter adapter = new ConfAdapter(getActivity(), R.layout.conf_item, list);
//        ListView listView = (ListView) view.findViewById(R.id.listview);
//        listView.setAdapter(adapter);
//        textView = new TextView(mContext);
//        textView.setGravity(Gravity.CENTER);
//        textView.setText("");
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(25);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        textView.setText(content);
    }
}

