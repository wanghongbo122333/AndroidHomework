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

public class ConfAdapter extends ArrayAdapter<Conf> {
    private int resourceId;
    private Context context;

    List<String> list = new ArrayList<String>();

//    SharedPreferences sp= context.getSharedPreferences("config", Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sp.edit();

    public ConfAdapter(Context context, int textViewResourceId, List<Conf> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Conf conf = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        final TextView nameview = (TextView) view.findViewById(R.id.name);
        TextView priceview = (TextView) view.findViewById(R.id.price);
        final Button choosebtu = (Button) view.findViewById(R.id.choose);

        nameview.setTextSize(18);
        nameview.setText(conf.getName());

        priceview.setTextSize(18);
        priceview.setText(conf.getPrice());

        choosebtu.setTextSize(18);
        choosebtu.setText(conf.getChoose());
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

                conf.setChoose("退点");
                Toast.makeText(getContext(), nameview.getText() + " 点菜成功", Toast.LENGTH_SHORT).show();
                Food food = new Food();
                food.setFoodName(nameview.getText().toString());
                food.setFoodPrice(nameview.getText().toString());

//                String myfood = food.toString();
//                list.add(myfood);
//                editor.putInt("Nums", list.size());
//                for (int i = 0; i < list.size(); i++)
//                {
//                    editor.putString("item_"+i, list.get(i));
//                }
//                editor.commit();

                choosebtu.setText("退点");
            }
        });

        return view;
    }
}
