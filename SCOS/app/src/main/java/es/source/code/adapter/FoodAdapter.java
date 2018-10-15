package es.source.code.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.R;
import es.source.code.activity.FoodDetailed;
import es.source.code.model.Food;

/**
 * Created by WangHongbo on 2018/10/11.
 */

public class FoodAdapter extends ArrayAdapter<Food> {
    private int resourceId;
    private Context context;

    List<String> list = new ArrayList<String>();

//    SharedPreferences sp= context.getSharedPreferences("config", Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sp.edit();

    public FoodAdapter(Context context, int textViewResourceId, List<Food> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Food currentfood = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        final TextView nameview = (TextView) view.findViewById(R.id.name);
        TextView priceview = (TextView) view.findViewById(R.id.price);
        final Button choosebtu = (Button) view.findViewById(R.id.choose);

        nameview.setTextSize(18);
        nameview.setText(currentfood.getName());

        priceview.setTextSize(18);
        priceview.setText(String.valueOf(currentfood.getPrice()));

        choosebtu.setTextSize(18);
        if (currentfood.getIsReturnable()){//菜品一开始都是不可退的
            choosebtu.setText("退菜");
        }
        else {
            choosebtu.setText("点菜");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDetailed.class);
                context.startActivity(intent);
            }
        });
        choosebtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(currentfood.getIsReturnable()){//如果是可退的，说明当前已经点击的按钮是退菜按钮
                choosebtu.setText("点菜");
                currentfood.setIsReturnable(false);//当前菜品已经点菜，也就可以退菜
                Toast.makeText(getContext(), nameview.getText() + " 退菜成功", Toast.LENGTH_SHORT).show();
            }
            else {
                choosebtu.setText("退菜");
                currentfood.setIsReturnable(true);
                Toast.makeText(getContext(), nameview.getText() + " 点菜成功", Toast.LENGTH_SHORT).show();
            }




                //下面应该处理传递点菜的数据了
//                Food food = new Food();
//                food.setFoodName(nameview.getText().toString());
//                food.setFoodPrice(Integer.parseInt(nameview.getText().toString()));

//                String myfood = food.toString();
//                list.add(myfood);
//                editor.putInt("Nums", list.size());
//                for (int i = 0; i < list.size(); i++)
//                {
//                    editor.putString("item_"+i, list.get(i));
//                }
//                editor.commit();
            }
        });

        return view;
    }
}
