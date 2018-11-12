package es.source.code.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import es.source.code.activity.R;
import es.source.code.adapter.BillFoodAdapter;
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
    private int money;
    private int total_amount;
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

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void initView(final View view) {
        Log.d("BillFragment", "initView: ");
        BillFoodAdapter adapter = new BillFoodAdapter(mContext, R.layout.already_order_conf_item, MyApplication.billOrder);
        ListView listView = view.findViewById(R.id.listview);
        LinearLayout payL = view.findViewById(R.id.pay_bottom);
        LinearLayout submitL = view.findViewById(R.id.submit_bottom);
        TextView amount = view.findViewById(R.id.total);
        this.total_amount = MyApplication.billOrder.size();
        this.money = MyApplication.getBill(MyApplication.billOrder);
        amount.setText("共" + String.format("%d", this.total_amount) + "份");
        TextView totalPrice = view.findViewById(R.id.totalprice);
        totalPrice.setText("总价" + String.format("%d", this.money) + "元");
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
                TextView tv = view.findViewById(R.id.progresstext);
                ProgressBar pb = view.findViewById(R.id.myprogressBar);
                MyAsyncTask myBarAsyncTask=new MyAsyncTask(tv, pb);
                myBarAsyncTask.execute();

            }
        });
        listView.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initView(view);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class MyAsyncTask extends AsyncTask {
        private TextView textView;
        private ProgressBar progressBar;

        MyAsyncTask(TextView textView, ProgressBar progressBar) {
            super();
            this.textView = textView;
            this.progressBar = progressBar;
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }



        @Override
        protected Object doInBackground(Object[] params) {
            int i;
            for (i = 1; i <= 6; i ++) {
                //textView.setText(Integer.toString(j));
                try {
                    Thread.sleep(1000);
                    publishProgress(i*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return i + Arrays.toString(params);
        }

        @Override
        protected void onPostExecute(Object o) {
            //textView.setText("结账完成！");
            Toast.makeText(mContext, "结账完成，金额：" + String.valueOf(money) + "元，积分：" +
                    String.valueOf(money), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            Button button = view.findViewById(R.id.pay_btn);
            button.setClickable(false);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            int value = (int) values[0];
            progressBar.setProgress(value);
        }
    }
}

