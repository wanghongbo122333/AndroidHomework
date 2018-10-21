package es.source.code.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import es.source.code.activity.R;
import es.source.code.adapter.AlreadyOrderFoodAdapter;
import es.source.code.model.MyApplication;
import es.source.code.model.User;

/**
 * Created by WangHongbo on 2018/10/11.
 */

/**
 * 已经点了并且已经下单的菜
 */
public class BillFragment extends Fragment {
    private static final String TAG = "BillFragment";

    Context mContext;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_view, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initView(View view) {
        Log.d("BillFragment", "initView: ");
        AlreadyOrderFoodAdapter adapter = new AlreadyOrderFoodAdapter(getActivity(), R.layout.already_order_conf_item, MyApplication.billOrder);
        ListView listView = view.findViewById(R.id.listview);
        LinearLayout payL = view.findViewById(R.id.pay_bottom);
        LinearLayout submitL = view.findViewById(R.id.submit_bottom);
        payL.setVisibility(View.VISIBLE);
        submitL.setVisibility(View.GONE);
        Button button = view.findViewById(R.id.pay_btn);
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
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            initView(view);
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }
}

