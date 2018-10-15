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

import static android.content.ContentValues.TAG;


/**
 * Created by WangHongbo on 2018/10/11.
 */

public class CuisineFragment extends Fragment {
    private Context mContext;
    private TextView textView;
    private String mTitle;//标签名字
    private ArrayList<Food> foodList;//该标签下对应的菜
    private View view;

    public CuisineFragment() {//必须含有的空构造方法
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }


    @SuppressLint({"NewApi", "ValidFragment"})
    public CuisineFragment(String title, ArrayList foodList) {
        this.mTitle = title;
        this.foodList = foodList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView:执行 " + this.foodList.get(0).getName());
        view = inflater.inflate(R.layout.list_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: 执行");
        super.onActivityCreated(savedInstanceState);
        List<Food> list = this.foodList;
        FoodAdapter adapter = new FoodAdapter(getActivity(), R.layout.food_item, list);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bottom);
        LinearLayout linearLayout_1 = (LinearLayout) view.findViewById(R.id.bottom_1);
        linearLayout.setVisibility(View.GONE);
        linearLayout_1.setVisibility(View.GONE);
        listView.setAdapter(adapter);
//        textView.setText(content);
    }
}
