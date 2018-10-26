package es.source.code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.source.code.activity.R;
import es.source.code.model.OrderItem;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class BillFoodAdapter extends ArrayAdapter<OrderItem> {
    private int resourceId;

    public BillFoodAdapter(Context context, int textViewResourceId, List<OrderItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItem orderItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        final TextView nameview = (TextView) view.findViewById(R.id.name);
        final TextView priceview = (TextView) view.findViewById(R.id.price);
        final TextView numberview = (TextView) view.findViewById(R.id.number);
        final TextView remarksview = (TextView) view.findViewById(R.id.remarks);

        nameview.setTextSize(18);
        nameview.setText(orderItem.getFood().getName());//菜名

        priceview.setTextSize(18);
        priceview.setText(String.valueOf(orderItem.getFood().getPrice()));//价格int所以需要String.valueOf

        numberview.setTextSize(18);
        numberview.setText(String.valueOf(orderItem.getAmount()));//数量

        remarksview.setTextSize(18);
        remarksview.setText(orderItem.getRemarks());//备注


        return view;
    }
}
