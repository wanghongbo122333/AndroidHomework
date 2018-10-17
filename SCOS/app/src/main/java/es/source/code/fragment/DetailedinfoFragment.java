package es.source.code.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import es.source.code.activity.R;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class DetailedinfoFragment extends Fragment {

    Context mContext;
    ImageView food_image;
    TextView food_name, food_price;
    EditText food_remarks;
    Button food_order;
    private View view;

    public DetailedinfoFragment() {

    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.food_detail_item, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        food_image = view.findViewById(R.id.food_image);
        food_name = view.findViewById(R.id.food_name);
        food_price = view.findViewById(R.id.food_price);
        food_remarks = view.findViewById(R.id.food_remarks);
        food_order = view.findViewById(R.id.food_order);

//         Drawable drawable = getResources().getDrawable(R.drawable.food_1);
        food_image.setImageResource(R.drawable.help);
        food_name.setText("心灵鸡汤");
        food_price.setText("25元");
        food_remarks.setHint(" ");//备注
        food_order.setText("点菜");
//        textView.setText(content);
    }
}
