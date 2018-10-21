package es.source.code.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.R;
import es.source.code.model.Food;
import es.source.code.model.MyApplication;
import es.source.code.model.OrderItem;

import static android.content.ContentValues.TAG;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodAdapter extends ArrayAdapter<Food> {
    private int resourceId;
    private Context context;
    private List<OrderItem> orderList;//存储点的菜

    public List<OrderItem> getOrderList() {
        return orderList;
    }

    public FoodAdapter(Context context, int textViewResourceId, List<Food> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
        this.context = context;
        this.orderList = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final Food currentfood = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.choosebtu = view.findViewById(R.id.choose);
            viewHolder.nameview = view.findViewById(R.id.name);
            viewHolder.priceview = view.findViewById(R.id.price);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (currentfood != null) {
            viewHolder.nameview.setTextSize(18);
            viewHolder.nameview.setText(currentfood.getName());

            viewHolder.priceview.setTextSize(18);
            viewHolder.priceview.setText(String.valueOf(currentfood.getPrice()));

            viewHolder.choosebtu.setTextSize(18);


            if (currentfood.getIsReturnable()) {//菜品一开始都是不可退的
                viewHolder.choosebtu.setText("退菜");
            } else {
                viewHolder.choosebtu.setText("点菜");
            }
            /**
             * view中点击的其他部分点击查看菜品详情
             */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FoodDetailed.class);
                    context.startActivity(intent);
                }
            });
            viewHolder.choosebtu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentfood.getIsReturnable()) {//如果是可退的，说明当前已经点击的按钮是退菜按钮
                        viewHolder.choosebtu.setText("点菜");
                        currentfood.setIsReturnable(false);//当前菜品已经点菜，也就可以退菜
                        Toast.makeText(getContext(), viewHolder.nameview.getText() + " 退菜成功", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onClick:删除" + currentfood.getName());
                        MyApplication.userOrder.remove(MyApplication.findOrderItem(currentfood.getName()));//通过下标删除该菜
                        MyApplication.printItems(MyApplication.userOrder);
                    } else {
                        viewHolder.choosebtu.setText("退菜");
                        currentfood.setIsReturnable(true);
                        Toast.makeText(getContext(), viewHolder.nameview.getText() + " 点菜成功", Toast.LENGTH_SHORT).show();
//                        orderList.add(new OrderItem(currentfood, 1, "备注1"));
                        MyApplication.userOrder.add(new OrderItem(currentfood, 1, "备注1"));
                        MyApplication.printItems(MyApplication.userOrder);
                    }
                }
            });
        }

        return view;
    }


    /**
     * 新建一个内部类ViewHolder
     */
    class ViewHolder {
        TextView nameview;
        TextView priceview;
        Button choosebtu;

    }

}
