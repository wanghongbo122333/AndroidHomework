package es.source.code.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.adapter.AlreadyOrderFood;
import es.source.code.adapter.NoOrderFoodAdapter;

/**
 * Created by WangHongbo on 2018/10/11.
 */

/**
 * 已经点了，但是没有下单的菜
 */
public class OrderFragment extends Fragment {
    Context mContext;
    TextView textView;
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

        View view = inflater.inflate(R.layout.list_view,container,false);

        List<AlreadyOrderFood> list = new ArrayList<>();
        AlreadyOrderFood tex1 = new AlreadyOrderFood("红烧鸡", "21元", "1份", "备注");
        list.add(tex1);
        AlreadyOrderFood tex2 = new AlreadyOrderFood("白烧鸡", "23元", "1份", "备注");
        list.add(tex2);
        AlreadyOrderFood tex3 = new AlreadyOrderFood("叫花鸡", "24元", "1份", "备注");
        list.add(tex3);
        AlreadyOrderFood tex4 = new AlreadyOrderFood("白斩鸡", "21元", "1份", "备注");
        list.add(tex4);
        AlreadyOrderFood tex5 = new AlreadyOrderFood("清蒸鸡", "24元", "1份", "备注");
        list.add(tex5);


        NoOrderFoodAdapter adapter = new NoOrderFoodAdapter(getActivity(), R.layout.no_order_conf_item, list);
        ListView listView = (ListView) view.findViewById(R.id.listview);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.bottom);
        LinearLayout linearLayout_1 = (LinearLayout)view.findViewById(R.id.bottom_1);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout_1.setVisibility(View.VISIBLE);

        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        textView.setText(content);
    }
}

