package es.source.code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.source.code.activity.R;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class AlreadyOrderConfAdapter extends ArrayAdapter<AlreadyOrderConf> {
    private int resourceId;

    public AlreadyOrderConfAdapter(Context context, int textViewResourceId, List<AlreadyOrderConf> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlreadyOrderConf AlreadyOrderConf = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        final TextView nameview = (TextView) view.findViewById(R.id.name);
        final TextView priceview = (TextView) view.findViewById(R.id.price);
        final TextView numberview = (TextView) view.findViewById(R.id.number);
        final TextView remarksview = (TextView) view.findViewById(R.id.remarks);
//        final Button notorderbtu = (Button) view.findViewById(R.id.notorder);

        nameview.setTextSize(18);
        nameview.setText(AlreadyOrderConf.getName());

        priceview.setTextSize(18);
        priceview.setText(AlreadyOrderConf.getPrice());

        numberview.setTextSize(18);
        numberview.setText(AlreadyOrderConf.getNumber());

        remarksview.setTextSize(18);
        remarksview.setText(AlreadyOrderConf.getRemarks());


        return view;
    }
}