package es.source.code.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.source.code.activity.R;
import es.source.code.model.MyApplication;
import es.source.code.model.OrderItem;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class OrderFoodAdapter extends ArrayAdapter<OrderItem> {
    private int resourceId;

    public OrderFoodAdapter(Context context, int textViewResourceId, List<OrderItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final OrderItem orderItem = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            TextView nameview = view.findViewById(R.id.name);
            TextView priceview = view.findViewById(R.id.price);
            TextView numberview = view.findViewById(R.id.number);
            TextView remarksview = view.findViewById(R.id.remarks);
            Button notorderbtu = view.findViewById(R.id.notorder);
            viewHolder = new ViewHolder(nameview, priceview, numberview, remarksview, notorderbtu);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (orderItem != null) {
            viewHolder.nameview.setTextSize(18);
            viewHolder.nameview.setText(orderItem.getFood().getName());//菜名

            viewHolder.priceview.setTextSize(18);
            viewHolder.priceview.setText(String.valueOf(orderItem.getFood().getPrice()));//价格int所以需要String.valueOf

            viewHolder.numberview.setTextSize(18);
            viewHolder.numberview.setText(String.valueOf(orderItem.getAmount()));//数量

            viewHolder.remarksview.setTextSize(18);
            viewHolder.remarksview.setText(orderItem.getRemarks());//备注

            viewHolder.notorderbtu.setTextSize(18);
            viewHolder.notorderbtu.setText("退点");
            viewHolder.notorderbtu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(orderItem);
                    Toast.makeText(getContext(), viewHolder.nameview.getText() + " 退点成功", Toast.LENGTH_SHORT).show();
                    MyApplication.userOrder.remove(orderItem);

                }
            });
        } else {
            //没点菜
            viewHolder.nameview.setTextSize(18);
            viewHolder.nameview.setText("暂时没有点菜");//菜名
        }

        return view;
    }

    class ViewHolder {
        TextView nameview;
        TextView priceview;
        TextView numberview;
        TextView remarksview;
        Button notorderbtu;

        ViewHolder(TextView nameview, TextView priceview, TextView numberview, TextView remarksview, Button notorderbtu) {
            this.nameview = nameview;
            this.priceview = priceview;
            this.numberview = numberview;
            this.remarksview = remarksview;
            this.notorderbtu = notorderbtu;
        }
    }
}
