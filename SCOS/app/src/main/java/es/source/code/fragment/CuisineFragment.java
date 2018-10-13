package es.source.code.fragment;

import android.annotation.SuppressLint;
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
import es.source.code.adapter.Conf;
import es.source.code.adapter.ConfAdapter;


/**
 * Created by WangHongbo on 2018/10/11.
 */

public class CuisineFragment extends Fragment {
    Context mContext;
    TextView textView;
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public CuisineFragment() {

    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public CuisineFragment(String title) {
        this.title = title;
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
//        Toast.makeText(getContext(), ""+getTitle(), Toast.LENGTH_SHORT).show();
        List<Conf> list = new ArrayList<>();
        Conf tex1, tex2, tex3, tex4, tex5, tex6;
        switch (getTitle()) {
            case "冷菜":
                tex1 = new Conf("凉皮", "11元", "点菜");
                list.add(tex1);
                tex2 = new Conf("大凉皮", "13元", "点菜");
                list.add(tex2);
                tex3 = new Conf("小鸡炖蘑菇", "14元", "点菜");
                list.add(tex3);
                tex4 = new Conf("酸菜鱼", "11元", "点菜");
                list.add(tex4);
                tex5 = new Conf("酱香青瓜丝", "14元", "点菜");
                list.add(tex5);
                tex6 = new Conf("缤纷水晶卷", "12元", "点菜");
                list.add(tex6);
                break;
            case "热菜":
                tex1 = new Conf("酸菜鱼", "21元", "点菜");
                list.add(tex1);
                tex2 = new Conf("小鸡炖蘑菇", "23元", "点菜");
                list.add(tex2);
                tex3 = new Conf("叫花鸡", "24元", "点菜");
                list.add(tex3);
                tex4 = new Conf("宫保鸡丁", "21元", "点菜");
                list.add(tex4);
                tex5 = new Conf("清蒸鸡", "24元", "点菜");
                list.add(tex5);
                break;
            case "海鲜":
                tex1 = new Conf("皮皮虾", "23元", "点菜");
                list.add(tex1);
                tex2 = new Conf("阳澄湖大闸蟹", "3.3元", "点菜");
                list.add(tex2);
                tex3 = new Conf("鲸鱼", "34元", "点菜");
                list.add(tex3);
                tex4 = new Conf("海豚", "31元", "点菜");
                list.add(tex4);
                break;
            case "酒水":
                tex1 = new Conf("啤酒", "81元", "点菜");
                list.add(tex1);
                tex2 = new Conf("白酒", "83元", "点菜");
                list.add(tex2);
                tex3 = new Conf("红酒", "84元", "点菜");
                list.add(tex3);
                break;
        }


        ConfAdapter adapter = new ConfAdapter(getActivity(), R.layout.conf_item, list);
        ListView listView = (ListView) view.findViewById(R.id.listview);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bottom);
        LinearLayout linearLayout_1 = (LinearLayout) view.findViewById(R.id.bottom_1);
        linearLayout.setVisibility(View.GONE);
        linearLayout_1.setVisibility(View.GONE);

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
